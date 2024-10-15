package com.example.room.repository;

import androidx.lifecycle.LiveData;
import com.example.room.dao.UserDao;
import com.example.room.entity.User;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private ExecutorService executorService;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
        this.allUsers = userDao.getAllUsers();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    public void delete(User user) {
        executorService.execute(() -> userDao.delete(user));
    }
}
