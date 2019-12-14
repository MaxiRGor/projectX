package com.example.kkaminets.myapplication4.listOne;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
    //Наследуемся от класса SQLiteOpenHelper
public class ServiceDBHelper extends SQLiteOpenHelper {
    // имя и номер версии таблицы
    public static final String DATABASE_NAME = "servicelis.db";
    public static final int DATABASE_VERSION = 1;

    public ServiceDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //вызов конструктора суперкласса и заполняем имя и версию
    }

    // создание БД со всеми полями
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
                ServiceBase.ServiceEntry.TABLE_NAME + " (" +                            // имя таблицы
                ServiceBase.ServiceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + //id таблицы с автоинкрементированием и уникальным ключем.
                ServiceBase.ServiceEntry.COLUMN_WORK + " TEXT NOT NULL, " +
                ServiceBase.ServiceEntry.COLUMN_COST + " TEXT NOT NULL, " +
                ServiceBase.ServiceEntry.COLUMN_FILED_ONE + " TEXT NOT NULL, " +
                ServiceBase.ServiceEntry.COLUMN_FILED_TWO + " TEXT NOT NULL, " +
                ServiceBase.ServiceEntry.COLUMN_FILED_THREE + " TEXT NOT NULL, " +
                ServiceBase.ServiceEntry.COLUMN_FILED_FOUR + " TEXT NOT NULL, " + // 4 поля текстовых таблицы без пустых значениий
                ServiceBase.ServiceEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_GROCERYLIST_TABLE); // заполнение и создание таблицы данными полями
    }
        //onUpgrade метод обновления таблицы
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ServiceBase.ServiceEntry.TABLE_NAME);
        onCreate(db);  // при закрытии таблицы записывает данные и пересоздает ее
    }
}
