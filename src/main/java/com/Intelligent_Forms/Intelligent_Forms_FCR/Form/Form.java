package com.Intelligent_Forms.Intelligent_Forms_FCR.Form;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.Submission;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.User;
import com.Intelligent_Forms.Intelligent_Forms_FCR.field.DynamicFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    private List<DynamicFields> dynamicFields = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    @ToString.Exclude
    private List<Submission> formSubmissions = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Form form = (Form) o;
        return dataRetentionPeriod == form.dataRetentionPeriod && id.equals(form.id) && formName.equals(form.formName) && dynamicFields.equals(form.dynamicFields) && formSubmissions.equals(form.formSubmissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formName, dataRetentionPeriod, dynamicFields, formSubmissions);
    }

}
