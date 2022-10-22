package com.example.medreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText userName=null;
    EditText password=null;
    User user=null;
    DBAdapter db=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //adds a back arrow button.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void getRegister(View view)
    {
        //Open the Register activity
        userName=findViewById(R.id.username_id);
        password=findViewById(R.id.password_id);

        user= new User(userName.getText().toString(),password.getText().toString());
        db=new DBAdapter(this);
        db.open();
        System.out.println(user.getUsername());
        db.addUser(user);
    }
}