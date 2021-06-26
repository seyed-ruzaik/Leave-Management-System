package com.leavemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Manager_Menu_activity extends AppCompatActivity {


    protected DatabaseHelper myDb; // database helper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__menu_activity);

        //set context for the DatabaseHelper
        myDb = new DatabaseHelper(this);


        //Buttons
        Button view = findViewById(R.id.manager_leave_balance);
        Button approve_or_reject = findViewById(R.id.approve_or_reject_btn);
        Button reset = findViewById(R.id.btn_reset);

        //view leave balance
        view.setOnClickListener(v -> startActivity(new Intent(Manager_Menu_activity.this, View_Leave_Balances_activity.class)));
        //approve or reject
        approve_or_reject.setOnClickListener(v -> startActivity(new Intent(Manager_Menu_activity.this, Approve_And_Reject_activity.class)));

        //reset the database
       reset.setOnClickListener(v -> {
           myDb.removeAll();
       });
    }


    //override the back button to go to main menu when pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Manager_Menu_activity.this, MainActivity.class));
    }
}