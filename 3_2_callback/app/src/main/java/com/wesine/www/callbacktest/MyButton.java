package com.wesine.www.callbacktest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Button;

public class MyButton extends Button {
    private static String TAG = "呵呵";

    public MyButton(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        super.onKeyDown(keyCode,event);
        Log.i(TAG, "onKeyDown方法被调用");
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){
        super.onKeyUp(keyCode, event);
        Log.i(TAG, "onKeyUp方法被调用");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
        Log.i(TAG, "onTouchEvent方法被调用");
        return true;
    }
}
