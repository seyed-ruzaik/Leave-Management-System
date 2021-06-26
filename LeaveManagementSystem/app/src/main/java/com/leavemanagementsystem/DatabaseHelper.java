package com.leavemanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Employee.db"; //database name
    public static final String TABLE_NAME = "employee_table"; //table name
    public static final String COL_1 = "NAME"; //name column
    public static final String COL_2 = "CASUAL"; // casual column
    public static final String COL_3 = "MEDICAL"; // medical column
    public static final String COL_4 = "ANNUAL"; // annual column

    //constructor which takes Context as a parameter
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //on create method which will create the Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (NAME TEXT,CASUAL TEXT," +
                "MEDICAL TEXT,ANNUAL TEXT)");
    }

    //on upgrade method which will drop the table if its exists
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

   public boolean tableExists(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT "+COL_1+ " FROM "+TABLE_NAME+" WHERE "+
                COL_1 + " = '"+name+"'", null);
        if (!cursor.moveToFirst())
        {
            System.out.println("Record Exist");
            return false;
        }else {
            System.out.println("Record Does Not Exist");
            return true;
        }

    }

    //method which will insert the data
    public boolean insertData(String name, String casual, String medical, String annual) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();



       
            //put all values in contentValues
            contentValues.put(COL_1, name);
            contentValues.put(COL_2, casual);
            contentValues.put(COL_3, medical);
            contentValues.put(COL_4, annual);

            //insert only if result is not equals to -1
            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        
       
    }

    //method which will return all data from the DB
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        //will return all data from the table
        return cursor;
    }



    //method which will return all the titles which has a match
    public Cursor getSearchItem(String search){

        SQLiteDatabase db = this.getWritableDatabase();
        String query3 = "select TITLE from "+TABLE_NAME+ " where "+COL_1+" like "+"'%"+search+"%' ";
        String query2 = "select TITLE from "+TABLE_NAME+" where "+COL_1 + " or ACTORS like "+"'%"+search+"%' union "+query3;
        String query = "select TITLE from "+TABLE_NAME+ " where "+COL_3+" like "+"'%"+search+"%' union "+query2;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;


    }

    //method which will update all the data in the DB for a given movie title
    public boolean updateData(String title, String titleName, String year, String director, String actors, String rating,
                              String review, String favourites) {
        int num;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //add all to content values
        contentValues.put(COL_1, titleName);
        contentValues.put(COL_2, year);
        contentValues.put(COL_3, director);
        contentValues.put(COL_4, actors);


        num = db.update(TABLE_NAME,contentValues,"TITLE = ?",new String[]{title});
        System.out.println("This is row "+num);


        if (num > 0) {
            return true;
        }else {
            return false;
        }

    }

    //method which will update the casual column
    public boolean updateCasual(String name,String val){
        int num;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //put value to content values
        contentValues.put(COL_2,val);
        num = db.update(TABLE_NAME,contentValues,"NAME = ?",new String[]{name});



        //update favourites if num > 0

        if (num > 0) {
            return true;
        }else {
            return false;
        }
    }

    //method which will update the medical column
    public boolean updateMedical(String name,String val){
        int num;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //put value to content values
        contentValues.put(COL_3,val);
        num = db.update(TABLE_NAME,contentValues,"NAME = ?",new String[]{name});



        //update favourites if num > 0

        if (num > 0) {
            return true;
        }else {
            return false;
        }
    }

    //method which will update the annual column
    public boolean updateAnnual(String name,String val){
        int num;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //put value to content values
        contentValues.put(COL_4,val);
        num = db.update(TABLE_NAME,contentValues,"NAME = ?",new String[]{name});



        //update favourites if num > 0

        if (num > 0) {
            return true;
        }else {
            return false;
        }
    }


    //remove all
    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }



}