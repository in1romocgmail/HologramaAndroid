package com.cromero.hiTFG;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class ReconocimientoActivity extends CustomActivity implements
        RecognitionListener {

    private TextView returnedText;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private static SpeechRecognizer speech = null;
    private static Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    private ImageView image;

    public boolean correcto = false;
    public String number;
    public String alternativa;
    public String cadenaAnalizar[];
    public static int contador = 0;
    int numeroimagen;
    int var;

    public static Context context;
    public static AudioManager audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reconocimiento);

        //recuperamos informacion del intent de lanzar numero
        Bundle extra = this.getIntent().getExtras();
        contador = extra.getInt("numero");
        cadenaAnalizar = extra.getStringArray("cadenaAnalizar");
        numeroimagen = extra.getInt("imagen");

        returnedText = (TextView) findViewById(R.id.textView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);
        image = (ImageView) findViewById(R.id.img);

        toggleButton.setVisibility(View.INVISIBLE);
        returnedText.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        image.setImageResource(numeroimagen);

        context = this;

        start();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (speech != null) {
            speech.stopListening();
            speech.cancel();
            speech.destroy();
            speech = null;
        }

    }

    @Override
    public void onBeginningOfSpeech() {
        Log.e(LOG_TAG, "onBeginningOfSpeech");
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.e(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        Log.e(LOG_TAG, "onEndOfSpeech");
        progressBar.setIndeterminate(true);
        toggleButton.setChecked(false);
    }

    @Override
    public void onError(int errorCode) {
        if (speech != null) {
            speech.stopListening();
            speech.cancel();
            speech.destroy();
            speech = null;
        }
        start();
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        returnedText.setText(errorMessage);
        toggleButton.setChecked(false);
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.e(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.e(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.e(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        Log.e(LOG_TAG, "onResults");
        ArrayList<String> matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        salida:
        for (String result : matches) {
            for (var = 0; var < cadenaAnalizar.length; var++) {
                if (result.equals(cadenaAnalizar[var])) {
                    correcto = true;
                    //Se acierta el contador se incrementa para seguir al numero siguiente
                    contador++;
                    break salida;
                }
            }
        }

        Intent i = new Intent();

        i.putExtra("numerosiguiente", contador);

        i.putExtra("respuesta", correcto);

        i.setClass(ReconocimientoActivity.this, RespuestaActivity.class);

        startActivity(i);
        finish();
        returnedText.setText(text);
    }

    @Override
    public void onRmsChanged(float rmsdB) {
//        Log.e(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }

    static void start() {
        Log.e("***START","dentro");
        audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        audio.setStreamVolume(AudioManager.STREAM_MUSIC, audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        speech = SpeechRecognizer.createSpeechRecognizer(context);
        speech.setRecognitionListener((RecognitionListener) context);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-GB");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 1000);
        if(!recognizerIntent.hasExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE))
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
//        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS,5000);

        if (contador == 10) {
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        } else {
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        }
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);

        speech.startListening(recognizerIntent);

    }
}