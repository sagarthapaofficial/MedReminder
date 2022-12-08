package com.example.medreminder;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;

public class MedRDialog extends DialogFragment implements View.OnClickListener {
    private AppCompatActivity mClass;
    static final int ALAR_REQ_CODE=100;
    //Buttons
    private Button selectDate;
    private Button selectTime;
    private Button cancel;

    private TextView setTime;
    private TextView setDate;
    private FragmentManager manager;
    private int mYear, mMonth, mDay, mHour, mMinute, mSecond;
    private int notificationId=1;

    private Button addReminder;

    private TextView title;
    private TextView description;

    TimePicker pickerId;

    public MedRDialog(){}

    public static MedRDialog newInstance(String name)
    {
        MedRDialog meds = new MedRDialog();
        Bundle args = new Bundle();
        args.putString("name", name);
        meds.setArguments(args);
        return meds;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        return inflater.inflate(R.layout.fragment_reminder,container);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //info
        title= view.findViewById(R.id.remTitile_id);
        description=view.findViewById(R.id.reDesc_id);


        //textView listeners
        setDate= view.findViewById(R.id.setDate_id);
        setTime=view.findViewById(R.id.setTime_id);


        selectDate= view.findViewById(R.id.selectDate_id);
        selectTime=view.findViewById(R.id.selecttime_id);
        cancel= view.findViewById(R.id.cancelBtn);


        addReminder=view.findViewById(R.id.addReminderbtn_id);

        selectDate.setOnClickListener(this);
        selectTime.setOnClickListener(this);
        addReminder.setOnClickListener(this);

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        String title = getArguments().getString("name", "Add Reminder");
        getDialog().setTitle(title);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

    }


    @Override
    public void onClick(View view) {
        TimePicker timePicker_id;
        if(view==selectDate)
        {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {


                            setDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if (view == selectTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            mSecond= c.get(Calendar.SECOND);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                            pickerId=timePicker;
                            String AM_PM ;
                            if(hourOfDay < 12) {
                                AM_PM = "AM";
                            } else {
                                AM_PM = "PM";
                                hourOfDay-=12;
                            }
                            setTime.setText(hourOfDay + ":" + minute +" "+ AM_PM);
                        }


                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if(view==addReminder)
        {

            Toast.makeText(getContext(), setDate.getText().toString()+ " "+ setTime.getText().toString(), Toast.LENGTH_SHORT).show();


            Intent intent= new Intent(getContext(), AlarmReceiver.class);

            intent.putExtra("notificationID",notificationId);
            intent.putExtra("reminderTitle",title.getText().toString());
            intent.putExtra("reminderDesc",description.getText().toString());

            PendingIntent alarmIntent = PendingIntent.getBroadcast(
                    getContext(), 0, intent, 0);

            //alarm manager object
            AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);

            int hour= pickerId.getHour();
            int minute= pickerId.getMinute();

            //Create time
            Calendar startTime= Calendar.getInstance();
            startTime.set(Calendar.HOUR_OF_DAY,hour);
            startTime.set(Calendar.MINUTE, minute);
            startTime.set(Calendar.SECOND,0);

            long alarmStarTime= startTime.getTimeInMillis();
            //Give a toast that alarm is set.
            Toast.makeText(getActivity(), "Reminder SET!", Toast.LENGTH_SHORT).show();
            //set alarm
            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStarTime, alarmIntent);

        }
    }


}
