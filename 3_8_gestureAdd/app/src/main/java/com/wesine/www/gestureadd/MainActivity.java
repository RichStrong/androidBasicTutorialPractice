package com.wesine.www.gestureadd;

import android.content.Context;
import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "GestureAdd";

    private GestureOverlayView mGestureOverlayView;
    private GestureLibrary mGestureLibrary;
    private Context mContext;
    private Button mButton;
    private final static int AddMode = 0;
    private final static int DistinguishMode = 1;
    private int mode = AddMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        mGestureLibrary = GestureLibraries.fromFile(Environment.getExternalStorageDirectory().getPath()+"/mygestures");
        if (mGestureLibrary.load()) {
            Toast.makeText(mContext, "手势库加载成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "手势库加载失败", Toast.LENGTH_SHORT).show();
        }

        mGestureOverlayView = findViewById(R.id.gesture);
        mGestureOverlayView.setGestureColor(Color.GREEN);
        mGestureOverlayView.setGestureStrokeWidth(5);
        mButton = findViewById(R.id.btnSwitch);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == AddMode){
                    mode = DistinguishMode;
                    mButton.setText("添加手势");
                }else {
                    mode = AddMode;
                    mButton.setText("识别手势");
                }
            }
        });
        mGestureOverlayView.addOnGesturePerformedListener(new DistinguishGesture());
        mGestureOverlayView.addOnGesturePerformedListener(new AddGesture());
    }

    public class AddGesture implements GestureOverlayView.OnGesturePerformedListener{
        @Override
        public void onGesturePerformed(GestureOverlayView overlay, final Gesture gesture) {
            if (mode != AddMode) return;
            View saveDialog = getLayoutInflater().inflate(R.layout.dialog_save, null, false);
            ImageView img_show = saveDialog.findViewById(R.id.img_show);
            final EditText edit_name = saveDialog.findViewById(R.id.edit_name);
            Bitmap bitmap = gesture.toBitmap(128,128,10,0xffff0000);
            img_show.setImageBitmap(bitmap);
            new AlertDialog.Builder(MainActivity.this).setView(saveDialog).setPositiveButton("保存", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    GestureLibrary gestureLibrary = GestureLibraries.fromFile(Environment.getExternalStorageDirectory().getPath()+"/mygestures");
                    mGestureLibrary.addGesture(edit_name.getText().toString(), gesture);
                    mGestureLibrary.save();
                }
            }).setNegativeButton("取消", null).show();
        }
    }

    public class DistinguishGesture implements GestureOverlayView.OnGesturePerformedListener{
        @Override
        public void onGesturePerformed(GestureOverlayView gestureOverlayView, final Gesture gesture) {
            if (mode != DistinguishMode) return;
            //识别用户刚绘制的手势
            ArrayList<Prediction> predictions = mGestureLibrary.recognize(gesture);
            ArrayList<String> result = new ArrayList<String>();
            //遍历所有找到的Prediction对象
            for (Prediction pred : predictions) {
                if (pred.score > 2.0) {
                    result.add("与手势【" + pred.name + "】相似度为" + pred.score);
                }
            }
            if (result.size() > 0) {
                ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(mContext,
                        android.R.layout.simple_dropdown_item_1line, result.toArray());
                new AlertDialog.Builder(mContext).setAdapter(adapter,null).setPositiveButton("确定",null).show();
            }else{
                Toast.makeText(mContext,"无法找到匹配的手势！",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
