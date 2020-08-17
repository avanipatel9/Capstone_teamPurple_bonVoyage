package com.avanipatel9.capstone_teampurple_bonvoyage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.*;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AddTripActivity extends AppCompatActivity {
    private DatabaseReference mDatabase,m2Database;

    private String Destination,FriendPhone1,FriendPhone2,FriendPhone3;
    private int budget;
    private String DateOfReturn, DateOfDeparture;

    private static final String _CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STR_LENGTH = 12;

    private EditText t_Destination,t_FriendPhone1,t_FriendPhone2,t_FriendPhone3,t_budget,t_DateOfReturn,t_DateOfDeparture;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
    }
}