package com.wesine.www.handlertest2;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String UPPER_NUM = "upper";
    static final String RESULT = "result";

    EditText mEditText;
    TextView mTextView;
    CalThread calThread;

    // 额外测试用的handler，用于在mTextView上显示结果
    public Handler mainActHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if (msg.what == 0x1){
                mTextView.setText(msg.getData().getIntegerArrayList(RESULT).toString());
            }
        }

    };

    class CalThread extends Thread{
        public Handler mHandler;

        @Override
        public void run()
        {
            Looper.prepare();
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg){
                    if (msg.what == 0x123){
                        int upper = msg.getData().getInt(UPPER_NUM);
                        ArrayList<Integer> nums = new ArrayList<Integer>();
                        outer:
                        for (int i=2; i<=upper; i++){
                            for (int j=2; j<= Math.sqrt(i); j++){
                                if (i != 2 && i%j==0){
                                    continue outer;
                                }
                            }
                            nums.add(i);

                        }
                        Toast.makeText(MainActivity.this, nums.toString(), Toast.LENGTH_SHORT).show();

                        // 向主线程发送计算结果
                        Message msgToMain = new Message();
                        msgToMain.what = 0x1;
                        Bundle bundle = new Bundle();
                        bundle.putIntegerArrayList(RESULT, nums);
                        msgToMain.setData(bundle);
                        mainActHandler.sendMessage(msgToMain);
                    }
                }
            };
            Looper.loop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.etNum);
        mTextView = findViewById(R.id.txtRes);
        calThread = new CalThread();
        calThread.start();
    }

    public boolean cal(View view){
        Message msg = new Message();
        msg.what = 0x123;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM, Integer.parseInt(mEditText.getText().toString()));
        msg.setData(bundle);
        calThread.mHandler.sendMessage(msg);
        return true;
    }
}
