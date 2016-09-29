package suzp1984.github.io.exapidemo.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jacobsu on 9/29/16.
 */

public class ManuelKastenView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public ManuelKastenView(Context context) {
        super(context);

        init();
    }

    public ManuelKastenView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public ManuelKastenView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        int w = canvas.getWidth();
        int h = canvas.getHeight();

        double r = 0;
        double g = 0;
        double b = 0;

        for (int j = 0; j < h; j++)
            for (int i = 0; i < w; i++) {
                r += Math.random();
                g += Math.random();
                b += Math.random();

                int red = (int) r;
                red = red % 512;
                red = red > 255 ? 511 - red : red;

                int green = (int) g;
                green = green % 512;
                green = green > 255 ? 511 - green : green;

                int blue = (int) b;
                blue = blue % 512;
                blue = blue > 255 ? 511 - blue : blue;

                mBitmap.setPixel(i, j, Color.rgb(red, green, blue));
            }

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    private void init() {
        mPaint = new Paint();
    }
}
