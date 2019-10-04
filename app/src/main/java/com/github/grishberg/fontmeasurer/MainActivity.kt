package com.github.grishberg.fontmeasurer

import android.graphics.Rect
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextPaint
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    private lateinit var typeface: Typeface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        typeface = Typeface.createFromAsset(assets, "fonts/blag.ttf")

        val textView1 = findViewById<TextView>(R.id.textView1)
        setFontToTextView(textView1)

        val text = getString(R.string.sampleWord1)
        printTextMetrics(text, textView1.paint, textView1)
    }

    private fun setFontToTextView(textView: TextView) {
        textView.setTypeface(typeface)
    }

    private fun printTextMetrics(text: String, paint: TextPaint, textView: TextView) {
        Log.d(
            "FONT_SIZE", "font scale = ${resources.configuration.fontScale}," +
                    " font size = ${textView.textSize}"
        )
        for (symbol in text) {
            val symbolAsString = "$symbol"

            val fm = paint.fontMetrics
            val height = roundAvoid(fm.descent.toDouble() - fm.top, 3)
            val width = paint.measureText(symbolAsString)

            Log.d(
                "FONT_SIZE", "symbol = '$symbol', " +
                        " h = $height, w = $width"
            )
        }
    }

    private fun roundAvoid(value: Double, places: Int): Double {
        val scale = 10.0.pow(places.toDouble())
        return (value * scale).roundToInt() / scale
    }
}
