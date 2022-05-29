package fo.pigdm.colors2048.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.View;
import android.graphics.Paint;

import fo.pigdm.colors2048.R;

public class TestCustomDrawableView extends View {

    private final Paint paint;

    private int xTopLeftCornerRect;
    private int yTopLeftCornerRect;
    private int widthRect;
    private int heightRect;

    public TestCustomDrawableView(Context context) {
        super(context);

        paint = new Paint();

        xTopLeftCornerRect = 50;
        yTopLeftCornerRect = 50;
        widthRect = 300;
        heightRect = 300;
    }

    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);

        int primaryColor = getResources().getColor(R.color.purple_200);
        int secondaryColor = getResources().getColor(R.color.teal_200);
        paint.setColor(primaryColor);
        canvas.drawRect(xTopLeftCornerRect, yTopLeftCornerRect, widthRect, heightRect, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((float)2.5);
        paint.setColor(secondaryColor);
        canvas.drawRect(xTopLeftCornerRect, yTopLeftCornerRect, widthRect, heightRect, paint);
    }
}
