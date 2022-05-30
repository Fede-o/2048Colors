package fo.pigdm.colors2048.view.testView;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyRedCircle extends Drawable {

    private final Paint mRedPaint;

    public MyRedCircle() {
        mRedPaint = new Paint();
        mRedPaint.setARGB(255, 255, 0, 0);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        //int width = 400;
        //int height = 400;
        //float radius = ( (float)Math.min(width, height) / (float)2 );
        float lato = (float)width - (float)200;
        //canvas.drawCircle((width / (float)2), (height / (float)2), radius, mRedPaint);
        canvas.drawRoundRect((float)100, (float)400, (float)width - (float)100, lato + (float)400, (float)100, (float)100, mRedPaint);
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
