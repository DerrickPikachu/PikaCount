package com.example.pikacount;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

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

    public static TodayCostController todayCostLayout;

    public static AnalyzeController analyzeLayout;

    public static SearchController searchLayout;

    // Release to let every class can easily access the context of MainActivity
    public static AppCompatActivity mainContext;


    public final static int ADD_NEW_DATA_CODE = 200;

    public final static int EDIT_DATA_CODE = 300;

    public final static int EDIT_PAST_DATA = 350;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = this;

        initBackend();
        initSlideLayout();

//        costDb.newCost("Test", 200, "2019-12-11", "dinner");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initSlideLayout() {
        pageList = new ArrayList<>();
        // Create all layout

        todayCostLayout = new TodayCostController(MainActivity.this);
        analyzeLayout = new AnalyzeController(MainActivity.this);
        searchLayout = new SearchController(MainActivity.this);

        pageList.add(todayCostLayout);
        pageList.add(analyzeLayout);
        pageList.add(searchLayout);

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
        if (requestCode == ADD_NEW_DATA_CODE && resultCode == RESULT_OK) {
            costDb.newCost(data.getStringExtra("costName"),
                    Integer.parseInt(data.getStringExtra("price")),
                    data.getStringExtra("date"),
                    data.getStringExtra("type"));
            todayCostLayout.updateList();
            analyzeLayout.updateChart();
        }
        if (requestCode == EDIT_DATA_CODE && resultCode == RESULT_OK) {
            ArrayList<String> setting = new ArrayList<>();

            setting.add(data.getStringExtra("costName"));
            setting.add(data.getStringExtra("price"));
            setting.add(data.getStringExtra("date"));
            setting.add(data.getStringExtra("type"));

            costDb.editRow(setting, Integer.parseInt(data.getStringExtra("id")));
            todayCostLayout.updateList();
            analyzeLayout.updateChart();
        }
        if (requestCode == EDIT_PAST_DATA && resultCode == RESULT_OK) {
            ArrayList<String> setting = new ArrayList<>();

            setting.add(data.getStringExtra("costName"));
            setting.add(data.getStringExtra("price"));
            setting.add(data.getStringExtra("date"));
            setting.add(data.getStringExtra("type"));

            costDb.editRow(setting, Integer.parseInt(data.getStringExtra("id")));
            searchLayout.updateSearch();
            todayCostLayout.updateList();
            analyzeLayout.updateChart();
        }
    }
}
