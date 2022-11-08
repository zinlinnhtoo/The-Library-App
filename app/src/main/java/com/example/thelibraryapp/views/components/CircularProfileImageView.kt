package com.example.thelibraryapp.views.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import com.example.thelibraryapp.R

class CircularProfileImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    private var mStrokeColor = DEFAULT_STROKE_COLOR
    private var mStrokeWidth = DEFAULT_STROKE_WIDTH
    private val path = Path()
    private var paint = Paint()
    private var size = 0

    companion object {
        private const val DEFAULT_STROKE_COLOR =  Color.WHITE
        private const val DEFAULT_STROKE_WIDTH =  10f
    }

    init {
        setUpAttributes(attrs)
    }

    private fun setUpAttributes(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.CircleImageView) {
            mStrokeColor = getColor(R.styleable.CircleImageView_strokeColor, DEFAULT_STROKE_COLOR)
            mStrokeWidth = getDimension(R.styleable.CircleImageView_strokeWidth, DEFAULT_STROKE_WIDTH)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        paint.apply {
            style = Paint.Style.STROKE
            color = mStrokeColor
            strokeWidth = mStrokeWidth
        }
        path.addCircle(size / 2f, size / 2f, size / 2f, Path.Direction.CCW)
        canvas?.clipPath(path)
        super.onDraw(canvas)
        canvas?.drawCircle(size / 2f, size / 2f, size / 2f - (mStrokeWidth / 2f ), paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        size = w.coerceAtMost(h)
    }
}