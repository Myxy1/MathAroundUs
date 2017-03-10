package com.MAU.erasmus.mau;


import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Level04 extends AppCompatActivity {


    private Boolean exit = false;
    private TextView tv;
    private TextView question;
    private TextView topic;
    private Button btnNext;
    private Button btnExit;
    private Button play,pause,stop;
    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3,rb4;

    private ImageView life1;
    private ImageView life2;
    private ImageView life3;
    private ImageView life4;
    private ImageView life5;

    private ImageView kategoria;

    private int topics[]={1,2,3,4,5,6,7,8};
    private String elfogadva;


    private String questions[];
    private String ans[];
    private String opt[];

    private int flag=0;
    private int correct,wrong;
    private int elet=5;
    private int kerdesek = 1;
    private int kategoriak =0;
    private int paused;

    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        elfogadva = "accepted";

        mp = MediaPlayer.create(Level04.this,R.raw.level4);
        mp.start();

        play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp == null)
                {
                    mp = MediaPlayer.create(Level04.this,R.raw.level1);
                    mp.start();
                }else if (!mp.isPlaying()){
                    mp.seekTo(paused);
                    mp.start();
                }
            }
        });

        pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
                paused = mp.getCurrentPosition();
            }
        });

        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.release();
                mp = null;
            }
        });

        questions = getResources().getStringArray(R.array.level2_q);

        ans = getResources().getStringArray(R.array.level2_a);
        opt = getResources().getStringArray(R.array.level2_o);

        tv = (TextView)findViewById(R.id.textView1);
        question = (TextView)findViewById(R.id.textView3);
        btnNext = (Button)findViewById(R.id.button1);
        btnExit = (Button)findViewById(R.id.button2);
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        rb1 = (RadioButton)findViewById(R.id.radio0);
        rb2 = (RadioButton)findViewById(R.id.radio1);
        rb3 = (RadioButton)findViewById(R.id.radio2);
        rb4 = (RadioButton)findViewById(R.id.radio3);

        life1 = (ImageView) findViewById(R.id.Life1);
        life2 = (ImageView) findViewById(R.id.Life2);
        life3 = (ImageView) findViewById(R.id.Life3);
        life4 = (ImageView) findViewById(R.id.Life4);
        life5 = (ImageView) findViewById(R.id.Life5);

        kategoria = (ImageView) findViewById(R.id.kategoria);

        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        question.setText("(" + kerdesek + "/" + questions.length + ")");

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert1 = new AlertDialog.Builder(Level04.this, R.style.AppCompatAlertDialogStyle);
                alert1.setMessage("Do you want to leave game?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                Intent tomenu = new Intent(Level04.this,Menu_Activity.class);
                                startActivity(tomenu);
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                mp.stop();
                            }
                        })
                        .setNegativeButton("No", null)
                        .setIcon(R.drawable.icon1)
                        .create();
                alert1.show();
            }
        });

        topic();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb1.isChecked()==true || rb2.isChecked()==true || rb3.isChecked()==true || rb4.isChecked()==true)
                {
                    topic();
                    Intent towin = new Intent(Level04.this,Win_Activity.class);

                    Intent tolose = new Intent(Level04.this,Lose_Activity.class);
                    RadioButton uans =(RadioButton)findViewById(rg.getCheckedRadioButtonId());
                    String ansText="";
                    ansText = uans.getText().toString();
                    if (ansText.equalsIgnoreCase(ans[flag]))
                    {
                        correct++;
                    }
                    else
                    {
                        wrong++;
                        elet--;
                    }
                    flag++;
                    kerdesek++;
                    switch(elet){
                        case 4:
                            life5.setBackgroundResource(R.drawable.heart1);
                            break;
                        case 3:
                            life5.setBackgroundResource(R.drawable.heart1);
                            life4.setBackgroundResource(R.drawable.heart1);
                            break;
                        case 2:
                            life5.setBackgroundResource(R.drawable.heart1);
                            life4.setBackgroundResource(R.drawable.heart1);
                            life3.setBackgroundResource(R.drawable.heart1);
                            break;
                        case 1:
                            life5.setBackgroundResource(R.drawable.heart1);
                            life4.setBackgroundResource(R.drawable.heart1);
                            life3.setBackgroundResource(R.drawable.heart1);
                            life2.setBackgroundResource(R.drawable.heart1);
                            break;
                        case 0:
                            startActivity(tolose);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            mp.stop();
                            break;
                    }
                    if (flag<questions.length)
                    {
                        tv.setText(questions[flag]);
                        rg.clearCheck();
                        rb1.setText(opt[flag*4]);
                        rb2.setText(opt[(flag*4)+1]);
                        rb3.setText(opt[(flag*4)+2]);
                        rb4.setText(opt[(flag*4)+3]);
                        question.setText("(" + kerdesek + "/" + questions.length + ")");
                    }
                    else
                    {
                        Toast.makeText(Level04.this, "You win this game", Toast.LENGTH_SHORT).show();

                        startActivity(towin);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        mp.stop();
                    }
                }else{
                    Toast.makeText(Level04.this, "Please check answer(s)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Log.d("LOG_game","Start");

    }

    public int topic()
    {

        Random rand = new Random();
        kategoriak = rand.nextInt(topics.length);
        switch (kategoriak)
        {
            case 0 :
                kategoria.setBackgroundResource(R.drawable.denmark_happy);
                break;
            case 1 :
                kategoria.setBackgroundResource(R.drawable.greece_happy);
                break;
            case 2 :
                kategoria.setBackgroundResource(R.drawable.hungary_happy);
                break;
            case 3 :
                kategoria.setBackgroundResource(R.drawable.italy_happy);
                break;
            case 4 :
                kategoria.setBackgroundResource(R.drawable.lithuania_happy_);
                break;
            case 5 :
                kategoria.setBackgroundResource(R.drawable.polonia_happy);
                break;
            case 6 :
                kategoria.setBackgroundResource(R.drawable.portugal_happy);
                break;
            case 7 :
                kategoria.setBackgroundResource(R.drawable.romania_happy);
                break;
        }
        return kategoriak;
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
        Log.d("LOG_game","Start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LOG_game","Pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LOG_game","Resume");
        mp.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LOG_game","Stop");
        mp.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LOG_game", "Destroy");
    }
}
