package fo.pigdm.colors2048.view.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import fo.pigdm.colors2048.R;
import fo.pigdm.colors2048.utils.BackgroundMusicService;

public class SettingsActivity extends AppCompatActivity {

    private boolean isBound = false;
    private BackgroundMusicService service;
    boolean bgMusic;


    private ServiceConnection Scon = new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = ((BackgroundMusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            service = null;
        }
    };

    public void readSavedSettings() {
        SharedPreferences gameSettings = PreferenceManager.getDefaultSharedPreferences(this);
        bgMusic = gameSettings.getBoolean("background_music", false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        doBindService();

    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }


    void doBindService(){
        if(bindService(new Intent(this, BackgroundMusicService.class),
                Scon, Context.BIND_AUTO_CREATE)){
            isBound = true;
        }

    }

    void doUnbindService() {
        if(isBound)
        {
            unbindService(Scon);
            isBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        readSavedSettings();
        if(!bgMusic)
            service.stopMusic();
        else
            service.playMusic();
        doUnbindService();
    }
}