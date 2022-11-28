package com.example.takownersmart.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

// Reference: Some parts of the following code is from an online Android example https://github.com/ravizworldz/AndroidRoomDB_Java
//Wishlist database for the guitar items
@Database(entities = {Guitar.class}, version = 1)
public abstract class WishListDatabase extends RoomDatabase {

    //Abstract Dao
    public abstract WishDao wishDao();

    // Variable for INSTANCE
    private static WishListDatabase INSTANCE;

    // Method for fetching the data for each instance
    public static WishListDatabase getDbInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WishListDatabase.class, "DB_GUITAR")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}

