package suzp1984.github.io.exapidemo.graphics

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by jacobsu on 9/27/16.
 */

class MartinView : View {

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

        val r = Array(h) { IntArray(w) }
        val g = Array(h) { IntArray(w) }
        val b = Array(h) { IntArray(w) }

        for (j in 0..h - 1)
            for (i in 0..w - 1) {
                mBitmap?.setPixel(i, j, Color.rgb(martin_r(r, i, j), martin_g(g, i, j), martin_b(b, i, j)))
            }

        canvas.drawBitmap(mBitmap, 0f, 0f, mPaint)
    }

    private fun martin_r(r: Array<IntArray>, w: Int, h: Int): Int {
        if (r[h][w] == 0) {
            r[h][w] = if (Math.floor(Math.random() * 999) == 0.0)
                Math.floor(Math.random() * 256).toInt()
            else
                martin_r(r, (w + Math.floor(Math.random() * 2).toInt()) % width,
                        (h + Math.floor(Math.random() * 2).toInt()) % height)
        }

        return r[h][w]
    }

    private fun martin_g(g: Array<IntArray>, w: Int, h: Int): Int {
        if (g[h][w] == 0) {
            g[h][w] = if (Math.floor(Math.random() * 999) == 0.0)
                Math.floor(Math.random() * 256).toInt()
            else
                martin_g(g, (w + Math.floor(Math.random() * 2).toInt()) % width,
                        (h + Math.floor(Math.random() * 2).toInt()) % height)
        }

        return g[h][w]
    }

    private fun martin_b(b: Array<IntArray>, w: Int, h: Int): Int {
        if (b[h][w] == 0) {
            b[h][w] = if (Math.floor(Math.random() * 999) == 0.0)
                Math.floor(Math.random() * 256).toInt()
            else
                martin_b(b, (w + Math.floor(Math.random() * 2).toInt()) % width,
                        (h + Math.floor(Math.random() * 2).toInt()) % height)
        }

        return b[h][w]
    }

    private fun init() {
        mPaint = Paint()
    }
}
