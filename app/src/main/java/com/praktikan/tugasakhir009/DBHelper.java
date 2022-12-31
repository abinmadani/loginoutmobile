package com.praktikan.tugasakhir009;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table mhs(nim TEXT primary key, nama TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists mhs");
    }

    public Boolean insertData(String nim, String nama,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("nim", nim);
        contentValues.put("nama",nama);
        contentValues.put("password", password);
        long result = MyDB.insert("mhs", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String nim) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from mhs where nim = ?", new String[]{nim});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String nim, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from mhs where nim = ? and password = ?", new String[] {nim,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
