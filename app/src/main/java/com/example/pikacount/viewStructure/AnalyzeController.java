package com.example.pikacount.viewStructure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.pikacount.R;
import com.example.pikacount.viewStructure.PageView;

public class AnalyzeController extends PageView {

    private Context mainContext;

    public AnalyzeController(Context context) {
        super(context);
        this.mainContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.analyze_layout, null);
        addView(view);
    }
}
