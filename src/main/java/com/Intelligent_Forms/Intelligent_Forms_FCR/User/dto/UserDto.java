package com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto;

import com.Intelligent_Forms.Intelligent_Forms_FCR.User.utils.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID id;

    private String email;

    private String name;

    private String surname;

    private String address;

    private String companyName;

    private String fiscalCode;

    public AccountType accountType;

}
