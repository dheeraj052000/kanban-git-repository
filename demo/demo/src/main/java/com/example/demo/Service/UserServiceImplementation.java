package com.example.demo.Service;

import com.example.demo.Models.User;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    boolean flag;
    @Autowired
    private UserRepo ur;
    @Override
    public User getUserByUser(String UserName, String Password) {
        List<User> users=ur.findAll();
        User u=null;
            for(User user:users) {
                if(user.getUserName().equals(UserName) && user.getPassword().equals(Password)) {
                    flag = true;
                    u=user;
                    return user;
                }
                else{
                    flag = false;
                }
            }
            System.out.println(u);
        return null;
    }

    @Override
    public String AddUser(User newuser) {
        List<User> users=ur.findAll();
            for(User user:users) {
                if(user.getUserName().equals(newuser.getUserName()))
                {
                    flag = true;
                    ur.save(newuser);

                }
                else{
                    flag = false;
                }
            }

        if(flag) {
            return "User Already Exists";
        }
        return "User Added";
    }

    @Override
    public String UpdateUser(String Username,User u) {
        List<User> users=ur.findAll();
        for(User user:users) {
            if(user.getUserName().equals(Username))
            {
                flag = true;
                user.setPassword(u.getPassword());
                user.setUserName(Username);
                user.setRoleName(u.getRoleName());

            }
            else{
                flag = false;
            }
        }
        if(flag) {
            return "User Updated Successfully";
        }
        return "User Not Found";
    }

    @Override
    public String DeleteUser(String UserName) {
        List<User> users=ur.findAll();
            for(User user:users) {
                if(user.getUserName().equals(UserName))
                {
                    flag = true;
                    ur.delete(user);

                }
                else{
                    flag = false;
                }
            }
        if(flag) {
            return "User Deleted Successfully";
        }
        return "User Not Found";
    }
}
