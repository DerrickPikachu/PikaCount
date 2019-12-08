package com.example.pikacount.viewStructure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.pikacount.R;

public class TodayCostActivity extends PageView {
    public TodayCostActivity(Context context) {
        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.today_cost_layout, null);
        addView(view);
    }
}
