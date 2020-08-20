package com.avanipatel9.capstone_teampurple_bonvoyage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TripActivity extends AppCompatActivity {
    private DatabaseReference mDatabase,m2Database;
    private String tripid, budget2;
    private String phonenumber;
    private String moneyspent;
    private TextView Budget;
    private TextView Destination,moneyspentbyme;
    private Button submit,location;
    private EditText enteredmoney;
    private String moneybythis;

    private ArrayAdapter adapter;
    ArrayList<String> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        ListView myListView = (ListView) findViewById(R.id.mylist);
        listItems=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        myListView.setAdapter(adapter);


        mDatabase = FirebaseDatabase.getInstance().getReference("trips");
        m2Database =  FirebaseDatabase.getInstance().getReference("users");

        GlobalVariables a = (GlobalVariables)getApplication();
        tripid = a.getTripid();
        SharedPreferences prefs = getSharedPreferences("phoneandpass", MODE_PRIVATE);
        phonenumber = prefs.getString("phonenumber", null);
        Destination = findViewById(R.id.destination);
        Budget = findViewById(R.id.moneyspent);
        submit = findViewById(R.id.submit);
        location = findViewById(R.id.addLocation);
        enteredmoney = findViewById(R.id.entermoney);
        moneyspentbyme = findViewById(R.id.moneyspentbyme);

//        adapter=new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                listItems);

        mDatabase.child(tripid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String destination = dataSnapshot.child("Destination").getValue().toString();
                moneyspent = dataSnapshot.child("moneyspent").getValue().toString();
                moneybythis = dataSnapshot.child(phonenumber).getValue().toString();
                Destination.setText(destination);
                System.out.print(moneyspent + "  " + moneyspentbyme);
                Budget.setText("total money spent in the group:: " +moneyspent);
                moneyspentbyme.setText("total money contributed by me::  " + moneybythis);
                System.out.println("   ");
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.getKey().toString();
                    final String m = ds.getValue().toString();
                    if(name.matches("[1-9][0-9]{9,10}")){
//                        System.out.println(name);
                        m2Database.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                System.out.println(dataSnapshot.child("Name").getValue()+"  " + m);
//                                adapter.add(m);
                                String name = (String)dataSnapshot.child("Name").getValue();
                                listItems.add(name + "    "  + m);
                                adapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.child(tripid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String destination = dataSnapshot.child("Destination").getValue().toString();
                        moneyspent = dataSnapshot.child("moneyspent").getValue().toString();
                        moneybythis = dataSnapshot.child(phonenumber).getValue().toString();
                        Destination.setText(destination);
                        Budget.setText("total money spent in the group:: " +moneyspent);
                        moneyspentbyme.setText("total money contributed by me::  " + moneybythis);
//                        listItems.remove(0);
                        listItems.clear();
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            String name = ds.getKey().toString();
                            final String m = ds.getValue().toString();
                            if(name.matches("[1-9][0-9]{9,10}")){
//                        System.out.println(name);
                                m2Database.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        System.out.println(dataSnapshot.child("Name").getValue()+"  " + m);
//                                adapter.add(m);
                                        String name = (String)dataSnapshot.child("Name").getValue();

                                        listItems.add(name + "    "  + m);
                                        adapter.notifyDataSetChanged();
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                int entered = Integer.parseInt(enteredmoney.getText().toString());
                int totalforthis = entered + Integer.parseInt(moneybythis);
                int total = entered + Integer.parseInt(moneyspent);
                String text = String.valueOf(total);
                String text1 = String.valueOf(totalforthis);
                mDatabase.child(tripid).child(phonenumber).setValue(totalforthis);
                mDatabase.child(tripid).child("moneyspent").setValue(total);
                Budget.setText("total money spent in the group:: " + text);
                moneyspentbyme.setText("total money contributed by me::  " + text1);
            }
        });




        //open maps
        location.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }
}