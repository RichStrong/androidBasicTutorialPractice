package com.wesine.www.edittextlistener;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class DelEditText extends EditText {
    private Drawable imgClear;
    private Context mContext;

    public DelEditText(Context context, AttributeSet attrs){
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init(){
        imgClear = mContext.getResources().getDrawable(R.drawable.delete_gray);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();

            }
        });
    }

    private void setDrawable(){
        if (length()<1)
            setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        else
            setCompoundDrawablesWithIntrinsicBounds(null,null,imgClear,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (imgClear != null && event.getAction() == MotionEvent.ACTION_UP){
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 100;
            if (rect.contains(eventX,eventY))
                setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void  finalize() throws Throwable{
        super.finalize();
    }
}
