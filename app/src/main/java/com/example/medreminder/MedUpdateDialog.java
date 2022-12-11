package com.example.medreminder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MedUpdateDialog extends DialogFragment {

    private AppCompatActivity mClass;
    private EditText medName,doses,usage,notes,link;
    private Button btnCancel,btnAdd;

    Medication medication = null;
    DatabaseHelper db = null;


    public MedUpdateDialog(){}

    public static MedUpdateDialog newInstance(String name){
        MedUpdateDialog meds = new MedUpdateDialog();
        Bundle args = new Bundle();
        args.putString("name", name);
        meds.setArguments(args);
        return meds;
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstance){
        return inflater.inflate(R.layout.fragment_med_update,container);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        medName =   view.findViewById(R.id.nameId);
        btnAdd =    view.findViewById(R.id.idEdit);
        btnCancel = view.findViewById(R.id.idCancel);
        doses = view.findViewById(R.id.lastNameId);
        usage = view.findViewById(R.id.emailId);
        notes = view.findViewById(R.id.phoneNoId);
        link = view.findViewById(R.id.addressId);

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dismiss();
            }
         });

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {

                    medication = new Medication(medName.getText().toString(),doses.getText().toString(),usage
                            .getText().toString(),notes.getText().toString(), link.getText().toString());

                    //connects to the database
                    db=new DatabaseHelper(getApplicationContext());
                    db.createDataBase   ();

                    //if addition of user is successful.
                    if (db.addMedication(medication)) {
                        System.out.println("user Added");
                   //     Toast.makeText(MedUpdateDialog.this, "Medication Registered",).show();
                    }
                    db.close();

                }catch (Exception ex)
                {
                    System.out.println(ex.getStackTrace());
                }
            }
        });

        String title = getArguments().getString("name", "Enter Name of medication");
        getDialog().setTitle(title);
        medName.requestFocus();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
    }

    private Context getApplicationContext() {
        return mClass.getApplicationContext();
    }


}
