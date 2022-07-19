package fo.pigdm.colors2048.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import fo.pigdm.colors2048.R;

public class MainActivity extends AppCompatActivity {

    boolean tutorialDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideSystemBars();
        SharedPreferences gameSettings = PreferenceManager.getDefaultSharedPreferences(this);
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        readSavedSettings();
    }

    public void playGame(View view){
        readSavedSettings();
        if(tutorialDone)
            startGameActivity(view);
        else
            startTutorial(view);

    }

    public void readSavedSettings() {
        SharedPreferences gameSettings = PreferenceManager.getDefaultSharedPreferences(this);
        tutorialDone = gameSettings.getBoolean("tutorial_done", false);
    }

    public void startGameActivity(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void startSettingsActivity(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void startLevelsActivity(View view) {
        Intent intent = new Intent(this, LevelsActivity.class);
        startActivity(intent);
    }

    public void startTutorial(View view) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
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