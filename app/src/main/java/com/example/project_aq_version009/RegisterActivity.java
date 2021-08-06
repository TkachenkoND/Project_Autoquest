package com.example.project_aq_version009;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private static EditText name_t;
    private static EditText mail;
    private static EditText pass;
    private Button btn_singUp;
    DBHelper dbHelper;

    public static void setCode(int code) {
        RegisterActivity.code = code;
    }

    public static int code = 0;

    public static int getCode() {
        return code;
    }


    private static final Pattern rfc2822 = Pattern.compile(
            "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        |View.SYSTEM_UI_FLAG_FULLSCREEN);


        name_t = (EditText) findViewById(R.id.name_team);
        mail = (EditText) findViewById(R.id.mail);
        pass = (EditText) findViewById(R.id.password);
        btn_singUp = (Button) findViewById(R.id.btn_singUp);
        btn_singUp.setOnClickListener(this);

        dbHelper = new DBHelper(this);

    }


    @Override
    public void onClick(View view) {

        String name_team = name_t.getText().toString().trim();
        String email = mail.getText().toString().trim();
        String password = pass.getText().toString().trim();


        switch (view.getId()) {

            case R.id.btn_singUp:
            if (TextUtils.isEmpty(name_team)) {
                Toast.makeText(getApplicationContext(), "Enter name your team !", Toast.LENGTH_SHORT).show();
                return;
            }

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
            //Генерація кода підтвердження
                Random random=new Random();
                int rage=9999;
                code =1000+random.nextInt(rage-1000);

                setCode(code);
                VerificationActivity.setName_team(name_team);
                VerificationActivity.setMail(email);
                VerificationActivity.setPassword(password);

                //Надсилання кода для підтвердження
                try {
                    GMailSender sender = new GMailSender("autoquestche@gmail.com", "qmpftrt1337");
                    sender.sendMail("Код для підтвердження реєстрації",
                            "Введіть даний код в відповідне поле : " + String.valueOf(code) ,
                            "autoquestche@gmail.com",
                            email);
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }

                Intent intent = new Intent(RegisterActivity.this, VerificationActivity.class);
                RegisterActivity.this.startActivity(intent);

            break;

        }
    }

    public void OnClickGoLogin(View v){
        // Перехід до активності входу
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(intent);
        //Анімація переходу між активностями

    }
   /* */


}
