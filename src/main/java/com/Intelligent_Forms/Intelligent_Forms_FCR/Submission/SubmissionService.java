package com.Intelligent_Forms.Intelligent_Forms_FCR.Submission;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.Form;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    private final FormRepository formRepository;

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public void addNewSubmission(Submission submission) {
        submissionRepository.save(submission);
    }

    public List<Submission> getSubmissionsOfForm(UUID formID) throws Exception {
        Form form = formRepository.findById(formID).orElse(null);
        if (form == null) {
            throw new Exception();
        }
        return form.getFormSubmissions();
    }

    public void deleteSubmission(UUID submissionID) {
        submissionRepository.deleteById(submissionID);
    }
}
