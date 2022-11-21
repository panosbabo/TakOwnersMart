package com.example.takownersmart;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Entities for the wishlist items
@Entity
public class Guitar {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "guitar_brand")
    public String guitarBrand;

    @ColumnInfo(name = "guitar_model")
    public String guitarModel;

    @ColumnInfo(name = "guitar_price")
    public String guitarPrice;

    @ColumnInfo(name = "guitar_owner")
    public String guitarOwner;

    @ColumnInfo(name = "owner_email")
    public String ownerEmail;
}