package com.IntelligentForms.Intelligent_Forms_FCR.User;
import com.IntelligentForms.Intelligent_Forms_FCR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public List<User> GetAllUsers()
    {
        return userRepository.SelectAllUsers();
    }

    public void addNewUser(User user)
    {
        if(userRepository.isEmailTaken(user.getEmail()))
            throw new ApiRequestException(user.getEmail() + " is not valid");
        userRepository.insertUser(user);
    }

    public User getUserById(UUID userID) {
        return userRepository.getUserById(userID);
    }

    public String login(String email, String password) {
        return userRepository.login(email, password);
    }
}
