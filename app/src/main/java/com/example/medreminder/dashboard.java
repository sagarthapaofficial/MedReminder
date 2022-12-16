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
import android.os.Bundle;
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

    private DatabaseHelper db = null;

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

    }



    //show the  calendar
    public void showCalendar(View view)
    {


    }
}