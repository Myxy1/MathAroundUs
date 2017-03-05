package com.example.miki_proba.mau;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Menu_Activity extends AppCompatActivity {

    private static final String TAG = "Menu_Activity";
    private TextView name2;     //A játékos nevének a mező.
    private String name;        //A játékos neve.
    private ImageView img1,img2;      //a facebook logo és az erasmus kép.
    private Boolean exit = false;   //Ez a változó azért kell, mert a vissaz gombbal a programot bzárjuk, ha ez az érték true lesz!
    private View button_options;    //Beállítások opció gomb.
    private Button button1, button4, button5, reset;    //A játék indítása gomb és a készítők gomb.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("name","");
        name2 = (TextView) findViewById(R.id.name2);
        name2.setText(name);

        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert1 = new AlertDialog.Builder(Menu_Activity.this, R.style.AppCompatAlertDialogStyle);
                alert1.setMessage(R.string.do_you_want_to_restart)
                        .setTitle(R.string.Restart)
                        .setPositiveButton(R.string.no, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){

                            }
                        })
                        .setNegativeButton(R.string.yes, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.remove("level2");
                                editor.remove("level3");
                                editor.remove("level4");
                                editor.commit();

                                Intent intent = new Intent(Menu_Activity.this,Levels_Activity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                                Toast.makeText(Menu_Activity.this, "Your game was restarted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setIcon(R.drawable.icon1)
                        .create();
                alert1.show();

            }
        });

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_Activity.this,Levels_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Menu_Activity.this, R.style.FullHeightDialog);
                dialog.setContentView(R.layout.credits);
                dialog.show();
            }
        });

        button_options = findViewById(R.id.button_option);
        button_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick");
                Intent option = new Intent(Menu_Activity.this, Options_Activity.class);
                startActivity(option);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "This game is awesome!!! You need to download this game!!! Website:http://www.matharoundus.nhely.hu/ GooglePlay:";
                String shareSub = "Your Subject here";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }
        });

        img1 = (ImageView)findViewById(R.id.fb);
        img1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Uri uri = Uri.parse("http://www.facebook.com/MathAroundUs");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        img2 = (ImageView)findViewById(R.id.erasmus);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.matharoundus.nhely.hu/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
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
