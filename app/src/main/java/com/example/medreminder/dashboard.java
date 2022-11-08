package com.example.medreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class dashboard extends AppCompatActivity {

    ImageButton medUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashbord);
        //adds a back arrow button.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        medUpdate = findViewById(R.id.med_id);

        medUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                MedUpdateDialog editNameDialogFragment = MedUpdateDialog.newInstance("Some Name");
                editNameDialogFragment.show(fm, "fragment_med_updates");
            }
        });
    }
}