package com.example.miki_proba.mau;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Levels extends AppCompatActivity {


    private TextView name2;     //A játékos nevének a mező.
    private String level2,level3,level4;
    private Boolean exit = false;   //Ez a változó azért kell, mert a vissaz gombbal a programot bzárjuk, ha ez az érték true lesz!
    private ImageView imageView;    //A vissza gomb amivel vissza lehet menni a főmenübe.
    private Button button1,button2,button3,button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);

        button1 = (Button) findViewById(R.id.level1);
        button2 = (Button) findViewById(R.id.level2  );
        button3 = (Button) findViewById(R.id.level3);
        button4 = (Button) findViewById(R.id.level4);

        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);

        level2 = sharedPreferences.getString("level2","");
        level3 = sharedPreferences.getString("level3","");
        level4 = sharedPreferences.getString("level4","");
        int declined = Color.rgb(163, 12, 12);//piros szín lesz, ha nincs engedélyezve.
        int accepted = Color.rgb(255, 255, 255);//fehér szín lesz, ha engedélyezve van.

        if (level2.length()>0){
            button2.setTextColor(accepted);
        }else{
            button2.setTextColor(declined);
        }

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tothird = new Intent(Levels.this, ThirdActivity.class);
                startActivity(tothird);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent togame = new Intent(Levels.this, Game.class);
                startActivity(togame);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (level2.length()>0){
                    Intent togame = new Intent(Levels.this, Game2.class);
                    startActivity(togame);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }else{
                    Toast.makeText(Levels.this,"This level is locked",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (level3.length()>0){
                    Intent togame = new Intent(Levels.this, Game3.class);
                    startActivity(togame);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }else{
                    Toast.makeText(Levels.this,"This level is locked",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (level4.length()>0){
                    Intent togame = new Intent(Levels.this, Game4.class);
                    startActivity(togame);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }else{
                    Toast.makeText(Levels.this,"This level is locked",Toast.LENGTH_SHORT).show();
                }
            }
        });


        Log.d("LOG_third","Create");
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
        Log.d("LOG_third","Start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LOG_third","Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LOG_third","Resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LOG_third","Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LOG_third", "Destroy");
    }
}
