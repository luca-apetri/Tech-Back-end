package com.IntelligentForms.Intelligent_Forms_FCR.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        userRepository.insertUser(user);
    }
}
