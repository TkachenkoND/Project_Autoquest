package com.example.StartQuest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.GPS.MyLocationListener;
import com.example.project_aq_version009.Coordinate;
import com.example.project_aq_version009.R;

public class SecondCoordinate extends Activity {

    TextView _tvPrompt;
    ImageView gotomap;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_coordinate_activity);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        |View.SYSTEM_UI_FLAG_FULLSCREEN);

        _tvPrompt = (TextView) findViewById(R.id.firstPrompt);
        gotomap = (ImageView) findViewById(R.id.gotomap);

        MyLocationListener.SetUpLocationListener(this);
        position();
    }

    public void GoToMaP(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/place/49°25'33.6\"N+32°02'57.8\"E/@49.426,32.0486243,18z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d49.426!4d32.0494"));
        startActivity(browserIntent);
    }

    public void position() {
        if (MyLocationListener.checkPosition(MyLocationListener.imHere, Coordinate.latitude[1],Coordinate.longitude[1])) {
            Intent intent = new Intent(SecondCoordinate.this, TheThirdTask.class);
            SecondCoordinate.this.startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(SecondCoordinate.this);
            builder.setMessage("Виїхавши за радіус виконання завдання, ви не можете його вирішувати. Поверніться в точку отримання завдання!! \uD83D\uDE0A");
            builder.setTitle("Попередження");

            builder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }


}
