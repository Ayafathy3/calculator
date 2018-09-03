package com.example.kiwan.calculatorapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OperationsHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "operationCalculator.dp";
    private static final int DATABASE_VERSION = 1;


    public OperationsHelper( Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate( SQLiteDatabase sqLiteDatabase ) {

        String SQL_CREATE_CALCULATE_TABLE = "CREATE TABLE " + OperationsContract.OperationEntry.TABLE_NAME + " (" +
                OperationsContract.OperationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                OperationsContract.OperationEntry.COLUMN_TIME_STAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                OperationsContract.OperationEntry.COLUMN_OPERATION + " TEXT NOT NULL);";

        // Execute the SQL statement
        sqLiteDatabase.execSQL(SQL_CREATE_CALCULATE_TABLE);
    }

    @Override
    public void onUpgrade( SQLiteDatabase sqLiteDatabase, int i, int i1 ) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OperationsContract.OperationEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


}
