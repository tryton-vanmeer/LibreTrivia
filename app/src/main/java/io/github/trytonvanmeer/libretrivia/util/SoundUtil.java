package io.github.trytonvanmeer.libretrivia.util;

import android.content.Context;
import android.media.MediaPlayer;

import io.github.trytonvanmeer.libretrivia.R;

public class SoundUtil {
    public static final int SOUND_ANSWER_CORRECT = R.raw.sound_answer_correct;
    public static final int SOUND_ANSWER_WRONG = R.raw.sound_answer_wrong;

    public static void playSound(Context context, int sound) {
        final MediaPlayer player = MediaPlayer.create(context, sound);
        player.setVolume(0.25f, 0.25f);
        player.start();
    }
}
