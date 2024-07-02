package com.cromero.hiTFG;


import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;


public class RespuestaActivity extends CustomActivity implements OnCompletionListener {
    public int contador;
    public int number;
    public boolean bandera;
    public boolean seguir;


    final static int correcto = R.raw.correcto;

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
        contador = extra.getInt("numerosiguiente");

        bandera = extra.getBoolean("respuesta");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.practica);


        if (bandera == true) {
            seguir = true;
            Reproducir(correcto);
        } else {
            number = ProcesaContador(contador);
            seguir = false;
            Reproducir(number);
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
        // Statements to be executed when the video finishes.
        Intent i = new Intent();
        //Asignamos informaci�n al Intent
        i.putExtra("lanzar", contador);

        i.putExtra("seguir", seguir);

        //Asignamos al intent i parametros para iniciar la nueva Activity
        i.setClass(RespuestaActivity.this, LanzarNumero.class);


        //Iniciamos la nueva actividad
        finish();
        startActivity(i);


    }

    public int ProcesaContador(int contador) {
        int number;

        switch (contador) {
            case 1:
                number = uno;
                break;
            case 2:
                number = dos;
                break;
            case 3:
                number = tres;
                break;
            case 4:
                number = cuatro;
                break;
            case 5:
                number = cinco;
                break;
            case 6:
                number = seis;
                break;
            case 7:
                number = siete;
                break;
            case 8:
                number = ocho;
                break;
            case 9:
                number = nueve;
                break;
            case 10:
                number = diez;
                break;
            default:
                number = 0;
                Intent i = new Intent();
                i.setClass(RespuestaActivity.this, ReproducirFinal.class);
                finish();
                startActivity(i);
        }

        return number;
    }
}
