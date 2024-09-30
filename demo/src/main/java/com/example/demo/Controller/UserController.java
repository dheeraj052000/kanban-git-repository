package com.example.demo.Controller;

import com.example.demo.Models.User;
import com.example.demo.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class UserController {
    @Autowired
    public userService us;

    @GetMapping("/getuser")
    public User getUser(@RequestParam String UserName, @RequestParam String Password) {
        System.out.println(UserName+"----"+Password);
        return us.getUserByUser(UserName, Password);
    }
    @GetMapping("/users")
    public List<User> getUsers() {
        return us.users();
    }
    @GetMapping("/logout")
    public String logout(@RequestParam String UserName) {
        us.logout(UserName);
        return "Logged out successfully";
    }

    @PostMapping("/adduser")
    public String addUser(@RequestBody User user) {
        us.AddUser(user);
        return "success";
    }

    @PutMapping("update")
    public String updateUser(@RequestBody User user,@RequestParam String Username) {
        us.UpdateUser(Username,user);
        return "success";
    }

}
