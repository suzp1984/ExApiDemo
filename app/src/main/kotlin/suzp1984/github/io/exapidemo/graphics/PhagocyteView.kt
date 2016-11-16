package suzp1984.github.io.exapidemo.graphics

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by jacobsu on 9/29/16.
 */

class PhagocyteView : View {

    private lateinit var mPaint: Paint
    private var mBitmap: Bitmap? = null

    constructor(context: Context) : super(context) {

        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        init()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minW = paddingLeft + paddingRight + suggestedMinimumWidth
        val w = View.resolveSizeAndState(minW, widthMeasureSpec, 1)

        val minH = paddingBottom + paddingTop + suggestedMinimumHeight
        val h = View.resolveSizeAndState(minH, heightMeasureSpec, 1)

        setMeasuredDimension(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        if (mBitmap == null) {
            return
        }

        val w = canvas.width
        val h = canvas.height

        for (j in 0..h - 1)
            for (i in 0..w - 1) {
                val s = 3.0 / (j + 99).toDouble()
                val y = (j + Math.sin((i * i + Math.pow((j - 700).toDouble(), 2.0) * 5.0) / 100.0 / w.toDouble()) * 35.0) * s

                val r = (((i + w) * s + y).toInt() % 2 + ((w * 2 - i) * s + y).toInt() % 2) * 127
                val g = ((5 * ((i + w) * s + y)).toInt() % 2 + (5 * ((w * 2 - i) * s + y)).toInt() % 2) * 127
                val b = ((29 * ((i + w) * s + y)).toInt() % 2 + (29 * ((w * 2 - i) * s + y)).toInt() % 2) * 127

                mBitmap?.setPixel(i, j, Color.rgb(r, g, b))
            }

        canvas.drawBitmap(mBitmap, 0f, 0f, mPaint)
    }

    private fun init() {
        mPaint = Paint()
    }
}
