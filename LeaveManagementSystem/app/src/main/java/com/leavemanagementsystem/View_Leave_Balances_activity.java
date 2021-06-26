package com.leavemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class View_Leave_Balances_activity extends AppCompatActivity {
    protected final String john = "John Doe";
    protected final String jane = "Jane Doe";
    protected TextView casualTextView, medicalTextView,annualTextView;
    protected TextView casualTextView_jane, medicalTextView_jane,annualTextView_jane;
    protected DatabaseHelper myDb; // database helper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__leave__balances_activity);

        //set context for the DatabaseHelper
        myDb = new DatabaseHelper(this);

        //textViews for john
        casualTextView = findViewById(R.id.textView1_joh);
        medicalTextView = findViewById(R.id.textView2_joh);
        annualTextView = findViewById(R.id.textView3_joh);

        //textViews for jane
        casualTextView_jane = findViewById(R.id.textView1_jan);
        medicalTextView_jane = findViewById(R.id.textView2_jan);
        annualTextView_jane = findViewById(R.id.textView3_jan);


        getCasualLeaves();
        getMedicalLeaves();
        getAnnualLeaves();


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
            if (res.getString(0).equals(john)) {

                casualTextView.setText("Casual Leaves Remaining: "+cas);



            }else if (res.getString(0).equals(jane)){
                casualTextView_jane.setText("Casual Leaves Remaining: "+cas);

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
            if (res.getString(0).equals(john)) {

                medicalTextView.setText("Medical Leaves Remaining: "+cas);



            }else if (res.getString(0).equals(jane)){
                medicalTextView_jane.setText("Medical Leaves Remaining: "+cas);

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
            if (res.getString(0).equals(john)) {

                annualTextView.setText("Annual Leaves Remaining: "+cas);



            }else if (res.getString(0).equals(jane)){
                annualTextView_jane.setText("Annual Leaves Remaining: "+cas);

            }



        }

    }


    //override the back button to go to main menu when pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(View_Leave_Balances_activity.this, Manager_Menu_activity.class));
    }
}