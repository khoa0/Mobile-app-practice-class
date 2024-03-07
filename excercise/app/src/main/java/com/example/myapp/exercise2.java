package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class exercise2 extends AppCompatActivity {

    Button callbtn, exercise_3btn;
    EditText phonenum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise2);

        callbtn = findViewById(R.id.callbtn);
        phonenum = findViewById(R.id.phonenum);
        exercise_3btn = findViewById(R.id.exercise_3btn);

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phonenum.getText().toString();
                make_call(phone);
            }
            
        });
        exercise_3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextExercise();
            }
        });
    }
    protected void make_call(String phone){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phonenum.getText()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void goNextExercise() {
        Intent intent = new Intent(this , exercise3.class);
        startActivity(intent);
    }
}