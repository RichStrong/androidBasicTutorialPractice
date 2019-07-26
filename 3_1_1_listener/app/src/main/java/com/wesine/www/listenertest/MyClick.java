package com.wesine.www.listenertest;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class MyClick implements View.OnClickListener {
    private Context mContext;

    public MyClick(Context context)
    {
        mContext = context;
    }

    @Override
    public void onClick(View view)
    {
        Toast.makeText(mContext, R.string.toastMsg3, Toast.LENGTH_SHORT).show();
    }
}
