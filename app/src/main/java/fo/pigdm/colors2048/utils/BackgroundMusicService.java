package fo.pigdm.colors2048.utils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import fo.pigdm.colors2048.R;

public class BackgroundMusicService extends Service {

    MediaPlayer player;
    private final IBinder mBinder = new ServiceBinder();

    //public BackgroundMusicService() {}

    public class ServiceBinder extends Binder {
        public BackgroundMusicService getService(){
            return BackgroundMusicService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.bg_music_loop);
        if(player != null) {
            player.setLooping(true);
            player.setVolume(20, 20);
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        //super.onStartCommand(intent, flags, startId);
        player.start();
        return START_STICKY;

    }


    public IBinder onUnBind(Intent intent) {
        return null;
    }

    public void playMusic() {
        if(!player.isPlaying())
        {
            player.start();
        }
    }

    public void pauseMusic() {
        if(player.isPlaying())
        {
            player.pause();

        }
    }

    public void resumeMusic() {
        if(!player.isPlaying())
        {
            player.start();
        }
    }

    public void stopMusic() {
        player.stop();
        player.release();
        player = null;
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        if(player != null)
        {
            try{
                player.stop();
                player.release();
            }finally {
                player = null;
            }
        }
    }

}
