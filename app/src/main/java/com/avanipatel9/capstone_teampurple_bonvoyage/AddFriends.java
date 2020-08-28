package com.avanipatel9.capstone_teampurple_bonvoyage;

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
}
