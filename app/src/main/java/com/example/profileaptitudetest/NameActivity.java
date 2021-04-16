package com.example.profileaptitudetest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    public static final String NAME_ACTIVITY_TAG = "Aptitude NameActivity"; // For Logging


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(NAME_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
    }

    public void updateName(View view) {
        Log.d(NAME_ACTIVITY_TAG, "Update Name");
        // Read the input
        EditText firstNameText = findViewById(R.id.firstName);
        EditText lastNameText = findViewById(R.id.lastName);
        String name = firstNameText.getText().toString() + " " + lastNameText.getText().toString();


        // Send back the new string
        if (name != null) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", name);
            setResult(Activity.RESULT_OK, returnIntent);
            Log.d(NAME_ACTIVITY_TAG, "My Name is...");
            finish();
        }

        // If there was nothing, send nothing back
        else {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            Log.d(NAME_ACTIVITY_TAG, "My Name is what?");
            finish();
        }
    }
}
