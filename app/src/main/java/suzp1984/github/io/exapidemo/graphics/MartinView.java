package suzp1984.github.io.exapidemo.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jacobsu on 9/27/16.
 */

public class MartinView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public MartinView(Context context) {
        super(context);

        init();
    }

    public MartinView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public MartinView(Context context, AttributeSet attrs, int defStyleAttr) {
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

        int[][] r = new int[h][w];
        int[][] g = new int[h][w];
        int[][] b = new int[h][w];

        for (int j = 0; j < h; j++)
            for (int i = 0; i < w; i++) {
                mBitmap.setPixel(i, j, Color.rgb(martin_r(r, i, j), martin_g(g, i, j), martin_b(b, i, j)));
            }

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    private int martin_r(int[][] r, int w, int h) {
        if (r[h][w] == 0) {
            r[h][w] = Math.floor(Math.random() * 999) == 0 ? (int)(Math.floor(Math.random()*256)) :
                    martin_r(r, (w + (int) Math.floor(Math.random()*2))%getWidth(),
                                (h + (int) Math.floor(Math.random()*2))%getHeight());
        }

        return r[h][w];
    }

    private int martin_g(int[][] g, int w, int h) {
        if (g[h][w] == 0) {
            g[h][w] = Math.floor(Math.random() * 999) == 0 ? (int)(Math.floor(Math.random()*256)) :
                    martin_g(g, (w + (int) Math.floor(Math.random()*2))%getWidth(),
                            (h + (int) Math.floor(Math.random()*2))%getHeight());
        }

        return g[h][w];
    }

    private int martin_b(int[][] b, int w, int h) {
        if (b[h][w] == 0) {
            b[h][w] = Math.floor(Math.random() * 999) == 0 ? (int)(Math.floor(Math.random()*256)) :
                    martin_b(b, (w + (int) Math.floor(Math.random()*2))%getWidth(),
                            (h + (int) Math.floor(Math.random()*2))%getHeight());
        }

        return b[h][w];
    }

    private void init() {
        mPaint = new Paint();
    }
}
