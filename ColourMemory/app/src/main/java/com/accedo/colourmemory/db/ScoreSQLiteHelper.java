package com.accedo.colourmemory.db;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Customized SQLite helper to create db and table
 */
public class ScoreSQLiteHelper extends SQLiteOpenHelper {
    public static final String TAG = ScoreSQLiteHelper.class.getSimpleName();

    public static final String TABLE_SCORE= "score";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER = "user";
    public static final String COLUMN_SCORE = "score";

    private static final String DATABASE_NAME = "memory.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_SCORE + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_USER + " text not null, "
            + COLUMN_SCORE + " integer no null);";

    public ScoreSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        onCreate(db);
    }

}