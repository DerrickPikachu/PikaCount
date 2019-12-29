package com.example.pikacount.viewStructure;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pikacount.R;
import com.example.pikacount.viewStructure.PageView;

import java.util.Calendar;

public class SearchController extends PageView
        implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private AppCompatActivity mainContext;

    private View layout;

    private TextView setDateTxv;

    public SearchController(AppCompatActivity context) {
        super(context);
        this.mainContext = context;

        layout = LayoutInflater.from(context).inflate(R.layout.search_layout, null);
        addView(layout);

        setDateTxv = layout.findViewById(R.id.setDateTxv);
        setDateTxv.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dateStr = year + "/" + month + "/" + dayOfMonth;
        setDateTxv.setText(dateStr);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.setDateTxv) {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(mainContext, this, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }
}
