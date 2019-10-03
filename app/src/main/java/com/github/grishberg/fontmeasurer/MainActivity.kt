package com.github.grishberg.fontmeasurer

import android.graphics.Rect
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextPaint
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var typeface: Typeface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        typeface = Typeface.createFromAsset(assets, "fonts/blag.ttf")

        val textView1 = findViewById<TextView>(R.id.textView1)
        setFontToTextView(textView1)

        val text = getString(R.string.sampleWord1)
        printTextMetrix(text, textView1.paint)
    }

    private fun setFontToTextView(textView: TextView) {
        textView.setTypeface(typeface)
    }

    private fun printTextMetrix(text: String, paint: TextPaint) {
        for (symbol in text) {
            val bounds = Rect()
            val symbolAsString = "$symbol"
            paint.getTextBounds(symbolAsString, 0, 1, bounds)
            val textHeight = bounds.height()
            val textWidth = bounds.width()

            val fm = paint.fontMetrics
            val height = Math.ceil(fm.descent.toDouble() - fm.top).toInt()
            val width = paint.measureText(symbolAsString)

            Log.d(
                "FONT_SIZE", "symbol = '$symbol', " +
                        " h = $height, w = $width"
            )
        }
    }
}
