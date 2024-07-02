package com.cromero.hiTFG;


import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.VideoView;


public class LanzarNumero extends CustomActivity implements OnCompletionListener {

    final static int one = R.raw.uno;
    final static int two = R.raw.dos;
    final static int three = R.raw.tres;
    final static int four = R.raw.cuatro;
    final static int five = R.raw.cinco;
    final static int six = R.raw.seis;
    final static int seven = R.raw.siete;
    final static int eight = R.raw.ocho;
    final static int nine = R.raw.nueve;
    final static int ten = R.raw.diez;


    final static int unoI = R.drawable.uno;
    final static int dosI = R.drawable.dos;
    final static int tresI = R.drawable.tres;
    final static int cuatroI = R.drawable.cuatro;
    final static int cincoI = R.drawable.cinco;
    final static int seisI = R.drawable.seis;
    final static int sieteI = R.drawable.siete;
    final static int ochoI = R.drawable.ocho;
    final static int nueveI = R.drawable.nueve;
    final static int diezI = R.drawable.diez;

    public int numero;
    public int numeroimagen;
    public String number;

    public static int contador = 1;
    public static boolean bandera = false;
    public static boolean seguir = true;

    public String cadenaAnalizar[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practica);

        if (bandera == true) {
            //Obtenemos el contador de la Clase Prueba para saber el siguiente numero a lanzar
            Bundle extra = this.getIntent().getExtras();
            contador = extra.getInt("lanzar");
            seguir = extra.getBoolean("seguir");
        }

        numero = ProcesaContador(contador, 1);
        numeroimagen = ProcesaContador(contador, 2);
        cadenaAnalizar = ProcesaCadena(contador);

        if (seguir == true) {
            Reproducir(numero);
        } else {
            onCompletion(null);
        }
    }

    public void Reproducir(int NombreVideo) {

        bandera = true;

        VideoView videoPlayer = (VideoView) findViewById(R.id.videoView_video);

        //videoPlayer.setOnPreparedListener(this);
        videoPlayer.setOnCompletionListener(this);
        videoPlayer.setKeepScreenOn(true);


        //specify the location of media file
        Uri uri = Uri.parse("android.resource://com.cromero.hiTFG/"
                + NombreVideo);
        //Setting MediaController and URI, then starting the videoView
        videoPlayer.setVideoURI(uri);
        videoPlayer.requestFocus();
        videoPlayer.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * This callback will be invoked when the file is finished playing
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        // Statements to be executed when the video finishes.
        Intent i = new Intent(this, ReconocimientoActivity.class);
        i.putExtra("numero", contador);
        i.putExtra("cadenaAnalizar", cadenaAnalizar);
        i.putExtra("imagen", numeroimagen);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(i);


    }

    public int ProcesaContador(int contador, int var) {
        int numero;
        int numeroImagen;

        switch (contador) {
            case 1:
                numero = one;
                numeroImagen = unoI;

                break;
            case 2:
                numero = two;
                numeroImagen = dosI;
                break;
            case 3:
                numero = three;
                numeroImagen = tresI;
                break;
            case 4:
                numero = four;
                numeroImagen = cuatroI;
                break;
            case 5:
                numero = five;
                numeroImagen = cincoI;
                break;
            case 6:
                numero = six;
                numeroImagen = seisI;
                break;
            case 7:
                numero = seven;
                numeroImagen = sieteI;
                break;
            case 8:
                numero = eight;
                numeroImagen = ochoI;
                break;
            case 9:
                numero = nine;
                numeroImagen = nueveI;
                break;
            case 10:
                numero = ten;
                numeroImagen = diezI;
                break;
            default:
                numero = 0;
                numeroImagen = 0;

                Intent i = new Intent();
                i.setClass(LanzarNumero.this, ReproducirFinal.class);
                finish();
                startActivity(i);

        }

        if (var == 1) {
            return numero;
        } else {
            return numeroImagen;
        }
    }

    public String[] ProcesaCadena(int contador) {
        String cadena[];

        switch (contador) {
            case 1:
                cadena = uno;
                break;
            case 2:
                cadena = dos;
                break;
            case 3:
                cadena = tres;
                break;
            case 4:
                cadena = cuatro;
                break;
            case 5:
                cadena = cinco;
                break;
            case 6:
                cadena = seis;
                break;
            case 7:
                cadena = siete;
                break;
            case 8:
                cadena = ocho;
                break;
            case 9:
                cadena = nueve;
                break;
            case 10:
                cadena = diez;
                break;
            default:
                cadena = null;
                finish();

        }

        return cadena;
    }

}  