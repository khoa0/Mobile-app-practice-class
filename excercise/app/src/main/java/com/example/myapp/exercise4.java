package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class exercise4 extends AppCompatActivity {

    EditText nameEditText, emailEditText, phoneEditText;
    RadioButton femaleRadioButton, maleRadioButton;
    ImageView profileImageView;
    Button saveButton, cancelButton, changeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise4);

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

    }

    private void loadUserData() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "profile.txt");
        if (!file.exists()) {
            // Handle the case where the file doesn't exist
            return;
        }

        try {
            FileInputStream fis = new FileInputStream(file);
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

        File imageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "user_profile_image.png");
        if (!imageFile.exists()) {
            // Handle the case where the image file doesn't exist
            return;
        }

        try {
            FileInputStream imageFis = new FileInputStream(imageFile);
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
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "profile.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
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

    private void saveImage(Bitmap imageBitmap) {
        File imageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "user_profile_image.png");
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}