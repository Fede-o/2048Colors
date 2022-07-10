package fo.pigdm.colors2048.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import fo.pigdm.colors2048.logic.ILogic;
import fo.pigdm.colors2048.R;



public class GameView extends View implements IView {

    private static ILogic logic;

    int slotSize = 0;
    int boardMargin = 0;
    float boardStartingX;
    float boardStartingY;
    float boardEndingX;
    float boardEndingY;
    float boardWidth;
    float boardHeight;
    private final Paint paint = new Paint();
    public int currentLevel;
    public int[] colorPalette;

    float prevX, prevY;

    //forme

    public GameView(Context context) {
        super(context);
        //logic.newGame();
    }

    //CONSTRUCTOR FOR GENERATION OF VIEW FROM XML LAYOUT
    public GameView(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int width = this.getWidth();
        int height = this.getHeight();

        this.setElementsDimensions(width, height);

    }

    private void setElementsDimensions(int width, int height) {
        slotSize = Math.min(width / (logic.getNumColumns() + 1), height / (logic.getNumRows() + 3));
        boardMargin = slotSize / 7;
        int boardMiddleX = width / 2;
        int boardMiddleY = (height / 2);

        //board dimensions
        double halfNumBoardColumns = (double)logic.getNumColumns() / (double)2;
        double halfNumBoardRows = (double)logic.getNumRows() / (double)2;
        boardStartingX = (float) (boardMiddleX - ((slotSize + boardMargin) * halfNumBoardColumns) - boardMargin / 2);
        boardStartingY = (float) (boardMiddleY - ((slotSize + boardMargin) * halfNumBoardRows) - boardMargin / 2);
        boardEndingX = (float) (boardMiddleX + ((slotSize + boardMargin) * halfNumBoardColumns) + boardMargin / 2);
        boardEndingY = (float) (boardMiddleY + ((slotSize + boardMargin) * halfNumBoardRows) + boardMargin / 2);

        boardWidth = boardEndingX - boardStartingX;
        boardHeight = boardEndingY - boardStartingY;
    }

    @Override
    public void onDraw(Canvas canvas) {
        drawBoardAndSlots(canvas);
        drawTiles(canvas);
    }

    private void drawTiles(Canvas canvas) {
        currentLevel = logic.getCurrentLevel();
        colorPalette = getColorPalette();
        for (int y = 0; y < logic.getNumRows(); y++) {
            for (int x = 0; x < logic.getNumColumns(); x++) {
                int slotStartX = (int) boardStartingX + boardMargin + ((slotSize + boardMargin) * x);
                int slotEndX = slotStartX + slotSize;
                int slotStartY = (int) boardStartingY + boardMargin + ((slotSize + boardMargin) * y);
                int slotEndY = slotStartY + slotSize;

                int currentTileColor = logic.getTileColor(x, y);

                if (currentTileColor >= 0 && currentTileColor <colorPalette.length) {
                    paint.setColor(colorPalette[currentTileColor]);
                    canvas.drawRoundRect((float) slotStartX, (float) slotStartY, (float) slotEndX, (float) slotEndY, (float) 50, (float) 50, paint);
                }
            }
        }
    }

    private void drawBoardAndSlots(Canvas canvas) {
        paint.setColor(Color.GRAY);
        canvas.drawRoundRect((float) boardStartingX, (float) boardStartingY, (float) boardEndingX, (float) boardEndingY, (float) 50, (float) 50, paint);
        paint.setColor(Color.LTGRAY);
        for (int y = 0; y < logic.getNumRows(); y++) {
            for (int x = 0; x < logic.getNumColumns(); x++) {
                int slotStartX = (int) boardStartingX + boardMargin + ((slotSize + boardMargin) * x);
                int slotEndX = slotStartX + slotSize;
                int slotStartY = (int) boardStartingY + boardMargin + ((slotSize + boardMargin) * y);
                int slotEndY = slotStartY + slotSize;

                canvas.drawRoundRect((float) slotStartX, (float) slotStartY, (float) slotEndX, (float) slotEndY, (float) 50, (float) 50, paint);

            }
        }
    }

    public int[] getColorPalette() {
        int[] numColors = getResources().getIntArray(R.array.numColorsLevels);
        int[] palette = new int[numColors[currentLevel]];
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

    public void setLogic(ILogic logic) {
        this.logic = logic;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                prevX = motionEvent.getX();
                prevY = motionEvent.getY();

                break;

            case MotionEvent.ACTION_UP:
                float newX = motionEvent.getX();
                float newY = motionEvent.getY();

                if(Math.abs(newX - prevX) > Math.abs(newY - prevY)){
                    //HORIZONTAL SWIPE
                    if(newX > prevX) {
                        //SWIPE RIGHT
                        logic.playerMove(1);
                        invalidate();

                    }else{
                        //SWIPE LEFT
                        logic.playerMove(3);
                        invalidate();

                    }
                }else{
                    //VERTICAL SWIPE
                    if(newY < prevY) {
                        //SWIPE UP
                        logic.playerMove(0);
                        invalidate();

                    }else{
                        //SWIPE DOWN
                        logic.playerMove(2);
                        invalidate();

                    }
                }
                break;

        }

        return true;
    }

}
