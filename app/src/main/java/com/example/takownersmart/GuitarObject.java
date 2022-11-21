package com.example.takownersmart;


// Creating a guitar object to be used in a storing point in a csv file of the allegedly Takamine guitar owners.
public class GuitarObject {

    // All important private variables for Guitar Object
    private int uid;
    private String guitarModel;
    private String guitarPrice;
    private String guitarOwner;
    private String ownerEmail;

    // Constructor for the guitar object
    public GuitarObject(String guitarModel, String guitarPrice, String guitarOwner, String ownerEmail) {
        this.guitarModel = guitarModel;
        this.guitarPrice = guitarPrice;
        this.guitarOwner = guitarOwner;
        this.ownerEmail = ownerEmail;
    }

    //Setters and Getters of the Guitar Object
    public String getGuitarModel() {
        return guitarModel;
    }

    public void setGuitarModel(String guitarModel) {
        this.guitarModel = guitarModel;
    }

    public String getGuitarPrice() {
        return guitarPrice;
    }

    public void setGuitarPrice(String guitarPrice) {
        this.guitarPrice = guitarPrice;
    }

    public String getGuitarOwner() {
        return guitarOwner;
    }

    public void setGuitarOwner(String guitarOwner) {
        this.guitarOwner = guitarOwner;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public int getId() {
        return uid;
    }

    public void setId(int uid) {
        this.uid = uid;
    }
}
