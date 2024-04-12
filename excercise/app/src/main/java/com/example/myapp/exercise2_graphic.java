package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class exercise2_graphic extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new exercise2_graphicview(this));
    }

}



