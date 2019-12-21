package com.example.pikacount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.pikacount.backend.CostDataBase;
import com.example.pikacount.myAdapter.ViewAdapter;
import com.example.pikacount.viewStructure.TodayCostController;
import com.example.pikacount.viewStructure.AnalyzeController;
import com.example.pikacount.viewStructure.SearchController;
import com.example.pikacount.viewStructure.PageView;
=======
import androidx.viewpager.widget.ViewPager;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import com.example.pikacount.viewStructure.AnalyzeActivity;
import com.example.pikacount.viewStructure.PageView;
import com.example.pikacount.viewStructure.SearchActivity;
import com.example.pikacount.viewStructure.TodayCostActivity;
>>>>>>> 8ef4810534be616c5feb1ef06348630bb242dfa2
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD

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

    private TodayCostController todayCostLayout;

    private AnalyzeController analyzeLayout;

    private SearchController searchLayout;

    // Release to let every class can easily access the context of MainActivity
    public static AppCompatActivity mainContext;


    public final static int ADD_NEW_DATA_CODE = 200;

    public final static int EDIT_DATA_CODE = 300;

=======
    private ArrayList<String> tabName;
    private TabLayout tab;
    private ViewPager pager;
    private ArrayList<PageView> pageList;

>>>>>>> 8ef4810534be616c5feb1ef06348630bb242dfa2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        mainContext = this;

        initBackend();
        initSlideLayout();

//        costDb.newCost("Test", 200, "2019-12-11", "dinner");
    }

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
        }
        if (requestCode == EDIT_DATA_CODE && resultCode == RESULT_OK) {
            ArrayList<String> setting = new ArrayList<>();

            setting.add(data.getStringExtra("costName"));
            setting.add(data.getStringExtra("price"));
            setting.add(data.getStringExtra("date"));
            setting.add(data.getStringExtra("type"));

            costDb.editRow(setting, Integer.parseInt(data.getStringExtra("id")));
            todayCostLayout.updateList();
        }
=======

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
>>>>>>> 8ef4810534be616c5feb1ef06348630bb242dfa2
    }
}
