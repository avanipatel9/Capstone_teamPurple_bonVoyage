package com.avanipatel9.capstone_teampurple_bonvoyage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private DatabaseReference mDatabase,databaseref;
    private Button submitButton;
    private EditText et_name,et_email,et_password,et_confirmPassword,et_city;
    private String Name,Email,Password,ConfirmPassword,City;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
}