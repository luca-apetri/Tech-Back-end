package com.Intelligent_Forms.Intelligent_Forms_FCR.User;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.Form;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.utils.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @NotBlank
    @Id
    @GeneratedValue
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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Form> forms;

}
