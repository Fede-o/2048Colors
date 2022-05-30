package fo.pigdm.colors2048.view.testView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import fo.pigdm.colors2048.R;

public class TestCustomDrawable extends Drawable {

    private final Paint paint;

    private int xTopLeftCornerRect;
    private int yTopLeftCornerRect;
    private int widthRect;
    private int heightRect;

    public TestCustomDrawable() {

        paint = new Paint();

        xTopLeftCornerRect = 50;
        yTopLeftCornerRect = 50;
        widthRect = 300;
        heightRect = 300;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);

        //int primaryColor = getResources().getColor(R.color.purple_200);
        //int secondaryColor = getResources().getColor(R.color.teal_200);

        int primaryColor = Color.RED;
        int secondaryColor = Color.BLUE;

        paint.setColor(primaryColor);
        canvas.drawRect(xTopLeftCornerRect, yTopLeftCornerRect, widthRect, heightRect, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((float) 2.5);
        paint.setColor(secondaryColor);
        canvas.drawRect(xTopLeftCornerRect, yTopLeftCornerRect, widthRect, heightRect, paint);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}

