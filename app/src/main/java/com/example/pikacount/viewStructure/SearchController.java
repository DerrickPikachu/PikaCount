package com.example.pikacount.viewStructure;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
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
        AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener {

    private AppCompatActivity mainContext;

    private View layout;

    private TextView setDateTxv;

    private TextView toDateTxv;

    private ListView searchList;

    private CostDataBase costDb;

    private ArrayList<Cost> result;

    private Spinner typeSpin;

    private int whoClicked;

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
        typeSpin = layout.findViewById(R.id.typeSpinner);
        toDateTxv = layout.findViewById(R.id.toDateTxv);

        // Set the listener
        setDateTxv.setOnClickListener(this);
        toDateTxv.setOnClickListener(this);
        searchList.setOnItemClickListener(this);
        typeSpin.setOnItemSelectedListener(this);

        // Initialize today information into toDateTxv
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        toDateTxv.setText(format.format(new Date()));
    }

    public void updateSearch() {
        String fromDateStr = setDateTxv.getText().toString();
        String toDateStr = toDateTxv.getText().toString();

        try {
            // Prepare the date object to query the DB
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date searchFromDate = format.parse(fromDateStr);
            Date searchToDate = format.parse(toDateStr);

            // Query to DB
            result = costDb.search(typeSpin.getSelectedItem().toString(), searchFromDate, searchToDate);
            // Set the ListView
            SearchListAdapter adapter = new SearchListAdapter(result);
            searchList.setAdapter(adapter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Set the TextView
        String dateStr = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
        if (whoClicked == R.id.setDateTxv) {
            setDateTxv.setText(dateStr);
        } else if (whoClicked == R.id.toDateTxv) {
            toDateTxv.setText(dateStr);
        }

        if (!setDateTxv.getText().equals(mainContext.getResources().getString(R.string.unset))) {
            updateSearch();
        }
    }

    @Override
    public void onClick(View v) {
        whoClicked = v.getId();
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(mainContext, this, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InformationDialog dialog = new InformationDialog(mainContext, result.get(position));
        dialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (!setDateTxv.getText().equals(mainContext.getResources().getString(R.string.unset))) {
            updateSearch();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
