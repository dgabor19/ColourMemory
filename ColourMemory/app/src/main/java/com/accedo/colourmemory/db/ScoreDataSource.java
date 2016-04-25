package com.accedo.colourmemory.db;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.accedo.colourmemory.models.Score;


import java.util.ArrayList;
import java.util.List;

/**
 * DB data source handler class
 */
public class ScoreDataSource {

    // Database fields
    private SQLiteDatabase database;
    private ScoreSQLiteHelper dbHelper;

    private String[] allColumns = {
            ScoreSQLiteHelper.COLUMN_ID,
            ScoreSQLiteHelper.COLUMN_USER,
            ScoreSQLiteHelper.COLUMN_SCORE };

    public ScoreDataSource(Context context) {
        dbHelper = new ScoreSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /**
     * Add new row to the table
     * @param score
     * @return
     */
    public long createScore(Score score) {
        ContentValues values = new ContentValues();
        values.put(ScoreSQLiteHelper.COLUMN_USER, score.getName());
        values.put(ScoreSQLiteHelper.COLUMN_SCORE, score.getScore());

        long insertId = database.insert(ScoreSQLiteHelper.TABLE_SCORE, null, values);

        Cursor cursor = database.query(ScoreSQLiteHelper.TABLE_SCORE,
                allColumns, ScoreSQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();
        Score newScore = cursorToScore(cursor);
        cursor.close();
        return insertId;
    }

    /**
     * Delete row from the table
     * @param score
     */
    public void deleteScore(Score score) {
        long id = score.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(ScoreSQLiteHelper.TABLE_SCORE, ScoreSQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    /**
     * Gets all the records, descending ordered by score
     * @return
     */
    public List<Score> getAllScores() {
        List<Score> comments = new ArrayList<>();

        Cursor cursor = database.query(ScoreSQLiteHelper.TABLE_SCORE,
                allColumns, null, null, null, null, ScoreSQLiteHelper.COLUMN_SCORE + " DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Score score = cursorToScore(cursor);
            comments.add(score);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Score cursorToScore(Cursor cursor) {
        Score score = new Score(cursor.getLong(0), cursor.getString(1), cursor.getInt(2));
        return score;
    }
}
