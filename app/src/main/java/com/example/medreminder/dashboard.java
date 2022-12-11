package com.example.medreminder;

import static android.app.PendingIntent.getActivity;

import static com.example.medreminder.R.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.Time;
import java.util.Calendar;

public class dashboard extends AppCompatActivity  {

    ImageButton medUpdate;
    ImageButton addReminder;
    ImageButton menu;

    private DatabaseHelper db = null;

    User user;

    //For the reminder fragment.

    private DatePicker date;
    private Time time;
    private TextView event;

    //btn to add Reminder
    private Button reminderBtn;
    private Button selectDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.dashbord);
        //adds a back arrow button.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        medUpdate = findViewById(id.med_id);

        //for reminder
        addReminder= findViewById(id.reminderbtn_id);

        medUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                MedUpdateDialog editNameDialogFragment = MedUpdateDialog.newInstance("Some Name");
                editNameDialogFragment.show(fm, "fragment_med_updates");
            }
        });

        //initially the fragment is to be hidden.
        //show Reminder when clicked on alarm icon
        addReminder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
//                ReminderFragment fragment= new ReminderFragment();
//                transaction.replace(id.frameLayout_id, fragment);
//                transaction.commit();


                FragmentManager fm = getSupportFragmentManager();
                MedRDialog editNameDialogFragment = MedRDialog.newInstance("Some Name");
                editNameDialogFragment.show(fm, "fragment_reDialog");
            }
        });

        //for menu - user profile edit
        Intent intent = getIntent();

        user = (User) intent.getSerializableExtra("user");

        menu = findViewById(id.menu_id);


        //profile menu image change to user's profile image
        if(user.getImage() != "")
        {


            menu.setImageURI(Uri.parse(user.getImage()));
        }
        else
        {
            menu.setImageResource(drawable.ic_launcher_foreground);
        }

        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intentToDialog = new Intent(dashboard.this, ProfileEdit.class);
                intentToDialog.putExtra("user", user);
                //start(intentToDialog); //Here is the exception

                FragmentManager fm = getSupportFragmentManager();
                ProfileEdit editUserInfoFragment = ProfileEdit.newInstance("Some Name");
                editUserInfoFragment.show(fm, "fragment_user_profile");
            }
        });




    }



    //show the  calendar
    public void showCalendar(View view)
    {


    }
}