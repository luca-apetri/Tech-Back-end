package com.IntelligentForms.Intelligent_Forms_FCR.Submission;

import com.IntelligentForms.Intelligent_Forms_FCR.Form.Form;
import com.IntelligentForms.Intelligent_Forms_FCR.Form.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        UUID submissionID = UUID.randomUUID();
        int success = submissionRepository.insertSubmission(submission, submissionID);
        if(success == 1)
        {
            submissionRepository.insertSubmissionIntoForm(submissionID, submission.getSubmissionForm());
        }
    }

    public List<Submission> getSubmissionsOfForm(UUID formID) {
        return submissionRepository.getSubmissionsOfForm(formID);
    }
}
