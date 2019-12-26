package com.example.pikacount.viewStructure;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.swipe.util.Attributes;
import com.example.pikacount.MainActivity;
import com.example.pikacount.NewDataActivity;
import com.example.pikacount.R;
import com.example.pikacount.backend.Cost;
import com.example.pikacount.backend.CostDataBase;
import com.example.pikacount.myAdapter.CostListAdapter;
import com.example.pikacount.viewStructure.PageView;

import java.util.ArrayList;
import java.util.Date;

public class TodayCostController extends PageView {

    private AppCompatActivity mainContext;

    private View layout;

    private ArrayList<Cost> data;

    private ListView costListView;

    private TextView addNewTxv;

    private CostDataBase costDb;

    private TextView costTxv;

    public TodayCostController(AppCompatActivity context) {
        super(context);
        this.mainContext = context;

        layout = LayoutInflater.from(context).inflate(R.layout.today_cost_layout, null);
        addView(layout);

        costListView = layout.findViewById(R.id.costList);
        addNewTxv = layout.findViewById(R.id.addNewTxv);
        costTxv = layout.findViewById(R.id.cost);

        costDb = CostDataBase.getInstance();
        updateList();

        addNewTxv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.mainContext, NewDataActivity.class);
                MainActivity.mainContext.startActivityForResult(intent, MainActivity.ADD_NEW_DATA_CODE);
            }
        });

        // Test
//        findViewById(R.id.testBtn).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ArrayList<String> setting = new ArrayList<>();
//                setting.add("");
//                setting.add("100");
//                setting.add("");
//                setting.add("");
//                costDb.editRow(setting, 1);
//            }
//        });
    }

    @Override
    public void updateList() {
        // Query today consumes
        data = costDb.search(new Date());
        // Prepare the adapter of the list
        CostListAdapter listAdapter = new CostListAdapter(data, mainContext, this);
        listAdapter.setMode(Attributes.Mode.Single);
        // Set the adapter to the view
        costListView.setAdapter(listAdapter);

        // Count the total cost
        int count = 0;
        for (int i=0; i<data.size(); i++) {
            count += data.get(i).getPrice();
        }
        // Set the view text
        costTxv.setText(Integer.toString(count));
    }
}
