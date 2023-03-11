package com.IntelligentForms.Intelligent_Forms_FCR.Submission;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class Submission {
    private UUID submissionId;
    private UUID submissionForm;
    private Map<String, ?> submissions = new HashMap<>();

    public Submission(UUID submissionId, UUID submissionForm, Map<String, ?> submissions) {
        this.submissionId = submissionId;
        this.submissionForm = submissionForm;
        this.submissions = submissions;
    }

    public Map<String, ?> getSubmissions() {
        return submissions;
    }

    public UUID getSubmissionId() {
        return submissionId;
    }

    public UUID getSubmissionForm() {
        return submissionForm;
    }

}
