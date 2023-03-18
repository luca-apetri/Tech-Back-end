package com.Intelligent_Forms.Intelligent_Forms_FCR.User.utils;

import com.Intelligent_Forms.Intelligent_Forms_FCR.User.User;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto.CreateUserDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    private UserMapper() {
    }

    public static UserDto userToUserDto(User user) {
        UserDto user1 = new UserDto();
        user1.setId(user.getId());
        user1.setEmail(user.getEmail());
        user1.setAddress(user.getAddress());
        user1.setName(user.getName());
        user1.setSurname(user.getSurname());
        user1.setAccountType(user.getAccountType());
        user1.setCompanyName(user.getCompanyName());
        user1.setFiscalCode(user.getFiscalCode());
        user1.setAccountType(AccountType.PUBLIC_INSTITUTION);
        return user1;
    }

    public static List<UserDto> userToUserDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users
        ) {
            userDtos.add(new UserDto(user.getId(), user.getEmail(), user.getName(), user.getSurname(),
                    user.getAddress(), user.getCompanyName(), user.getFiscalCode(), user.getAccountType()));
        }
        return userDtos;
    }

    public static User createUserDtoToUser(CreateUserDto user) {
        User user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setAddress(user.getAddress());
        user1.setName(user.getName());
        user1.setSurname(user.getSurname());
        user1.setAccountType(user.getAccountType());
        user1.setCompanyName(user.getCompanyName());
        user1.setFiscalCode(user.getFiscalCode());
        user1.setAccountType(AccountType.PUBLIC_INSTITUTION);
        user1.setPassword(user.getPassword());
        return user1;
    }
}
