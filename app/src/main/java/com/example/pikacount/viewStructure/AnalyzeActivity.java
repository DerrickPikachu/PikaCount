package com.example.pikacount.viewStructure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.pikacount.R;

public class AnalyzeActivity extends PageView {
    public AnalyzeActivity(Context context) {
        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.analyze_layout, null);
        addView(view);
    }
}
