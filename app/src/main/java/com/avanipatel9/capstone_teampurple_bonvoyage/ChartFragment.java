package com.avanipatel9.capstone_teampurple_bonvoyage;

import android.view.View;
import android.widget.TextView;

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
}
