package com.example.pikacount.viewStructure;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.pikacount.R;
import com.example.pikacount.backend.Cost;
import com.example.pikacount.backend.CostDataBase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class AnalyzeController extends PageView {

    private Context mainContext;

    private PieChartView pieChart;

    private ColumnChartView columnChart;

    private View layout;

//    private ArrayList<SliceValue> slices;

    private CostDataBase costDb;

    private HashMap<String, Float> eachTypeCost;

    private Date preQueryDate;

    private String[] typeNames;

    private final int[] colors = {R.color.color1, R.color.color2, R.color.color3, R.color.color4,
            R.color.color5, R.color.color6, R.color.color7, R.color.color8, R.color.color9,
            R.color.color10};

    @RequiresApi(api = Build.VERSION_CODES.O)
    public AnalyzeController(Context context) {
        super(context);
        this.mainContext = context;

        // Inflate the layout
        layout = LayoutInflater.from(context).inflate(R.layout.analyze_layout, null);
        addView(layout);

        // Initialize the DataBase instance
        costDb = CostDataBase.getInstance();
        typeNames = layout.getResources().getStringArray(R.array.type_list);

        // Get the two chart reference
        pieChart = layout.findViewById(R.id.chart);
        columnChart = layout.findViewById(R.id.columnChart);

        // Build up the Chart to the UI
        buildChart(new Date());

        // Set the three mode of the analyze
        initListener();
    }

    private void initListener() {
        // Init today button
        layout.findViewById(R.id.todayBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChart(0);
            }
        });

        // Init week button
        layout.findViewById(R.id.weekBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChart(6);
            }
        });

        // Init month button
        layout.findViewById(R.id.monthBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChart(30);
            }
        });
    }

    private void buildChart(Date date) {
        preQueryDate = date;

        // Query for a range of days
        ArrayList<Cost> data = costDb.search(date);
        eachTypeCost = new HashMap<>();

        if (!data.isEmpty()) {
            ArrayList<SliceValue> slices = new ArrayList<>();
            ArrayList<Column> columns = new ArrayList<>();

            // Push every data queried from database into the map
            for (int i = 0; i < data.size(); i++) {
                String type = data.get(i).getType();
                float value = data.get(i).getPrice();

                if (eachTypeCost.containsKey(type)) {
                    eachTypeCost.put(type, eachTypeCost.get(type) + value);
                } else {
                    eachTypeCost.put(type, value);
                }
            }

            // Traversal all the types by type name
            for (int i = 0; i < typeNames.length; i++) {
                if (eachTypeCost.containsKey(typeNames[i])) {
                    float value = eachTypeCost.get(typeNames[i]);
                    String label = typeNames[i];
                    ArrayList<SubcolumnValue> subColumn = new ArrayList<>();

                    // Add new pie chart data
                    slices.add(new SliceValue(value, layout.getResources().getColor(colors[i]))
                            .setLabel(label));

                    // Add new column chart data
                    subColumn.add(new SubcolumnValue(value, layout.getResources().getColor(colors[i]))
                            .setLabel(label + ":" + (int) value));
                    columns.add(new Column(subColumn)
                            .setHasLabels(true)
                            .setHasLabelsOnlyForSelected(true));
                }
            }

            // Set the pie chart data
            PieChartData chartData = new PieChartData(slices);
            // Set pie chart data label
            chartData.setHasLabels(true).setValueLabelTextSize(14);
            // Set the pie chart center circle
            chartData.setHasCenterCircle(true).setCenterText1("Analyze")
                    .setCenterText1FontSize(20);
            pieChart.setPieChartData(chartData);

            // Setting the column chart
            ColumnChartData columnData = new ColumnChartData(columns);
            columnChart.setColumnChartData(columnData);
        }
    }

    public void updateChart() {
        buildChart(preQueryDate);
    }

    public void updateChart(int numOfDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -numOfDays);
        buildChart(cal.getTime());
    }
}
