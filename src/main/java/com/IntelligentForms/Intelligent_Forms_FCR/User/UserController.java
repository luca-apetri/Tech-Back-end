package com.IntelligentForms.Intelligent_Forms_FCR.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private List<User> getAllUsers(){
        return userService.GetAllUsers();
    }

    @PostMapping
    public void addNewUser(@RequestBody User user)
    {
        userService.addNewUser(user);
    }
}
