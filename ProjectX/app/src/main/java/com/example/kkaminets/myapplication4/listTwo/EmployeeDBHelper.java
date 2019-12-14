package com.example.kkaminets.myapplication4.listTwo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
    //Наследуемся от класса SQLiteOpenHelper
public class EmployeeDBHelper extends SQLiteOpenHelper {
    // имя и номер версии таблицы
    public static final String DATABASE_NAME = "employeelis.db";
    public static final int DATABASE_VERSION = 1;

    public EmployeeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //вызов конструктора суперкласса и заполняем имя и версию
    }

    // создание БД со всеми полями
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
                EmployeeBase.EmployeeEntry.TABLE_NAME + " (" +                              // имя таблицы
                EmployeeBase.EmployeeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +   //id таблицы с автоинкрементированием и уникальным ключем.
                EmployeeBase.EmployeeEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                EmployeeBase.EmployeeEntry.COLUMN_WORKER + " TEXT NOT NULL, " +
                EmployeeBase.EmployeeEntry.COLUMN_FILED + " TEXT NOT NULL, " +
                EmployeeBase.EmployeeEntry.COLUMN_CATEGORY + " TEXT NOT NULL, " +           // 4 поля текстовых таблицы без пустых значениий
                EmployeeBase.EmployeeEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_GROCERYLIST_TABLE); // заполнение и создание таблицы данными полями
    }
        //onUpgrade метод обновления таблицы
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EmployeeBase.EmployeeEntry.TABLE_NAME);
        onCreate(db); // при закрытии таблицы записывает данные и пересоздает ее
    }
}
