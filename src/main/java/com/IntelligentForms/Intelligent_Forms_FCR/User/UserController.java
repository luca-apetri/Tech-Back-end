package com.IntelligentForms.Intelligent_Forms_FCR.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("users")
public class UserController {
    private UUID[] arrayUUID = new UUID[]{UUID.randomUUID(), UUID.randomUUID()};
    @GetMapping
    private List<User> getAllUsers(){
        return List.of(new User(UUID.randomUUID(), "nume",  "prenume", "Str sdlfj", arrayUUID, "SKDFDS INC", "875864hk453", User.AccountType.COMPANY,
                "kdjhfg@gmail.com", "sdfsdfgw2452"));
    }
}
