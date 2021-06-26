package com.leavemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Screen extends AppCompatActivity {

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        //set the isSplashScreenShown to true
        MainActivity.isSplashScreenShown = true;
        //Create a new handler
        //go to main activity after some time
        new Handler().postDelayed(() -> {
            Intent i = new Intent(com.leavemanagementsystem.Splash_Screen.this, MainActivity.class);

            startActivity(i);
            //finish the activity
            finish();
        }, 3000);
    }


}
