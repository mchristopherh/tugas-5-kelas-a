package com.example.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.room.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    LiveData<List<User>> getAllUsers();
}
