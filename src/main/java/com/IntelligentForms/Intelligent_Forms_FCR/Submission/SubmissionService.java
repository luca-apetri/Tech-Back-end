package com.IntelligentForms.Intelligent_Forms_FCR.Submission;

import com.IntelligentForms.Intelligent_Forms_FCR.Form.Form;
import com.IntelligentForms.Intelligent_Forms_FCR.Form.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubmissionService
{
    SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Autowired
    public List<Submission> getAllSubmitions()
    {
        return submissionRepository.SelectAllSubmissions();
    }

    public void addNewSubmission(Submission submission)
    {
        submissionRepository.insertSubmission(submission);
    }
}
