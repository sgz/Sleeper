package com.apps.serghei.sleeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by serghei on 7/23/2016.
 */
public final class DayReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DayReaderContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class DayEntry implements BaseColumns {
        public static final String TABLE_NAME = "day";
        public static final String COLUMN_NAME_DAY_ID = "dayid";
        public static final String COLUMN_NAME_CURRENT_DAY = "current_day";
        public static final String COLUMN_NAME_RISE_TIME = "rise_time";
        public static final String COLUMN_NAME_SLEEP_TIME = "sleep_time";
        public static final String COLUMN_NAME_HOURS_SLEPT = "hours_slept";
        public static final String COLUMN_NAME_COMMENTS = "comments";
        public static final String COLUMN_NAME_RATING = "rating";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String DOUBLE_TYPE = " REAL";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DayEntry.TABLE_NAME + " (" +
                    DayEntry._ID + " INTEGER PRIMARY KEY," +
                    DayEntry.COLUMN_NAME_DAY_ID + TEXT_TYPE + COMMA_SEP +
                    DayEntry.COLUMN_NAME_CURRENT_DAY + INTEGER_TYPE + COMMA_SEP +
                    DayEntry.COLUMN_NAME_RISE_TIME + INTEGER_TYPE + COMMA_SEP +
                    DayEntry.COLUMN_NAME_SLEEP_TIME + INTEGER_TYPE + COMMA_SEP +
                    DayEntry.COLUMN_NAME_HOURS_SLEPT + DOUBLE_TYPE + COMMA_SEP +
                    DayEntry.COLUMN_NAME_COMMENTS + TEXT_TYPE + COMMA_SEP +
                    DayEntry.COLUMN_NAME_RATING + INTEGER_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DayEntry.TABLE_NAME;

    public static class DayReaderDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "DayReader.db";

        public DayReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        // Inserts a day record and returns the record id
        public long insertDay(DayModel day) {
            // Gets the data repository in write mode
            SQLiteDatabase db = this.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(DayEntry.COLUMN_NAME_CURRENT_DAY, day.CurrentDay.getTimeInMillis());
            values.put(DayEntry.COLUMN_NAME_RISE_TIME, day.RiseTime.getTimeInMillis());
            values.put(DayEntry.COLUMN_NAME_SLEEP_TIME, day.SleepTime.getTimeInMillis());
            values.put(DayEntry.COLUMN_NAME_HOURS_SLEPT, day.HoursSlept);
            values.put(DayEntry.COLUMN_NAME_COMMENTS, day.Comments);
            values.put(DayEntry.COLUMN_NAME_RATING, day.Rating);

            // Insert the new row, returning the primary key value of the new row
            long newRowId;
            newRowId = db.insert(
                    DayEntry.TABLE_NAME,
                    null,
                    values);

            return newRowId;
        }

        public ArrayList<DayModel> getAllDays(){
            SQLiteDatabase db = this.getReadableDatabase();

            ArrayList<DayModel> days = new ArrayList<DayModel>();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    DayEntry._ID,
                    DayEntry.COLUMN_NAME_CURRENT_DAY,
                    DayEntry.COLUMN_NAME_RISE_TIME,
                    DayEntry.COLUMN_NAME_SLEEP_TIME,
                    DayEntry.COLUMN_NAME_COMMENTS,
                    DayEntry.COLUMN_NAME_HOURS_SLEPT,
                    DayEntry.COLUMN_NAME_RATING,
                    DayEntry.COLUMN_NAME_DAY_ID
            };

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    DayEntry.COLUMN_NAME_CURRENT_DAY + " ASC";

            Cursor c = db.query(
                    DayEntry.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );

            long millis;
            while(!c.isAfterLast()) {
                DayModel day = new DayModel();
                millis = c.getLong(c.getColumnIndex(DayEntry.COLUMN_NAME_CURRENT_DAY));
                day.CurrentDay.setTimeInMillis(millis);
                millis = c.getLong(c.getColumnIndex(DayEntry.COLUMN_NAME_RISE_TIME));
                day.RiseTime.setTimeInMillis(millis);
                millis = c.getLong(c.getColumnIndex(DayEntry.COLUMN_NAME_SLEEP_TIME));
                day.SleepTime.setTimeInMillis(millis);

                day.Comments = c.getString(c.getColumnIndex(DayEntry.COLUMN_NAME_COMMENTS));
                day.Rating = c.getInt(c.getColumnIndex(DayEntry.COLUMN_NAME_RATING));
                day.HoursSlept = c.getDouble(c.getColumnIndex(DayEntry.COLUMN_NAME_HOURS_SLEPT));

            }

            return days;
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
//            db.execSQL(SQL_DELETE_ENTRIES);
//            onCreate(db);
        }
//        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            onUpgrade(db, oldVersion, newVersion);
//        }
    }
}
