package com.cromero.hiTFG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        start();
    }

    private void start() {
        long time = 2 * 1000;
        Timer timer = new Timer(true);

        timer.schedule(new FinishTask(), time);
    }

    private class FinishTask extends TimerTask {

        @Override
        public void run() {
            Intent activity = new Intent((SplashActivity.this), MainActivity.class);
            startActivity(activity);
            SplashActivity.this.finish();
        }

    }

}
