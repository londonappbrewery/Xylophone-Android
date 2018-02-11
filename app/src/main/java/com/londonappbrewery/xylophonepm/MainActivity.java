package com.londonappbrewery.xylophonepm;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Audio
    private final int NR_OF_SIMULTANEOUS_SOUNDS = 7;
    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int PRIORITY = 0;
    private final int NO_LOOP = 0;
    private final float NORMAL_PLAY_RATE = 1.0f;

    private SoundPool mSoundPool;
    final private int[] SOUND_IDS = { R.raw.note1_c, R.raw.note2_d,  R.raw.note3_e, R.raw.note4_f,
                                      R.raw.note5_g, R.raw.note6_a, R.raw.note7_b };

    // GUI
    final private int[] BUTTON_IDS = { R.id.c_key, R.id.d_key, R.id.e_key,  R.id.f_key,
                                       R.id.g_key, R.id.a_key, R.id.b_key };

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------------------------------------------------------------------
        // Prep Pool for load.
        mSoundPool = new SoundPool(NR_OF_SIMULTANEOUS_SOUNDS, AudioManager.STREAM_MUSIC, 0);

        // Load Sound IDs into Pool --> Generates a UID ( 1(C) - 7(B) )
        for (int id : SOUND_IDS) {
            mSoundPool.load(getApplicationContext(), id, PRIORITY);
        }
        //------------------------------------------------------------------------------------------
        // Button Config: For each button, in order, assign relevant sound from our pool.
        ArrayList<Button> btn_keys = new ArrayList<>();

        for(int i = 0; i < BUTTON_IDS.length; i++) {
            final int current_key = i; // For inner compatibility, but used generally for consistency.

            Button button = (Button) findViewById(BUTTON_IDS[current_key]); // Get next button ID.
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    v.startAnimation(new AlphaAnimation(1F, 0.8F)); // Fade effect.
                    // Assign sound. @offset SoundPool index offset. ( 1(C) - 7(B) )
                    mSoundPool.play(
                            current_key + 1,
                            LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
                    System.out.println("Playing " + current_key);
                }
            });
            btn_keys.add(button); // Our completed button.

        }
        //------------------------------------------------------------------------------------------
    }
    //----------------------------------------------------------------------------------------------
}
