package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;

public class exercise3_graphic extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new exercise3_graphicview(this));
    }
}



