package com.example.medreminder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

        try {

            user = new User(userName.getText().toString(), password.getText().toString());
            db = new DBAdapter(this);
            //db.open();

            if (db.addUser(user)) {
                Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
            } else {
                System.out.println("Had Error on insertion");
                Toast.makeText(RegisterActivity.this, "User Already Exists", Toast.LENGTH_LONG).show();
            }

        }catch (Exception ex)
        {
            System.out.println(ex.getStackTrace());
        }

//        Cursor res=db.getUser(user);
//        if(res.getCount()==0)
//        {
//            Toast.makeText(RegisterActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
//        }
//
//        StringBuffer buffer= new StringBuffer();
//        while(res.moveToNext())
//        {
//            buffer.append("username"+ res.getString(1)+"\n");
//            buffer.append("password"+ res.getString(2)+"\n");
//        }
//
//
//        //Shows the value of buffer in the alert dialog.
//        AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
//        builder.setCancelable(true);
//        builder.setMessage(buffer.toString());
//        builder.show();

    }
}