package com.example.project_aq_version009;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ListView.ListWithQuest;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static EditText login;
    private static EditText pass;
    boolean a = true;

    private static final Pattern rfc2822 = Pattern.compile(
            "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        |View.SYSTEM_UI_FLAG_FULLSCREEN);

        login = (EditText) findViewById(R.id.mail_log);
        pass = (EditText) findViewById(R.id.pass_log);
    }


    public void OnClickGoRegistration(View v){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(intent);
        //Анімація переходу між активностями
    }


    public void OnClickGoPasswordRecovery(View v){
        Intent intent = new Intent(LoginActivity.this, PasswordRecoveryActivity.class);
        LoginActivity.this.startActivity(intent);
        //Анімація переходу між активностями
    }


    public void OnClickGoMenuActivity(View view) {

        String email = login.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (TextUtils.isEmpty(email) || !rfc2822.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }


        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("contactDb", MODE_PRIVATE, null);
        Cursor query = db.rawQuery("SELECT * FROM contacts;", null);

        while(query.moveToNext()){

            String login = query.getString(2);
            String pass = query.getString(3);


            if(email.equals(login) && password.equals(pass)){
                //Toast.makeText(getApplicationContext(), "Goooooooooddddd!", Toast.LENGTH_SHORT).show();
                a = true;
                Intent intent = new Intent(LoginActivity.this, ListWithQuest.class);
                LoginActivity.this.startActivity(intent);
                break;
            }else {
                a = false;
            }

        }
        if (!a){
            Toast.makeText(getApplicationContext(), "Неправильно введений логін або пароль", Toast.LENGTH_SHORT).show();
            return;
        }


    }
}