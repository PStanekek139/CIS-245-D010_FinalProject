package edu.rvc.student.shoppingtracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val btnTest = findViewById<Button>(R.id.btnAddTestData)
        val btnDeleteHistory = findViewById<Button>(R.id.btnReinitializeDatabase)


        //get screen dimensions
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels

        //adjust font size based on screen width
        if (screenWidth == 480){
            btnTest.textSize = 16f
            btnDeleteHistory.textSize = 16f
        }
        else if (screenWidth < 480){
            btnTest.textSize = 14f
            btnDeleteHistory.textSize = 14f
        }
        else {
            btnTest.textSize = 24f
            btnDeleteHistory.textSize = 24f
        }

        //disabled in release build - only used for debugging
        btnTest.setEnabled(false)
        btnTest.setVisibility(View.INVISIBLE)
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
