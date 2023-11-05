package com.example.smartbasket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "ProductDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table ProductDetails(barcode TEXT primary key, name TEXT, price TEXT, category TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Productdetails");
    }

    public Boolean insertuserdata(String barcode, String name, String price, String category, String description) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("barcode", barcode);
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("category", category);
        contentValues.put("description", description);
        long result = DB.insert("Productdetails", null, contentValues);
        return result != -1;
    }

    public Boolean updateuserdata(String barcode, String name, String price, String category, String description) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("barcode", barcode);
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("category", category);
        contentValues.put("description", description);
        Cursor cursor = DB.rawQuery("Select * from Productdetails where barcode = ?", new String[] {barcode});
        if(cursor.getCount()>0) {
            long result = DB.update("Productdetails", contentValues, "barcode=?", new String[] {barcode});
            return result != -1;
        } else {
            return false;
        }
    }

    public Boolean deletedata(String barcode) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Productdetails where barcode = ?", new String[] {barcode});
        if(cursor.getCount()>0) {
            long result = DB.delete("Productdetails", "barcode=?", new String[] {barcode});
            return result != -1;
        } else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Productdetails", null);
        return cursor;
    }

}
