package com.example.user.kculture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Places.db";
    public static final String PLACE_TABLE_NAME= "places";
    public static final String PLACE_ID = "id";
    public static final String PLACE_NAME_KO = "name_ko";
    public static final String PLACE_NAME_EN = "name_en";
    public static final String PLACE_INFO_KO = "info_ko";
    public static final String PLACE_INFO_EN = "info_en";
    public static final String PLACE_LATITUDE = "latitude";
    public static final String PLACE_LONGITUDE = "longitude";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table places "+ "(id integer primary key, name_ko text, name_en text, " +
                "info_ko text, info_en text, latitude double, longitude double)");

    }

    public void deleteDatabase()
    {
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS places");
        onCreate(db);
    }
    public Integer deletePlace(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("places", "id = ? ",
                new String[] {Integer.toString(id)});
    }
    public int numberOfRows()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,
                PLACE_TABLE_NAME);
        return numRows;
    }
    public Cursor getData(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        //Log.d("SQL", Long.toString(id));
        //Cursor res = db.rawQuery("select * from places where id=" + id + "",
        //        null);
        Cursor res = db.query(PLACE_TABLE_NAME, null, "id=?",
                new String[]{Long.toString(id)},
                null, null, null);
        res.moveToFirst();
        return res;
    }
    public long insertPlace(String name_ko, String name_en,
                               String info_ko, String info_en,
                               double latitude, double longitude)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PLACE_NAME_KO, name_ko);
        contentValues.put(PLACE_NAME_EN, name_en);
        contentValues.put(PLACE_INFO_KO, info_ko);
        contentValues.put(PLACE_INFO_EN, info_en);
        contentValues.put(PLACE_LATITUDE,latitude);
        contentValues.put(PLACE_LONGITUDE, longitude);
        long pkey = db.insert(PLACE_TABLE_NAME, null, contentValues);
        return pkey;
    }

    public Cursor getData_ko(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.query(PLACE_TABLE_NAME, new String[]{PLACE_NAME_KO},
                PLACE_NAME_KO + " LIKE '%"+name+"%'", null, null,
                null, null);
        return res;
    }
}
