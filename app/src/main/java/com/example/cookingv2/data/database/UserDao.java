package com.example.cookingv2.data.database;


import com.example.cookingv2.model.User;

import java.util.List;

public interface UserDao {

    void insert(User user);

    List<User> getAll();

    void deleteAll();

    User findUserById(String id);
}
