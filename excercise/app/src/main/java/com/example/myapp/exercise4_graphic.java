package com.example.myapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class exercise4_graphic extends Activity {
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new exercise4_graphicview(this));
        mPlayer = MediaPlayer.create(this, R.raw.gangnam);
        mPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }
}



