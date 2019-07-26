package com.wesine.www.listenertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnTest1, btnTest2, btnTest3, btnTest4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTest1 = findViewById(R.id.btnTest);
        btnTest2 = findViewById(R.id.btnTest2);
        btnTest3 = findViewById(R.id.btnTest3);
        btnTest4 = findViewById(R.id.btnTest4);

        btnTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), R.string.toastMsg1, Toast.LENGTH_SHORT).show();
            }
        });

        btnTest2.setOnClickListener(new BtnClickListener());

        btnTest3.setOnClickListener(new MyClick(getApplicationContext()));

        btnTest4.setOnClickListener(this);

    }

    class BtnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            Toast.makeText(getApplicationContext(), R.string.toastMsg2, Toast.LENGTH_SHORT).show();
        }
    }

    public void myClick(View view)
    {
        Toast.makeText(getApplicationContext(), R.string.toastMsg5, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view)
    {
        Toast.makeText(getApplicationContext(), R.string.toastMsg4, Toast.LENGTH_SHORT).show();
    }
}
