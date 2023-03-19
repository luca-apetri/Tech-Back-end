package com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubmissionDto {
    @NotBlank
    @Size(min = 13, max = 13)
    public String cnp;
    @NotBlank
    public String userName;
    @NotBlank
    @Size(min = 2, max = 2)
    public String series;

    @NotBlank
    @Size(min = 6, max = 6)
    public String number;

}