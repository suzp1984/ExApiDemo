package suzp1984.github.io.exapidemo.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created by jacobsu on 9/4/16.
 */
public class StarsView extends View {

    private static final String TAG = "StarsView";

    private Paint mStarPaint;
    private Paint mBackgroundPaint;
    private Path  mPath;
    private Random mRandom;

    public StarsView(Context context) {
        super(context);

        init();
    }

    public StarsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public StarsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public StarsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        // super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged: " + w + ", " + h + ", " + oldw + ", " + oldh);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int hightMeasureSpec) {
        Log.e(TAG, "onMeasure: " + widthMeasureSpec + ", " + hightMeasureSpec);

        int minW = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minW, widthMeasureSpec, 1);

        int minH = getPaddingBottom() + getPaddingTop() + getSuggestedMinimumHeight();
        int h = resolveSizeAndState(minH, hightMeasureSpec, 1);

        setMeasuredDimension(w, h);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw");

        mBackgroundPaint.setShader(new LinearGradient(0, 0, 0, getHeight(),
                Color.BLACK, Color.WHITE,
                Shader.TileMode.CLAMP));

        canvas.drawPaint(mBackgroundPaint);

        for (int i = 0; i < 100; i++) {
            float x = mRandom.nextFloat() * getMeasuredWidth();
            float y = mRandom.nextFloat() * getMeasuredHeight() * 0.7f;
            float r = mRandom.nextFloat() * 10 + 10;
            float a = mRandom.nextFloat() * 360;

            drawStar(canvas, x, y, r, a);
        }

    }

    private void init() {
        // must use software layer to render
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mStarPaint = new Paint();
        mStarPaint.setAntiAlias(true);
        mStarPaint.setStrokeWidth(1f);
        // mStarPaint.setDither(true);
        // mStarPaint.setStrokeJoin(Paint.Join.ROUND);
        // mStarPaint.setStrokeCap(Paint.Cap.ROUND);
        mStarPaint.setColor(Color.rgb(255, 0, 0));
        mStarPaint.setARGB(255, 255, 0, 0);
        mStarPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mPath = new Path();
        mRandom = new Random(System.currentTimeMillis());

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setStyle(Paint.Style.FILL);
    }

    private void drawStar(Canvas canvas, float x, float y, float r, float a) {
        int count = canvas.getSaveCount();
        canvas.save();

        canvas.translate(x, y);
        canvas.scale(r, r);
        canvas.rotate(a);

        drawNormalStar();

        canvas.drawPath(mPath, mStarPaint);

        canvas.restoreToCount(count);
    }

    private void drawNormalStar() {
        mPath.reset();

        mPath.moveTo((float)Math.cos(Math.toRadians(18)), -(float)Math.sin(Math.toRadians(18)));
        for (int i = 0; i < 5; i++) {
            float x1 = (float)Math.cos(Math.toRadians(18 + i * 72));
            float y1 = -(float)Math.sin(Math.toRadians(18 + i * 72));

            float x2 = (float)Math.cos(Math.toRadians(54 + i * 72)) * 0.5f;
            float y2 = -(float)Math.sin(Math.toRadians(54 + i * 72)) * 0.5f;

            mPath.lineTo(x1, y1);
            mPath.lineTo(x2, y2);
        }

        mPath.close();
    }
}
