package com.leavemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Leaves_For_Jane extends AppCompatActivity {
    protected DatabaseHelper myDb; // database helper
    protected final String name = "Jane Doe";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaves__for__jane);

        //set context for the DatabaseHelper
        myDb = new DatabaseHelper(this);

        //buttons
        Button casual = findViewById(R.id.btn_casual_jane);
        Button medical = findViewById(R.id.btn_medical_jane);
        Button annual = findViewById(R.id.btn_annual_jane);


        casual.setOnClickListener(v -> {
            if (Integer.parseInt(getCasualLeaves()) > 0) {

                boolean update = false;

                int var = Integer.parseInt(getCasualLeaves()) - 1;
                update = myDb.updateCasual("Jane Doe",Integer.toString(var));



            }else {
                ShowToast("You can't apply for more\ncasual leaves!!!");
            }
        });

        medical.setOnClickListener(v -> {
            if (Integer.parseInt(getMedicalLeaves()) > 0) {

                boolean update = false;

                int var = Integer.parseInt(getMedicalLeaves()) - 1;
                update = myDb.updateMedical("Jane Doe",Integer.toString(var));



            }else {
                ShowToast("You can't apply for more\nmedical leaves!!!");
            }
        });

        annual.setOnClickListener(v -> {
            if (Integer.parseInt(getAnnualLeaves()) > 0) {

                boolean update = false;

                int var = Integer.parseInt(getAnnualLeaves()) - 1;
                update = myDb.updateAnnual("Jane Doe",Integer.toString(var));



            }else {
                ShowToast("You can't apply for more\nannual leaves!!!");
            }
        });

    }


    //get all favourites movie titles
    @SuppressLint("SetTextI18n")
    public String getCasualLeaves(){
        //using cursor to retrieve data from DB
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            System.out.println("Nothing Found!");
            return null;
        }

        String cas;
        String remaining = "";
        while (res.moveToNext()) {
            cas = res.getString(1);
            if (res.getString(0).equals(name)) {



                remaining = cas;

            }



        }

        return remaining;
    }

    //get all favourites movie titles
    @SuppressLint("SetTextI18n")
    public String getMedicalLeaves(){
        //using cursor to retrieve data from DB
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            System.out.println("Nothing Found!");
            return null;
        }

        String cas;
        String remaining = "";
        while (res.moveToNext()) {
            cas = res.getString(2);
            if (res.getString(0).equals(name)) {



                remaining = cas;

            }



        }

        return remaining;
    }


    //get all favourites movie titles
    @SuppressLint("SetTextI18n")
    public String getAnnualLeaves(){
        //using cursor to retrieve data from DB
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            System.out.println("Nothing Found!");
            return null;
        }

        String cas;
        String remaining = "";
        while (res.moveToNext()) {
            cas = res.getString(3);
            if (res.getString(0).equals(name)) {



                remaining = cas;

            }



        }

        return remaining;
    }

    //show a toast message
    @SuppressWarnings("deprecation")
    public void ShowToast(String message){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v3, findViewById(R.id.toast_message));
        TextView textView = layout.findViewById(R.id.textView_v3);
        textView.setText(message);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0 , 0 );
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();



    }

    //override the back button to go to main menu when pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Leaves_For_Jane.this, Jane_Doe_activity.class));
    }

}