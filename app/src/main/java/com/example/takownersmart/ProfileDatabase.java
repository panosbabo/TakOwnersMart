package com.example.takownersmart;

//import android.arch.persistence.room.Database;
//import android.arch.persistence.room.RoomDatabase;
//import android.arch.persistence.room.TypeConverters;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Profile.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class ProfileDatabase extends RoomDatabase {
    public abstract ProfileDao getsProfile();
}
