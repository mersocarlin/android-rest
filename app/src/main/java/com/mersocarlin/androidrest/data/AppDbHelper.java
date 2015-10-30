package com.mersocarlin.androidrest.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.inject.Inject;
import com.mersocarlin.androidrest.data.contracts.PersonReader;

public class AppDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AndroidREST.db";


    private static final String INTEGER_TYPE = " INTEGER";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PersonReader.PersonEntry.TABLE_NAME + " (" +
                    PersonReader.PersonEntry._ID + " INTEGER PRIMARY KEY," +
                    PersonReader.PersonEntry.COLUMN_NAME_PERSON_ID + INTEGER_TYPE + COMMA_SEP +
                    PersonReader.PersonEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    PersonReader.PersonEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    PersonReader.PersonEntry.COLUMN_NAME_CREATED_AT + TEXT_TYPE + COMMA_SEP +
                    PersonReader.PersonEntry.COLUMN_NAME_HOME_PHONE + TEXT_TYPE + COMMA_SEP +
                    PersonReader.PersonEntry.COLUMN_NAME_MOBILE_PHONE + TEXT_TYPE + COMMA_SEP +
                    PersonReader.PersonEntry.COLUMN_NAME_WORK_PHONE + TEXT_TYPE +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PersonReader.PersonEntry.TABLE_NAME;


    @Inject
    public AppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(SQL_DELETE_ENTRIES);

        onCreate(db);
    }
}
