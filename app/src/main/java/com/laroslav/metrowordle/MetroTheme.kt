package com.laroslav.metrowordle

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.ViewGroup

object MetroTheme {

    fun applyMetroBackground(activity: Activity) {

        val prefs = activity.getSharedPreferences("MetroPrefs", Context.MODE_PRIVATE)
        val bg = prefs.getString("bg", "white")

        val root = activity.findViewById<ViewGroup>(android.R.id.content)
        val content = root.getChildAt(0) ?: return

        when (bg) {
            "black" -> content.setBackgroundColor(Color.BLACK)
            "img0" -> content.setBackgroundResource(R.drawable.img0)
            "img1" -> content.setBackgroundResource(R.drawable.img1)
            "img2" -> content.setBackgroundResource(R.drawable.img2)
            else -> content.setBackgroundColor(Color.WHITE)
        }
    }
}
