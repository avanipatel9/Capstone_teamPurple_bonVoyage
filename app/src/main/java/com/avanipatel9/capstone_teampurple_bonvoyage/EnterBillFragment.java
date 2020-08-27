package com.avanipatel9.capstone_teampurple_bonvoyage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

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

    public EnterBillFragment(){

    }
    public static EnterBillFragment newInstance(){
        EnterBillFragment fragment=new EnterBillFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view =inflater.inflate(R.layout.fragment_enter_bill,container,false);
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
