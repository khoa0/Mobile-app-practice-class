package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class exercise2 extends Activity implements View.OnClickListener {

    Button exercise_3btn, read_data_btn, write_data_btn ;
    EditText text_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise2);
        exercise_3btn = findViewById(R.id.exercise_3btn);

        exercise_3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextExercise();
            }
        });
        text_file = findViewById(R.id.text_file);
        read_data_btn = findViewById(R.id.read_data_btn);
        write_data_btn = findViewById(R.id.write_data_btn);

        read_data_btn.setOnClickListener(this);
        write_data_btn.setOnClickListener(this);
    }
    protected void goNextExercise() {
        Intent intent = new Intent(this , exercise3.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.read_data_btn) {
            readData();
        }
        else if (view.getId() == R.id.write_data_btn) {
            writeData();
        }
    }

    public void readData() {
        try {
            FileInputStream in = openFileInput("myfile.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String data = "";
            StringBuilder builder = new StringBuilder();
            while ((data = reader.readLine()) != null) {
                builder.append(data);
                builder.append("\n");
            }
            in.close();
            text_file.setText(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeData() {
        try {
            FileOutputStream out = openFileOutput("myfile.txt", 0);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(text_file.getText().toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}