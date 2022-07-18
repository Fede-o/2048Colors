package fo.pigdm.colors2048.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentResultListener;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import fo.pigdm.colors2048.R;
import fo.pigdm.colors2048.logic.GameEngine;
import fo.pigdm.colors2048.logic.ILogic;
import fo.pigdm.colors2048.view.ColorPaletteView;
import fo.pigdm.colors2048.view.GameView;
import fo.pigdm.colors2048.view.IView;
import fo.pigdm.colors2048.utils.SoundPlayer;
import fo.pigdm.colors2048.view.gameDialogs.GameOverDialogFragment;
import fo.pigdm.colors2048.view.gameDialogs.GameWinDialogFragment;
import fo.pigdm.colors2048.view.gameDialogs.OnGameOverListener;
import fo.pigdm.colors2048.view.gameDialogs.OnGameWonListener;
import fo.pigdm.colors2048.view.gameDialogs.OnTileMergeListener;
import fo.pigdm.colors2048.view.gameDialogs.OnTileMoveListener;

public class GameActivity extends AppCompatActivity {

    int currentLevel = 0;
    boolean soundFX = false;
    boolean bgMusic = false;
    int gridDim = 4;

    ILogic gameEngine;
    GameView gameView;
    ColorPaletteView colorPaletteView;
    SoundPlayer soundPlayer;
    MediaPlayer musicPlayer;

    public void startMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startSettingsActivity(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void readSavedSettings() {
        SharedPreferences gameSettings = PreferenceManager.getDefaultSharedPreferences(this);
        currentLevel = Integer.parseInt(gameSettings.getString("level", "0"));
        soundFX = gameSettings.getBoolean("sound_effects", false);
        bgMusic = gameSettings.getBoolean("background_music", false);
        gridDim = Integer.parseInt(gameSettings.getString("grid_dim", "1"));
    }

    public void showWinnerDialog() {
        GameWinDialogFragment gameWinDialog = new GameWinDialogFragment();
        gameWinDialog.setCancelable(false);
        gameWinDialog.show(getSupportFragmentManager(), "game_win");
    }

    public void showGameOverDialog() {
        GameOverDialogFragment gameOverDialog = new GameOverDialogFragment();
        gameOverDialog.setCancelable(false);
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
        soundPlayer = new SoundPlayer(this);
        musicPlayer = MediaPlayer.create(this, R.raw.bg_music_loop);
        if(musicPlayer != null) {
            musicPlayer.setLooping(true);
            musicPlayer.setVolume(0.1f, 0.1f);
            //if(bgMusic)
               // musicPlayer.start();
        }

        gameEngine = new GameEngine();

        //retrieve saved settings
        gameEngine.setCurrentLevel(currentLevel);
        gameEngine.setGridDim(gridDim);

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
                        if(soundFX)
                            soundPlayer.playSound("LEVELCOMPLETE");
                    }
                });

        gameView.setOnGameOverListener(
                new OnGameOverListener() {
                    @Override
                    public void onGameOver() {
                        showGameOverDialog();
                        if(soundFX)
                            soundPlayer.playSound("GAMEOVER");

                    }
                });

        gameView.setOnTileMoveListener(
                new OnTileMoveListener() {
                    @Override
                    public void onTileMove() {
                        if(soundFX)
                            soundPlayer.playSound("TILEMOVE");
                    }
                });

        gameView.setOnTileMergeListener(
                new OnTileMergeListener() {
                    @Override
                    public void onTileMerge() {
                        if(soundFX)
                            soundPlayer.playSound("TILEMERGE");
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

    @Override
    protected void onPause() {
        super.onPause();
        musicPlayer.pause();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(bgMusic)
            musicPlayer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(bgMusic)
            musicPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        musicPlayer.pause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicPlayer.stop();
        musicPlayer.release();
    }






}