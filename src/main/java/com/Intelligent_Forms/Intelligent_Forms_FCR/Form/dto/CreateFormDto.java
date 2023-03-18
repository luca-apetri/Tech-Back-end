package com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateFormDto {
    @NotBlank
    private String formName;
    @NotBlank
    private int dataRetentionPeriod;
    @NotBlank
    private List<CreateFieldDto> dynamicFields;

}
