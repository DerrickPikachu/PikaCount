package com.example.pikacount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class NewDataActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nameTxv;
    private TextView priceTxv;
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
        System.out.println("insert!!");
        Intent data = new Intent();
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        data.putExtra("costName", nameTxv.getText().toString());
        data.putExtra("price", priceTxv.getText().toString());
        data.putExtra("type", typeSpinner.getSelectedItem().toString());
        if (preData.hasExtra("date")) {
            data.putExtra("date", preData.getStringExtra("date"));
        } else {
            data.putExtra("date", format.format(today));
        }
        if (preData.hasExtra("id")) {
            data.putExtra("id", preData.getStringExtra("id"));
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
