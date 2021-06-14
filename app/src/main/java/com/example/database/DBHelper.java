package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "register.db";
    private byte[] RePass;


    public DBHelper(@Nullable Context context) {
        super(context, "register.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL("create Table users(StudentId TEXT primary key, Password TEXT,RePass TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int i, int i1) {


        mydb.execSQL("drop Table if exists users");


    }

    public Boolean insertdata(String StudentId, String Password){


        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("StudentId", StudentId);
        contentvalues.put("Password",Password);
        contentvalues.put("RePass",RePass);
        long result = mydb.insert("users",null,contentvalues);

        if (result == -1) return false;

        else
            return true;


    }

    public Boolean checkUserName(String StudentId)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users where StudendId = ?", new String[] {StudentId});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String StudentId, String Password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users where StudendId = ? and  Password = ?", new String[] {StudentId, Password});

        if (cursor.getCount()> 0)
            return true;
        else
            return false;

    }

    public Boolean  checkPasswords(String Password, String RePass){

        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users where RePass = ? and  Password = ?", new String[] {RePass, Password});
        if (cursor.getCount()>0) {

            if (RePass == Password)

                return true;
            else
                return false;

        }

        else
            return false;
    }
}