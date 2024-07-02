package com.cromero.hiTFG;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!verificaConexion(this)) {
            onCreateDialog();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_tutorial:
                lanzarTutorial(null);
                break;

            case R.id.action_info:
                lanzarAcerca(null);
                break;

            case R.id.action_exit:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void lanzarAprende(View view) {
//        Intent i = new Intent(this, ColocacionMovil.class);
        Intent i = new Intent();
        i.putExtra("modo", 1);
        i.setClass(this, AprendeActivity.class);
        startActivity(i);
    }

    public void lanzarPractica(View view) {
//        Intent i = new Intent(this, ColocacionMovil.class);
        Intent i = new Intent();
        i.putExtra("modo", 2);
        i.setClass(this, Presentacion.class);
        startActivity(i);
    }

    public void lanzarPractica2(View view) {
//        Intent i = new Intent(this, ColocacionMovil.class);
        Intent i = new Intent();
        i.putExtra("modo", 3);
        i.setClass(this, Presentacion.class);
        startActivity(i);
    }

    public void lanzarEvalua(View view) {
//        Intent i = new Intent(this, ColocacionMovil.class);
        Intent i = new Intent();
        i.putExtra("modo", 4);
        i.setClass(this, Presentacion.class);
        startActivity(i);
    }

    public void lanzarTutorial(View view) {
        Intent i = new Intent(this, Tutorial.class);
        startActivity(i);
    }

    public void lanzarAcerca(View view) {
        Intent i = new Intent(this, Acerca.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if (exit)
            MainActivity.this.finish();
        else {
            Toast.makeText(this, "Pulse de nuevo para Salir.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No s�lo wifi, tambi�n GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle deber�a no ser tan �apa
        for (int i = 0; i < 2; i++) {
            // �Tenemos conexi�n? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }

    public void onCreateDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(
                "Atención, para usar esta aplicación de forma correcta el dispositivo necesita estar conectado a internet. Por favor, comprueba la conexión mediante WiFi o datos móviles.")
                .setTitle("Error de conexión a internet")
                .setPositiveButton("Comprobar conexión",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(
                                        android.provider.Settings.ACTION_WIRELESS_SETTINGS));

                            }
                        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();
    }


}
