package com.example.medreminder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Register(View view)
    {


        //passes the value to the second activity through the intent
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);

    }



    public void Login(View view)
    {
       //Check if user enters valid username and password

        //if valid goes to the home activity.
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);

    }
}