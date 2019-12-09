package com.example.pikacount.myAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.pikacount.R;

import java.util.ArrayList;

public class ListAdapter extends BaseSwipeAdapter {

    Context mainContext;
    ArrayList<String> list;

    public ListAdapter(ArrayList<String> list, Context context) {
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
