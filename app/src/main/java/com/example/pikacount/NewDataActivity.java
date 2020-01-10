package com.example.pikacount;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static java.util.Calendar.*;

public class NewDataActivity extends AppCompatActivity
        implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener {

    private TextView nameTxv;

    private TextView priceTxv;

    private TextView dateTxv;

    private Spinner typeSpinner;

    private HashMap<String, Integer> spinnerMap;

    private Intent preData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);

        findViewById(R.id.insertBtn).setOnClickListener(this);
        nameTxv = findViewById(R.id.nameInput);
        priceTxv = findViewById(R.id.priceTxv);
        dateTxv = findViewById(R.id.dateTxv);
        typeSpinner = findViewById(R.id.typeSpinner);

        initMap();

        preData = getIntent();
        if (preData.hasExtra("name")) {
            nameTxv.setText(preData.getStringExtra("name"));
        }
        if (preData.hasExtra("price")) {
            priceTxv.setText(preData.getStringExtra("price"));
        }
        if (preData.hasExtra("type")) {
            typeSpinner.setSelection(spinnerMap.get(preData.getStringExtra("type")));
        }
        if (preData.hasExtra("date")) {
            dateTxv.setText(preData.getStringExtra("date"));
        }
        else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            dateTxv.setText(format.format(today));
        }

        dateTxv.setOnClickListener(this);
    }

    private void initMap() {
        // Get the type list
        String[] type = getResources().getStringArray(R.array.type_list);
        // Build the map with each type corresponding to the index
        spinnerMap = new HashMap<>();
        for (int i=0; i<type.length; i++) {
            spinnerMap.put(type[i], i);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dateTxv) {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, this, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
        else {
            System.out.println("insert!!");
            Intent data = new Intent();
            Date today = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            data.putExtra("costName", nameTxv.getText().toString());
            data.putExtra("price", priceTxv.getText().toString());
            data.putExtra("type", typeSpinner.getSelectedItem().toString());
            data.putExtra("date", dateTxv.getText().toString());
//            if (preData.hasExtra("date")) {
//                data.putExtra("date", preData.getStringExtra("date"));
//            } else {
//                data.putExtra("date", format.format(today));
//            }
            if (preData.hasExtra("id")) {
                data.putExtra("id", preData.getStringExtra("id"));
            }

            setResult(RESULT_OK, data);
            finish();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dateStr = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
        dateTxv.setText(dateStr);
    }
}
