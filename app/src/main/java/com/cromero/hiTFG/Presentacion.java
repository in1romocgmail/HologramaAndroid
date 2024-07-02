package com.cromero.hiTFG;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class Presentacion extends CustomActivity {
    public int modo;
    int video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.colocacion);

        Bundle extra = this.getIntent().getExtras();
        modo = extra.getInt("modo");

        if (modo == 2 || modo == 3) {
            video = R.raw.presentacionpractica;
        } else {
            video = R.raw.presentacionevalua;
        }


        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        Uri path = Uri.parse("android.resource://com.cromero.hiTFG/"
                + video);

        videoView.setVideoURI(path);
        videoView.start();

        // in this callback.
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent i = new Intent();

                switch (modo) {
                    case 1:
                        i.setClass(Presentacion.this, AprendeActivity.class);
                        break;
                    case 2:
                        i.setClass(Presentacion.this, LanzarNumero.class);
                        break;
                    case 3:
                        i.setClass(Presentacion.this, Practica2Activity.class);
                        break;
                    case 4:
                        i.setClass(Presentacion.this, EvaluaActivity.class);
                        break;
                    default:
                        break;
                }

                startActivity(i);
                Presentacion.this.finish();
            }
        });

    }
}
