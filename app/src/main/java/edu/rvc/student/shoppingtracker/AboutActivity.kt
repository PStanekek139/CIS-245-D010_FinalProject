package edu.rvc.student.shoppingtracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.TextView

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val txtViewAbout = findViewById<TextView>(R.id.textViewAbout)

        var thisText = "This program was originally developed by Paul Stanek as a class project for" +
                " CIS-245 at Rock Valley College during the Spring of 2018. It allows the user to save their shopping list and" +
                " history in a local SQLite database."


        txtViewAbout.setText(thisText)

        //get screen dimensions
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels

        if (screenWidth <= 480){
            txtViewAbout.textSize = 12f
        }
        else {
            txtViewAbout.textSize = 24f
        }
    }
}
