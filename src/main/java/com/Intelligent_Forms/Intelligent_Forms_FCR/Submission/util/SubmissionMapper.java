package com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.util;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.FormRepository;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.Submission;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.dto.SubmissionDto;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class SubmissionMapper {

    private final FormRepository formRepository;

    public static List<Submission> createSubmissionListDtoToSubmissionList(List<SubmissionDto> dto) {
        List<Submission> submissionList = new ArrayList<>();
        for (SubmissionDto submissionDto : dto
        ) {
            submissionList.add(
                    new Submission(submissionDto.getId(), submissionDto.getCnp(), submissionDto.getUserName(),
                            submissionDto.getSeries(), submissionDto.getNumber(), submissionDto.getCreationDate(), null));
        }
        return submissionList;
    }

    public static List<SubmissionDto> submissionListToSubmissionDtoList(List<Submission> submissions) {
        List<SubmissionDto> submissionDtos = new ArrayList<>();
        for (Submission submission : submissions
        ) {
            submissionDtos.add(new SubmissionDto(submission.getId(), submission.getCnp(), submission.getUserName(),
                    submission.getSeries(), submission.getNumber(), submission.getCreationDate()));
        }
        return submissionDtos;
    }

    public Submission submissions(SubmissionDto submissionDto, UUID formID) {

        Submission submission = new Submission();
        submission.setForm(formRepository.findFormById(formID));
        submission.setId(submissionDto.getId());
        submission.setCreationDate(submissionDto.getCreationDate());
        return submission;
    }
}
