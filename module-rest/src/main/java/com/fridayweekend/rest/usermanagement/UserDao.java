package com.fridayweekend.rest.usermanagement;

import java.util.List;

import com.fridayweekend.rest.valueobjects.User;

public interface UserDao {

    public void addUser(User user);

    public List<User> getUsers();

    public User deletedUser(String email);

    public void updateUser(String email, User newUserData);

    public User findUser(String email);
}
