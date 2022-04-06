package com.example.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.Db_Name, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
        onCreate(db);
    }
    public long insertInfo(String name, String age, String phone,String image,
                           String addTimeStamp, String updateTimeStamp){
        SQLiteDatabase db= getReadableDatabase();
        ContentValues values= new ContentValues();
        values.put(Constants.C_NAME, name);
        values.put(Constants.C_AGE, age);
        values.put(Constants.C_PHONE, phone);
        values.put(Constants.C_IMAGE, image);
        values.put(Constants.C_ADD_TIMESTAMP,addTimeStamp);
        values.put(Constants.C_UPDATE_TIMESTAMP, updateTimeStamp);

        long id= db.insert(Constants.TABLE_NAME,null,values);
        db.close();
        return id;
    }
}
