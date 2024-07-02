package com.cromero.hiTFG;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class AprendeActivity extends CustomActivity {

    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.practica);

        VideoView videoView = (VideoView) findViewById(R.id.videoView_video);

        Uri path = Uri.parse("android.resource://com.cromero.hiTFG/"
                + R.raw.aprende6);

        videoView.setVideoURI(path);
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                finish();
            }
        });
    }
}
