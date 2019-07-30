package com.wesine.www.hidepassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edit_pswd;
    private Button btnChange;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_pswd = findViewById(R.id.edit_pswd);
        btnChange = findViewById(R.id.btnChange);
        edit_pswd.setHorizontallyScrolling(true);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == true){
                    edit_pswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                    btnChange.setText("密码不可见");
                }else {
                    edit_pswd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                    btnChange.setText("密码可见");
                }
            }
        });
    }
}
