package edu.rvc.student.shoppingtracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnCurrentList = findViewById<Button>(R.id.btnList)
        val btnHistory = findViewById<Button>(R.id.btnHistory)
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        val btnAbout = findViewById<Button>(R.id.btnAbout)

        btnCurrentList.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, CurrentListActivity::class.java)
            startActivity(intent)
        })

        btnHistory.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        })

        btnSettings.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        })

        btnAbout.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        })
    }
}
