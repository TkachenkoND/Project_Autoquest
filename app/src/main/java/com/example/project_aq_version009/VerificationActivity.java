package com.example.project_aq_version009;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class VerificationActivity extends AppCompatActivity {

    DBHelper dbHelper;
    private EditText code_;

    static String name_team, mail, password;
    static int code;

    public static void setCode(int code) {
        VerificationActivity.code = code;
    }

    public static int getCode() {
        return code;
    }

    public static void setName_team(String name_team) {
        VerificationActivity.name_team = name_team;
    }

    public static void setMail(String mail) {
        VerificationActivity.mail = mail;
    }

    public static void setPassword(String password) {
        VerificationActivity.password = password;
    }

    public static String getName_team() {
        return name_team;
    }

    public static String getMail() {
        return mail;
    }

    public static String getPassword() {
        return password;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_code);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        |View.SYSTEM_UI_FLAG_FULLSCREEN);

        code_ = (EditText) findViewById(R.id.code_verifi);

        dbHelper = new DBHelper(this);
    }

    // Обробка натиску на кнопку "Підтвердити"
    public void OnClickConfirm(View v){
        String _code_ = code_.getText().toString().trim();

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (_code_.equals(String.valueOf(RegisterActivity.getCode())) || _code_.equals(String.valueOf(getCode()))){

            //Додаємо куристувача до БД

            contentValues.put(DBHelper.KEY_NAME_TEAM, getName_team());
            contentValues.put(DBHelper.KEY_MAIL, getMail());
            contentValues.put(DBHelper.KEY_PASSWORD, getPassword());
            database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);

            Toast.makeText(getApplicationContext(), "Реєстрація пройшла успішно !!!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(VerificationActivity.this, LoginActivity.class);
            VerificationActivity.this.startActivity(intent);
        }else
        Toast.makeText(getApplicationContext(), "Неправильно введений код !", Toast.LENGTH_SHORT).show();
    }

    // Обробка натиску на кнопку "Відправити повторно"
    public void OnClickSendMaillRepeatedly (View v){

        Random random=new Random();
        int rage=9999;
        setCode(1000+random.nextInt(rage-1000));

        //Надсилання кода для підтвердження
        try {
            GMailSender sender = new GMailSender("autoquestche@gmail.com", "qmpftrt1337");
            sender.sendMail("Код для підтвердження реєстрації, повторний",
                    "Введіть даний код в відповідне поле : " + String.valueOf(getCode()) ,
                    "autoquestche@gmail.com",
                    getMail());
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }

        Toast.makeText(getApplicationContext(), "Код надіслано повторно", Toast.LENGTH_SHORT).show();
    }

    // Обробка натиску на кнопку "Повернутись до реєстрації"
    public void OnClickGoRegistration(View view) {
        Intent intent = new Intent(VerificationActivity.this, RegisterActivity.class);
        VerificationActivity.this.startActivity(intent);
    }
}
