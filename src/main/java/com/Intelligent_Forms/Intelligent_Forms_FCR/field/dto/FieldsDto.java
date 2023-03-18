package com.Intelligent_Forms.Intelligent_Forms_FCR.field.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class FieldsDto {
    @NotBlank
    public UUID id;
    @NotBlank
    public String name;
    @NotBlank
    public String content;


}
