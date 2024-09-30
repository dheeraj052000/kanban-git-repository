package com.example.demo.Service;

import com.example.demo.Models.User;
import com.example.demo.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImplementation implements userService {
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
    public List<User> getAllUsers() {
        return ur.findAll();
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

    @Override
    public String logout(String UserName) {
        //String s=session.getAttribute("status").toString();
        //if (Objects.equals(s, "on")) {
            // Perform the logout process, such as invalidating a session, removing tokens, etc.
            // Assuming you're using some session management:
            // request.getSession().invalidate(); (in the controller)
            //session.invalidate();
            //return "User logged out successfully";
        //}

        return "User not found or not logged in";
    }

    @Override
    public List<User> users() {
        return ur.findAll();
    }
}
