package com.example.medreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText password=null;
    EditText firstName=null;
    EditText lastName=null;
    EditText email=null;
    EditText phoneNumber=null;
    EditText postalCode=null;
    EditText address=null;


    User user=null;
    DatabaseHelper db=null;

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
        firstName=findViewById(R.id.firstname_id);
        password=findViewById(R.id.password_id);
        lastName=findViewById(R.id.lastname_id);
        email=findViewById(R.id.email_id);
        phoneNumber=findViewById(R.id.phoneNumber_id);
        postalCode=findViewById(R.id.postalcode_id);
        address=findViewById(R.id.address_id);

        try {

            user = new User(password.getText().toString(),firstName.getText().toString(),lastName
                    .getText().toString(),address.getText().toString(), postalCode.getText().toString(),phoneNumber.getText().toString(), email.getText().toString());

            //connects to the database
            db=new DatabaseHelper(getApplicationContext());
            db.createDataBase();

            //if addition of user is successful.
            if (db.addUser(user)) {
                System.out.println("user Added");
                Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
            } else {
                System.out.println("Had Error on insertion");
                Toast.makeText(RegisterActivity.this, "User Already Exists", Toast.LENGTH_LONG).show();
            }
            db.close();

        }catch (Exception ex)
        {
            System.out.println(ex.getStackTrace());
        }


    }
}