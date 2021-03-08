package com.example.retrofitdemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "Data";
    private static final String TABLE_NAME = "User";
    private static final int VERSION = 1;

    SQLiteDatabase sqLiteDatabase;

    public AppDatabase(Context ctx){
        super(ctx,DB_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (_id integer primary key autoincrement, user_id text," +
                "email text, first_name text, last_name text, avatar text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData (String userId, String email, String fName, String lName, String avatar){
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME + " (user_id,email,first_name,last_name,avatar) " +
                "values('"+userId+"', '"+email+"', '"+fName+"', '"+lName+"', '"+avatar+"');");
    }

    public Cursor getDataFromDB(){
        sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("select * from " + TABLE_NAME,null);
    }
}
