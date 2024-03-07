package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class exercise1 extends AppCompatActivity {

    Button exercise_2btn ;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise1);
        exercise_2btn = findViewById(R.id.exercise_2btn);

        exercise_2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextExercise();
            }
        });
    }

    protected void goNextExercise() {
        Intent intent = new Intent(this , exercise2.class);
        startActivity(intent);
    }
}