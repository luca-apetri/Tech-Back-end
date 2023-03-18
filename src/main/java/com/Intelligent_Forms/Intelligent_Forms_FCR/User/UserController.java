package com.Intelligent_Forms.Intelligent_Forms_FCR.User;

import com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto.CreateUserDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto.UserDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping(path = "{userID}")
    public UserDto getUserById(@PathVariable("userID") UUID userID) {
        return userService.getUserById(userID);
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody @Valid CreateUserDto user) {
        userService.addNewUser(user);
        return "register_success";
    }

    @PostMapping( "/login")
    public String login(@RequestBody UserLoginDto userLoginDto) throws Exception {
        String email = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();
        return userService.login(email, password);
    }
}
