package suzp1984.github.io.exapidemo.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jacobsu on 9/4/16.
 */
public class StarsView extends View {

    private Paint mTxtPaint;

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
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int hightMeasureSpec) {

        int minW = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minW, widthMeasureSpec, 1);

        int minH = getPaddingBottom() + getPaddingTop() + getSuggestedMinimumHeight();
        int h = resolveSizeAndState(minH, hightMeasureSpec, 1);

        setMeasuredDimension(w, h);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mTxtPaint.setTextSize(40f);
        canvas.drawText("hello world", 190, 190, mTxtPaint);
    }

    private void init() {
        mTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setColor(0xffffff00);
        mTxtPaint.setStyle(Paint.Style.STROKE);
    }
}
