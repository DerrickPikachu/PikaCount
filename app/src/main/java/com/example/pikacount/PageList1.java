package com.example.pikacount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class PageList1 extends PageView {
    public PageList1(Context context) {
        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.sample_page, null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("pagelist1");
        addView(view);
    }
}
