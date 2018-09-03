package com.example.kiwan.calculatorapp;

import android.provider.BaseColumns;

public class OperationsContract {

    private OperationsContract() {
    }

    public static class OperationEntry implements BaseColumns {

        public static final String TABLE_NAME = "operationData";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_OPERATION = "operations";
        public static final String COLUMN_TIME_STAMP = "timestamp";
    }
}
