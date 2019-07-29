package com.wesine.www.listenerandenvet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imgtouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgtouch = findViewById(R.id.imgtouch);
        imgtouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(getApplicationContext(),"你通过监听器触摸了伦家～",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
