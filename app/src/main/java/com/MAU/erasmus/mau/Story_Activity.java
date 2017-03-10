package com.MAU.erasmus.mau;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Story_Activity extends AppCompatActivity {


    private boolean exit = false;   //Ez a változó azért kell, mert a vissaz gombbal a programot bzárjuk, ha ez az érték true lesz!
    private TextView textView;      //A történetnek a textview-a.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert1 = new AlertDialog.Builder(Story_Activity.this, R.style.AppCompatAlertDialogStyle);//Ez itt a történet.
                alert1.setMessage("One day Europe was kidnapped by a terrible "+
                        "group of evil forces.\n"+
                        "She asked 8 friends to help her.\n"+
                        "They are trying to do their best to make\n"+
                        "Europe free again and defeat the evil forces.\n"+
                        "You can help too."+
                        "To do that you must answer 3 questions right.\n"+
                        "But you are not immortal!\n"+
                        "You have only 5 lives to find the correct answers.\n"+
                        "Will Europe make it?\nCan you help her friends to win?")
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                Intent tosecond = new Intent(Story_Activity.this, Third_Activity.class);
                                startActivity(tosecond);
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            }
                        })
                        .setTitle("Welcome Adventurer!")
                        .setIcon(R.drawable.icon1)
                        .create();
                alert1.show();
            }
        });


        Log.d("LOG_first","Create");
    }
    //ha kétszer rá kattintunk a vissza gombra akkor ne a másik activityre menjen át hanem kilép.
    @Override
    public void onBackPressed() {
        if (exit) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {
            AlertDialog.Builder alert1 = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
            alert1.setMessage("QUIT THE GAME?")
                    .setPositiveButton("NO", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){

                        }
                    })
                    .setNegativeButton("YES", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setIcon(R.drawable.icon1)
                    .create();
            alert1.show();

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LOG_first","Start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LOG_first","Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LOG_first","Resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LOG_first","Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LOG_first", "Destroy");
    }


}
