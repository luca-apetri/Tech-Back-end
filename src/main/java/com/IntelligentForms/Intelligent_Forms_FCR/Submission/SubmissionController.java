package com.IntelligentForms.Intelligent_Forms_FCR.Submission;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

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
    public List<Submission> getAllSubmissions()
    {
        return submissionService.getAllSubmitions();
    }

    @GetMapping(path = "{FormID}")
    public List<Submission> getSubmissionsOfForm(@PathVariable("FormID") UUID formID)
    {
        return submissionService.getSubmissionsOfForm(formID);
    }

    @PostMapping
    public void addNewSubmission(@RequestBody Submission submission)
    {
        submissionService.addNewSubmission(submission);
    }
}
