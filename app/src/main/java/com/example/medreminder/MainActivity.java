package com.example.medreminder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    EditText userName=null;
    EditText password=null;
    User user=null;
    DBAdapter db=null;


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
        userName=findViewById(R.id.phone_email_id);
        password=findViewById(R.id.editTextTextPassword);

        try {

            user = new User(userName.getText().toString(), password.getText().toString());
            db = new DBAdapter(this);

            Cursor res=db.getUser(user);
            //checks if username is valid
            if(res.getCount()==0)
            {
                Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
            }else
            {
                //Check if user entered valid password.

                //code to get the data from cursor.
                StringBuffer buffer= new StringBuffer();

                while(res.moveToNext())
                {
                    if(password.getText().toString().equals(res.getString(1)))
                    {
                        //if valid goes to the home activity.
                        Intent intent = new Intent(this,HomeActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "Password is Incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }catch (Exception ex)
        {
            System.out.println(ex.getStackTrace());
        }


    }
}