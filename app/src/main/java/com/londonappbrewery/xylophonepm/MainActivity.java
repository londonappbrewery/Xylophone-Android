package com.londonappbrewery.xylophonepm;

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

    // Helpful Constants
    private final int NR_OF_SIMULTANEOUS_SOUNDS = 7;
    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int NO_LOOP = 0;
    private final int PRIORITY = 0;
    private final float NORMAL_PLAY_RATE = 1.0f;

    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Audio SFX
        final MediaPlayer[] sfx_notes = {
            MediaPlayer.create(MainActivity.this, R.raw.note1_c),
            MediaPlayer.create(MainActivity.this, R.raw.note2_d),
            MediaPlayer.create(MainActivity.this, R.raw.note3_e),
            MediaPlayer.create(MainActivity.this, R.raw.note4_f),
            MediaPlayer.create(MainActivity.this, R.raw.note5_g),
            MediaPlayer.create(MainActivity.this, R.raw.note6_a),
            MediaPlayer.create(MainActivity.this, R.raw.note7_b)
        };

        // Button Identification
        final int[] BUTTON_IDS = {
                R.id.c_key,
                R.id.d_key,
                R.id.e_key,
                R.id.f_key,
                R.id.g_key,
                R.id.a_key,
                R.id.b_key
        };

        // Button Config: For each stored ID, assign relevant playNote(sound).
        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F); // Fade effect.
        ArrayList<Button> keys = new ArrayList<>();

        for(int i = 0; i < BUTTON_IDS.length; i++) {
            final int current_key = i; // For inner use, but used generally for consistency.

            Button button = (Button) findViewById(BUTTON_IDS[current_key]);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    v.startAnimation(buttonClick);
                    playNote(sfx_notes[current_key]);
                }
            });

            keys.add(button);
        }
    }
    //----------------------------------------------------------------------------------------------
    private void playNote(MediaPlayer sfx) {
        System.out.println(this.getTaskId() + " pressed.");
        sfx.start();
    }
    //----------------------------------------------------------------------------------------------
}
