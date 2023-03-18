package com.Intelligent_Forms.Intelligent_Forms_FCR.User.dto;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.Form;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.utils.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO {
    @NotBlank
    private UUID id;
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
