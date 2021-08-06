package com.example.project_aq_version009;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class PasswordRecoveryActivity extends Activity {

    private static final Pattern rfc2822 = Pattern.compile(
            "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
    );
    private static EditText maill;
    boolean a = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        |View.SYSTEM_UI_FLAG_FULLSCREEN);

        maill = (EditText) findViewById(R.id.maill);


    }

    public void OnClickGoRegistration(View view) {
        // Перехід до попередньої активності
        Intent intent = new Intent(PasswordRecoveryActivity.this, LoginActivity.class);
        PasswordRecoveryActivity.this.startActivity(intent);
    }


    public void OnClickSendMaill(View view) {

        String email = maill.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!rfc2822.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(), "Enter the mail name correctly!", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("contactDb", MODE_PRIVATE, null);
        Cursor query = db.rawQuery("SELECT * FROM contacts;", null);

        while(query.moveToNext()){

            String login = query.getString(2);
            String pass = query.getString(3);
            if(email.equals(login)){
                //Toast.makeText(getApplicationContext(), "Goooooooooddddd!", Toast.LENGTH_SHORT).show();
                try {
                    GMailSender sender = new GMailSender("autoquestche@gmail.com", "qmpftrt1337");
                    sender.sendMail("Відновлення пароля",
                            "Пароль від вашого аккаунта : " + pass,
                            "autoquestche@gmail.com",
                            email);
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }

                Toast.makeText(getApplicationContext(), "На вказану пошту відправлено пароль", Toast.LENGTH_SHORT).show();
                a = true;
                break;
            }else {
                a = false;
            }
        }
        if (!a){
            Toast.makeText(getApplicationContext(), "Введена пошта не належить ні одному з користувачів", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Зареєструйтесь або спробуйте ще раз", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
