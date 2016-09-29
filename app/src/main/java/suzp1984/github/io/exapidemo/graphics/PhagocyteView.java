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

public class PhagocyteView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public PhagocyteView(Context context) {
        super(context);

        init();
    }

    public PhagocyteView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public PhagocyteView(Context context, AttributeSet attrs, int defStyleAttr) {
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
                double s = 3.0/(double)(j+99);
                double y = (j + Math.sin((i*i + Math.pow(j - 700, 2.0)*5.0)/100.0/w)*35.0) * s;

                int r = ((int)((i + w)*s + y)%2 + (int)((w*2 - i)*s + y)%2)*127;
                int g = ((int)(5*((i + w)*s+y))%2 + (int)(5*((w*2 - i)*s + y))%2)*127;
                int b = ((int)(29*((i + w)*s+y))%2 + (int)(29*((w*2 - i)*s + y))%2)*127;

                mBitmap.setPixel(i, j, Color.rgb(r, g, b));
            }

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    private void init() {
        mPaint = new Paint();
    }
}
