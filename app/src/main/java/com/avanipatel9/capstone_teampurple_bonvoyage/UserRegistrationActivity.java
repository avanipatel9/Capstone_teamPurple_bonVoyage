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

public class UserRegistrationActivity extends AppCompatActivity {

    private DatabaseReference mDatabase,databaseref;
    private Button submitButton;
    private EditText et_name,et_email,et_password,et_confirmPassword,et_city;
    private String Name,Email,Password,ConfirmPassword,City;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        Bundle bundle = new Bundle();
        bundle  = getIntent().getExtras();
        final String phNumber = bundle.getString("PhNumber");
        System.out.println("In user registration class "+ phNumber);
//        mDatabase.child("users").setValue(phNumber);
        GlobalVariables a = (GlobalVariables)getApplication();
        a.setData(phNumber);
        submitButton = (Button) findViewById(R.id.submit_button);

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
                    if (Password.equals(ConfirmPassword) && Password.length() != 0) {

//                        String key = mDatabase.child("posts").push().getKey();
//                        Post post = new Post(userId, username, title, body);
//                        Map<String, Object> postValues = post.toMap();
//                        Map<String, Object> childUpdates = new HashMap<>();
//                        childUpdates.put("/"+phNumber +"/", { Name : Name});
//                        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);
//
//                        mDatabase.updateChildren(childUpdates);

                        databaseref.child("Name").setValue(Name);
//                        databaseref.child("Phonenumber").setValue(phNumber);
                        databaseref.child("Email").setValue(Email);
                        databaseref.child("City").setValue(City);
                        databaseref.child("Password").setValue(Password);
                        databaseref.child("Trips").setValue(" ");
                        message.setText("you have successfully registered");

                        SharedPreferences.Editor editor = getSharedPreferences("phoneandpass", MODE_PRIVATE).edit();
                        editor.putString("phonenumber", phNumber);
                        editor.putString("password", Password);
                        editor.apply();

                        Intent intent = new Intent(UserRegistrationActivity.this,HomeActivity.class);
                        startActivity(intent);

                    } else {
                        message.setText("password and confirm password should be same");
                    }
                }
            }
        });
    }
}