package suzp1984.github.io.exapidemo.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jacobsu on 9/26/16.
 */

public class MandelbrotView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public MandelbrotView(Context context) {
        super(context);

        init();
    }

    public MandelbrotView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public MandelbrotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int minW = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minW, widthMeasureSpec, 1);

        int minH = getPaddingBottom() + getPaddingTop() + getSuggestedMinimumHeight();
        int h = resolveSizeAndState(minH, heightMeasureSpec, 1);

        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        final int w = canvas.getWidth();
        final int h = canvas.getHeight();

        for (int j = 0; j < h; j++)
            for (int i = 0; i < w; i++) {

                float x = 0;
                float y = 0;
                int k = 0;

                for (k = 1; k < 256; k++) {
                    float a = (float) (x*x - y*y + (float)(i - w * 0.75)*2/(float)w);
                    y = (float) (2*x*y + (j - w*0.5)*2/(float)w);
                    x = a;

                    if ((x*x + y*y) > 4) {
                        break;
                    }
                }

                int r = (int) Math.log(k) * 46;
                int g = (int) Math.log(k) * 46;
                int b = (128 - (int) Math.log(k) * 23);

                mBitmap.setPixel(i, j, Color.rgb(r, g, b));
            }

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    private void init() {
        mPaint = new Paint();
    }
}
