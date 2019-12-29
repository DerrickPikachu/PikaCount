package com.example.pikacount.backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pikacount.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CostDataBase {

    private static CostDataBase instance;

    private static SQLiteDatabase SQLDb;

    public static final String TABLE_COST_NAME = "Cost";

    private final String CREATE_COST = "CREATE TABLE IF NOT EXISTS " + TABLE_COST_NAME +
            "(name VARCHAR(32), " +
            "price INTEGER, " +
            "date DATE, " +
            "type VARCHAR(20), " +
            "costId INTEGER PRIMARY KEY AUTOINCREMENT);";

    private final String SEARCH_BY_DATE = "SELECT * " +
            "FROM " + TABLE_COST_NAME + " " +
            "WHERE date between '";

    private final String[] COST_ATTRIBUTE = {"name", "price", "date", "type"};

    private CostDataBase() {
        SQLDb = MainActivity.mainContext.openOrCreateDatabase("PikaCountDb", Context.MODE_PRIVATE, null);
        SQLDb.execSQL(CREATE_COST);
    }

    public static CostDataBase getInstance() {
        if (instance == null)
            instance = new CostDataBase();
        return instance;
    }

    public void newCost(String name, int price, String date, String type) {
        ContentValues cv = new ContentValues(4);

        cv.put("name", name);
        cv.put("price", price);
        cv.put("date", date);
        cv.put("type", type);

        SQLDb.insert(TABLE_COST_NAME, null, cv);
    }

    public ArrayList<Cost> search(Date date) {
        ArrayList<Cost> costList = new ArrayList<>();
        String forSearch;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        forSearch = SEARCH_BY_DATE + format.format(date) + "' and '" + format.format(new Date()) + "23:59:59'";
        Cursor cur = SQLDb.rawQuery(forSearch, null);

        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            Cost cost = new Cost(cur.getString(0), cur.getInt(1),
                    cur.getString(2), cur.getString(3), cur.getInt(4));
            costList.add(cost);
            cur.moveToNext();
        }

        return costList;
    }

    public void delete(String tableName, int id) {
        String deleteSQL = "DELETE FROM " + tableName + " " +
                            "WHERE costId = " + id + ";";
        SQLDb.execSQL(deleteSQL);
    }

    public void editRow(ArrayList<String> newSet, int id) {
        String editSQL = "UPDATE " + TABLE_COST_NAME + " ";
        String setting = "SET ";
        String condition = "WHERE costId=" + id + ";";

        for (int i=0; i<newSet.size(); i++) {
            if (newSet.get(i) != "") {
                if (!setting.equals("SET "))
                    setting = setting + ",";
                setting = setting + COST_ATTRIBUTE[i] + "='" + newSet.get(i) + "'";
            }
        }

        editSQL = editSQL + setting + " " + condition;
        SQLDb.execSQL(editSQL);
    }
}
