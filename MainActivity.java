package com.example.teamteach06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void runOdd(View view){
        Thread numberOdd = new Thread(new RunnableOdd(getApplicationContext()));
        numberOdd.start();
    }

    public void runEven(View view){
        Thread numberEven = new Thread(new RunnableEven(getApplicationContext()));
        numberEven.start();
    }
}
