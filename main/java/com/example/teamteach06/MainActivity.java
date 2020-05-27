package com.example.teamteach06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    // Change this boolean to true, and the app will run perfectly with just this .java file.
    // In order to do the stretch challenge, I made another class that does the exact same thing,
    // but using a reference to the MainActivity.
    boolean useThisFile = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void runOdd(View view){
        if (useThisFile) {
            // Use the function defined in this file
            count(1, "Odd");
        } else {
            // This class was created to be able to use a WeakReference<> for the stretch challenge
            new countToHundred(this, 1, "Odd").run();
        }
    }

    public void runEven(View view){
        if (useThisFile) {
            // Use the function defined in this file
            count(2, "Even");
        } else {
            // This class was created to be able to use a WeakReference<> for the stretch challenge
            new countToHundred(this, 2, "Even").run();
        }
    }




    // When we asked Brother Barney to help figure out how to make the toast, he helped us see
    // how we could restructure the code so that we
    // 1. Didn't need other classes, and
    // 2. Didn't need to copy code for each even and odd run (since we were just reusing code)
    // I decided to leave it in here (even though the stretch challenge needed another class) to
    // show a cleaner/simpler way to accomplish the same thing.

    // This is the function that actually defines, makes, and runs the new thread
    private void count(final int startingPoint, final String numberType) {
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

                // Create a new one-time Runnable (that only makes a
                // toast popup) to send to the UI Thread
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                numberType + " Count Finished", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        });
        countToHundred.start();
    }
}
