package com.example.pikacount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.pikacount.myAdapter.ViewAdapter;
import com.example.pikacount.viewStructure.TodayCostController;
import com.example.pikacount.viewStructure.AnalyzeController;
import com.example.pikacount.viewStructure.SearchController;
import com.example.pikacount.viewStructure.PageView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ArrayList<PageView> pageList;
    private ArrayList<String> titleName;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSlideLayout();
    }

    private void initSlideLayout() {
        pageList = new ArrayList<>();
        pageList.add(new TodayCostController(MainActivity.this));
        pageList.add(new AnalyzeController(MainActivity.this));
        pageList.add(new SearchController(MainActivity.this));

        titleName = new ArrayList<>();
        titleName.add("Today Cost");
        titleName.add("Analyze");
        titleName.add("Search");

        viewPager = findViewById(R.id.viewPage);
        viewPager.setAdapter(new ViewAdapter(pageList, titleName));
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

}
