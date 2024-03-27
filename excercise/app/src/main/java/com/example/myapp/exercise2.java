package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class exercise2 extends AppCompatActivity {

    Button exercise_3btn;
    private RadioGroup radioGroup;
    private RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex2);

            radioGroup = findViewById(R.id.radioGroup);
            layout = findViewById(R.id.layout);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    //RadioButton selectedRadioButton = findViewById(checkedId);
                    changeBackgroundColor(checkedId);
                }
            });

        exercise_3btn = findViewById(R.id.exercise_3btn);

        exercise_3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextExercise();
            }
        });


    }

    private void changeBackgroundColor(int id) {
        switch (id) {
            case R.id.redRadioButton:
                layout.setBackgroundColor(Color.RED);
                break;
            case R.id.greenRadioButton:
                layout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.blueRadioButton:
                layout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.grayRadioButton:
                layout.setBackgroundColor(Color.GRAY);
                break;
            default:
                layout.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    protected void goNextExercise() {
        Intent intent = new Intent(this , exercise3.class);
        startActivity(intent);
    }
}