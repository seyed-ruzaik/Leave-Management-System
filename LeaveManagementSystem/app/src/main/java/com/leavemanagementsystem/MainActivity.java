package com.leavemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected DatabaseHelper myDb; // database helper
    protected static boolean isSplashScreenShown = false; // boolean to check if splash screen is shown initially it has to be false
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //show splash screen
        if (!isSplashScreenShown) {
            startActivity(new Intent(MainActivity.this, Splash_Screen.class));

        }


       //set context for the DatabaseHelper
        myDb = new DatabaseHelper(this);

        //Buttons
        Button manager = findViewById(R.id.btn_manager);
        Button john = findViewById(R.id.approve_or_reject_btn);
        Button jane = findViewById(R.id.employee_jane_btn);


        //go to manager menu
        manager.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Manager_Menu_activity.class)));

        //go to john doe
        john.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, John_Doe_activity.class)));
        //go to jane doe
        jane.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Jane_Doe_activity.class)));




            boolean checkJohn = myDb.tableExists("John Doe");
            boolean checkJane = myDb.tableExists("Jane Doe");

            System.out.println("check "+checkJohn);
            System.out.println("check "+checkJane);


            if (!checkJohn) {
                myDb.insertData("John Doe", Integer.toString(7), Integer.toString(7),
                        Integer.toString(14));

            }

            if (!checkJane){
                myDb.insertData("Jane Doe", Integer.toString(7), Integer.toString(7),
                        Integer.toString(14));

            }

    }

    //close app on back pressed
    @Override
    public void onBackPressed() {

        //finish all activity
        finishAffinity();

    }
}