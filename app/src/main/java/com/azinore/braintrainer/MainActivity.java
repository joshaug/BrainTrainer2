package com.azinore.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button but1;
    Button but2;
    Button but3;
    Button but4;
    Button butPlay;
    Button butEasy;
    Button butHard;
    MediaPlayer right;
    MediaPlayer wrong;

    TextView sum;
    TextView timer;
    TextView score;
    boolean clicker = false;
    //int[] guess = new int[100];
    Random random = new Random();
    //ArrayList<Integer> ans = new ArrayList<Integer>();
    int[] ans = new int[4];
    //int x = random.nextInt(3-0);
    int y;
    int dif;
    int dom=0, num=0;
    int corLoc;

    public void playAgain(View view){

        String test = view.getTag().toString();
        dif = Integer.parseInt(test);
        Log.i("tag is",test);
        dom=0;
        num=0;
        score.setText("0/0");
        clicker = true;
        //butPlay = (Button)findViewById(R.id.buttonPlay);
        Log.i("Play again", "Pressed");
        //butPlay.setText("hello");
        fast();




        CountDownTimer countDownTimer = new CountDownTimer(60000+100,1000) {
            @Override
            public void onTick(long l) {
                butPlay.setEnabled(false);
                butEasy.setEnabled(false);
                butHard.setEnabled(false);
                digit((int)l/1000);
                //Log.i("Time left", Integer.toString((int)l/1000));

            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Great Job You Got "+dom+" Right!", Toast.LENGTH_LONG).show();
                timer.setText("1:00");
                //butPlay.setText("Play Again?");
                sum.setText("Well Done!");
                clicker = false;
                butPlay.setEnabled(true);
                butEasy.setEnabled(true);
                butHard.setEnabled(true);


            }
        }.start();
    }

    public void digit(int x){

        String s = x/60 +":"+x%60;
        if((x%60)<10){
            s = x/60 +":0"+x%60;
        }
        //Log.i("as","we");
        timer.setText(s);
    }

    public void fast(){
        sumM();
        Log.i("fast","clicked");
        int g;

        corLoc = random.nextInt(4);
        Log.i("corloc", Integer.toString(corLoc));

        for(int i=0;i<4;i++){
            if(i==corLoc){
                ans[i]=y;
                System.out.println(ans[i]);
            }
            else{
                g = (random.nextInt((dif*2) - 1));

                while(g==y){
                    g = (random.nextInt((dif*2) - 1));
                }


                ans[i]=g;
            }
        }




        /*
        but1.setText(Integer.toString(ans.get(0)));
        but2.setText(Integer.toString(ans.get(1)));
        but3.setText(Integer.toString(ans.get(2)));
        but4.setText(Integer.toString(ans.get(3)));
        */

        but1.setText(Integer.toString(ans[0]));
        but2.setText(Integer.toString(ans[1]));
        but3.setText(Integer.toString(ans[2]));
        but4.setText(Integer.toString(ans[3]));






    }


    public void clickBut(View view){

        if(clicker) {
            if(view.getTag().toString().equals(Integer.toString(corLoc))) {
                dom++;
                right.start();

            }
            else{
                wrong.start();

            }
            num++;
            keepScore();
            fast();
            //wrong.start();

        }

    }

    public void keepScore(){

        score.setText(dom+"/"+num);

    }

    public void sumM(){

        int a = random.nextInt(dif);
        int b = random.nextInt(dif-1);
        y = a+b;

        sum.setText(a+"+"+b+"=");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but1 = (Button)findViewById(R.id.tL);
        but2 = (Button)findViewById(R.id.tR);
        but3 = (Button)findViewById(R.id.lB);
        but4 = (Button)findViewById(R.id.rB);
        sum = (TextView) findViewById(R.id.sum);
        timer = (TextView) findViewById(R.id.timer);
        score = (TextView) findViewById(R.id.score);
        butPlay =(Button) findViewById(R.id.buttonPlay);
        butEasy =(Button) findViewById(R.id.buttonEasy);
        butHard =(Button) findViewById(R.id.buttonHard);
        right = MediaPlayer.create(this,R.raw.correct);
        wrong = MediaPlayer.create(this,R.raw.incorrect);
    }
}
