package edu.rvc.student.shoppingtracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun addTestData(view: View){
        val dbHandler = DBHelper(this,null,null,1)
        val quantity = 5
        val price = 2.50f
        val listentry = ListEntry("TestItem1",quantity,price)
        dbHandler.addCurrentListEntry(listentry)
        dbHandler.addHistoryListEntry(listentry)
        Toast.makeText(this, "Test Item Added!", Toast.LENGTH_LONG).show()
    }

    fun reinitializeDatabase(view:View){
        val dbHandler = DBHelper(this, null, null, 1)

        val success = dbHandler.reinitializeDatabase()

        if (success){
            Toast.makeText(this, "Database Reinitialized", Toast.LENGTH_LONG).show()
        } else{
            Toast.makeText(this, "Error - Unable to Reinitialize!", Toast.LENGTH_LONG).show()
        }

    }
}
