package com.example.medreminder;

public class Medication {
    private int id=0;
    private int userId=0;
    private String name="";
    private String doses="";
    private String usage="";
    private String notes="";
    private String link="";

    //Constructor
    public Medication(String name_, String doses_, String usage, String notes, String link)
    {
        this.name=name_;
        this.doses=doses_;
        this.usage=usage;
        this.notes=notes;
        this.link=link;
    }

    public Medication() {

    }

    //getter and setters
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getDoses() {
        return doses;
    }

    public void setDoses(String doses) {
        this.doses = doses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
