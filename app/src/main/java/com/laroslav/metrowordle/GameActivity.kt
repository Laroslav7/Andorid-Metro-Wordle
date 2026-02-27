package com.laroslav.metrowordle

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    private lateinit var engine: WordleEngine
    private var currentAttempt = 0
    private var currentWord = ""

    private val wordsDB = listOf("ДОМ", "УЛИЦА", "ГОРОД", "СТРАНА")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        MetroTheme.applyMetroBackground(this)

        val target = wordsDB.random().uppercase()
        engine = WordleEngine(target)

        setupGrid(target.length)
    }

    private fun setupGrid(wordLength: Int) {
        val container = findViewById<LinearLayout>(R.id.gameGridContainer)
        container.removeAllViews()

        for (i in 0 until 6) {

            val row = LinearLayout(this)
            row.orientation = LinearLayout.HORIZONTAL
            row.gravity = Gravity.CENTER

            for (j in 0 until wordLength) {

                val tile = TextView(this)
                val params = LinearLayout.LayoutParams(120, 120)
                params.setMargins(8, 8, 8, 8)

                tile.layoutParams = params
                tile.gravity = Gravity.CENTER
                tile.textSize = 20f
                tile.setTextColor(Color.BLACK)
                tile.setBackgroundColor(Color.LTGRAY)

                row.addView(tile)
            }

            container.addView(row)
        }
    }

    fun onKeyPress(char: Char) {
        if (currentWord.length < engine.targetWord.length) {
            currentWord += char
            updateUI()
        }
    }

    private fun updateUI() {
        val container = findViewById<LinearLayout>(R.id.gameGridContainer)
        val row = container.getChildAt(currentAttempt) as LinearLayout

        for (i in currentWord.indices) {
            val tile = row.getChildAt(i) as TextView
            tile.text = currentWord[i].toString()
        }
    }

    fun submitGuess() {

        if (currentWord.length != engine.targetWord.length) return

        val results = engine.checkGuess(currentWord)
        colorTiles(results)

        if (currentWord == engine.targetWord) {
            Toast.makeText(this, "ПОБЕДА!", Toast.LENGTH_LONG).show()
        }

        currentAttempt++
        currentWord = ""
    }

    private fun colorTiles(results: List<TileStatus>) {

        val container = findViewById<LinearLayout>(R.id.gameGridContainer)
        val row = container.getChildAt(currentAttempt) as LinearLayout

        for (i in results.indices) {

            val tile = row.getChildAt(i) as TextView

            when (results[i]) {
                TileStatus.CORRECT -> tile.setBackgroundColor(Color.GREEN)
                TileStatus.PRESENT -> tile.setBackgroundColor(Color.YELLOW)
                TileStatus.ABSENT -> tile.setBackgroundColor(Color.DKGRAY)
                else -> {}
            }
        }
    }
}
