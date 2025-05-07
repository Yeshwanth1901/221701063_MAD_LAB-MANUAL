package com.example.exp_3

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DrawingView(this))
    }

    inner class DrawingView(context: Context) : View(context) {
        private val paint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = 5f
            color = Color.BLUE
        }

        private val textPaint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            textSize = 50f
            color = Color.RED
        }

        private val fillPaint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = Color.GREEN
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            // Set background color
            canvas.drawColor(Color.WHITE)

            // Draw a circle
            paint.color = Color.BLUE
            canvas.drawCircle(200f, 200f, 150f, paint)

            // Draw a filled circle
            fillPaint.color = Color.parseColor("#88FF0000") // Semi-transparent red
            canvas.drawCircle(600f, 200f, 150f, fillPaint)

            // Draw an ellipse
            paint.color = Color.GREEN
            val rectF = RectF(100f, 400f, 500f, 700f)
            canvas.drawOval(rectF, paint)

            // Draw a filled rectangle
            fillPaint.color = Color.parseColor("#880000FF") // Semi-transparent blue
            canvas.drawRect(550f, 400f, 850f, 700f, fillPaint)

            // Draw text
            textPaint.color = Color.BLACK
            canvas.drawText("Android Graphics Primitives", 100f, 800f, textPaint)

            // Draw rotated text
            canvas.save()
            canvas.rotate(-45f, 800f, 900f)
            textPaint.color = Color.MAGENTA
            canvas.drawText("Rotated Text", 800f, 900f, textPaint)
            canvas.restore()

            // Draw a rectangle with rounded corners
            paint.color = Color.DKGRAY
            val roundRect = RectF(100f, 950f, 400f, 1200f)
            canvas.drawRoundRect(roundRect, 30f, 30f, paint)
        }
    }

}