package com.example.pikacount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewDataActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nameTxv;
    private TextView priceTxv;
    private Spinner typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);

        findViewById(R.id.insertBtn).setOnClickListener(this);
        nameTxv = findViewById(R.id.nameInput);
        priceTxv = findViewById(R.id.priceTxv);
        typeSpinner = findViewById(R.id.typeSpinner);
    }

    @Override
    public void onClick(View v) {
        System.out.println("insert!!");
        Intent data = new Intent();
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        data.putExtra("name", nameTxv.getText());
        data.putExtra("price", Integer.parseInt(priceTxv.getText().toString()));
        data.putExtra("type", typeSpinner.getSelectedItem().toString());
        data.putExtra("date", format.format(today));

        setResult(RESULT_OK, data);
        finish();
    }
}
