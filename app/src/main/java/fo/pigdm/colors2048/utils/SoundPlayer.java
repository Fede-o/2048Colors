package fo.pigdm.colors2048.utils;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import fo.pigdm.colors2048.R;

public class SoundPlayer {

    SoundPool soundPool;

    int game_over;
    int level_complete;
    int tile_move;
    int tile_merge;

    public SoundPlayer(Context context) {

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(3)
                .setAudioAttributes(audioAttributes)
                .build();

        tile_move = soundPool.load(context, R.raw.tile_move, 1);
        tile_merge = soundPool.load(context, R.raw.tile_merge, 1);
        game_over = soundPool.load(context, R.raw.game_over, 1);
        level_complete = soundPool.load(context, R.raw.level_complete, 1);
    }

    public void playSound(String soundName) {

        switch (soundName) {
            case "GAMEOVER":
                soundPool.play(game_over, 1.0f, 1.0f, 1, 0, 1.0f);
                break;
            case "TILEMOVE":
                soundPool.play(tile_move, 1.0f, 1.0f, 1, 0, 1.0f);
                //soundPool.autoPause();
                break;
            case "TILEMERGE":
                soundPool.play(tile_merge, 0.7f, 0.7f, 1, 0, 1.0f);
                break;
            case "LEVELCOMPLETE":
                soundPool.play(level_complete, 1.0f, 1.0f, 1, 0, 1.0f);

                break;
        }
    }

}
