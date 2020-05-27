package com.example.teamteach06;

import android.content.Context;
import android.widget.Toast;

public class RunnableOdd implements Runnable{
    Context c1;
    RunnableOdd(Context c){
        c1 = c;
    }


    @Override
    public void run() {
        for(int i = 1;i <= 100; i+=2){
            System.out.println(i);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Toast toastEven = Toast.makeText(c1, "Even Count Finished", Toast.LENGTH_SHORT);
    }
}
