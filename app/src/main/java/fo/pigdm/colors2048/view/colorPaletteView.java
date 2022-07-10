package fo.pigdm.colors2048.view;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
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

    private void setElementsDimensions(int width, int height) {

        boxSize = ((float)width / (float)numColors);
        boxMargin = boxSize / 7;
        boxSize = boxSize - (boxMargin);

        float paletteMiddleX = ((float)width / 2);
        float paletteMiddleY = ((float)height / 2);

        paletteStartingX = boxMargin;
        paletteStartingY = (paletteMiddleY - (boxSize / 2));
        paletteEndingX = (width - boxMargin);
        paletteEndingY = (paletteMiddleY + (boxSize / 2));

    }

    @Override
    public void onDraw(Canvas canvas) {
        currentLevel = logic.getCurrentLevel();
        colorPalette = getColorPalette();
        this.setElementsDimensions(viewWidth, viewHeight);
        drawPalette(canvas);
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

    @Override
    public void setLogic(ILogic logic) {
        this.logic = logic;
    }
}
