package com.example.pikacount.myAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pikacount.MainActivity;
import com.example.pikacount.R;
import com.example.pikacount.backend.Cost;

import java.util.ArrayList;

public class SearchListAdapter extends BaseAdapter {

    private ArrayList<Cost> costList;

    private AppCompatActivity mainActivity;

    public SearchListAdapter(ArrayList<Cost> list) {
        costList = list;
        mainActivity = MainActivity.mainContext;
    }

    @Override
    public int getCount() {
        return costList.size();
    }

    @Override
    public Object getItem(int position) {
        return costList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
        TODO:
            Must complete this function,
            this function is used to generate item automatically
         */
        View v = mainActivity.getLayoutInflater().inflate(R.layout.searched_item, parent, false);

        // Get the instance
        TextView name = v.findViewById(R.id.costName);
        TextView price = v.findViewById(R.id.costPrice);
        TextView date = v.findViewById(R.id.costDate);

        // Set the data to the textView
        name.setText(costList.get(position).getCostName());
        price.setText(Integer.toString((costList.get(position).getPrice())));
        date.setText(costList.get(position).getDate());

        return v;
    }
}
