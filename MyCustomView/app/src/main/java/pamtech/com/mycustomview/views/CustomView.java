package pamtech.com.mycustomview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {

    private static final int SQUARE_SIZE = 100;
    private Rect rect;
    private Paint paint;

    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        rect = new Rect();
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        rect.left = 10;
        rect.top = 10;
        rect.right = rect.left + SQUARE_SIZE;
        rect.bottom = rect.right + SQUARE_SIZE;

        paint.setColor(Color.GREEN);

        canvas.drawRect(rect, paint);
    }
}
