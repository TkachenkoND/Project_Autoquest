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

public class EighthCoordinate extends Activity {

    TextView _tvPrompt;
    ImageView gotomap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eighth_coordinate_activity);
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
                Uri.parse("https://www.google.com/maps/place/Velyka+Kyshenya/@49.4340964,32.0859675,18.75z/data=!4m5!3m4!1s0x40d14b0b57aeed47:0x17972c86fc435d20!8m2!3d49.4340397!4d32.0870074"));
        startActivity(browserIntent);
    }

    public void position() {
        if (MyLocationListener.checkPosition(MyLocationListener.imHere, Coordinate.latitude[7],Coordinate.longitude[7])) {
            Intent intent = new Intent(EighthCoordinate.this, TheNinthTask.class);
            EighthCoordinate.this.startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(EighthCoordinate.this);
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
