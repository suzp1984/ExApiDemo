package suzp1984.github.io.exapidemo.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jacobsu on 9/4/16.
 */
public class StarsView extends View {

    private static final String TAG = "StarsView";

    private Paint mStarPaint;
    private Path  mPath;

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
        // super.onDraw(canvas);
        Log.e(TAG, "onDraw");

        canvas.save();
        canvas.translate(200, 200);
        // canvas.rotate(45f);
        // canvas.scale(200, 200);

        // canvas.drawRect(0, 0, 1, 1, mStarPaint);
        mPath.reset();
        mPath.moveTo(0, 0);
        mPath.lineTo(100, 100);
        // mPath.moveTo(3, 2);
        mPath.lineTo(100, 0);
        // mPath.moveTo(3, 3);
        mPath.lineTo(0, 0);
        mPath.close();

        mStarPaint.setStrokeWidth(1f);
        mStarPaint.setDither(false);

        canvas.drawPath(mPath, mStarPaint);

        canvas.restore();
        // drawStar(canvas, 100, 100, 100, 0);
    }

    private void init() {
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

    }

    private void drawStar(Canvas canvas, int x, int y, int r, int a) {
        canvas.save();

        canvas.translate(x, y);
        // canvas.scale(r, r);

        drawStandardStar(canvas);
        canvas.drawPath(mPath, mStarPaint);

        canvas.restore();
    }

    private void drawStandardStar(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(0, 0);
        for (int i = 0; i < 5; i++) {
            mPath.lineTo((float)Math.cos((18 + i * 72)/180 * Math.PI) * 200f,
                    (float)-Math.sin((18 + i * 72)/180 * Math.PI) * 200f);

            mPath.lineTo((float)Math.cos((54 + i * 72)/180 * Math.PI) * 0.5f * 200f,
                    (float)-Math.sin((54 + i * 72)/180 * Math.PI) * 0.5f * 200f);
        }

        mPath.close();
    }
}
