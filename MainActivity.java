package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    boolean buttonIsPressed = false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void buttonPressed(View view) {


        if (buttonIsPressed){

            textView.setText("0:30");
            seekBar.setProgress(30);
            seekBar.setEnabled(true);
            countDownTimer.cancel();
            goButton.setText("GO!");
            buttonIsPressed = false;
        }else {

            buttonIsPressed = true;
            seekBar.setEnabled(false);
            goButton.setText("STOP!");
            Log.i("buton Pressed", "Button Pressed");
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {


                @Override
                public void onTick(long millisUntilFinished) {
                    updateTime((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
//                Toast.makeText(makeTextthis,"Times Up").show();show
                }

            }.start();
        }
    }

    public void updateTime(int secondsLeft){
        int minutes = secondsLeft/60;
        int second = secondsLeft - (minutes*60);
//        textView.setText(Integer.toString(minutes) +":"+ Integer.toString(second));

        String seconds = Integer.toString(second);
             if(second <= 9){
                 seconds = "0" +seconds;
             }
        textView.setText(Integer.toString(minutes) +":"+ seconds);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.timeSeekBar);
        textView = findViewById(R.id.timeTextView);
        goButton = findViewById(R.id.buttonPress);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes = progress/60;
                int second = progress-(minutes*60);
            textView.setText(Integer.toString(minutes) +":"+ Integer.toString(second));

                String seconds = Integer.toString(second);
                if(seconds == "0"){
                    seconds = "00";
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






    }
}