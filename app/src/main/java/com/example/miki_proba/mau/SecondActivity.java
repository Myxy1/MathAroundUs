package com.example.miki_proba.mau;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {


    private EditText name1;
    private Button button1;
    private Boolean exit = false;
    private MediaPlayer mp1,mp2;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        name1 = (EditText) findViewById(R.id.name1);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name1.getText().length() == 0){
                    Toast.makeText(SecondActivity.this, "Please write your name", Toast.LENGTH_SHORT).show();
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

                    Intent tothird = new Intent(SecondActivity.this, ThirdActivity.class);
                    startActivity(tothird);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        });

        mp1 = MediaPlayer.create(this,R.raw.wrong);
        mp2 = MediaPlayer.create(this,R.raw.correct);


        Log.d("LOG_second","Create");
    }



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
