package com.avanipatel9.capstone_teampurple_bonvoyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

        Bundle bundle = new Bundle();
        bundle  = getIntent().getExtras();
        final String phNumber = bundle.getString("PhNumber");
        System.out.println("In Signup class "+ phNumber);

        GlobalVariables a = (GlobalVariables)getApplication();
        a.setData(phNumber);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                databaseref = mDatabase.child("users").child(phNumber);
//                String id = mDatabase.push().getKey();
                databaseref = mDatabase.child(phNumber);
                et_name = (EditText)findViewById(R.id.name);
                et_city = (EditText)findViewById(R.id.city);
                et_password = findViewById(R.id.password);
                et_email = findViewById(R.id.email);
                et_confirmPassword = findViewById(R.id.confirm_password);
                message = findViewById(R.id.message);

                Name = et_name.getText().toString();
                Email = et_email.getText().toString();
                City = et_city.getText().toString();
                Password = et_password.getText().toString();
                ConfirmPassword = et_confirmPassword.getText().toString();

                if (!Email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                    message.setText("Invalid Email Address");
                }
                else {
                    
                }

            }
        });

    }
}