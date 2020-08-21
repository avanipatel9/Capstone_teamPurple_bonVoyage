package com.avanipatel9.capstone_teampurple_bonvoyage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TripActivity extends AppCompatActivity {
    private DatabaseReference mDatabase,m2Database;
    private String tripid, budget2;
    private String phonenumber;
    private String moneyspent;
    private TextView Budget;
    private TextView Destination,moneyspentbyme,moneyspentdetail;
    private Button submit,location;
    private EditText enteredmoney,enterdetail;
    private String moneybythis;
    private Button addBillPic;
    Dialog dialog;
    int REQUEST_CAMERA = 100;
    File photoFile;
    Uri mediaUri;
    private ArrayAdapter adapter;
    ArrayList<String> listItems;
    int SELECT_IMAGES = 200;

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
        enterdetail = findViewById(R.id.enterdetail);

        moneyspentbyme = findViewById(R.id.moneyspentbyme);
       //moneyspentdetail = findViewById(R.id.moneyspentdetail);
        addBillPic = findViewById(R.id.uploadBillPicBtn);

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
                moneyspentbyme.setText("total money contributed by me:  " + moneybythis);
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
                enteredmoney.setText("");
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

        addBillPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showchooser();
            }
        });


    }

    public void showchooser() {
        //dialog intialization
        dialog = new Dialog(TripActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        TripActivity.this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.choose);
        dialog.setCancelable(true);

        LinearLayout camera_choose = (LinearLayout) dialog.findViewById(R.id.camera_picker);
        LinearLayout gallery_choose = (LinearLayout) dialog.findViewById(R.id.gallery_picker);


        camera_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("CAMERA >>>>>> ");
                try {
                    launchCameraForImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });

        gallery_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryImageIntent();
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    // for high quality image
    public void launchCameraForImage() throws IOException {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            photoFile = getPhotoFileUri();
            mediaUri = FileProvider.getUriForFile(TripActivity.this, "com.avanipatel9.capstone_teampurple_bonvoyage.provider", photoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mediaUri);
            if (intent.resolveActivity(TripActivity.this.getPackageManager()) != null) {
                startActivityForResult(intent, REQUEST_CAMERA);

            }

        }
         catch (Exception e) {

            try {
                Log.e("launchCameraForImage: ", e.getMessage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }

    void galleryImageIntent() {
        Intent intent = null;
        try {
            intent = ImagePicker.getPickImageIntent(TripActivity.this);
            startActivityForResult(intent, SELECT_IMAGES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getPhotoFileUri() {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(TripActivity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "firechat");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            // Log.e(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + System.currentTimeMillis() + ".jpg");

        return file;
    }
}