package com.avanipatel9.capstone_teampurple_bonvoyage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.HashMap;

public class ChartFragment extends Fragment {
    private View chartView;
    private PieChart pieChart1, pieChart2, pieChart3;
    private TextView pieChart1Title, pieChart2Title, pieChart3Title;

    public ChartFragment()
    {
    }
    public static ChartFragment newInstance(){
        ChartFragment fragment= new ChartFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        chartView = inflater.inflate(R.layout.fragment_chart,container,false);

        findViewsById();
        loadSummary();
        return chartView;
    }
    private void findViewsById() {
        pieChart1 = (PieChart) chartView.findViewById(R.id.pieChart1);
        pieChart2 = (PieChart) chartView.findViewById(R.id.pieChart2);
        pieChart3 = (PieChart) chartView.findViewById(R.id.pieChart3);
        pieChart1Title = (TextView)chartView.findViewById(R.id.pieChart1Title);
        pieChart2Title = (TextView)chartView.findViewById(R.id.pieChart2Title);
        pieChart3Title = (TextView)chartView.findViewById(R.id.pieChart3Title);
    }
    private void setupPieChart3(HashMap<String, Double> pieChart3Data) {

    }
    private void loadSummary() {
    }

}
