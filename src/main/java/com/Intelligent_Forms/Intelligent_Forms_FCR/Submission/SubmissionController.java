package com.Intelligent_Forms.Intelligent_Forms_FCR.Submission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("submissions")
public class SubmissionController {
    SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @GetMapping(path = "{FormID}")
    public List<Submission> getSubmissionsOfForm(@PathVariable("FormID") UUID formID) throws Exception {
        return submissionService.getSubmissionsOfForm(formID);
    }

    @DeleteMapping(path = "{SubmissionID}")
    public void deleteSubmission(@PathVariable("SubmissionID") UUID submissionID) {
        submissionService.deleteSubmission(submissionID);
    }

    @PostMapping
    public void addNewSubmission(@RequestBody Submission submission) {
        submissionService.addNewSubmission(submission);
    }
}
