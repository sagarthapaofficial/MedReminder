package com.example.medreminder;

public class User {

    private int id=0;
    private String username="";
    private String password="";

    public User(String username_, String password_)
    {
        this.username=username_;
        this.password=password_;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

}
