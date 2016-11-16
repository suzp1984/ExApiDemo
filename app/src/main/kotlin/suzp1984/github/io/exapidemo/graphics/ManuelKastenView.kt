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

class ManuelKastenView : View {

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

        var r = 0.0
        var g = 0.0
        var b = 0.0

        for (j in 0..h - 1)
            for (i in 0..w - 1) {
                r += Math.random()
                g += Math.random()
                b += Math.random()

                var red = r.toInt()
                red = red % 512
                red = if (red > 255) 511 - red else red

                var green = g.toInt()
                green = green % 512
                green = if (green > 255) 511 - green else green

                var blue = b.toInt()
                blue = blue % 512
                blue = if (blue > 255) 511 - blue else blue

                mBitmap?.setPixel(i, j, Color.rgb(red, green, blue))
            }

        canvas.drawBitmap(mBitmap, 0f, 0f, mPaint)
    }

    private fun init() {
        mPaint = Paint()
    }
}
