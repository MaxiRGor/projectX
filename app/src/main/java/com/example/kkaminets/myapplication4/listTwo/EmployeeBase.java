package com.example.kkaminets.myapplication4.listTwo;

import android.provider.BaseColumns;

public class EmployeeBase {

    private EmployeeBase(){
    }
    // имплементим интерфейс BaseColumns который добавляет два поля "_id","_count"
    public static final class EmployeeEntry implements BaseColumns {
        public static final String TABLE_NAME = "groceryList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_WORKER = "work";
        public static final String COLUMN_FILED = "filed";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
