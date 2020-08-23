package com.avanipatel9.capstone_teampurple_bonvoyage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class SigninActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    EditText t_phoneNumber;
    EditText t_password;
    private String phoneNumber;
    private String password;
    TextView message;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        t_phoneNumber = (EditText) findViewById(R.id.phone_number);
        t_password =(EditText) findViewById(R.id.login_password);
        message =  findViewById(R.id.message);
        submit = (Button) findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = t_phoneNumber.getText().toString();
                password = t_password.getText().toString();
                GlobalVariables a = (GlobalVariables)getApplication();
                a.setData(phoneNumber);
                myRef.child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String Password = (String)dataSnapshot.child("Password").getValue();
                        System.out.println(password + "   " + Password);
                        if(password.equals(Password)){
                            SharedPreferences.Editor editor = getSharedPreferences("phoneandpass", MODE_PRIVATE).edit();
                            editor.putString("phonenumber", phoneNumber);
                            editor.putString("password", Password);
                            editor.apply();

                            startActivity(new Intent(SigninActivity.this,HomeActivity.class));
                        }
                        else{
                            message.setText("you have entered wrong password");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}
