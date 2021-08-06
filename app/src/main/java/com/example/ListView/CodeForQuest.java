package com.example.ListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.StartQuest.TheFirstTask;
import com.example.project_aq_version009.R;

public class CodeForQuest extends Activity implements View.OnClickListener {

    TextView _tvTxt;
    EditText _enterCodeForQst;
    Button _btnOkForCodeQst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_for_quest);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        |View.SYSTEM_UI_FLAG_FULLSCREEN);

        _tvTxt = (TextView) findViewById(R.id._tvTxt);
        _enterCodeForQst = (EditText) findViewById(R.id._enterCodeForQst);
        _btnOkForCodeQst = (Button) findViewById(R.id._btnOkForCodeQst);
        _btnOkForCodeQst.setOnClickListener(this);

    }

    public TextView get_tvTxt() {
        return _tvTxt;
    }

    // нобрабатываем кнопку для перехода на следующую активность и проверяем поле на вместимость
    public void onClick(View view){

        String _enterCodeForQuest = _enterCodeForQst.getText().toString().trim();

        switch (view.getId()){
            case R.id._btnOkForCodeQst:
                if(TextUtils.isEmpty(_enterCodeForQuest)){
                    Toast.makeText(getApplicationContext(), "Поле пусте, введіть значення для продовження", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!_enterCodeForQuest.equalsIgnoreCase("a777")){
                    Toast.makeText(getApplicationContext(), "Неправильно введкний код, спробуйте ще раз", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(CodeForQuest.this, TheFirstTask.class);
                startActivity(intent);
                break;
        }
    }
}