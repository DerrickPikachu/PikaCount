package com.example.pikacount.myAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.pikacount.R;
import com.example.pikacount.backend.Cost;

import java.util.ArrayList;

public class CostListAdapter extends BaseSwipeAdapter {

    Context mainContext;
    ArrayList<Cost> list;

    public CostListAdapter(ArrayList<Cost> list, Context context) {
        this.list = list;
        this.mainContext = context;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View view = LayoutInflater.from(mainContext).inflate(R.layout.list_item, null);
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
