package com.example.kkaminets.myapplication4.listOne;

import android.provider.BaseColumns;

public class ServiceBase {

    private ServiceBase() {
    }
        // имплементим интерфейс BaseColumns который добавляет два поля "_id","_count"
    public static final class ServiceEntry implements BaseColumns {
        public static final String TABLE_NAME = "ServiceList";
        public static final String COLUMN_WORK = "work";
        public static final String COLUMN_COST = "cost";
        public static final String COLUMN_FILED_ONE = "filed_one";
        public static final String COLUMN_FILED_TWO = "filed_two";
        public static final String COLUMN_FILED_THREE = "filed_three";
        public static final String COLUMN_FILED_FOUR = "filed_four";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
