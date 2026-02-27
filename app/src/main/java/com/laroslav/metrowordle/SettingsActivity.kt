package com.laroslav.metrowordle

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        MetroTheme.applyMetroBackground(this)

        val prefs = getSharedPreferences("MetroPrefs", MODE_PRIVATE)

        findViewById<Button>(R.id.btnThemeBlack).setOnClickListener {
            prefs.edit().putString("bg", "black").apply()
            applyBackground()
        }

        findViewById<Button>(R.id.btnImg0).setOnClickListener {
            prefs.edit().putString("bg", "img0").apply()
            applyBackground()
        }
    }
}

private fun SettingsActivity.applyBackground() {
    TODO("Not yet implemented")
}
