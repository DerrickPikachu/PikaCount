package com.example.pikacount.viewStructure;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pikacount.R;
import com.example.pikacount.backend.Cost;
import com.example.pikacount.backend.CostDataBase;
import com.example.pikacount.myAdapter.SearchListAdapter;
import com.example.pikacount.myDialog.InformationDialog;
import com.example.pikacount.viewStructure.PageView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SearchController extends PageView
        implements DatePickerDialog.OnDateSetListener,
        View.OnClickListener,
        AdapterView.OnItemClickListener {

    private AppCompatActivity mainContext;

    private View layout;

    private TextView setDateTxv;

    private ListView searchList;

    private CostDataBase costDb;

    private ArrayList<Cost> result;

    public SearchController(AppCompatActivity context) {
        super(context);
        this.mainContext = context;

        layout = LayoutInflater.from(context).inflate(R.layout.search_layout, null);
        addView(layout);

        // Get instance of database
        costDb = CostDataBase.getInstance();

        // Get instance of the view
        setDateTxv = layout.findViewById(R.id.setDateTxv);
        searchList = layout.findViewById(R.id.searchedList);

        // Set the listener
        setDateTxv.setOnClickListener(this);
        searchList.setOnItemClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Set the TextView
        String dateStr = year + "/" + (month + 1) + "/" + dayOfMonth;
        setDateTxv.setText(dateStr);

        try {
            // Prepare the date object to query the DB
            SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
            Date searchDate = format.parse(dateStr);

            // Query to DB
            result = costDb.search(searchDate);
            // Set the ListView
            SearchListAdapter adapter = new SearchListAdapter(result);
            searchList.setAdapter(adapter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.setDateTxv) {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(mainContext, this, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InformationDialog dialog = new InformationDialog(mainContext, result.get(position));
        dialog.show();
    }
}
