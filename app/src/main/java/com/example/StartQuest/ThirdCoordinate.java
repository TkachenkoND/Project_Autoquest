package com.example.StartQuest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.GPS.MyLocationListener;
import com.example.project_aq_version009.Coordinate;
import com.example.project_aq_version009.R;

import java.util.Date;

public class ThirdCoordinate extends Activity {

    TextView _tvPrompt;
    ImageView gotomap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_coordinate_activity);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        |View.SYSTEM_UI_FLAG_FULLSCREEN);

        _tvPrompt = (TextView) findViewById(R.id.firstPrompt);
        gotomap = (ImageView) findViewById(R.id.gotomap);

        MyLocationListener.SetUpLocationListener(this);
        position();
    }

    public TextView get_tvPrompt() {
        return _tvPrompt;
    }

    public void GoToMaP(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/place/49°26'28.7\"N+32°03'57.6\"E/@49.4413,32.063806,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d49.4413!4d32.066"));
        startActivity(browserIntent);
    }

    public void position() {
        if (MyLocationListener.checkPosition(MyLocationListener.imHere, Coordinate.latitude[2],Coordinate.longitude[2])) {
            Intent intent = new Intent(ThirdCoordinate.this, TheFourthTask.class);
            ThirdCoordinate.this.startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(ThirdCoordinate.this);
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
