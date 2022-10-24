package com.example.medreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //open a new activity when menu is clicked.
    public void onMenu(View view)
    {
        //if valid goes to the home activity.
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

    }

    //TODO
    public void onReminder(View view)
    {

    }

    //TO:DO
    public void openMedications(View view)
    {

    }

}