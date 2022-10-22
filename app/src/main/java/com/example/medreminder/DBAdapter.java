package com.example.medreminder;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="medreminder.db";
    private static final String TABLE_NAME="users";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_USERNAME="username";

    private static final String COLUMN_PASSWORD="password";


    private static final String TABLE_CREATE="create table users (id INTEGER PRIMARY KEY AUTOINCREMENT,"+"username text not null, password text not null)";

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
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(query);
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
        public void addUser(User user) {
            db.execSQL("INSERT INTO TABLE_NAME(username, password) VALUES(" + user.getUsername() + "," + user.getPassword() + ")");
        }

        //get the user information.
        public User getContact(String userName) {
            User user1 = null;

            return user1;
        }


        //returns true if the user is present in db
        public Boolean UserExists(String username, String password) {

            return false;
        }



}
