package edu.rvc.student.shoppingtracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.DisplayMetrics
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

        //get screen dimensions
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels

        //adjust font size based on screen width
        if (screenWidth == 480){
            btnCurrentList.textSize = 16f
            btnHistory.textSize = 16f
            btnSettings.textSize = 16f
            btnAbout.textSize = 16f
        }
        else if (screenWidth < 480) {
            btnCurrentList.textSize = 14f
            btnHistory.textSize = 14f
            btnSettings.textSize = 14f
            btnAbout.textSize = 14f
        }
        else {
            btnCurrentList.textSize = 24f
            btnHistory.textSize = 24f
            btnSettings.textSize = 24f
            btnAbout.textSize = 24f
        }

        btnCurrentList.width = (screenWidth / 2).toInt()

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
