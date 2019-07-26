package com.wesine.www.handlertest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int imgIds[] = new int[]{
            R.drawable.s1, R.drawable.s2, R.drawable.s3,
            R.drawable.s4, R.drawable.s5
    };

    int imgStart = 0;
    ImageView imgChange;

    final Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if (msg.what == 0x123){
                imgChange.setImageResource(imgIds[imgStart++%5]);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgChange = findViewById(R.id.imgchange);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(0x123);
            }
        },0,200);
    }


}
