package com.example.miki_proba.mau;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class Options_Activity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;       //Ország nevét lehet beállítani.
    private List<String> list;                  //Lista az országok nevének.
    private ImageView back;                     //Vissza a főmenübe gomb.
    //private Spinner spinner;                    //Spinner = legördülő menü.
    private Boolean exit = false;               //Ez a változó azért kell, mert a vissaz gombbal a programot bzárjuk, ha ez az érték true lesz!
    private Button hack1,hack2,hack3;
    private String elfogadva;
    private EditText nev, pw1, pw2, pw3;        //Név és jelszók
    private String name; //Globális változó név

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option);

        elfogadva = "accepted";

        //spinner = (Spinner) findViewById(R.id.spinner);

        pw1 = (EditText) findViewById(R.id.hacked1_e);
        pw2 = (EditText) findViewById(R.id.hacked2_e);
        pw3 = (EditText) findViewById(R.id.hacked3_e);

        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("name","");

        nev = (EditText) findViewById(R.id.optionname);
        nev.setText(name);

        back = (ImageView) findViewById(R.id.image_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("name", nev.getText().toString());
                editor.commit();

                Intent tothird = new Intent(Options_Activity.this, Menu_Activity.class);
                startActivity(tothird);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        hack1 = (Button) findViewById(R.id.hacked1);
        hack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pw1.getText().toString().equals(getString(R.string.password)))
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("level2", elfogadva);
                    editor.apply();
                    editor.commit();
                    Toast.makeText(Options_Activity.this, "Your level 2 was  unlocked", Toast.LENGTH_SHORT).show();
                }else
                {
                   Toast.makeText(Options_Activity.this, "Your code was wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        hack2 = (Button) findViewById(R.id.hacked2);
        hack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pw2.getText().toString().equals(getString(R.string.password)))
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("level3", elfogadva);
                    editor.apply();
                    editor.commit();
                    Toast.makeText(Options_Activity.this, "Your level 3 was unlocked", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(Options_Activity.this, "Your code was wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        hack3 = (Button) findViewById(R.id.hacked3);
        hack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pw3.getText().toString().equals(getString(R.string.password)))
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("level4", elfogadva);
                    editor.apply();
                    editor.commit();
                    Toast.makeText(Options_Activity.this, "Your level 4 was unlocked", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(Options_Activity.this, "Your code was wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

/*
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
*/

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

        /*Intent refresh = new Intent(this, Menu_Activity.class);
        startActivity(refresh);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        */
    }

}
