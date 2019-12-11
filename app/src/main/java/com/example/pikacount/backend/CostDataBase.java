package com.example.pikacount.backend;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.pikacount.MainActivity;

public class CostDataBase {

    private static CostDataBase instance;
    private static SQLiteDatabase SQLDb;

    private final String TABLE_COST_NAME = "Cost";
    private final String CREAT_COST = "CREATE TABLE IF NOT EXISTS " + TABLE_COST_NAME +
                                        "(name VARCHAR(32), " +
                                        "price INTEGER, " +
                                        "date DATE, " +
                                        "type VARCHAR(20))";

    private CostDataBase() {
        SQLDb = MainActivity.mainContext.openOrCreateDatabase("PikaCountDb", Context.MODE_PRIVATE, null);
        SQLDb.execSQL(CREAT_COST);
    }

    public static CostDataBase getInstance() {
        if (instance == null)
            instance = new CostDataBase();
        return instance;
    }
}
