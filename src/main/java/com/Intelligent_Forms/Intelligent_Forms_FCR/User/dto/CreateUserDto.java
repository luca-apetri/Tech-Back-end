package com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto;

import com.Intelligent_Forms.Intelligent_Forms_FCR.User.utils.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String address;
    @NotBlank
    private String companyName;
    @NotBlank
    private String fiscalCode;
    @NotBlank
    public AccountType accountType;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
