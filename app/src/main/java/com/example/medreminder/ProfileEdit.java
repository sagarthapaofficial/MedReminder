package com.example.medreminder;
import static android.app.Activity.RESULT_OK;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfileEdit extends DialogFragment {

    private AppCompatActivity mClass;

    // One Button
    Button BSelectImage;
    private Button btnCancel,btnEdit;
    // One Preview Image
    ImageView IVPreviewImage;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText phoneNo;
    EditText address;
    EditText postalCode;
    Uri imageURL;

    DatabaseHelper db = null;

    User editedUser = null;



    //User user = (User) intent.getSerializableExtra("user");


    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;

    public ProfileEdit(){}

    public static ProfileEdit newInstance(String name){
        ProfileEdit meds = new ProfileEdit();
        Bundle args = new Bundle();
        args.putString("name", name);
        meds.setArguments(args);
        return meds;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){


        return inflater.inflate(R.layout.fragment_user_profile,container);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Intent intent = getActivity().getIntent();
        User user = (User) intent.getSerializableExtra("user");

        //
        firstName = view.findViewById(R.id.nameId);
        lastName = view.findViewById(R.id.lastNameId);
        email = view.findViewById(R.id.emailId);
        phoneNo = view.findViewById(R.id.phoneNoId);
        address = view.findViewById(R.id.addressId);
        postalCode = view.findViewById(R.id.postalcodeid);


        firstName.setText(user.getfirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        phoneNo.setText(user.getphoneNumber());
        address.setText(user.getAddress());
        postalCode.setText(user.getPostalCode());

        // register the UI widgets with their appropriate IDs
        IVPreviewImage = view.findViewById(R.id.profileImage);

        if(user.getImage() != "")
        {
            IVPreviewImage.setImageURI(Uri.parse(user.getImage()));
        }
        else
        {
           IVPreviewImage.setImageResource(R.drawable.ic_launcher_foreground);
        }


        // handle the Choose Image button to trigger
        // the image chooser function

            IVPreviewImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageChooser();
                }
            });

        btnCancel = view.findViewById(R.id.idCancel);

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnEdit = view.findViewById(R.id.idEdit);

        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {

                    editedUser = new User(

                            user.getPassword(),
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            address.getText().toString(),
                            postalCode.getText().toString(),
                            phoneNo.getText().toString(),
                            email.getText().toString(),
                            ""
                    );


                    //connects to the database
                    db = new DatabaseHelper(getActivity().getApplicationContext());
                    db.createDataBase   ();
                    //if addition of user is successful.
                    if (db.UpdateUser(editedUser)) {
                        dismiss();
                        System.out.println("user Edit");
                             Toast.makeText(getActivity(), "User Info edited", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        dismiss();
                        System.out.println("user Edit");
                        Toast.makeText(getActivity(), "User Info not edited", Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                }catch (Exception ex)
                {
                    System.out.println(ex.getStackTrace());
                }
            }
        });



    }

    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode

        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

//    // this function is triggered when user
//    // selects the image from the imageChooser
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                    imageURL = selectedImageUri;
                    //IVPreviewImage.setClickable(true);
                }
            }
        }
    }

    private Context getApplicationContext() {
        return mClass.getApplicationContext();
    }
}
