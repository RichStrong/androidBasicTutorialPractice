package com.wesine.www.asynctasktest;

public class DelayOperator {
    public void delay(){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
