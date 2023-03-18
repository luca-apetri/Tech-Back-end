package com.Intelligent_Forms.Intelligent_Forms_FCR.Submission;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.dto.CreateSubmissionDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.dto.SubmissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/submissions")
@RequiredArgsConstructor
@Validated
public class SubmissionController {
    private final SubmissionService submissionService;

    @GetMapping(path = "/{userId}/{formId}")
    public List<SubmissionDto> getSubmissionsOfForm(@PathVariable UUID formId,
                                                    @PathVariable UUID userId) throws Exception {
        return submissionService.getSubmissionsOfForm(formId, userId);
    }

    @DeleteMapping(path = "/{userId}/{formId}/{submissionID}")
    public void deleteSubmission(@PathVariable UUID submissionID,
                                 @PathVariable UUID userId,
                                 @PathVariable UUID formId) throws Exception {
        submissionService.deleteSubmission(submissionID, userId, formId);
    }

    @PostMapping("/{userId}/{formId}")
    public void addNewSubmission(@PathVariable UUID formId,
                                 @PathVariable UUID userId,
                                 @RequestBody @Valid CreateSubmissionDto createSubmissionDto) throws Exception {
        submissionService.addNewSubmission(userId, formId, createSubmissionDto);
    }
}
