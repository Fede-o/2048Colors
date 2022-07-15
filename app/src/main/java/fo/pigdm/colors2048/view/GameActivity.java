package fo.pigdm.colors2048.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentResultListener;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import fo.pigdm.colors2048.R;
import fo.pigdm.colors2048.logic.GameEngine;
import fo.pigdm.colors2048.logic.ILogic;
import fo.pigdm.colors2048.view.gameDialogs.GameOverDialogFragment;
import fo.pigdm.colors2048.view.gameDialogs.GameWinDialogFragment;
import fo.pigdm.colors2048.view.gameDialogs.OnGameOverListener;
import fo.pigdm.colors2048.view.gameDialogs.OnGameWonListener;

public class GameActivity extends AppCompatActivity {

    int currentLevel = 0;

    ILogic gameEngine;
    IView gameView;
    IView colorPaletteView;

    public void startMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void readSavedSettings() {
        SharedPreferences gameSettings = PreferenceManager.getDefaultSharedPreferences(this);
        currentLevel = Integer.parseInt(gameSettings.getString("level", "0"));
    }

    public void showWinnerDialog() {
        GameWinDialogFragment gameWinDialog = new GameWinDialogFragment();
        gameWinDialog.show(getSupportFragmentManager(), "game_win");
    }

    public void showGameOverDialog() {
        GameOverDialogFragment gameOverDialog = new GameOverDialogFragment();
        gameOverDialog.show(getSupportFragmentManager(), "game_over");
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

        gameView.setOnGameOverListener(
                new OnGameOverListener() {
                    @Override
                    public void onGameOver() {
                        showGameOverDialog();
                    }
                });

        colorPaletteView.setLogic(gameEngine);

        gameEngine.newGame();

        getSupportFragmentManager().setFragmentResultListener("onGameWonUserAction", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {

                String result = bundle.getString("wonResponse");
                if (result.equals("NEXT")){
                    gameEngine.setCurrentLevel(gameEngine.getCurrentLevel() + 1);
                    gameEngine.newGame();
                    gameView.updateView();
                    colorPaletteView.updateView();
                }
            }
        });

        getSupportFragmentManager().setFragmentResultListener("onGameOverUserAction", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {

                String result = bundle.getString("lostResponse");
                if (result.equals("RETRY")){
                    gameEngine.newGame();
                    gameView.updateView();
                    colorPaletteView.updateView();
                }
            }
        });

    }






}