package com.example.boxingtime;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Get TextViews From the view
    private TextView TimeTextView;
    private TextView SparringTimeTextView;
    private TextView BreakTimeTextView;
    //Get Buttons From the view
    private Button StartButton;
    private Button SparringUpButton;
    private Button SparringDownButton;
    private Button BreakUpButton;
    private Button BreakDownButton;

    private CountDownTimer countDownTimer;
    private  boolean isRunning = true;
    private  int round =  180000;
    private  int breakTime =  60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get TextViews From the view
        TimeTextView = findViewById(R.id.TimeTextView);
        SparringTimeTextView = findViewById(R.id.SparringTimeTextView);
        BreakTimeTextView = findViewById(R.id.BreakTimeTextView);

        StartButton = findViewById(R.id.StartButton);
        SparringUpButton = findViewById(R.id.SparringUpButton);
        SparringDownButton = findViewById(R.id.SparringDownButton);
        BreakUpButton = findViewById(R.id.BreakUpButton);
        BreakDownButton = findViewById(R.id.BreakDownButton);

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTimer();
            }
        });

        SparringUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSparringTime();

            }
        });

        SparringDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LessSparringTime();
            }
        });

        BreakUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBreakTime();
            }
        });

        BreakDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessBreakTime();
            }
        });
    }

    private void AddSparringTime() {
        round += 10000;
        int minutes = (int) round / 60000;
        int seconds = (int) round % 60000 / 1000;
        String timeLeft = ""+minutes+":"+seconds;
        if (seconds < 10 ){
            timeLeft = timeLeft+"0";
        }
        SparringTimeTextView.setText(timeLeft);
    }
    private void LessSparringTime(){
        if (round != 0) {
            round -= 10000;
            int minutes = (int) round / 60000;
            int seconds = (int) round % 60000 / 1000;
            String timeLeft = "" + minutes + ":" + seconds;
            if (seconds < 10) {
                timeLeft = timeLeft + "0";
            }
            SparringTimeTextView.setText(timeLeft);
        }

    }

    private void lessBreakTime() {
        if (breakTime != 0) {
            breakTime -= 10000;
            int minutes = (int) breakTime / 60000;
            int seconds = (int) breakTime % 60000 / 1000;
            String timeLeft = "" + minutes + ":" + seconds;
            if (seconds < 10) {
                timeLeft = timeLeft + "0";
            }
            BreakTimeTextView.setText(timeLeft);
        }
    }

    private void AddBreakTime() {
        breakTime += 10000;
        int minutes = (int) breakTime / 60000;
        int seconds = (int) breakTime % 60000 / 1000;
        String timeLeft = ""+minutes+":"+seconds;
        if (seconds < 10 ){
            timeLeft = timeLeft+"0";
        }
        BreakTimeTextView.setText(timeLeft);
    }

    private void StartTimer() {
        if(isRunning){
            ContDownTimerRoundNow(round);
        }else{
            StopTimer();
        }
    }

    private void ContDownTimerRoundNow(final int time) {
        StartButton.setText("Stop");
        isRunning = false;
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //TimeTextView.setText("This is in milliseconds"+millisUntilFinished);
                updateTimeTextView (millisUntilFinished);
            }
            @Override
            public void onFinish() {
                TimeTextView.setText("0");
                isRunning = true;
                StartButton.setText("Start");
                if(time == round && round != breakTime){
                    ContDownTimerRoundNow(breakTime);
                }else{
                    ContDownTimerRoundNow(round);
                }
            }
        }.start();
    }

    private void StopTimer() {
        countDownTimer.cancel();
        isRunning = true;
        StartButton.setText("Start");
    }

    public void updateTimeTextView (long time){
        int minutes = (int) time / 60000;
        int seconds = (int) time % 60000 / 1000;

        String timeLeft = ""+minutes+":"+seconds;
        if (seconds < 10 ){
            timeLeft = ""+minutes+":0"+seconds;
        }
        TimeTextView.setText(timeLeft);
    }
}
