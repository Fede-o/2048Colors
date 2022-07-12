package fo.pigdm.colors2048.view;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import fo.pigdm.colors2048.R;
import fo.pigdm.colors2048.logic.ILogic;
import fo.pigdm.colors2048.view.GameView;

public class colorPaletteView extends View  implements IView {

    private static ILogic logic;

    int currentLevel;
    int[] colorPalette;
    int numColors;
    float boxSize;
    float boxMargin;
    float paletteStartingX;
    float paletteStartingY;
    float paletteEndingX;
    float paletteEndingY;
    float paletteAreaHeight;
    float nextColorAreaHeight;
    float textX;
    float textY;
    float nextColorX;
    float nextColorY;
    int viewWidth;
    int viewHeight;
    private final Paint paint = new Paint();


    public colorPaletteView(Context context) {
        super(context);
    }

    //CONSTRUCTOR FOR GENERATION OF VIEW FROM XML LAYOUT
    public colorPaletteView(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        viewWidth = this.getWidth();
        viewHeight = this.getHeight();



    }

    private void setElementsDimensions(float width, float height) {

        paletteAreaHeight = (height / 3) * 2;
        nextColorAreaHeight = height / 3;

        boxSize = width / (float)numColors;
        boxMargin = boxSize / 7;
        boxSize = boxSize - (boxMargin);



        float paletteMiddleX = width / 2;
        float paletteMiddleY = paletteAreaHeight / 2;

        paletteStartingX = boxMargin;
        paletteStartingY = paletteMiddleY - (boxSize / 2);
        paletteEndingX = width - boxMargin;
        paletteEndingY = paletteMiddleY + (boxSize / 2);

        float nextColorAreaMiddleY = paletteAreaHeight + (nextColorAreaHeight / 2);
        textX = ((width / 3) * 2) - 50;
        textY = nextColorAreaMiddleY;
        nextColorX = ((width / 3) * 2) + 50;
        nextColorY = nextColorAreaMiddleY;

    }

    @Override
    public void onDraw(Canvas canvas) {
        currentLevel = logic.getCurrentLevel();
        colorPalette = getColorPalette();
        this.setElementsDimensions(viewWidth, viewHeight);
        drawPalette(canvas);

        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTextSize(50);
        paint.setColor(Color.BLACK);
        canvas.drawText("PROSSIMO COLORE:",textX, textY, paint);

        float boxStartX = nextColorX;
        float boxEndX = nextColorX + boxSize;
        float boxStartY = nextColorY - (boxSize / 2);
        float boxEndY = nextColorY + (boxSize / 2);

        paint.setColor(colorPalette[logic.getNextColor()]);
        canvas.drawRoundRect(boxStartX, boxStartY, boxEndX, boxEndY, (float) 20, (float) 20, paint);
    }

    private void drawPalette(Canvas canvas) {

        for(int i = 0; i < numColors; i++) {
            float boxStartX = paletteStartingX + ((boxSize + boxMargin) * i);
            float boxEndX = boxStartX + boxSize;
            float boxStartY = paletteStartingY;
            float boxEndY = paletteEndingY;

            paint.setColor(colorPalette[i]);
            canvas.drawRoundRect(boxStartX, boxStartY, boxEndX, boxEndY, (float) 20, (float) 20, paint);
        }
    }
    public int[] getColorPalette() {
        int[] numColorsLevels = getResources().getIntArray(R.array.numColorsLevels);
        numColors = numColorsLevels[currentLevel];
        int[] palette = new int[numColors];
        TypedArray paletteFromXml;

        switch(currentLevel) {
            case 0:
                paletteFromXml = getResources().obtainTypedArray(R.array.colorPalette_level0);
                for (int i = 0; i < paletteFromXml.length(); i++) {
                    palette[i] = paletteFromXml.getColor(i, 0);
                }
                break;
            case 1:
                paletteFromXml = getResources().obtainTypedArray(R.array.colorPalette_level1);
                for (int i = 0; i < paletteFromXml.length(); i++) {
                    palette[i] = paletteFromXml.getColor(i, 0);
                }
                break;
        }
        return palette;
    }

    public int getNumColors() {;
        return numColors;
    }

    public void updateView() {
        invalidate();
    }

    @Override
    public void setLogic(ILogic logic) {
        this.logic = logic;
    }
}
