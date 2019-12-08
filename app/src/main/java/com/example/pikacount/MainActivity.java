package com.example.pikacount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import com.example.pikacount.viewStructure.AnalyzeActivity;
import com.example.pikacount.viewStructure.PageView;
import com.example.pikacount.viewStructure.SearchActivity;
import com.example.pikacount.viewStructure.TodayCostActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> tabName;
    private TabLayout tab;
    private ViewPager pager;
    private ArrayList<PageView> pageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);

        initView();

    }

    private void initView() {
        pageList = new ArrayList<>();
        tabName = new ArrayList<>();

        pageList.add(new TodayCostActivity(this));
        pageList.add(new AnalyzeActivity(this));
        pageList.add(new SearchActivity(this));

        tabName.add("Today Cost");
        tabName.add("Analyze");
        tabName.add("Search");

        ViewAdapter adapter = new ViewAdapter(pageList, tabName);
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);
    }
}
