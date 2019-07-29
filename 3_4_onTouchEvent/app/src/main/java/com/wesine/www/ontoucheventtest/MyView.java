package com.wesine.www.ontoucheventtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    public float X = 50;
    public float Y = 50;

    Paint mPaint = new Paint();

    public MyView(Context context, AttributeSet set)
    {
        super(context,set);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(X, Y,30, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.X = event.getX();
        this.Y = event.getY();
        this.invalidate();
        return true;
    }
}
