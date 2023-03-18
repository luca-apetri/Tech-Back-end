package com.Intelligent_Forms.Intelligent_Forms_FCR.field;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.Form;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fields")
public class Field {
    @Id
    @GeneratedValue
    public UUID id;
    @NotBlank
    public String name;
    @NotBlank
    public String content;
    @ManyToOne
    public Form form;

}
