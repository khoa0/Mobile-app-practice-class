package com.example.myapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

public class exercise3 extends AppCompatActivity {

    Button exercise_4btn;
    EditText nameEditText, emailEditText, phoneEditText;
    RadioButton femaleRadioButton, maleRadioButton;
    ImageView profileImageView;
    Button saveButton, cancelButton, changeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise3);

        exercise_4btn = findViewById(R.id.exercise_4btn);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        profileImageView = findViewById(R.id.profileImageView);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        changeButton = findViewById(R.id.changeButton);

        loadUserData();

        changeButton.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, 1);
            }
        });

        saveButton.setOnClickListener(v -> saveUserData());
        cancelButton.setOnClickListener(v -> finish());


        exercise_4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextExercise();
            }
        });
    }

    protected void goNextExercise() {
        Intent intent = new Intent(this , exercise4.class);
        startActivity(intent);
    }

    private void loadUserData() {
        try {
            FileInputStream fis = openFileInput("profile.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String name = reader.readLine();
            String email = reader.readLine();
            String phone = reader.readLine();
            boolean isFemale = Boolean.parseBoolean(reader.readLine());

            nameEditText.setText(name);
            emailEditText.setText(email);
            phoneEditText.setText(phone);
            if (isFemale) {
                femaleRadioButton.setChecked(true);
            } else {
                maleRadioButton.setChecked(true);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream imageFis = openFileInput("user_profile_image.png");
            Bitmap profileImage = BitmapFactory.decodeStream(imageFis);
            profileImageView.setImageBitmap(profileImage);
            imageFis.close();
        } catch (FileNotFoundException e) {
            // Image file not found, handle accordingly
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUserData() {
        try {
            FileOutputStream fos = openFileOutput("profile.txt", MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(nameEditText.getText().toString() + "\n");
            writer.write(emailEditText.getText().toString() + "\n");
            writer.write(phoneEditText.getText().toString() + "\n");
            writer.write(femaleRadioButton.isChecked() + "\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profileImageView.setImageBitmap(imageBitmap);
            saveImage(imageBitmap);
        }
    }

    private void saveImage(Bitmap imageBitmap) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("user_profile_image.png", MODE_PRIVATE);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
