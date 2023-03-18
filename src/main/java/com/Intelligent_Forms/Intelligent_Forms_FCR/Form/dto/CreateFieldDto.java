package com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFieldDto {
    @NotBlank
    public String name;
    @NotBlank
    public String content;
}
