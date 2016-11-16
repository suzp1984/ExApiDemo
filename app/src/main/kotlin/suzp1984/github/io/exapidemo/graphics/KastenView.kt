package suzp1984.github.io.exapidemo.graphics

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by moses on 9/29/16.
 */

class KastenView : View {

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

        for (j in 0..h - 1)
            for (i in 0..w - 1) {
                var a = 0.0
                var b = 0.0
                var c = 0.0
                var d = 0.0
                var n = 0.0

                while (c + d < 4 && n++ < 440) {
                    b = 2.0 * a * b + i * 10e-9 - 0.645411
                    a = c - d + j * 10e-9 + 0.356888

                    c = a * a
                    d = b * b
                }

                val red = (Math.pow((n - 40.0) / 400.0, 3.0) * 255).toInt()
                val green = (Math.pow((n - 40.0) / 400.0, 0.7) * 255).toInt()
                val blue = (Math.pow((n - 40.0) / 400.0, 0.5) * 255).toInt()

                mBitmap?.setPixel(i, j, Color.rgb(red, green, blue))
            }

        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0f, 0f, mPaint)
        }
    }

    private fun init() {
        mPaint = Paint()
    }
}
