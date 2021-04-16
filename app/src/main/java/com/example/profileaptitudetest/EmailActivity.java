package com.example.profileaptitudetest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity {

    public static final String EMAIL_ACTIVITY_TAG = "Apt EmailActivity"; // For Logging


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(EMAIL_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
    }

    public void updateEmail(View view) {
        Log.d(EMAIL_ACTIVITY_TAG, "Update Email");
        // Read the input
        EditText emailText = findViewById(R.id.emailAddress);
        String email = emailText.getText().toString();

        // Send back the new string
        if (email != null) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", email);
            setResult(Activity.RESULT_OK, returnIntent);
            Log.d(EMAIL_ACTIVITY_TAG, "Returning Email");
            finish();
        }

        // If there wasn't anything, send nothing back
        else {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            Log.d(EMAIL_ACTIVITY_TAG, "No Spam for this guy");
            finish();
        }
    }
}
