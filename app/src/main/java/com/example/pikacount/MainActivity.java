package com.example.pikacount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.pikacount.backend.CostDataBase;
import com.example.pikacount.myAdapter.ViewAdapter;
import com.example.pikacount.viewStructure.TodayCostController;
import com.example.pikacount.viewStructure.AnalyzeController;
import com.example.pikacount.viewStructure.SearchController;
import com.example.pikacount.viewStructure.PageView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    // ViewPager is used to slide the layout horizontally
    private ViewPager viewPager;

    // Used to collect all the layout
    private ArrayList<PageView> pageList;

    // Collect all the title of each layout
    private ArrayList<String> titleName;

    // The tabLayout is the top entry of the interface
    private TabLayout tabs;

    // My DataBase class used to control the data in primary data
    private CostDataBase costDb;

    // Release to let every class can easily access the context of MainActivity
    public static AppCompatActivity mainContext;


    public final static int ADD_NEW_DATA_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = this;

        initBackend();
        initSlideLayout();

//        costDb.newCost("Test", 200, "2019-12-11", "dinner");
    }

    private void initSlideLayout() {
        pageList = new ArrayList<>();
        // Create all layout
        pageList.add(new TodayCostController(MainActivity.this));
        pageList.add(new AnalyzeController(MainActivity.this));
        pageList.add(new SearchController(MainActivity.this));

        // Initialize tabLayout's items
        titleName = new ArrayList<>();
        titleName.add("Today Cost");
        titleName.add("Analyze");
        titleName.add("Search");

        // Initialize ViewPager
        viewPager = findViewById(R.id.viewPage);
        viewPager.setAdapter(new ViewAdapter(pageList, titleName));
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private void initBackend() {
        costDb = CostDataBase.getInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("in onActivityResult!!~~");
        if (requestCode == ADD_NEW_DATA_CODE) {
            costDb.newCost(data.getStringExtra("costName"),
                    data.getIntExtra("price", 0),
                    data.getStringExtra("date"),
                    data.getStringExtra("type"));
        }
    }
}
