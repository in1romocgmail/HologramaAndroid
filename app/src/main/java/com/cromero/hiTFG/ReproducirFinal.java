package com.cromero.hiTFG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;


public class ReproducirFinal extends CustomActivity {
    public int suma;
    public boolean evalua;
    public String Resultados[];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.colocacion);

        //Obtenemos la suma que contiene la puntuacion
        Bundle extra = this.getIntent().getExtras();

        if (extra != null) {
            suma = extra.getInt("suma");
            Resultados = extra.getStringArray("resultados");
            evalua = true;
        } else {
            evalua = false;
        }

        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        Uri path = Uri.parse("android.resource://com.cromero.hiTFG/"
                + R.raw.final3);

        videoView.setVideoURI(path);
        videoView.start();

        // in this callback.
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (evalua == true) {
                    onCreateDialog();
                } else {
                    ReproducirFinal.this.finish();
                }
            }
        });

    }

    public void onCreateDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Tu nota es un " + suma);
        builder.setTitle("PUNTUACIÓN");
        builder.setPositiveButton("Ver resultados",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        VerResultados();
                    }
                });
        builder.setIcon(android.R.drawable.ic_input_get);
        builder.show();
    }

    public void VerResultados() {
        AlertDialog.Builder notas = new AlertDialog.Builder(this);

        notas.setMessage(" Sol - Sun = " + Resultados[0] +
                "\n Coche - Car = " + Resultados[1] +
                "\n Pez - Fish = " + Resultados[2] +
                "\n Pájaro - Bird = " + Resultados[3] +
                "\n Zapato - Shoes = " + Resultados[4] +
                "\n Tiburon - Shark = " + Resultados[5] +
                "\n Árbol - Tree = " + Resultados[6] +
                "\n Flor - Flower = " + Resultados[7] +
                "\n Mariposa - Butterfly = " + Resultados[8] +
                "\n Medusa - Jelly Fish = " + Resultados[9]);

        notas.setTitle("Resultados");

        notas.setIcon(android.R.drawable.ic_menu_view);
        notas.show();


    }
}
