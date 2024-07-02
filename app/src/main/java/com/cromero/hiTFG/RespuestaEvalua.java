package com.cromero.hiTFG;


import android.content.Intent;
import android.os.Bundle;


public class RespuestaEvalua extends CustomActivity {
    public int contador;
    public int suma;
    public boolean bandera;
    public boolean incremento;
    public int nivel;
    public static String Resultados[] = {"Error", "Error", "Error", "Error", "Error", "Error", "Error", "Error", "Error", "Error",};
    public String resultado;


    @Override
    public void onCreate(Bundle savedInstanceState) {


        //Obtenemos el contador de la Clase LanzarNumero para saber el numero que tenemos que reconocer
        Bundle extra = this.getIntent().getExtras();
        suma = extra.getInt("suma");
        contador = extra.getInt("contador");
        resultado = extra.getString("resultado");


        super.onCreate(savedInstanceState);
        //setContentView(R.layout.practica);

        //Resultados[contador-2]=resultado;
        ProcesaErrores(contador, resultado);

        if (contador > 10) {

            Intent i = new Intent();
            //Asignamos informaci�n al Intent
            i.putExtra("suma", suma);
            i.putExtra("resultados", Resultados);

            //Asignamos al intent i parametros para iniciar la nueva Activity
            i.setClass(RespuestaEvalua.this, ReproducirFinal.class);

            //Iniciamos la nueva actividad
            finish();
            startActivity(i);
        } else {
            Intent i = new Intent();
            //Asignamos informaci�n al Intent
            i.putExtra("contador", contador);
            i.putExtra("suma", suma);


            //Asignamos al intent i parametros para iniciar la nueva Activity
            i.setClass(RespuestaEvalua.this, EvaluaActivity.class);

            //Iniciamos la nueva actividad
            finish();
            startActivity(i);
        }


    }

    public void ProcesaErrores(int contador, String resultado) {
        Resultados[contador - 2] = resultado;
    }

}
