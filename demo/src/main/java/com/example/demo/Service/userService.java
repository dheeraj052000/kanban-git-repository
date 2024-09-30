package com.example.demo.Service;

import com.example.demo.Models.User;

import java.util.List;

public interface userService {
    public User getUserByUser(String UserName,String Password);
    public String AddUser(User user);
    public String UpdateUser(String Username,User user);
    public String DeleteUser(String UserName);
    public String logout(String UserName);
    public List<User> users();
}
