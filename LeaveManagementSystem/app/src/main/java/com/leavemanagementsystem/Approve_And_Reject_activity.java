package com.leavemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Approve_And_Reject_activity extends AppCompatActivity {

    protected final String john = "John Doe";
    protected final String jane = "Jane Doe";

    protected int john_casual_length = 7 ;
    protected int john_medical_length = 7 ;
    protected int john_annual_length = 14 ;

    protected int jane_casual_length = 7 ;
    protected int jane_medical_length = 7 ;
    protected int jane_annual_length = 14 ;

    protected DatabaseHelper myDb; // database helper
    protected ArrayList<String> leaves = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve__and__reject_activity);
        ListView listView = findViewById(R.id.checkable_list2);


        //Buttons
        Button approve = findViewById(R.id.btn_approve);
        Button reject = findViewById(R.id.btn_reject);


        approve.setOnClickListener(v -> {

            ShowToast("Please Select Something!");

        });

        reject.setOnClickListener(v -> {
            ShowToast("Please Select Something!");



        });

        //set context for the DatabaseHelper
        myDb = new DatabaseHelper(this);

        getCasualLeaves();
        getMedicalLeaves();
        getAnnualLeaves();

        //set the list view choice mode to multiple selections
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //create an arrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.rowlayout, R.id.txt_title, leaves);
        //set the adapter to the list view
        listView.setAdapter(adapter);

        //set on click listener for the list view
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // selected item
            String selectedItem = ((TextView) view).getText().toString();


           if (selectedItem.contains(john)){

               if (selectedItem.contains("Casual")){

                   approve.setOnClickListener(v -> {

                       leaves.remove(position);
                       //set the adapter to the list view
                       listView.setAdapter(adapter);
                       SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
                       john_casual_length -= 1;
                       editor.putInt("john_cas_length", john_casual_length);
                       editor.apply();

                   });

                   reject.setOnClickListener(v -> {
                       boolean update = false;

                       int var = Integer.parseInt(numOfCasualLeavesJohn()) + 1;
                       update = myDb.updateCasual("John Doe",Integer.toString(var));

                       leaves.remove(position);
                       //set the adapter to the list view
                       listView.setAdapter(adapter);



                   });

               }else if (selectedItem.contains("Medical")){

                   approve.setOnClickListener(v -> {

                       leaves.remove(position);
                       //set the adapter to the list view
                       listView.setAdapter(adapter);
                       SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
                       john_medical_length -= 1;
                       editor.putInt("john_med_length", john_medical_length);
                       editor.apply();

                   });

                   reject.setOnClickListener(v -> {
                       boolean update = false;

                       int var = Integer.parseInt(numOfMedicalLeavesJohn()) + 1;
                       update = myDb.updateMedical("John Doe",Integer.toString(var));

                       leaves.remove(position);
                       //set the adapter to the list view
                       listView.setAdapter(adapter);



                   });

               }else {
                   approve.setOnClickListener(v -> {

                       leaves.remove(position);
                       //set the adapter to the list view
                       listView.setAdapter(adapter);
                       SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
                       john_annual_length -= 1;
                       editor.putInt("john_ann_length", john_annual_length);
                       editor.apply();

                   });

                   reject.setOnClickListener(v -> {
                       boolean update = false;

                       int var = Integer.parseInt(numOfAnnualLeavesJohn()) + 1;
                       update = myDb.updateAnnual("John Doe",Integer.toString(var));

                       leaves.remove(position);
                       //set the adapter to the list view
                       listView.setAdapter(adapter);



                   });


               }

           }else if(selectedItem.contains(jane)){


               if (selectedItem.contains("Casual")){

                   approve.setOnClickListener(v -> {

                       leaves.remove(position);
                       //set the adapter to the list view
                       listView.setAdapter(adapter);
                       SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
                       jane_casual_length -= 1;
                       editor.putInt("jane_cas_length", jane_casual_length);
                       editor.apply();

                   });

                   reject.setOnClickListener(v -> {
                       boolean update = false;

                       int var = Integer.parseInt(numOfCasualLeavesJane()) + 1;
                       update = myDb.updateCasual("Jane Doe",Integer.toString(var));

                       leaves.remove(position);
                       //set the adapter to the list view
                       listView.setAdapter(adapter);



                   });

               }else if (selectedItem.contains("Medical")){

                   approve.setOnClickListener(v -> {

                       leaves.remove(position);
                       //set the adapter to the list view
                       listView.setAdapter(adapter);
                       SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
                       jane_medical_length -= 1;
                       editor.putInt("jane_med_length", jane_medical_length);
                       editor.apply();

                   });

                   reject.setOnClickListener(v -> {
                       boolean update = false;

                       int var = Integer.parseInt(numOfMedicalLeavesJane()) + 1;
                       update = myDb.updateMedical("Jane Doe",Integer.toString(var));

                       leaves.remove(position);
                       //set the adapter to the list view
                       listView.setAdapter(adapter);



                   });

               }else {


                   approve.setOnClickListener(v -> {

                       leaves.remove(position);
                       //set the adapter to the list view
                       listView.setAdapter(adapter);
                       SharedPreferences.Editor editor = getSharedPreferences("PreferencesName", MODE_PRIVATE).edit();
                       jane_annual_length -= 1;
                       editor.putInt("jane_ann_length", jane_annual_length);
                       editor.apply();

                   });

                   reject.setOnClickListener(v -> {
                       boolean update = false;

                       int var = Integer.parseInt(numOfAnnualLeavesJane()) + 1;
                       update = myDb.updateAnnual("Jane Doe",Integer.toString(var));

                       leaves.remove(position);
                       //set the adapter to the list view
                       listView.setAdapter(adapter);



                   });

               }
           }else {

               approve.setOnClickListener(v -> {

                   ShowToast("No Data Available");

               });

               reject.setOnClickListener(v -> {
                   ShowToast("No Data Available");



               });
           }

        });

    }

    //get all favourites movie titles
    @SuppressLint("SetTextI18n")
    public String numOfCasualLeavesJohn(){
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
            if (res.getString(0).equals(john)) {



                remaining = cas;

            }



        }

        return remaining;
    }
    //get all favourites movie titles
    @SuppressLint("SetTextI18n")
    public String numOfMedicalLeavesJohn(){
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
            if (res.getString(0).equals(john)) {



                remaining = cas;

            }



        }

        return remaining;
    }

    //get all favourites movie titles
    @SuppressLint("SetTextI18n")
    public String numOfAnnualLeavesJohn(){
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
            if (res.getString(0).equals(john)) {



                remaining = cas;

            }



        }

        return remaining;
    }


    //get all favourites movie titles
    @SuppressLint("SetTextI18n")
    public String numOfCasualLeavesJane(){
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
            if (res.getString(0).equals(jane)) {



                remaining = cas;

            }



        }

        return remaining;
    }
    //get all favourites movie titles
    @SuppressLint("SetTextI18n")
    public String numOfMedicalLeavesJane(){
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
            if (res.getString(0).equals(jane)) {



                remaining = cas;

            }



        }

        return remaining;
    }

    //get all favourites movie titles
    @SuppressLint("SetTextI18n")
    public String numOfAnnualLeavesJane(){
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
            if (res.getString(0).equals(jane)) {



                remaining = cas;

            }



        }

        return remaining;
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






            SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
            if (prefs.contains("john_cas_length"))
             {
             int myInt = prefs.getInt("john_cas_length", 0); // 0 is default

             john_casual_length = myInt;

          }


        if (prefs.contains("jane_cas_length"))
        {
            int myInt = prefs.getInt("jane_cas_length", 0); // 0 is default

            jane_casual_length = myInt;

        }


        String cas;
        while (res.moveToNext()) {
            cas = res.getString(1);
            if (res.getString(0).equals(john)) {

              int length = john_casual_length - Integer.parseInt(cas);
              for (int i = 0 ; i < length; i++){

                  leaves.add("John Doe\nCasual Leave");
              }



            }else if (res.getString(0).equals(jane)){

                int length = jane_casual_length - Integer.parseInt(cas);
                for (int i = 0 ; i < length; i++){

                    leaves.add("Jane Doe\nCasual Leave");
                }
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

        SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
        if (prefs.contains("john_med_length"))
        {
            int myInt = prefs.getInt("john_med_length", 0); // 0 is default

            john_medical_length = myInt;

        }


        if (prefs.contains("jane_med_length"))
        {
            int myInt = prefs.getInt("jane_med_length", 0); // 0 is default

            jane_medical_length = myInt;

        }


        String cas;
        while (res.moveToNext()) {
            cas = res.getString(2);
            if (res.getString(0).equals(john)) {

                int length = john_medical_length - Integer.parseInt(cas);
                for (int i = 0 ; i < length; i++){

                    leaves.add("John Doe\nMedical Leave");
                }


            }else if (res.getString(0).equals(jane)){

                int length = jane_medical_length - Integer.parseInt(cas);
                for (int i = 0 ; i < length; i++){

                    leaves.add("Jane Doe\nMedical Leave");
                }
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

        SharedPreferences prefs = getSharedPreferences("PreferencesName", MODE_PRIVATE);
        if (prefs.contains("john_ann_length"))
        {
            int myInt = prefs.getInt("john_ann_length", 0); // 0 is default

            john_annual_length = myInt;

        }


        if (prefs.contains("jane_ann_length"))
        {
            int myInt = prefs.getInt("jane_ann_length", 0); // 0 is default

            jane_annual_length = myInt;

        }

        String cas;
        while (res.moveToNext()) {
            cas = res.getString(3);
            if (res.getString(0).equals(john)) {

                int length = john_annual_length - Integer.parseInt(cas);
                for (int i = 0 ; i < length; i++){

                    leaves.add("John Doe\nAnnual Leave");
                }



            }else if (res.getString(0).equals(jane)){

                int length = jane_annual_length - Integer.parseInt(cas);
                for (int i = 0 ; i < length; i++){

                    leaves.add("Jane Doe\nAnnual Leave");
                }
            }



        }

    }

    //show toast message
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
        startActivity(new Intent(Approve_And_Reject_activity.this, Manager_Menu_activity.class));
    }
}