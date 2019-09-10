package com.example.supuni.tha2_app_154107m;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBasehelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "THA2_DB";
    public static final String TABLE_NAME = "User_Infor";
    public static final String COL_1 = "name";
    public static final String COL_2 = "index_no";
    public static final String COL_3 = "email";
    public static final String COL_4 = "mobile";
    public static final String COL_5 = "GPA";
    public static final String COL_6 = "password";

    public DataBasehelper (Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists User_Infor " + " (user_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,name text, index_no text,email text,mobile text,GPA text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS User_Infor " );
        onCreate(db);
    }

    public boolean insertData(String name,String index_no,String mobile,String GPA,String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,index_no);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,mobile);
        contentValues.put(COL_5,GPA);
        contentValues.put(COL_6,password);
        long result =  db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return  false;
        else
            return true;
    }



    public Cursor getProfile(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select name,index_no,email,mobile,GPA from User_Infor where user_id = "+id ,null);
        return res;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select user_id,index_no,password from User_Infor ",null);
        return res;
    }


}
