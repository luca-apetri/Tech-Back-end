package com.Intelligent_Forms.Intelligent_Forms_FCR.User;

import com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto.CreateUserDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto.UserDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto.UserLoginDto;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/{formId}/{userId}/pdf")
    public ResponseEntity<FileSystemResource> generatePdf(@PathVariable UUID formId, @PathVariable UUID userId) throws MessagingException, DocumentException, IOException {
        return ResponseEntity
                .ok()
                .header(HttpHeaders
                        .CONTENT_DISPOSITION, "attachment; filename=\"form.pdf\"")
                .body(userService
                        .generatePdf(formId, userId));
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

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto) throws Exception {
        String email = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();
        return userService.login(email, password);
    }
}
