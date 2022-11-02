package com.example.medreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="MedReminder";
    private static final String TABLE_NAME="users";
    static final String TAG = "DBAdapter";


    private static final String TABLE_CREATE="create table users (USERID INTEGER PRIMARY key, Firusername TEXT PRIMARY KEY, password text not null)";

    final Context context;

     DatabaseHelper DBHelper;
     SQLiteDatabase db;


    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(TABLE_CREATE);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(query);
            onCreate(db);
        }
    }

        //---opens the database---
        public DBAdapter open() throws SQLException
        {
            db = DBHelper.getWritableDatabase();
            return this;
        }

        //---closes the database---
        public void close()
        {
            DBHelper.close();
        }

        //Adds the user to the database
        public Boolean addUser(User user) {
            SQLiteDatabase db=DBHelper.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put("username",user.getUsername());
            contentValues.put("password",user.getPassword());
            try {
                long result = db.insert("users", null, contentValues);

                if (result == -1) {
                    return false;

                }
            }catch (Exception ex)
            {
                System.out.println(ex.getStackTrace());
            }

            return true;
        }


    //Adds the user to the database
    public Boolean updateUser(User user) {
        SQLiteDatabase db=DBHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",user.getUsername());
        contentValues.put("password",user.getPassword());

        //this is for selecting row
        Cursor cursor =db.rawQuery("Select * from users where username = ?", new String[] {user.getUsername()});
        if(cursor.getCount()>0)
        {
            long result= db.update("users", contentValues, "username=?", new String[] {user.getUsername()});

            if(result==-1)
            {
                return false;

            }else
            {
                return true;
            }
        }else
        {
            return false;

        }

    }

    //Delete the user data
    public Boolean deleteUser(User user) {
        SQLiteDatabase db=DBHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",user.getUsername());
        contentValues.put("password",user.getPassword());

        //this is for selecting row
        Cursor cursor =db.rawQuery("Select * from users where username = ?", new String[] {user.getUsername()});
        if(cursor.getCount()>0)
        {
            long result= db.delete("users", "username=?", new String[] {user.getUsername()});

            if(result==-1)
            {
                return false;

            }else
            {
                return true;
            }
        }else
        {
            return false;

        }

    }


    public Cursor getUser(User user) {
        SQLiteDatabase db=DBHelper.getWritableDatabase();

        //this is for selecting row
        Cursor cursor =db.rawQuery("SELECT * from users WHERE username='"+ user.getUsername()+"'",null);
        return cursor;

    }


    //get the user information.
        public User getContact(String userName) {
            User user1 = null;
            db.execSQL("SELECT * from users WHERE username='sagar' ");

            return user1;
        }


        //returns true if the user is present in db
        public Boolean UserExists(String username, String password) {

            return false;
        }



}
