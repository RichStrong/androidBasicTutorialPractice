package com.wesine.www.asynctasktest;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyAsyncTask extends AsyncTask<Integer,Integer,String> {

    private TextView txt;
    private ProgressBar pgBar;

    public MyAsyncTask(TextView textView, ProgressBar progressBar){
        super();
        txt = textView;
        pgBar = progressBar;
    }

    @Override
    protected String doInBackground(Integer... params){
        DelayOperator dop = new DelayOperator();
        int i,end=params[0];
        for (i=10; i<=end; i+=10){
            dop.delay();
            publishProgress(i*100/end);
        }
        return i-10+"";
    }

    @Override
    protected void onPreExecute(){
        txt.setText("正在下载中");
        pgBar.setProgress(0);
    }

    @Override
    protected void onPostExecute(String result){
        txt.setText("下载完成："+result+"%");
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        int value = values[0];
        pgBar.setProgress(value);
    }
}
