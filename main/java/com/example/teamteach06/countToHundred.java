package com.example.teamteach06;

import android.app.Activity;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class countToHundred {
    private WeakReference<Activity> uiThread;
    private int startingPoint;
    private String numberType;

    // For the stretch challenge, we need a WeakReference to an Activity. So we set
    // that reference in the constructor, along with info for even or odd stuff
    countToHundred(Activity uiThread, int startingPoint, String numberType) {
        this.uiThread = new WeakReference<Activity>(uiThread);
        this.startingPoint = startingPoint;
        this.numberType = numberType;
    }

    // This is the function that actually defines, makes, and runs the new thread
    public void run() {
        if (uiThread.get() != null) {
            // The thread we are making, using a one-time Runnable
            Thread countToHundred = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = startingPoint; i <= 100; i+=2){
                        System.out.println(i);
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Create a new Runnable (that only makes a toast popup) to send to the UI Thread
                    uiThread.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(uiThread.get().getApplicationContext(),
                                    numberType + " Count Finished", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                }
            });
            countToHundred.start();
        }
    }
}
