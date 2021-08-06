package com.example.StartQuest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_aq_version009.R;



public class TheFirstTask extends AppCompatActivity implements View.OnClickListener {

    private TextView _tvTxtQuest, _time;
    private EditText _answerQuest;
    private Button _takePrompt;
    private Button _enterAnswerQuest;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 900000; //15 mins

    private static double point;
    private int mins, seconds;
    public static double getPoint() {
        return point;
    }

    public static void setPoint(double point) {
        TheFirstTask.point = point;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_first_task_activity);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        |View.SYSTEM_UI_FLAG_FULLSCREEN);

        _tvTxtQuest = (TextView) findViewById(R.id._txtQuest);
        _time = (TextView) findViewById(R.id.time);
        _answerQuest = (EditText) findViewById(R.id._answerQuest);

        _takePrompt = (Button) findViewById(R.id._btPrompt);
        _takePrompt.setOnClickListener(this);

        _enterAnswerQuest = (Button) findViewById(R.id._btEnterAnswer);
        _enterAnswerQuest.setOnClickListener(this);

        startTimer();
    }

    public TextView get_tvTxtQuest() {
        return _tvTxtQuest;
    }

    public void onClick(View view){

        String _answerForQuest = _answerQuest.getText().toString().trim();

        switch (view.getId()){

            case R.id._btEnterAnswer:

                if(TextUtils.isEmpty(_answerForQuest)){
                    Toast.makeText(getApplicationContext(), "Поле для вводу пусте", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!_answerForQuest.equalsIgnoreCase("Мері")){
                    Toast.makeText(getApplicationContext(), "Відповідь неправиль(", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Начисляємо бали відповідно до часу проходження завдання

                point = 10 - (900 - (mins * 60 + seconds) ) * 0.1;
                setPoint(point);

                Intent intent = new Intent(TheFirstTask.this, FirstCoordinate.class);
                startActivity(intent);
                break;

            case R.id._btPrompt:

                AlertDialog.Builder builder = new AlertDialog.Builder(TheFirstTask.this);
                builder.setMessage("Ви точно хочете використати підказку?");

                builder.setPositiveButton("Так", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        point = getPoint();
                        point -= 3;
                        setPoint(point);
                        Toast.makeText(getApplicationContext(), "Імя починається на букву -М- ", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Ні", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void updateTimer(){
        mins = (int)timeLeftInMilliseconds/60000;
        seconds = (int)timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + mins;
        timeLeftText += ":";
        if(seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        _time.setText(timeLeftText);
        if(mins == 0 && seconds == 0){
            point = 0;
            setPoint(point);

            Intent intent = new Intent(TheFirstTask.this, FirstCoordinate.class);
            startActivity(intent);
        }

    }
}
