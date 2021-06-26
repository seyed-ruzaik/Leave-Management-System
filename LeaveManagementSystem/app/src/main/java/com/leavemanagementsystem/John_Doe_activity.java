package com.leavemanagementsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class John_Doe_activity extends AppCompatActivity {


    protected final String name = "John Doe";
    protected TextView casualTextView, medicalTextView,annualTextView;
    protected DatabaseHelper myDb; // database helper
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_john__doe_activity);

         //set context for the DatabaseHelper
         myDb = new DatabaseHelper(this);

         casualTextView = findViewById(R.id.textView1_joh);
         medicalTextView = findViewById(R.id.textView2_joh);
         annualTextView = findViewById(R.id.textView3_joh);





        Button apply = findViewById(R.id.btn_apply_john);


        getCasualLeaves();
        getMedicalLeaves();
        getAnnualLeaves();


        //go to leave options and apply for a leave
        apply.setOnClickListener(v -> startActivity(new Intent(John_Doe_activity.this, Leaves_For_John_activity.class)));
    }

    //returns the remaining casual leaves and updates the text view
    @SuppressLint("SetTextI18n")
    public void getCasualLeaves(){
        //using cursor to retrieve data from DB
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            System.out.println("Nothing Found!");
            return;
        }

        String cas;
        while (res.moveToNext()) {
            cas = res.getString(1);
            if (res.getString(0).equals(name)) {

                casualTextView.setText("Casual Leaves Remaining: "+cas);



            }



        }

    }
    //returns the remaining medical leaves and updates the text view
    @SuppressLint("SetTextI18n")
    public void getMedicalLeaves(){
        //using cursor to retrieve data from DB
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            System.out.println("Nothing Found!");
            return;
        }

        String cas;
        while (res.moveToNext()) {
            cas = res.getString(2);
            if (res.getString(0).equals(name)) {

                medicalTextView.setText("Medical Leaves Remaining: "+cas);



            }



        }

    }

    //returns the remaining Annual leaves and updates the text view
    @SuppressLint("SetTextI18n")
    public void getAnnualLeaves(){
        //using cursor to retrieve data from DB
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            System.out.println("Nothing Found!");
            return;
        }

        String cas;
        while (res.moveToNext()) {
            cas = res.getString(3);
            if (res.getString(0).equals(name)) {

                annualTextView.setText("Annual Leaves Remaining: "+cas);



            }



        }

    }


    //override the back button to go to main menu when pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(John_Doe_activity.this, MainActivity.class));
    }


}