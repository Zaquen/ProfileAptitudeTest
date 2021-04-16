package com.example.profileaptitudetest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

/**
 * MainActivity, the initial activity when the app is booted, will
 * display basic profile information. Also includes the navigation to the
 * other activities where the information can be edited.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Various Background Variables for the MainActivity
     */
    public static final String EXTRA_MESSAGE = "com.example.prove05.MESSAGE"; // For Intents
    public static final String MAIN_ACTIVITY_TAG = "Aptitude MainActivity"; // For Logging


    /**
     * Activity Callback Codes
     */
    public static final int PICTURE_CODE = 1;
    public static final int NAME_CODE = 2;
    public static final int PHONE_CODE = 3;
    public static final int EMAIL_CODE = 4;
    public static final int BIO_CODE = 5;

    /**
     * Needed strings for the UI
     */
    Bitmap picture;
    String name;
    String phone;
    String email;
    String bio;

    /**
     * onCreate, to be run at the start of the app itself
     * should load the UI with any saved information
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(MAIN_ACTIVITY_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * onResume, to be run when the activity is back in focus
     * should reload the UI if anything was changed
     */
    protected void onResume(Bundle savedInstanceState) {
        Log.d(MAIN_ACTIVITY_TAG, "onResume");
        super.onResume();
    }
    /**
     * editPicture
     * Start activity for result, build an intent
     */
    public void editPicture(View view) {
        Log.d(MAIN_ACTIVITY_TAG, "editPicture");
        Intent i = new Intent(this, PictureActivity.class);
        startActivityForResult(i, PICTURE_CODE);
    }


    /**
     * editName
     * Start activity for result, build an intent
     */
    public void editName(View view) {
        Log.d(MAIN_ACTIVITY_TAG, "editName");
        Intent i = new Intent(this, NameActivity.class);
        startActivityForResult(i, NAME_CODE);
    }

    /**
     * editPhone
     * Start activity for result, build an intent
     */
    public void editPhone(View view) {
        Log.d(MAIN_ACTIVITY_TAG, "editPhone");
        Intent i = new Intent(this, PhoneActivity.class);
        startActivityForResult(i, PHONE_CODE);
    }

    /**
     * editEmail
     * Start activity for result, build an intent
     */
    public void editEmail(View view) {
        Log.d(MAIN_ACTIVITY_TAG, "editEmail");
        Intent i = new Intent(this, EmailActivity.class);
        startActivityForResult(i, EMAIL_CODE);
    }

    /**
     * editBio
     * Start activity for result, build an intent
     */
    public void editBio(View view) {
        Log.d(MAIN_ACTIVITY_TAG, "editBio");
        Intent i = new Intent(this, BioActivity.class);
        startActivityForResult(i, BIO_CODE);
    }

    /**
     * onActivityResult
     * callback function when starting an activity for result,
     * will be able to hand any set of information for all of the calls
     * from MainActivity
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(MAIN_ACTIVITY_TAG, "Activity Returned");
        // Check to see if the activity returned anything
        // If not, skip it all
        if (resultCode == Activity.RESULT_OK) {
            Log.d(MAIN_ACTIVITY_TAG, "Good Result");

            // Update the correct information based on the requestCode
            // All of the code blocks will put the information in the
            // associated variable, and then edit the UI
            switch(requestCode) {
                case PICTURE_CODE:
                    // Convert to Bitmap
                    Uri pictureURI = Uri.parse(data.getStringExtra("result"));
                    try {
                        picture = BitmapFactory.decodeStream(getContentResolver().openInputStream(pictureURI));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // Update GUI
                    ImageView profilePic = findViewById(R.id.profilePic);
                    profilePic.setImageBitmap(picture);
                    Log.d(MAIN_ACTIVITY_TAG, "Picture Updated");
                    break;

                case NAME_CODE:
                    name = data.getStringExtra("result");
                    TextView nameText = findViewById(R.id.name);
                    nameText.setText(String.valueOf(name));
                    Log.d(MAIN_ACTIVITY_TAG, "Name Updated");
                    break;

                case PHONE_CODE:
                    phone = data.getStringExtra("result");
                    TextView phoneText = findViewById(R.id.phone);
                    phoneText.setText(String.valueOf(phone));
                    Log.d(MAIN_ACTIVITY_TAG, "Phone Updated");
                    break;

                case EMAIL_CODE:
                    email = data.getStringExtra("result");
                    TextView emailText = findViewById(R.id.email);
                    emailText.setText(String.valueOf(email));
                    Log.d(MAIN_ACTIVITY_TAG, "Email Updated");
                    break;

                case BIO_CODE:
                    bio = data.getStringExtra("result");
                    TextView bioText = findViewById(R.id.bio);
                    bioText.setText(String.valueOf(bio));
                    Log.d(MAIN_ACTIVITY_TAG, "Bio Updated");
                    break;
            }
        }
    }


    /**
     * onPause, automatically called when the activity goes out
     * of focus. Simply added logging
     */
    @Override
    protected void onPause() {
        Log.d(MAIN_ACTIVITY_TAG, "Main Pause");
        super.onPause();
    }

    /**
     * onStop, automatically called when the activity is stopped for any reason
     * Simply added logging
     */
    @Override
    protected void onStop() {
        Log.d(MAIN_ACTIVITY_TAG, "Main Stop");
        super.onStop();
    }

    /**
     * onDestroy, automatically called when the activity is removed from memory
     * Simply added logging
     */
    @Override
    protected void onDestroy() {
        Log.d(MAIN_ACTIVITY_TAG, "Main Destroy");
        super.onDestroy();
        System.exit(0);
    }

    /**
     * onStart, automatically called after creation.
     * Simply added logging
     */
    @Override
    protected void onStart() {
        Log.d(MAIN_ACTIVITY_TAG, "Main Start");
        super.onStart();
    }
}
