package fo.pigdm.colors2048.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import fo.pigdm.colors2048.R;
import fo.pigdm.colors2048.logic.ILogic;

public class ColorPaletteView extends View  implements IView {

    private static ILogic logic;
    private static GameView gameView;

    int currentLevel;
    int[] colorPalette;
    float boxSize;
    float boxNextSize;
    float boxMargin;
    float paletteMargin;
    float paletteStartingX;
    float paletteStartingY;
    float paletteEndingX;
    float paletteEndingY;
    float paletteAreaHeight;
    float nextColorAreaHeight;
    float levelTitleAreaHeight;
    float topBarAreaHeight;
    float textX;
    float textY;
    float nextColorX;
    float nextColorY;
    private float viewWidth;
    private float viewHeight;
    private String[] levelNames;

    private final Paint paint = new Paint();
    private Typeface typeface;



    public ColorPaletteView(Context context) {
        super(context);
    }

    //CONSTRUCTOR FOR GENERATION OF VIEW FROM XML LAYOUT
    public ColorPaletteView(Context context, AttributeSet attributes) {
        super(context, attributes);
        typeface = ResourcesCompat.getFont(context, R.font.roboto_black);
        levelNames = getResources().getStringArray(R.array.levels_entries);
    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        viewWidth = (float) this.getWidth();
        viewHeight = (float) this.getHeight();

    }

    private void setElementsDimensions(float width, float height) {
        float numColors = (float)getNumColors();
        paletteMargin = gameView.getBoardStartingX();

        paletteAreaHeight = height / 4;
        nextColorAreaHeight = height / 4;
        levelTitleAreaHeight = height / 4;
        topBarAreaHeight = height / 4;

        boxMargin = (width - (paletteMargin *2 )) / (numColors*7);
        boxSize = (width - (paletteMargin * 2) - (boxMargin * (numColors -1))) / numColors;

        //boxSize = boxSize - (boxMargin);
        boxNextSize = width / 7;

        float paletteMiddleX = (width - (paletteMargin * 2) / 2);
        float paletteMiddleY = topBarAreaHeight + levelTitleAreaHeight + (paletteAreaHeight / 2);

        paletteStartingX = paletteMargin;
        paletteStartingY = paletteMiddleY - (boxSize / 2);
        paletteEndingX = width - paletteMargin;
        paletteEndingY = paletteMiddleY + (boxSize / 2);

        float nextColorAreaMiddleY = topBarAreaHeight + levelTitleAreaHeight + paletteAreaHeight + (nextColorAreaHeight / 2);
        textX = ((width / 3) * 2) - 20;
        textY = nextColorAreaMiddleY + 10;
        nextColorX = ((width / 3) * 2) + 20;
        nextColorY = nextColorAreaMiddleY;

    }

    @Override
    public void onDraw(Canvas canvas) {
        currentLevel = logic.getCurrentLevel();
        colorPalette = getColorPalette();
        this.setElementsDimensions(viewWidth, viewHeight);

        paint.setColor(getResources().getColor(R.color.md_theme_dark_inverseSurface));
        canvas.drawRect(0,0,viewWidth, topBarAreaHeight, paint);

        paint.setColor(getResources().getColor(R.color.md_theme_dark_surfaceVariant));
        canvas.drawRect(0,topBarAreaHeight,viewWidth, viewHeight, paint);

        drawPalette(canvas);

        paint.setTypeface(typeface);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(80);
        paint.setColor(getResources().getColor(R.color.md_theme_light_primary));

        canvas.drawText(levelNames[currentLevel], (float)(viewWidth/2), (paletteStartingY - 100), paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTextSize(50);
        paint.setColor(getResources().getColor(R.color.md_theme_light_primary));

        canvas.drawText("PROSSIMO COLORE:", textX, textY, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(60);
        paint.setColor(getResources().getColor(R.color.md_theme_light_primary));

        canvas.drawText(String.valueOf(logic.getScore()), topBarAreaHeight/2 +50, topBarAreaHeight/2 +20, paint);


        float boxStartX = nextColorX;
        float boxEndX = nextColorX + boxNextSize;
        float boxStartY = nextColorY - (boxNextSize / 2);
        float boxEndY = nextColorY + (boxNextSize / 2);

        paint.setColor(colorPalette[logic.getNextColor()]);
        canvas.drawRoundRect(boxStartX, boxStartY, boxEndX, boxEndY, (float) 20, (float) 20, paint);
    }

    private void drawPalette(Canvas canvas) {
        int numColors = getNumColors();

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
        int numColors = getNumColors();
        int[] palette = new int[numColors];
        TypedArray paletteFromXml;
        //retrieve the first i colors of the global palette based on the current level
        paletteFromXml = getResources().obtainTypedArray(R.array.colorPalette);
        for (int i = 0; i < numColors; i++) {
            palette[i] = paletteFromXml.getColor(i, 0);
        }
        return palette;
    }

    public int getNumColors() {
        int[] numColorsLevels = getResources().getIntArray(R.array.numColorsLevels);
        int numC = numColorsLevels[logic.getCurrentLevel()];
        return numC;
    }

    public void updateView() {
        invalidate();
    }

    @Override
    public void gameWon() {
        //do nothing
    }

    @Override
    public void gameOver() {
        //do nothing
    }

    @Override
    public void tileMove() {
        //do nothing
    }

    @Override
    public void tileMerge() {
        //do nothing
    }

    @Override
    public void setLogic(ILogic logic) {
        this.logic = logic;
    }

    public void setView(GameView view){
        this.gameView = view;
    }
}
