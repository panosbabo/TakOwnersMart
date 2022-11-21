package com.example.takownersmart;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface ProfileDao {
    @Insert
    public void insert(Profile profile);

    @Update
    public void update(Profile profile);

    @Delete
    public void delete(Profile profile);

    @Query("SELECT * FROM profile")
    public List<Profile> getmyProfile();

    @Query("SELECT * FROM profile WHERE personUsername = :username")
    public Profile getprofileWithId(String username);

}
