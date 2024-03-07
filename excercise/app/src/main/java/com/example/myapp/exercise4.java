package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class exercise4 extends AppCompatActivity {

    Button exercise_5btn;
    EditText message;
    AlertDialog alert_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise4);
        alert_dialog = new AlertDialog.Builder(this).create();
        message = findViewById(R.id.message);
        exercise_5btn = findViewById(R.id.exercise_5btn);

        message.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    String messageDia = message.getText().toString();
                    alert_dialog.setMessage(messageDia);
                    alert_dialog.show();
                    return true;
                }
                return false;
            }
        });

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