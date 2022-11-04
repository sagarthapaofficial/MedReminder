package com.example.medreminder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import kotlin.jvm.Throws;

public class MainActivity extends AppCompatActivity {

    EditText userName=null;
    EditText password=null;
    User user=null;
    Cursor res=null;
    DatabaseHelper db= null;


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

    //Logins to the app.
    public void Login(View view)
    {
        userName=findViewById(R.id.phone_email_id);
        password=findViewById(R.id.editTextTextPassword);

        db= new DatabaseHelper(getApplicationContext());
           try {

            db.createDataBase();
            user=db.getUser(userName.getText().toString());

            if(user==null)
            {
                Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
            }else
            {

                if(password.getText().toString().equals(user.getPassword()))
                {
                    //if valid goes to the home activity.
                    Intent intent = new Intent(this,dashboard.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Password is Incorrect", Toast.LENGTH_SHORT).show();
                }
            }



        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }



}