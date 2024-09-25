package com.example.demo.Service;

import com.example.demo.Models.User;

public interface UserService {
    public User getUserByUser(String UserName,String Password);
    public String AddUser(User user);
    public String UpdateUser(String Username,User user);
    public String DeleteUser(String UserName);
}
