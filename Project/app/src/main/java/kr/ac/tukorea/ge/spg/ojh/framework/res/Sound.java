package kr.ac.tukorea.ge.spg.ojh.framework.res;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;

import kr.ac.tukorea.ge.spg.ojh.framework.activity.GameActivity;

public class Sound {
    protected static MediaPlayer mediaPlayer;
    protected static SoundPool soundPool;
    private static final HashMap<Integer, Integer> soundIdMap = new HashMap<>();

    public static void initializeSoundPool( int[] soundResIds) {
        AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attrs)
                .setMaxStreams(3)
                .build();

        for (int resId : soundResIds) {
            int soundId = soundPool.load(GameActivity.activity, resId, 1);
            soundIdMap.put(resId, soundId);
        }
    }
    public static void playMusic(int resId) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        mediaPlayer = MediaPlayer.create(GameActivity.activity, resId);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
    public static void stopMusic() {
        if (mediaPlayer == null) return;
        mediaPlayer.stop();
        mediaPlayer = null;
    }
    public static void pauseMusic() {
        if (mediaPlayer == null) return;
        mediaPlayer.pause();
    }
    public static void resumeMusic() {
        if (mediaPlayer == null) return;
        mediaPlayer.start();
    }

   public static void playEffect(int resId) {
        SoundPool pool = getSoundPool();
        int soundId;
        if (soundIdMap.containsKey(resId)) {
            soundId = soundIdMap.get(resId);
        } else {
            soundId = pool.load(GameActivity.activity, resId, 1);
            soundIdMap.put(resId, soundId);
        }
        // int streamId =
        pool.play(soundId, 1f, 1f, 1, 0, 1f);
    }

    private static SoundPool getSoundPool() {
        if (soundPool != null) return soundPool;

        AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attrs)
                .setMaxStreams(3)
                .build();
        return soundPool;
    }
}
