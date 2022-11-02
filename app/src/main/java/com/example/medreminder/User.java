package com.example.medreminder;

public class User {

    private int id=0;
    private String password="";
    private String firstName="";
    private String lastName="";
    private String address="";
    private String postalCode="";
    private String phoneNumber="";
    private String Doctor="";
    private String email="";


    //constructor
    public User(String password_, String firstName_, String lastName_, String address_, String postalCode_, String phoneNumber_,  String email_)
    {
        this.password=password_;
        this.firstName=firstName_;
        this.lastName=lastName_;
        this.address=address_;
        this.postalCode=postalCode_;
        this.phoneNumber=phoneNumber_;
        this.email=email_;
    }


    //getters and setters

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password_)
    {
        this.password=password_;
    }

    public String getfirstName()
    {
        return this.firstName;
    }

    public void setfirstName(String firstname_)
    {
        this.firstName=firstname_;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastname_)
    {
        this.lastName=lastname_;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String address_)
    {
        this.address=address_;
    }

    public String getPostalCode()
    {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode_)
    {
        this.postalCode=postalCode_;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email_)
    {
        this.email=email_;
    }

    public String getDoctor()
    {
        return this.Doctor;
    }

    public void setDoctor(String doctor_)
    {
        this.Doctor=doctor_;
    }

    public String getphoneNumber()
    {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber_)
    {
        this.phoneNumber=phoneNumber_;
    }


}
