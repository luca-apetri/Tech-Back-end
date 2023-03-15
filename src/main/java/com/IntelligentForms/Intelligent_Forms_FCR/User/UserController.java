package com.IntelligentForms.Intelligent_Forms_FCR.User;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @GetMapping(path = "{userID}")
    private User getUserById(@PathVariable("userID") UUID userID)
    {
        return userService.getUserById(userID);
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody @Valid User user)
    {
        userService.addNewUser(user);
        return "register_success";
    }

    @PostMapping(path = "/login")
    public String login(HttpServletRequest request)
    {
        String email = request.getParameter("email");
        String password =  request.getParameter("password");
        return userService.login(email, password);
    }
}
