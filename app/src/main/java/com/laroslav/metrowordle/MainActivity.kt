package com.laroslav.metrowordle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MetroTheme.applyMetroBackground(this)

        val tilePlay = findViewById<CardView>(R.id.tilePlay)
        val tileSettings = findViewById<CardView>(R.id.tileSettings)

        tilePlay.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        tileSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
