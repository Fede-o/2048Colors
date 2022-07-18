package fo.pigdm.colors2048.view;

import android.view.MotionEvent;
import android.view.View;

public class InputListener implements View.OnTouchListener {

    GameView gameView;

    float prevX, prevY;
    float startingX, startingY;
    float previousX, previousY;

    public InputListener(GameView view) {
        super();
        this.gameView = view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                prevX = motionEvent.getX();
                prevY = motionEvent.getY();
                startingX = prevX;
                startingY = prevY;
                previousX = prevX;
                previousY = prevY;

                break;

            case MotionEvent.ACTION_UP:
                float newX = motionEvent.getX();
                float newY = motionEvent.getY();
                float dx = Math.abs(newX - prevX);
                float dy = Math.abs(newY - prevY);

                if(dx > dy){
                    //HORIZONTAL SWIPE
                    if(newX > prevX && dx >= 10) {
                        //SWIPE RIGHT
                        gameView.logic.playerMove(1);
                        //invalidate();

                    }else if(newX < prevX && dx > 10){
                        //SWIPE LEFT
                        gameView.logic.playerMove(3);
                        //invalidate();

                    }
                }else{
                    //VERTICAL SWIPE
                    if(newY < prevY && dy >= 10) {
                        //SWIPE UP
                        gameView.logic.playerMove(0);
                        //invalidate();

                    }else if(newY > prevY && dy > 10){
                        //SWIPE DOWN
                        gameView.logic.playerMove(2);
                        //invalidate();
                    }
                }
                break;

        }

        return true;
    }
}

