package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class exercise4_main extends AppCompatActivity {

    private EditText nameEditText, emailEditText, projectEditText;

    Button  exercise_5btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex4_main);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        projectEditText = findViewById(R.id.projectEditText);

        Button viewContactButton = findViewById(R.id.viewContactButton);
        viewContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String project = projectEditText.getText().toString();

                Intent intent = new Intent(exercise4_main.this, exercise4_contact_infor.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("project", project);
                startActivity(intent);
            }
        });

        exercise_5btn = findViewById(R.id.exercise_5btn);

        exercise_5btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextExercise();
            }
        });

    }

    protected void goNextExercise() {
        Intent intent = new Intent(this , exercise5.class);
        startActivity(intent);
    }

}