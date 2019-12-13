package com.example.pikacount.viewStructure;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

    private Context mainContext;
    private View layout;
    private ArrayList<String> data;
    private ListView costListView;
    private TextView addNewTxv;
    private CostDataBase costDb;

    public TodayCostController(Context context) {
        super(context);
        this.mainContext = context;

        layout = LayoutInflater.from(context).inflate(R.layout.today_cost_layout, null);
        addView(layout);

        costListView = layout.findViewById(R.id.costList);
        addNewTxv = layout.findViewById(R.id.addNewTxv);
        costDb = CostDataBase.getInstance();

        data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");

        CostListAdapter listAdapter = new CostListAdapter(data, mainContext);
        listAdapter.setMode(Attributes.Mode.Single);
        costListView.setAdapter(listAdapter);

        addNewTxv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.mainContext, NewDataActivity.class);
                MainActivity.mainContext.startActivityForResult(intent, MainActivity.ADD_NEW_DATA_CODE);
            }
        });

        findViewById(R.id.testBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Cost> list = costDb.searchByDate(new Date());

                for (int i=0; i<list.size(); i++)
                    System.out.println(list.get(i).getCostName());
            }
        });
    }
}
