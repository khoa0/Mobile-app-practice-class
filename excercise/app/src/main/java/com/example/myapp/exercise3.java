package com.example.myapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class exercise3 extends AppCompatActivity {

    Button timebtn,exercise_4btn;
    AlertDialog ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise3);
        timebtn = findViewById(R.id.timebtn);
        exercise_4btn = findViewById(R.id.exercise_4btn);
        ad = new AlertDialog.Builder(this).create();
        timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showtime();
            }
        });

        exercise_4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextExercise();
            }
        });
    }

    protected void showtime(){
        Date t = new Date();
        String message = "Time is :" + t;
        ad.setMessage(message);
        ad.show();
    }

    protected void goNextExercise() {
        Intent intent = new Intent(this , exercise4.class);
        startActivity(intent);
    }

}