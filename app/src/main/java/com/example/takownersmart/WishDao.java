package com.example.takownersmart;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// Database Access Object commands for the wishlist items
@Dao
public interface WishDao {

    // Dao command for Query SELECT
    @Query("SELECT * FROM guitar")
    List<Guitar> getAllGuitars();

    // Dao command for INSERT
    @Insert
    void insertGuitar(Guitar... guitars);

    // Dao command for DELETE
    @Delete
    void delete(Guitar guitar);
}