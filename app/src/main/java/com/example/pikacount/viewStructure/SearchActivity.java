package com.example.pikacount.viewStructure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.pikacount.R;

public class SearchActivity extends PageView {
    public SearchActivity(Context context) {
        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.search_layout, null);
        addView(view);
    }
}
