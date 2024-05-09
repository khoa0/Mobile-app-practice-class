package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class exercise1 extends AppCompatActivity {

    Button exercise_2btn, read_data_btn ;
    EditText text_file;

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
        text_file = findViewById(R.id.text_file);

        read_data_btn = findViewById(R.id.read_data_btn);

        read_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data;
                InputStream in = getResources().openRawResource(R.raw.myfile);
                InputStreamReader inreader = new InputStreamReader(in);
                BufferedReader bufreader = new BufferedReader(inreader);
                StringBuilder builder = new StringBuilder();
                if (in != null) {
                    try {
                        while ((data = bufreader.readLine()) != null) {
                            builder.append(data);
                            builder.append("\n");
                        }
                        in.close();
                        text_file.setText(builder.toString());
                    } catch (IOException ex) {
                        Log.e("ERROR", ex.getMessage());
                    }
                }
            }
        });
    }

    protected void goNextExercise() {
        Intent intent = new Intent(this , exercise2.class);
        startActivity(intent);
    }
}