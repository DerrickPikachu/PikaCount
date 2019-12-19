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

        forSearch = SEARCH_BY_DATE + format.format(date) + "' and '" + format.format(date) + "23:59:59'";
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

    /*
    TODO:
        use id to delete the target
     */
    public void delete(String tableName, ArrayList<String> condition) {
        String deleteSQL = "DELETE FROM " + tableName + " " +
                            "WHERE ";
        String conditionSQL = "";

        for (int i=0; i<condition.size(); i++) {
            if (condition.get(i) != "") {
                if (conditionSQL != "")
                    conditionSQL = conditionSQL + "and";
                conditionSQL = conditionSQL + COST_ATTRIBUTE[i] + "='" + condition.get(i) + "'";
            }
        }

        deleteSQL = deleteSQL + conditionSQL + ";";
        SQLDb.execSQL(deleteSQL);
    }

    public void editRow(String name, int price, String date, String type) {

    }
}
