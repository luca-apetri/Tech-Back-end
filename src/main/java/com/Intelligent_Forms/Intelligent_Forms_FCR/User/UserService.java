package com.Intelligent_Forms.Intelligent_Forms_FCR.User;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.Form;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto.CreateUserDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto.UserDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.utils.UserMapper;
import com.Intelligent_Forms.Intelligent_Forms_FCR.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return UserMapper.userToUserDto(userRepository.findAll());
    }

    public List<Form> getFormsOfUser(UUID userID) throws Exception {
        User user = userRepository.findById(userID).orElse(null);
        if (user == null) {
            throw new Exception();
        }
        return user.getForms();
    }


    public void addNewUser(CreateUserDto user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new ApiRequestException(user.getEmail() + " is not valid");
        userRepository.save(UserMapper.createUserDtoToUser(user));
    }

    public UserDto getUserById(UUID userID) {
        return UserMapper.userToUserDto(Objects.requireNonNull(userRepository.findById(userID).orElse(null)));
    }

    public String login(String email, String password) throws Exception {

        User user = userRepository.findByEmailAndPassword(email, password)
                .orElse(null);
        if (user == null) {
            throw new Exception();
        }
        return "Logged in";
    }
}