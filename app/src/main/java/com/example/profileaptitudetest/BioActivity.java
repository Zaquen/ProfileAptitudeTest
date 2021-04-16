package com.example.profileaptitudetest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BioActivity extends AppCompatActivity {

    public static final String BIO_ACTIVITY_TAG = "Apt BioActivity"; // For Logging


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(BIO_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);
    }

    public void updateBio(View view) {
        Log.d(BIO_ACTIVITY_TAG, "Update Bio");
        // Read the input
        EditText bioText = findViewById(R.id.bioText);
        String bio = bioText.getText().toString();

        // Send back the new string
        if (bio != null) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", bio);
            setResult(Activity.RESULT_OK, returnIntent);
            Log.d(BIO_ACTIVITY_TAG, "Returning Bio");
            finish();
        }

        // If there was nothing, send nothing back
        else {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            Log.d(BIO_ACTIVITY_TAG, "No Bio No Problem");
            finish();
        }
    }
}
