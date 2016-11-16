package suzp1984.github.io.exapidemo.graphics

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.AttributeSet
import android.util.Log
import android.view.View

import java.util.Random

/**
 * Created by jacobsu on 9/4/16.
 */
class StarsView : View {

    private lateinit var mStarPaint: Paint
    private lateinit var mBackgroundPaint: Paint
    private lateinit var mPath: Path
    private lateinit var mRandom: Random

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

        mBackgroundPaint.shader = LinearGradient(0f, 0f, 0f, height.toFloat(),
                Color.BLACK, Color.WHITE,
                Shader.TileMode.CLAMP)

        canvas.drawPaint(mBackgroundPaint)

        for (i in 0..99) {
            val x = mRandom.nextFloat() * measuredWidth
            val y = mRandom.nextFloat() * measuredHeight.toFloat() * 0.7f
            val r = mRandom.nextFloat() * 10 + 10
            val a = mRandom.nextFloat() * 360

            drawStar(canvas, x, y, r, a)
        }

    }

    private fun init() {
        // must use software layer to render
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        mStarPaint = Paint()
        mStarPaint.isAntiAlias = true
        mStarPaint.strokeWidth = 1f
        // mStarPaint.setDither(true);
        // mStarPaint.setStrokeJoin(Paint.Join.ROUND);
        // mStarPaint.setStrokeCap(Paint.Cap.ROUND);
        mStarPaint.color = Color.rgb(255, 0, 0)
        mStarPaint.setARGB(255, 255, 0, 0)
        mStarPaint.style = Paint.Style.FILL_AND_STROKE

        mPath = Path()
        mRandom = Random(System.currentTimeMillis())

        mBackgroundPaint = Paint()
        mBackgroundPaint.style = Paint.Style.FILL
    }

    private fun drawStar(canvas: Canvas, x: Float, y: Float, r: Float, a: Float) {
        val count = canvas.saveCount
        canvas.save()

        canvas.translate(x, y)
        canvas.scale(r, r)
        canvas.rotate(a)

        drawNormalStar()

        canvas.drawPath(mPath, mStarPaint)

        canvas.restoreToCount(count)
    }

    private fun drawNormalStar() {
        mPath.reset()

        mPath.moveTo(Math.cos(Math.toRadians(18.0)).toFloat(), -Math.sin(Math.toRadians(18.0)).toFloat())
        for (i in 0..4) {
            val x1 = Math.cos(Math.toRadians((18 + i * 72).toDouble())).toFloat()
            val y1 = -Math.sin(Math.toRadians((18 + i * 72).toDouble())).toFloat()

            val x2 = Math.cos(Math.toRadians((54 + i * 72).toDouble())).toFloat() * 0.5f
            val y2 = -Math.sin(Math.toRadians((54 + i * 72).toDouble())).toFloat() * 0.5f

            mPath.lineTo(x1, y1)
            mPath.lineTo(x2, y2)
        }

        mPath.close()
    }

    companion object {

        private val TAG = "StarsView"
    }
}
