package suzp1984.github.io.exapidemo.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jacobsu on 9/10/16.
 */
public class PixelArtView extends View {

    private static final String TAG = "StarsView";

    private Paint mPaint;
    private Bitmap mBitmap;

    public PixelArtView(Context context) {
        super(context);

        init();
    }

    public PixelArtView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public PixelArtView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public PixelArtView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        // super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged: " + w + ", " + h + ", " + oldw + ", " + oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);


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

        final int w = canvas.getWidth();
        final int h = canvas.getHeight();
        Log.e(TAG, "w = " + w + "; h = " + h);

        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++) {
                int r = (int) (Math.pow(Math.cos(Math.atan2(j - w/2, i - w/2) / 2), 2) * 255);
                int g = (int) (Math.pow(Math.cos(Math.atan2(j - w/2, i - w/2)/2 - 2 * Math.acos(-1)/3), 2) * 255);
                int b = (int) (Math.pow(Math.cos(Math.atan2(j - w/2, i - w/2)/2 + 2 * Math.acos(-1)/3), 2) * 255);
                // Log.e(TAG, "(r, g, b) = " + "(" + r + ", " + g + ", " + b + ")");
                mBitmap.setPixel(j, i, Color.argb(255, r, g, b));
            }

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    private void init() {
        mPaint = new Paint();
    }

}
