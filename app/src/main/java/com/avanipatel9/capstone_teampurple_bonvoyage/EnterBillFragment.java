package com.avanipatel9.capstone_teampurple_bonvoyage;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.Fragment;

public class EnterBillFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText billTitle;
    private EditText billAmount;
    private ListView friendsListView;
    private Button addBillButton;
    private ArrayList<String> checkedEmails;
    private View view;
    private ArrayList<UsersDataModel> friendsDataModels;
    private UsersListViewAdapter adapter;
    private String itemName="", itemCost="";
    private HashMap<String,Double> capturedItems;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
