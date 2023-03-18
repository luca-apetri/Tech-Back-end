package com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.dto.SubmissionDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.field.dto.FieldsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnFormDto {
    private UUID id;

    private String formName;
    private int dataRetentionPeriod;
    private List<FieldsDto> dynamicFields;
    private List<SubmissionDto> formSubmissions;

}