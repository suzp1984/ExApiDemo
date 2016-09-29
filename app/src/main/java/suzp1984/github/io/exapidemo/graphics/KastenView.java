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

public class KastenView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public KastenView(Context context) {
        super(context);

        init();
    }

    public KastenView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public KastenView(Context context, AttributeSet attrs, int defStyleAttr) {
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

        for (int j = 0; j < h; j++)
            for (int i = 0; i < w; i++) {
                double a = 0;
                double b = 0;
                double c = 0;
                double d = 0;
                double n = 0;

                while ((c + d) < 4 && n++ < 440) {
                    b = 2*a*b + i*10e-9 - 0.645411;
                    a = c - d + j*10e-9 + 0.356888;

                    c = a*a;
                    d = b*b;
                }

                int red = (int) (Math.pow((n-40.0)/400.0, 3.0)*255);
                int green = (int) (Math.pow((n-40.0)/400.0, 0.7)*255);
                int blue = (int) (Math.pow((n-40.0)/400.0, 0.5)*255);

                mBitmap.setPixel(i, j, Color.rgb(red, green, blue));
            }

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    private void init() {
        mPaint = new Paint();
    }
}
