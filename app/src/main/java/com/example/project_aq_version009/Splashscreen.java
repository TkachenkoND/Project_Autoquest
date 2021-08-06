package com.example.project_aq_version009;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;


public class Splashscreen extends Activity {

    private final int SPLASH_DISPLAY_LEHGHT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                 |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                 |View.SYSTEM_UI_FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent mainIntent = new Intent(Splashscreen.this, LoginActivity.class);
                Splashscreen.this.startActivity(mainIntent);
                Splashscreen.this.finish();
                //Анімація переходу між активностями
                overridePendingTransition(R.anim.diagonaltranslate,R.anim.diagonaltranslate_5);
            }
        }, SPLASH_DISPLAY_LEHGHT);
    }




}
