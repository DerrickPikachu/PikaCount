package com.example.pikacount.viewStructure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.pikacount.R;
import com.example.pikacount.viewStructure.PageView;

public class TodayCostController extends PageView {
    public TodayCostController(Context context) {
        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.today_cost_layout, null);
        addView(view);
    }
}
