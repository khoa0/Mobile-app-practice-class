package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

public class exercise5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex5);

        Button openGoogleButton = findViewById(R.id.openGoogleButton);
        openGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleWebsite();
            }
        });

        Button makeCallButton = findViewById(R.id.makeCallButton);
        makeCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });

        Button dialNumberButton = findViewById(R.id.dialNumberButton);
        dialNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialNumber();
            }
        });

        Button viewContactsButton = findViewById(R.id.viewContactsButton);
        viewContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewContacts();
            }
        });
    }

    private void openGoogleWebsite() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
        startActivity(intent);
    }

    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:123456789"));
        startActivity(intent);
    }

    private void dialNumber() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"));
        startActivity(intent);
    }

    private void viewContacts() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
        startActivity(intent);
    }
}


