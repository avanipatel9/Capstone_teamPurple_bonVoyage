package com.avanipatel9.capstone_teampurple_bonvoyage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class TripActivity extends AppCompatActivity {
    private DatabaseReference mDatabase,m2Database;
    private String tripid, budget2;
    private String phonenumber;
    private String moneyspent;
    private TextView Budget;
    private TextView Destination,moneyspentbyme;
    private Button submit;
    private EditText enteredmoney;
    private String moneybythis;

    private ArrayAdapter adapter;
    ArrayList<String> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
    }
}