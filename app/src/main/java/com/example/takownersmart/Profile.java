package com.example.takownersmart;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//below line is for setting table name.
@Entity(tableName = "profile")
public class Profile {
    //variable for our id.
    private int id;

    //below line is a variable for course name.
    @NonNull
    @PrimaryKey
    private String personUsername;
    //below line is use for course description.
    private String personEmail;
    //below line is use for course duration.
    private String personAddress;
    //below line is use for course duration.
    private String personGuitar;

    public Profile() {
        personUsername = null;
    }

    //below line we are creating constructor class.
//    public ProfileDetails(String personUsername, String personEmail, String personAddress, String personGuitar) {
//        this.personUsername = personUsername;
//        this.personEmail = personEmail;
//        this.personAddress = personAddress;
//        this.personGuitar = personGuitar;
//    }

    @NonNull
    public String getPersonUsername() {
        return personUsername;
    }

    public void setPersonUsername(@NonNull String personUsername) {
        this.personUsername = personUsername;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public String getPersonGuitar() {
        return personGuitar;
    }

    public void setPersonGuitar(String personGuitar) {
        this.personGuitar = personGuitar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
