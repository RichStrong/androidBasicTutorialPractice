package com.wesine.www.multitouch;

import android.graphics.PointF;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView mImageView;

    private Matrix mMatrix = new Matrix();
    private Matrix mSavedMatrix = new Matrix();

    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;

    private PointF startPoint = new PointF();
    private PointF midPoint = new PointF();
    private float oriDis = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.img);
        mImageView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event){
        //Log.i("呵呵", "onTouch");
        ImageView imageView = (ImageView)view;

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mMatrix.set(imageView.getImageMatrix());
                mSavedMatrix.set(mMatrix);
                startPoint.set(event.getX(),event.getY());
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oriDis = distance(event);
                if (oriDis > 10f){
                    mSavedMatrix.set(mMatrix);
                    midPoint = middle(event);
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG){
                    mMatrix.set(mSavedMatrix);
                    mMatrix.postTranslate(event.getX() - startPoint.x, event.getY() - startPoint.y);
                } else if (mode == ZOOM){
                    float newDist = distance(event);
                    if (newDist > 10f){
                        mMatrix.set(mSavedMatrix);
                        float scale = newDist/oriDis;
                        mMatrix.postScale(scale, scale, midPoint.x, midPoint.y);
                    }
                }
                break;
        }
        imageView.setImageMatrix(mMatrix);
        return true;
    }

    private float distance(MotionEvent event){
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x*x + y*y);
    }

    private PointF middle(MotionEvent event){
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return new PointF(x/2, y/2);
    }
}
