package com.cromero.hiTFG;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class ColocacionMovil extends CustomActivity {
    public int modo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.colocacion);

        Bundle extra = this.getIntent().getExtras();
        modo = extra.getInt("modo");


        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        Uri path = Uri.parse("android.resource://com.cromero.hiTFG/"
                + R.raw.colocacion2);

        videoView.setVideoURI(path);
        videoView.start();

        // in this callback.
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent i = new Intent();

                switch (modo) {
                    case 1:
                        i.setClass(ColocacionMovil.this, AprendeActivity.class);
                        break;
                    case 2:
                        i.putExtra("modo", 2);
                        i.setClass(ColocacionMovil.this, Presentacion.class);
                        break;
                    case 3:
                        i.putExtra("modo", 3);
                        i.setClass(ColocacionMovil.this, Presentacion.class);
                        break;
                    case 4:
                        i.putExtra("modo", 4);
                        i.setClass(ColocacionMovil.this, Presentacion.class);
                        break;
                    default:
                        break;
                }

                startActivity(i);
                ColocacionMovil.this.finish();
            }
        });

    }
}
