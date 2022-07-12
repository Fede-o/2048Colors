package fo.pigdm.colors2048.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;

import fo.pigdm.colors2048.R;
import fo.pigdm.colors2048.logic.GameEngine;
import fo.pigdm.colors2048.logic.ILogic;

public class GameActivity extends AppCompatActivity {

    int currentLevel = 0;



    public void readSavedSettings() {
        SharedPreferences gameSettings = PreferenceManager.getDefaultSharedPreferences(this);
        currentLevel = Integer.parseInt(gameSettings.getString("level", "0"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        hideSystemBars();

        readSavedSettings();

        ILogic gameEngine = new GameEngine();

        //retrieve saved settings
        gameEngine.setCurrentLevel(currentLevel);

        //set class instances
        IView gameView = findViewById(R.id.gameView);
        IView colorPaletteView = findViewById(R.id.colorPaletteView);
        gameEngine.setView(gameView, colorPaletteView);
        gameView.setLogic(gameEngine);
        colorPaletteView.setLogic(gameEngine);

        gameEngine.newGame();

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


}