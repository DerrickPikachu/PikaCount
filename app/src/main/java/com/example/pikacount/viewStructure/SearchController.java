package com.example.pikacount.viewStructure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.pikacount.R;
import com.example.pikacount.viewStructure.PageView;

public class SearchController extends PageView {

    private Context mainContext;

    public SearchController(Context context) {
        super(context);
        this.mainContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.search_layout, null);
        addView(view);
    }
}
