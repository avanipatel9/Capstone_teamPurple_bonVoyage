package com.avanipatel9.capstone_teampurple_bonvoyage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChartFragment extends Fragment {
    private View chartView;
    //private PieChart pieChart1, pieChart2, pieChart3;
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
    private void loadSummary() {
    }
    private void findViewsById() {
    }
}
