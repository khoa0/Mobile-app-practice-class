package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class homework2 extends AppCompatActivity {

    Button exercise_2btn ;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework2);
        exercise_2btn = findViewById(R.id.exercise_2btn);

        exercise_2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextExercise();
            }
        });
    }


    public String toJSON(){

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("id", getId());
            jsonObject.put("gender", getGender());
            jsonObject.put("age", getAge());
            jsonObject.put("first_name", getFirstName());
            jsonObject.put("last_name", getLastName());
            jsonObject.put("address", getAddress());

            return jsonObject.toString();
        } catch (JSONException
                e) {
            e.printStackTrace();
            return "";
        }
    }

    protected void goNextExercise() {
        Intent intent = new Intent(this , homework2.class);
        startActivity(intent);
    }
}