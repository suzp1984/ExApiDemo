package suzp1984.github.io.exapidemo.graphics

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by jacobsu on 9/26/16.
 */

class MandelbrotView : View {

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

        val w = canvas.width
        val h = canvas.height

        if (mBitmap == null) {
            return
        }

        for (j in 0..h - 1)
            for (i in 0..w - 1) {

                var x = 0f
                var y = 0f
                var k = 0

                k = 1
                while (k < 256) {
                    val a = (x * x - y * y + (i - w * 0.75).toFloat() * 2 / w.toFloat()).toFloat()
                    y = (2f * x * y + (j - w * 0.5) * 2 / w.toFloat()).toFloat()
                    x = a

                    if (x * x + y * y > 4) {
                        break
                    }
                    k++
                }

                val r = Math.log(k.toDouble()).toInt() * 46
                val g = Math.log(k.toDouble()).toInt() * 46
                val b = 128 - Math.log(k.toDouble()).toInt() * 23

                mBitmap?.setPixel(i, j, Color.rgb(r, g, b))
            }

        canvas.drawBitmap(mBitmap, 0f, 0f, mPaint)
    }

    private fun init() {
        mPaint = Paint()
    }
}
