package com.example.room.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.room.database.UserDatabase;
import com.example.room.entity.User;
import com.example.room.repository.UserRepository;
import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        UserDatabase database = UserDatabase.getInstance(application);
        repository = new UserRepository(database.userDao());
        allUsers = repository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }
}
