package com.avanipatel9.capstone_teampurple_bonvoyage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class AddFriends extends Fragment implements AdapterView.OnItemClickListener {
    private EditText searchQuery;
    private ListView usersListView;
    private View view;
    private UsersListViewAdapter adapter;

    public AddFriends(){
    }
    public static AddFriends newInstance(){
        AddFriends fragment=new AddFriends();
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view =inflater.inflate(R.layout.)
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
