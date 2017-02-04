package com.example.miki_proba.mau;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Options extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private List<String> list;
    private ImageView back;
    private Locale myLocale;
    private Spinner spinner;
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option);


        spinner = (Spinner) findViewById(R.id.spinner);
        back = (ImageView) findViewById(R.id.image_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tothird = new Intent(Options.this, ThirdActivity.class);
                startActivity(tothird);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        list = new ArrayList<String>();
        list.add("Languages");
        list.add("Hungary");
        list.add("Portuguese");
        list.add("Italian");
        list.add("Denmark");
        list.add("Poland");
        list.add("Romania");
        list.add("Lithuania");
        list.add("Greece");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch(i){
                    case 1 :
                        setLocale("hu-rHU");
                        break;
                    case 2 :
                        setLocale("pt");
                        break;
                    case 3 :
                        setLocale("it");
                        break;
                    case 4 :
                        setLocale("nl");
                        break;
                    case 5 :
                        setLocale("pl");
                        break;
                    case 6 :
                        setLocale("ro");
                        break;
                    case 7 :
                        setLocale("lt");
                        break;
                    case 8 :
                        setLocale("el");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        Log.d("LOG_Options","Create");
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


    public void setLocale(String lang) {

        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("languages", lang);
        editor.commit();

        /*Intent refresh = new Intent(this, ThirdActivity.class);
        startActivity(refresh);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        */
    }

}
