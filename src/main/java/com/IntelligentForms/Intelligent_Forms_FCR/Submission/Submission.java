package com.IntelligentForms.Intelligent_Forms_FCR.Submission;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.UUID;
import org.json.JSONObject;

public class Submission {
    private UUID submissionId;
    private UUID submissionForm;
    private JSONObject submissionValues;

    public Submission(UUID submissionId, UUID submissionForm, JSONObject submissionValues) {
        this.submissionId = submissionId;
        this.submissionForm = submissionForm;
        this.submissionValues = submissionValues;
    }

    public UUID getSubmissionId() {
        return submissionId;
    }

    public UUID getSubmissionForm() {
        return submissionForm;
    }

    public JSONObject getSubmissionValues() {
        return submissionValues;
    }
}
