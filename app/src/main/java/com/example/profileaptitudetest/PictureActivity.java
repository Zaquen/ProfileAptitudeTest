package com.example.profileaptitudetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class PictureActivity extends AppCompatActivity {

    public static final String PIC_ACTIVITY_TAG = "Apt PictureActivity"; // For Logging


    /**
     * Permission Variables for the gallery
     */
    public static final int GALLERY_REQUEST = 1; // For the Profile Picture
    private boolean galleryPermissionsGranted = false; // Permissions

    // Variable for the actual image to return, in a Bitmap form
    // and the return URI variable to go back to Main
    Bitmap photo;
    Uri photoURI = null;

    /**
     * On Create
     * Standardized creation loop
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(PIC_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
    }

    /**
     * Browse Pic
     * This function will attempt to enter the user's gallery to grab a picture,
     * but permissions must be granted first
     */
    public void browsePic(View view) {
        Log.d(PIC_ACTIVITY_TAG, "Browse Pic");
        // Check if we have permission already to access Gallery
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            // All is well
            galleryPermissionsGranted = true;
            Log.d(PIC_ACTIVITY_TAG, "Permission Previously Granted");
        }
        // Ask for permission if we don't have it
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY_REQUEST);
            Log.d(PIC_ACTIVITY_TAG, "Ask for Permission");
        }

        // If we have permission, continue. Else skip
        if (galleryPermissionsGranted) {
            Log.d(PIC_ACTIVITY_TAG, "Gallery Activity");
            Intent intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
            // So as long as the result is not null, it's safe to use the intent.
            if (intent.resolveActivity(getPackageManager()) != null) {
                // Bring up gallery to select a photo
                startActivityForResult(intent, 1);
            }
        }
    }

    /**
     * Standard implementation for async call for permissions
     * Found on StackOverflow and Android Dev website
     */
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] grantResults) {
        Log.d(PIC_ACTIVITY_TAG, "Permission Results");
        switch (requestCode) {
            case GALLERY_REQUEST:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    galleryPermissionsGranted = true;
                    Log.d(PIC_ACTIVITY_TAG, "Permission Granted");
                } else {
                    galleryPermissionsGranted = false;
                    Log.d(PIC_ACTIVITY_TAG, "Permission Refused");
                }
        }
    }

    /**
     * On Activity Result
     * In this case, this handles getting the picture from the gallery and updating
     * the GUI to show a preview
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(PIC_ACTIVITY_TAG, "Gallery Results");
        // Ensure the data expected is coming
        if (data != null && requestCode == 1) {
            Log.d(PIC_ACTIVITY_TAG, "Picture Selected");
            // Change the data to a Bitmap so we can draw it
            photoURI = data.getData();
            try {
                photo = BitmapFactory.decodeStream(getContentResolver().openInputStream(photoURI));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Put the image into the GUI as a preview
            ImageView preview = findViewById(R.id.picBrowse);
            preview.setImageBitmap(photo);
            Log.d(PIC_ACTIVITY_TAG, "Picture Updated");
        }
    }

    /**
     * Update Pic
     * When the button is clicked, send the new photoURI back to Main
     */
    public void updatePic(View view) {
        Log.d(PIC_ACTIVITY_TAG, "updatePic");
        //Send back the photoURI
        // Read the input

        // Send back the Uri
        if (photoURI != null) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", photoURI.toString());
            setResult(Activity.RESULT_OK, returnIntent);
            Log.d(PIC_ACTIVITY_TAG, "Returning Uri");
            finish();
        }

        // If there was nothing, send nothing back
        else {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            Log.d(PIC_ACTIVITY_TAG, "Returning Nothing");
            finish();
        }
    }
}
