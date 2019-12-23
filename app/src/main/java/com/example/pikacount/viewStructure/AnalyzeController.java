package com.example.pikacount.viewStructure;

import android.app.slice.Slice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.pikacount.R;
import com.example.pikacount.backend.Cost;
import com.example.pikacount.backend.CostDataBase;
import com.example.pikacount.viewStructure.PageView;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class AnalyzeController extends PageView {

    private Context mainContext;

    private PieChartView pieChart;

    private View layout;

//    private ArrayList<SliceValue> slices;

    private CostDataBase costDb;

    private HashMap<String, Float> eachTypeCost;

    private String[] typeNames;

    private int[] colors = {R.color.color1, R.color.color2, R.color.color3, R.color.color4,
            R.color.color5, R.color.color6, R.color.color7, R.color.color8, R.color.color9,
            R.color.color10};

    public AnalyzeController(Context context) {
        super(context);
        this.mainContext = context;

        layout = LayoutInflater.from(context).inflate(R.layout.analyze_layout, null);
        addView(layout);

        costDb = CostDataBase.getInstance();
        typeNames = layout.getResources().getStringArray(R.array.type_list);

        buildPieChart(new Date());
    }

    private void buildPieChart(Date date) {
        pieChart = layout.findViewById(R.id.chart);

        /*
        TODO:
            Query a week or a month for pie chart (fix the CostDataBase.search() function)
         */
        ArrayList<Cost> data = costDb.search(date);
        eachTypeCost = new HashMap<>();

        if (!data.isEmpty()) {
            ArrayList<SliceValue> slices = new ArrayList<>();

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
                    slices.add(new SliceValue(value, layout.getResources().getColor(colors[i]))
                                                                        .setLabel(label));
                }
            }

            // Setting the pie chart
            PieChartData chartData = new PieChartData(slices);
            chartData.setHasLabels(true).setValueLabelTextSize(14);
            chartData.setHasCenterCircle(true).setCenterText1("Analyze")
                    .setCenterText1FontSize(20);
            pieChart.setPieChartData(chartData);
        }
    }

    public void updatePieChart() {
        /*
        TODO:
            After can create the pie chart for a month or a week,
            need to record the parameter of the last call of buildPieChart
         */
        buildPieChart(new Date());
    }
}
