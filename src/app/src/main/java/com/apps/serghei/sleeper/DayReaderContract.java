package com.apps.serghei.sleeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by serghei on 7/23/2016.
 */
public final class DayReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DayReaderContract() {}

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

    public class DayReaderDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "DayReader.db";

        public DayReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
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
