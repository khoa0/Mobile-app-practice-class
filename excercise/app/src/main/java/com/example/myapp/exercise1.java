package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class exercise1 extends AppCompatActivity {

    Button exercise_2btn;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ex1_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex1);

        ImageView im = findViewById(R.id.img);
        registerForContextMenu(im);

        exercise_2btn = findViewById(R.id.exercise_2btn);
        exercise_2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextExercise();
            }
        });
    }

    protected void goNextExercise() {
        Intent intent = new Intent(this, exercise2.class);
        startActivity(intent);
    }
}
