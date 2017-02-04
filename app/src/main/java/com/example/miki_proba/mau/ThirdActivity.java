package com.example.miki_proba.mau;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ThirdActivity";
    private TextView name2;
    private String name;
    private ImageView img;
    private Boolean exit = false;
    private View button_options;
    private Button button1, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("name","");
        name2 = (TextView) findViewById(R.id.name2);
        name2.setText(name);



        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this,Game.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ThirdActivity.this, R.style.FullHeightDialog);
                dialog.setContentView(R.layout.credits);
                dialog.show();
            }
        });

        button_options = findViewById(R.id.button_option);
        button_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick");
                Intent option = new Intent(ThirdActivity.this, Options.class);
                startActivity(option);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        img = (ImageView)findViewById(R.id.fb);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Uri uri = Uri.parse("http://www.facebook.com/MathAroundUs");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Log.d("LOG_third","Create");
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
