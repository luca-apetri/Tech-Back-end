package com.Intelligent_Forms.Intelligent_Forms_FCR.Form;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.Submission;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.User;
import com.Intelligent_Forms.Intelligent_Forms_FCR.field.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "form")
public class Form {
    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank
    private String formName;
    @NotBlank
    private int dataRetentionPeriod;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Field> dynamicFields = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Submission> formSubmissions = new ArrayList<>();

}
