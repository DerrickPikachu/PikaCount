package com.example.pikacount.myDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pikacount.MainActivity;
import com.example.pikacount.NewDataActivity;
import com.example.pikacount.R;
import com.example.pikacount.backend.Cost;
import com.example.pikacount.backend.CostDataBase;

public class InformationDialog extends Dialog implements View.OnClickListener {

    private Cost data;

    private CostDataBase costDb;

    public InformationDialog(@NonNull Context context, Cost data) {
        super(context);
        this.data = data;

        // Get the DataBase instance
        costDb = CostDataBase.getInstance();

        // Set the layout to show the information
        setContentView(R.layout.information_layout);

        // Get all needed instance
        TextView nameTxv = findViewById(R.id.costName);
        TextView priceTxv = findViewById(R.id.priceData);
        TextView typeTxv = findViewById(R.id.typeData);
        TextView dateTxv = findViewById(R.id.dateData);

        // Set all TextView
        nameTxv.setText(data.getCostName());
        priceTxv.setText(Integer.toString(data.getPrice()));
        typeTxv.setText(data.getType());
        dateTxv.setText(data.getDate());

        // Set the listener to those button
        findViewById(R.id.close).setOnClickListener(this);
        findViewById(R.id.editBtn).setOnClickListener(this);
        findViewById(R.id.deleteBtn).setOnClickListener(this);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.close) {
            dismiss();
        }
        else if (v.getId() == R.id.editBtn) {
            Intent intent = new Intent(MainActivity.mainContext, NewDataActivity.class);

            intent.putExtra("name", data.getCostName());
            intent.putExtra("price", Integer.toString(data.getPrice()));
            intent.putExtra("type", data.getType());
            intent.putExtra("id", Integer.toString(data.getCostId()));
            intent.putExtra("date", data.getDate());

            dismiss();
            MainActivity.mainContext.startActivityForResult(intent, MainActivity.EDIT_PAST_DATA);
        }
        else if (v.getId() == R.id.deleteBtn) {
            costDb.delete(CostDataBase.TABLE_COST_NAME, data.getCostId());
            dismiss();
            MainActivity.todayCostLayout.updateList();
            MainActivity.analyzeLayout.updateChart();
            MainActivity.searchLayout.updateSearch();
        }
    }
}
