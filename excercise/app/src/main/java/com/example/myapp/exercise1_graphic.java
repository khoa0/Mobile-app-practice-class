package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;


public class exercise1_graphic extends Activity {

    //Button exercise_2btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.ex1);
        setContentView(new exercise1_graphicview(this));

//        ImageView im = findViewById(R.id.img);
//        registerForContextMenu(im);
//
//        exercise_2btn = findViewById(R.id.exercise_2btn);
//        exercise_2btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                goNextExercise();
//            }
//        });
    }

//    protected void goNextExercise() {
//        Intent intent = new Intent(this, exercise2.class);
//        startActivity(intent);
//    }
}



