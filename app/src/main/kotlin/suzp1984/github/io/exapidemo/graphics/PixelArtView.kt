package suzp1984.github.io.exapidemo.graphics

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by jacobsu on 9/10/16.
 */
class PixelArtView : View {

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

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {

        init()
    }

    public override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged: $w, $h, $oldw, $oldh")

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    }

    public override fun onMeasure(widthMeasureSpec: Int, hightMeasureSpec: Int) {
        Log.e(TAG, "onMeasure: $widthMeasureSpec, $hightMeasureSpec")

        val minW = paddingLeft + paddingRight + suggestedMinimumWidth
        val w = View.resolveSizeAndState(minW, widthMeasureSpec, 1)

        val minH = paddingBottom + paddingTop + suggestedMinimumHeight
        val h = View.resolveSizeAndState(minH, hightMeasureSpec, 1)

        setMeasuredDimension(w, h)
    }

    public override fun onDraw(canvas: Canvas) {
        Log.e(TAG, "onDraw")

        if (mBitmap == null) {
            return
        }

        val w = canvas.width
        val h = canvas.height
        Log.e(TAG, "w = $w; h = $h")

        for (i in 0..h - 1)
            for (j in 0..w - 1) {
                val r = (Math.pow(Math.cos(Math.atan2((j - w / 2).toDouble(), (i - w / 2).toDouble()) / 2), 2.0) * 255).toInt()
                val g = (Math.pow(Math.cos(Math.atan2((j - w / 2).toDouble(), (i - w / 2).toDouble()) / 2 - 2 * Math.acos(-1.0) / 3), 2.0) * 255).toInt()
                val b = (Math.pow(Math.cos(Math.atan2((j - w / 2).toDouble(), (i - w / 2).toDouble()) / 2 + 2 * Math.acos(-1.0) / 3), 2.0) * 255).toInt()
                // Log.e(TAG, "(r, g, b) = " + "(" + r + ", " + g + ", " + b + ")");
                mBitmap?.setPixel(j, i, Color.argb(255, r, g, b))
            }

        canvas.drawBitmap(mBitmap, 0f, 0f, mPaint)
    }

    private fun init() {
        mPaint = Paint()
    }

    companion object {

        private val TAG = "StarsView"
    }

}
