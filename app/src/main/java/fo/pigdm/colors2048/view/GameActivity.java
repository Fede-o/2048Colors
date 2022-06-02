package fo.pigdm.colors2048.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.os.Bundle;
import android.view.MotionEvent;

import fo.pigdm.colors2048.R;
import fo.pigdm.colors2048.logic.GameEngine;
import fo.pigdm.colors2048.view.testView.TestCustomDrawableView;

public class GameActivity extends AppCompatActivity {

    float prevX, prevY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        hideSystemBars();
    }


    private void hideSystemBars() {
        WindowInsetsControllerCompat windowInsetsController = ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController == null) {
            return;
        }
        // Configure the behavior of the hidden system bars
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
    }

    /*@Override
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
                    }else{
                        //SWIPE LEFT
                        //GameEngine.getInstance().playerMove(3);
                    }
                }else{
                    //VERTICAL SWIPE
                    if(newY < prevY) {
                        //SWIPE UP
                        GameEngine.getInstance().playerMove(0);

                    }else{
                        //SWIPE DOWN
                        //GameEngine.getInstance().playerMove(2);
                    }
                }
                break;

        }

        return true;
    }
     */
}