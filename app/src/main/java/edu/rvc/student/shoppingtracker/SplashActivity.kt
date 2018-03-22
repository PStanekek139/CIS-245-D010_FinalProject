package edu.rvc.student.shoppingtracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

import android.view.View


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val btnStart = findViewById<Button>(R.id.btnStart)

        btnStart.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            //val intent = Main2Activity(this, user)
            startActivity(intent)

        })

    }
}
