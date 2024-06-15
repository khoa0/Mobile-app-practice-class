package com.example.myapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private static final int SELECT_IMAGE = 1;
    private static final String TAG = "MainActivity";

    private Button buttonCamera, buttonGallery;
    private ImageView imageView;
    private CameraBridgeViewBase cameraView;
    private CascadeClassifier faceDetector;
    private Mat grayscaleImage;
    private int absoluteFaceSize;
    private int cameraId = CameraBridgeViewBase.CAMERA_ID_FRONT; // Set camera ID to front camera

    static {
        if (!OpenCVLoader.initDebug()) {
            Log.e(TAG, "OpenCV initialization failed");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCamera = findViewById(R.id.button_camera);
        buttonGallery = findViewById(R.id.button_gallery);
        imageView = findViewById(R.id.imageView);
        cameraView = (CameraBridgeViewBase) findViewById(R.id.camera_view);
        cameraView.setVisibility(View.GONE);

        cameraView.setCameraPermissionGranted();
        cameraView.setCvCameraViewListener(this);
        cameraView.setCameraIndex(cameraId); // Set camera index to front camera

        setupFaceDetector();

        buttonCamera.setOnClickListener(v -> requestCameraPermission());

        buttonGallery.setOnClickListener(v -> openGallery());
    }

    private void setupFaceDetector() {
        try {
            InputStream is = getResources().openRawResource(R.raw.lbpcascade_frontalface);
            File cascadeDir = getDir("cascade", MODE_PRIVATE);
            File mCascadeFile = new File(cascadeDir, "lbpcascade_frontalface.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();

            faceDetector = new CascadeClassifier(mCascadeFile.getAbsolutePath());
            if (faceDetector.empty()) {
                Log.e(TAG, "Failed to load cascade classifier");
                faceDetector = null;
            }
        } catch (IOException e) {
            Log.e(TAG, "Error loading cascade", e);
        }
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            cameraView.setVisibility(View.VISIBLE);
            cameraView.enableView();
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_IMAGE);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        grayscaleImage = new Mat();
        absoluteFaceSize = Math.round(height * 0.2f);
    }

    @Override
    public void onCameraViewStopped() {
        if (grayscaleImage != null) {
            grayscaleImage.release();
        }
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat rgba = inputFrame.rgba();
        Mat rotatedRgba = new Mat();
        int rotation = getRotationDegree();
        switch (rotation) {
            case 90:
                Core.transpose(rgba, rotatedRgba);
                Core.flip(rotatedRgba, rotatedRgba, 1);
                break;
            case 270:
                Core.transpose(rgba, rotatedRgba);
                Core.flip(rotatedRgba, rotatedRgba, 0);
                break;
            case 0:
            default:
                rgba.copyTo(rotatedRgba);
                break;
        }

        Imgproc.cvtColor(rotatedRgba, grayscaleImage, Imgproc.COLOR_RGBA2GRAY);

        MatOfRect faces = new MatOfRect();
        if (faceDetector != null) {
            faceDetector.detectMultiScale(grayscaleImage, faces, 1.1, 2, 2, new Size(absoluteFaceSize, absoluteFaceSize), new Size());
        }

        Rect[] facesArray = faces.toArray();
        for (Rect face : facesArray) {
            Imgproc.rectangle(rotatedRgba, face.tl(), face.br(), new Scalar(0, 255, 0, 255), 3);
        }

        return rotatedRgba;
    }

    private int getRotationDegree() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
            default:
                return 0;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                detectFacesInImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void detectFacesInImage(Bitmap bitmap) {
        Mat imageMat = new Mat();
        Utils.bitmapToMat(bitmap, imageMat);
        Imgproc.cvtColor(imageMat, imageMat, Imgproc.COLOR_BGR2GRAY);

        MatOfRect faces = new MatOfRect();
        if (faceDetector != null) {
            faceDetector.detectMultiScale(imageMat, faces);
        }

        Rect[] facesArray = faces.toArray();
        for (Rect face : facesArray) {
            Imgproc.rectangle(imageMat, face.tl(), face.br(), new Scalar(0, 255, 0, 255), 3);
        }

        Utils.matToBitmap(imageMat, bitmap);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cameraView != null) {
            cameraView.disableView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraView != null) {
            cameraView.disableView();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                cameraView.setVisibility(View.VISIBLE);
                cameraView.enableView();
            } else {
                Toast.makeText(this, "Camera permission is required", Toast.LENGTH_LONG).show();
            }
        }
    }
}
