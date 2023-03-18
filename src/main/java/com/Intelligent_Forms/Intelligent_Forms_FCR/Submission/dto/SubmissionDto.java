package com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SubmissionDto {
    @NotBlank
    private UUID id;
    @NotBlank
    public String cnp;

    @NotBlank
    public String userName;
    @NotBlank
    public String series;
    @NotBlank
    public String number;

    @NotBlank
    private LocalDate creationDate;


}