package com.example.medreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class RegisterActivity extends AppCompatActivity {

    EditText password=null;
    EditText firstName=null;
    EditText lastName=null;
    EditText email=null;
    EditText phoneNumber=null;
    EditText postalCode=null;
    EditText address=null;

    //
    ImageView image = null;

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
        password=findViewById(R.id.passwordid);
        lastName=findViewById(R.id.lastname_id);
        email=findViewById(R.id.email_id);
        phoneNumber=findViewById(R.id.phoneNumber_id);
        postalCode=findViewById(R.id.postalcodeid);
        address=findViewById(R.id.address_id);

        //
        //image.setImageResource(R.drawable.ic_launcher_foreground);
        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);


        try {

            user = new User(password.getText().toString(),firstName.getText().toString(),lastName
                    .getText().toString(),address.getText().toString(), postalCode.getText().toString(),phoneNumber.getText().toString(), email.getText().toString(), imageString
            );

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