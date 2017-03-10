package com.MAU.erasmus.mau;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Loading_Activity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;  //A betöltési időt itt lehet beállítani.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.betolto);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Loading_Activity.this, Story_Activity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        },SPLASH_TIME_OUT);
    }
}
