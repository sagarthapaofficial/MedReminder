package com.example.medreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DB_PATH = "/data/data/com.example.medreminder/databases/";

    public static String DB_NAME = "MedReminder.db";
    public static final int DB_VERSION = 1;

    private SQLiteDatabase myDB;
    private Context context;

    public DatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }


    public Boolean addUser(User user) {
        Cursor c=null;
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("FIRSTNAME",user.getfirstName());
        contentValues.put("LASTNAME",user.getLastName());
        contentValues.put("ADDRESS",user.getAddress());
        contentValues.put("POSTALCODE",user.getPostalCode());
        contentValues.put("PHONENUMBER",user.getphoneNumber());
        contentValues.put("DOCTOR"," ");
        contentValues.put("EMAIL",user.getEmail());
        contentValues.put("IMAGE",user.getImage());
        contentValues.put("PASSWORD",user.getPassword());

        c = db.rawQuery("SELECT * FROM USER WHERE EMAIL='" +user.getEmail()+"'" , null);

        System.out.println(c.getCount());
        if(c.getCount()==0) {

            try {
                long result = db.insert("USER", null, contentValues);

                if (result == -1) {
                    return false;

                } else {
                    return true;
                }
            } catch (Exception ex) {
                System.out.println(ex.getStackTrace());
            }
        }
        return false;
    }


    //retuns the cursor i.e the row
    public User getUser(String userName)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor c=null;
        User user=null;
        try
        {
            c = db.rawQuery("SELECT * FROM USER WHERE EMAIL='" +userName+"' OR PHONENUMBER='"+ userName+"'" , null);
            user= new User();
            //points the cursor to the first result.
            c.moveToFirst();
            user.setfirstName(c.getString(1));
            user.setLastName(c.getString(2));
            user.setAddress(c.getString(3));
            user.setPostalCode(c.getString(4));
            user.setPhoneNumber(c.getString(5));
            user.setDoctor(c.getString(6));
            user.setEmail(c.getString(7));
            user.setImage(c.getString(8));
            user.setPassword(c.getString(9));

            return user;

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return user;

    }

    //update the userInfo
    public Boolean UpdateUser(User user)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("FIRSTNAME",user.getfirstName());
        contentValues.put("LASTNAME",user.getLastName());
        contentValues.put("ADDRESS",user.getAddress());
        contentValues.put("POSTALCODE",user.getPostalCode());
        contentValues.put("PHONENUMBER",user.getphoneNumber());
        contentValues.put("DOCTOR"," ");
        contentValues.put("EMAIL",user.getEmail());
        contentValues.put("IMAGE",user.getImage());
        contentValues.put("PASSWORD",user.getPassword());

        try {
            String query="EMAIL='" +user.getEmail()+"' OR PHONENUMBER='"+ user.getphoneNumber()+"'";
            long result = db.update("USER", contentValues,query,null);

            if (result == -1) {
                return false;

            }else
            {
                return true;
            }
        }catch (Exception ex)
        {
            System.out.println(ex.getStackTrace());
        }

        return false;

    }


    public Boolean addMedication(Medication medication) {
        Cursor c=null;
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        //user id?
        contentValues.put("NAME",medication.getName());
        contentValues.put("DOSES",medication.getDoses());
        contentValues.put("USAGE",medication.getUsage());
        contentValues.put("NOTES",medication.getNotes());
        contentValues.put("LINK",medication.getLink());

      //  c = db.rawQuery("SELECT * FROM USER WHERE USERID ='" +medication.get()+"'" , null);

        System.out.println(c.getCount());
        if(c.getCount()==0) {

            try {
                long result = db.insert("MEDICATION", null, contentValues);

                if (result == -1) {
                    return false;

                } else {
                    return true;
                }
            } catch (Exception ex) {
                System.out.println(ex.getStackTrace());
            }
        }
        return false;
    }


    //retuns the cursor i.e the row

    public Medication getMedication(String userId)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor c=null;
        Medication medication=null;
        try
        {
            c = db.rawQuery("SELECT * FROM MEDICATION WHERE USERID='" +userId+"'",null);
            medication = new Medication();
            //points the cursor to the first result.
            c.moveToFirst();
            medication.setName(c.getString(1));
            medication.setDoses(c.getString(2));
            medication.setUsage(c.getString(3));
            medication.setNotes(c.getString(4));
            medication.setLink(c.getString(5));

            return medication;

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return medication;

    }

    //update the userInfo
//    public Boolean UpdateMedication(Medication medication)
//    {
//        SQLiteDatabase db= this.getWritableDatabase();
//        ContentValues contentValues=new ContentValues();
//        contentValues.put("NAME",medication.getName());
//        contentValues.put("DOSES",medication.getDoses());
//        contentValues.put("USAGE",medication.getUsage());
//        contentValues.put("NOTES",medication.getNotes());
//        contentValues.put("LINK",medication.getLink());
//
//        try {
//           // String query="SELECT * FROM MEDICATION WHERE USERID='" +medication.getUserId()+"'";
//           // long result = db.update("MEDICATION", contentValues,query,null);
//
//            if (result == -1) {
//                return false;
//
//            }else
//            {
//                return true;
//            }        }catch (Exception ex)
//        {
//            System.out.println(ex.getStackTrace());
//        }
//
//        return false;
//
//    }



    @Override
    public synchronized void close(){
        if(myDB!=null){
            myDB.close();
        }
        super.close();
    }

    private boolean checkDataBase() {
        SQLiteDatabase tempDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            tempDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            Log.e("tle99 - check", e.getMessage());
        }
        if (tempDB != null)
            tempDB.close();
        return tempDB != null ? true : false;
    }

    /***
     * Copy database from source code assets to device
     * @throws IOException
     */
    public void copyDataBase() throws IOException{
        try {
            InputStream myInput = context.getAssets().open(DB_NAME);
            String outputFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;

            while((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            Log.e("tle99 - copyDatabase", e.getMessage());
        }

    }

    /***
     * Open database
     * @throws SQLException
     */
    public void openDataBase() throws SQLException{
        String myPath = DB_PATH + DB_NAME;
        myDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }


    /***
     * Check if the database doesn't exist on device, create new one
     * @throws IOException
     */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {

        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e("tle99 - create", e.getMessage());
            }
        }
    }









}