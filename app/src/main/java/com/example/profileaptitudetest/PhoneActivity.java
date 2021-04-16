package com.example.profileaptitudetest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PhoneActivity extends AppCompatActivity {

    public static final String PHONE_ACTIVITY_TAG = "Apt PhoneActivity"; // For Logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(PHONE_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
    }

    public void updatePhone(View view) {
        Log.d(PHONE_ACTIVITY_TAG, "update Phone");
        // Read the input
        EditText phoneText = findViewById(R.id.phoneEdit);
        String phone = phoneText.getText().toString();

        // Send back the new string
        if (phone != null) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", phone);
            setResult(Activity.RESULT_OK, returnIntent);
            Log.d(PHONE_ACTIVITY_TAG, "Sending Back Phone");
            finish();
        }

        // If there was nothing, send nothing back
        else {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            Log.d(PHONE_ACTIVITY_TAG, "Give Nothing Back");
            finish();
        }
    }
}
