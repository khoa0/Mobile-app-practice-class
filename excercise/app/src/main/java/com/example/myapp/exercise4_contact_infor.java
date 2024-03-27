package com.example.myapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class exercise4_contact_infor extends AppCompatActivity {

    private TextView nameTextView, emailTextView, projectTextView;

    Button exercise_5btn, finishButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex4_contact_infor);

        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        projectTextView = findViewById(R.id.projectTextView);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String project = getIntent().getStringExtra("project");

        nameTextView.setText(name);
        emailTextView.setText(email);
        projectTextView.setText(project);

        exercise_5btn = findViewById(R.id.exercise_5btn);
        exercise_5btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(exercise5.class);;
            }
        });

        finishButton = findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(exercise4_main.class);
            }
        });
    }

    protected void goToActivity(Class<?> nextExerciseClass) {
        Intent intent = new Intent(this, nextExerciseClass);
        startActivity(intent);
    }

}