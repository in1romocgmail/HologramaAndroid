package com.cromero.hiTFG;


import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;


public class Respuesta2 extends CustomActivity implements OnCompletionListener {
    public int contador;
    public int number;
    public boolean bandera;
    public boolean incremento;
    public int nivel;


    final static int correcto = R.raw.correcto;
    final static int incorrecto = R.raw.incorrecto;


    final static int uno = R.raw.one;
    final static int dos = R.raw.two;
    final static int tres = R.raw.three;
    final static int cuatro = R.raw.four;
    final static int cinco = R.raw.five;
    final static int seis = R.raw.six;
    final static int siete = R.raw.seven;
    final static int ocho = R.raw.eight;
    final static int nueve = R.raw.nine;
    final static int diez = R.raw.ten;


    @Override
    public void onCreate(Bundle savedInstanceState) {


        //Obtenemos el contador de la Clase LanzarNumero para saber el numero que tenemos que reconocer
        Bundle extra = this.getIntent().getExtras();
        bandera = extra.getBoolean("respuesta");
        nivel = extra.getInt("nivel");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.practica);


        if (bandera == true) {
            nivel++;
            Reproducir(correcto);
        } else {
            Reproducir(incorrecto);
        }


    }

    private void Reproducir(int NombreVideo) {
        VideoView videoView = (VideoView) findViewById(R.id.videoView_video);

        videoView.setOnCompletionListener(this);
        videoView.setKeepScreenOn(true);

        Uri path = Uri.parse("android.resource://com.cromero.hiTFG/"
                + NombreVideo);

        videoView.setVideoURI(path);
        videoView.requestFocus();

        videoView.start();


    }

    /**
     * This callback will be invoked when the file is finished playing
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        if (nivel > 3) {
            Intent i = new Intent();
            i.setClass(Respuesta2.this, ReproducirFinal.class);
            this.finish();
            startActivity(i);
        } else {
            // Statements to be executed when the video finishes.
            Intent i = new Intent();
            //Asignamos información al Intent
            i.putExtra("nivel", nivel);

            //Asignamos al intent i parametros para iniciar la nueva Activity
            i.setClass(Respuesta2.this, Practica2Activity.class);

            //Iniciamos la nueva actividad
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // <- Aqu� :)
            startActivity(i);
            finish();
        }

    }


}
