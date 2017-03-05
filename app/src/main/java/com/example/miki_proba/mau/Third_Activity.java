package com.example.miki_proba.mau;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Third_Activity extends AppCompatActivity {


    private EditText name1;     //A játékos neve
    private Button button1;     //Tovább gomb
    private Boolean exit = false;   //Ez a változó azért kell, mert a vissaz gombbal a programot bzárjuk, ha ez az érték true lesz!
    private MediaPlayer mp1,mp2;    //Két hang fájl, egyik azt csinálja hogyha nem add meg nevet akkor sípól, ha meg sikerült és beír valamit akkor utána át teszi a következő activityre és kiadja a hangot.
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);


        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("name","");
        name1 = (EditText) findViewById(R.id.name1);
        name1.setText(name);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ellenőrzi, hogy a név mezőben van e valami.
                if (name1.getText().length() == 0){
                    Toast.makeText(Third_Activity.this, "Please write your name", Toast.LENGTH_SHORT).show();
                    mp1.setLooping(false);
                    mp1.start();

                }else
                {
                    mp2.setLooping(false);
                    mp2.start();

                    SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("name", name1.getText().toString());
                    editor.commit();

                    Intent tothird = new Intent(Third_Activity.this, Menu_Activity.class);
                    startActivity(tothird);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        });

        mp1 = MediaPlayer.create(this,R.raw.wrong);
        mp2 = MediaPlayer.create(this,R.raw.correct);


        Log.d("LOG_second","Create");
    }


    //ha kétszer rá kattintunk a vissza gombra akkor ne a másik activityre menjen át hanem kilép.
    @Override
    public void onBackPressed() {
        if (exit) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LOG_second","Start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LOG_second","Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        int pos = name1.getText().length();
        name1.setSelection(pos);

        Log.d("LOG_second","Resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LOG_second","Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LOG_second", "Destroy");
    }

}
