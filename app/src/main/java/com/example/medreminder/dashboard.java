package com.example.medreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashbord);
        //adds a back arrow button.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}