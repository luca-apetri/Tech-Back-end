package com.Intelligent_Forms.Intelligent_Forms_FCR.Submission;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.Form;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="submission")
public class Submission {
    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank
    private LocalDate creationDate;
    @ManyToOne
    @JoinColumn(name = "form_id",referencedColumnName = "id")
    public Form form;

}
