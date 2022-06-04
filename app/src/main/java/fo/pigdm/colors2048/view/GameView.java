package fo.pigdm.colors2048.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import fo.pigdm.colors2048.logic.GameEngine;


public class GameView extends View implements IView {

    private static GameView instance = null;


    int slotSize = 0;
    int boardMargin = 0;
    float boardStartingX;
    float boardStartingY;
    float boardEndingX;
    float boardEndingY;
    float boardWidth;
    float boardHeight;
    private final Paint paint = new Paint();

    float prevX, prevY;

    //forme

    private GameView(Context context) {
        super(context);
        GameEngine.getInstance().newGame();
    }

    //CONSTRUCTOR FOR GENERATION OF VIEW FROM XML LAYOUT
    public GameView(Context context, AttributeSet attributes) {
        super(context, attributes);
        GameEngine.getInstance().newGame();
    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int width = this.getWidth();
        int height = this.getHeight();

        this.setElementsDimensions(width, height);

    }

    private void setElementsDimensions(int width, int height) {
        slotSize = Math.min(width / (GameEngine.getInstance().NUM_COLUMNS + 1), height / (GameEngine.getInstance().NUM_ROWS + 3));
        boardMargin = slotSize / 7;
        int boardMiddleX = width / 2;
        int boardMiddleY = (height / 2);

        //board dimensions
        double halfNumBoardColumns = (double)GameEngine.getInstance().NUM_COLUMNS / (double)2;
        double halfNumBoardRows = (double)GameEngine.getInstance().NUM_ROWS / (double)2;
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
        for (int y = 0; y < GameEngine.getInstance().NUM_ROWS; y++) {
            for (int x = 0; x < GameEngine.getInstance().NUM_COLUMNS; x++) {
                int slotStartX = (int) boardStartingX + boardMargin + ((slotSize + boardMargin) * x);
                int slotEndX = slotStartX + slotSize;
                int slotStartY = (int) boardStartingY + boardMargin + ((slotSize + boardMargin) * y);
                int slotEndY = slotStartY + slotSize;

                int currentTileColor = GameEngine.getInstance().getTileColor(x, y);
                if (currentTileColor != -1) {
                    paint.setColor(currentTileColor);
                    canvas.drawRoundRect((float) slotStartX, (float) slotStartY, (float) slotEndX, (float) slotEndY, (float) 50, (float) 50, paint);

                }
            }
        }
    }

    private void drawBoardAndSlots(Canvas canvas) {
        paint.setColor(Color.GRAY);
        canvas.drawRoundRect((float) boardStartingX, (float) boardStartingY, (float) boardEndingX, (float) boardEndingY, (float) 50, (float) 50, paint);
        paint.setColor(Color.LTGRAY);
        for (int y = 0; y < GameEngine.getInstance().NUM_ROWS; y++) {
            for (int x = 0; x < GameEngine.getInstance().NUM_COLUMNS; x++) {
                int slotStartX = (int) boardStartingX + boardMargin + ((slotSize + boardMargin) * x);
                int slotEndX = slotStartX + slotSize;
                int slotStartY = (int) boardStartingY + boardMargin + ((slotSize + boardMargin) * y);
                int slotEndY = slotStartY + slotSize;

                canvas.drawRoundRect((float) slotStartX, (float) slotStartY, (float) slotEndX, (float) slotEndY, (float) 50, (float) 50, paint);

            }
        }
    }

    public static GameView getInstance(Context context, AttributeSet attributes) {
        if(instance == null) {
            instance = new GameView(context, attributes);
        }
        return instance;
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
                        //GameEngine.getInstance().playerMove(1);
                        //invalidate();
                        //GameEngine.getInstance().generateTile();
                    }else{
                        //SWIPE LEFT
                        //GameEngine.getInstance().playerMove(3);
                        //invalidate();
                        //GameEngine.getInstance().generateTile();
                    }
                }else{
                    //VERTICAL SWIPE
                    if(newY < prevY) {
                        //SWIPE UP
                        GameEngine.getInstance().playerMove(0);
                        invalidate();
                        GameEngine.getInstance().generateTile();

                    }else{
                        //SWIPE DOWN
                        GameEngine.getInstance().playerMove(2);
                        invalidate();
                        GameEngine.getInstance().generateTile();
                    }
                }
                break;

        }

        return true;
    }

}
