package fo.pigdm.colors2048.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentResultListener;
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

    ILogic gameEngine;
    IView gameView;
    IView colorPaletteView;



    public void readSavedSettings() {
        SharedPreferences gameSettings = PreferenceManager.getDefaultSharedPreferences(this);
        currentLevel = Integer.parseInt(gameSettings.getString("level", "0"));
    }

    public void showWinnerDialog() {
        GameWinDialogFragment gameWinDialog = new GameWinDialogFragment();
        gameWinDialog.show(getSupportFragmentManager(), "game_win");
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        hideSystemBars();

        readSavedSettings();

        gameEngine = new GameEngine();

        //retrieve saved settings
        gameEngine.setCurrentLevel(currentLevel);

        //set class instances
        gameView = findViewById(R.id.gameView);
        colorPaletteView = findViewById(R.id.colorPaletteView);

        gameEngine.setView(gameView, colorPaletteView);
        gameView.setView(colorPaletteView);
        colorPaletteView.setView(gameView);
        gameView.setLogic(gameEngine);
        gameView.setOnGameWonListener(
                new OnGameWonListener() {
                    @Override
                    public void onGameWon() {
                        showWinnerDialog();
                    }
                });

        colorPaletteView.setLogic(gameEngine);

        gameEngine.newGame();

        getSupportFragmentManager().setFragmentResultListener("getUserAction", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String result = bundle.getString("response");
                if (result.equals("NEXT")){
                    gameEngine.setCurrentLevel(gameEngine.getCurrentLevel() + 1);
                    gameEngine.newGame();
                    gameView.updateView();
                    colorPaletteView.updateView();
                }
            }
        });

    }




}