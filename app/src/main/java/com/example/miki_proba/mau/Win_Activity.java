package com.example.miki_proba.mau;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Win_Activity extends AppCompatActivity {

    private Boolean exit = false;   //Ez a változó azért kell, mert a vissaz gombbal a programot bzárjuk, ha ez az érték true lesz!
    private ImageView imageView;    //A vissza gomb amivel vissza lehet menni a főmenübe.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win);
        Log.d("LOG_lose","Start");


        imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tothird = new Intent(Win_Activity.this, Menu_Activity.class);
                startActivity(tothird);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LOG_lose","Start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LOG_lose","Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LOG_lose","Resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LOG_lose","Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LOG_lose", "Destroy");
    }

    @Override
    public void onBackPressed() {
        if (exit) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Press Back again to Exit.",
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
}
