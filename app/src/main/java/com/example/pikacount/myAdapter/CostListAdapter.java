package com.example.pikacount.myAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.pikacount.MainActivity;
import com.example.pikacount.NewDataActivity;
import com.example.pikacount.R;
import com.example.pikacount.backend.Cost;
import com.example.pikacount.backend.CostDataBase;
import com.example.pikacount.viewStructure.PageView;

import java.util.ArrayList;

public class CostListAdapter extends BaseSwipeAdapter {

    private AppCompatActivity mainContext;
    private ArrayList<Cost> list;
    private CostDataBase costDb;
    private PageView controlLayout;

    // The smallest id in the list
    // Use it to be the base when there is the need of getting the other id by adding an offset
    private int baseId;

    public CostListAdapter(ArrayList<Cost> list, AppCompatActivity context, PageView page) {
        this.list = list;

        if (list.isEmpty()) {
            baseId = -1;
        }
        else {
            baseId = list.get(0).getCostId();
        }

        this.mainContext = context;
        costDb = CostDataBase.getInstance();
        controlLayout = page;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        final View view = LayoutInflater.from(mainContext).inflate(R.layout.list_item, null);

        // Set delete operation
        view.findViewById(R.id.deleteTxv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ArrayList<String> condition = new ArrayList<>();
//                TextView costName = view.findViewById(R.id.costName);
//                condition.add(costName.getText().toString());
//                costDb.delete(CostDataBase.TABLE_COST_NAME, condition);
//                controlLayout.updateList();
                costDb.delete(CostDataBase.TABLE_COST_NAME, list.get(position).getCostId());
                controlLayout.updateList();
                MainActivity.analyzeLayout.updateChart();
            }
        });

        // Set edit operation
        view.findViewById(R.id.editTxv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent currentData = new Intent(mainContext, NewDataActivity.class);

                TextView costName = view.findViewById(R.id.costName);
                TextView price = view.findViewById(R.id.price);
                TextView type = view.findViewById(R.id.type);

                currentData.putExtra("name", costName.getText().toString());
                currentData.putExtra("price", price.getText().toString().substring(7));
                currentData.putExtra("type", type.getText().toString().substring(6));
                currentData.putExtra("id", Integer.toString(list.get(position).getCostId()));
                mainContext.startActivityForResult(currentData, MainActivity.EDIT_DATA_CODE);
            }
        });

        return view;
    }

    @Override
    public void fillValues(int position, View convertView) {
        Cost cost = list.get(position);

        TextView costName = convertView.findViewById(R.id.costName);
        TextView price = convertView.findViewById(R.id.price);
        TextView type = convertView.findViewById(R.id.type);
        TextView posTxv = convertView.findViewById(R.id.position);

        posTxv.setText(Integer.toString(position + 1) + ".");
        costName.setText(cost.getCostName());
        price.setText(convertView.getResources().getString(R.string.input_price) + " " + Integer.toString(cost.getPrice()));
        type.setText(convertView.getResources().getString(R.string.input_type) + " " + cost.getType());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
