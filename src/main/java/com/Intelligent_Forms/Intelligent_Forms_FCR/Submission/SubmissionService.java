package com.Intelligent_Forms.Intelligent_Forms_FCR.Submission;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.FormRepository;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.dto.CreateSubmissionDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.dto.SubmissionDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.util.SubmissionMapper;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final FormRepository formRepository;
    private final UserRepository userRepository;

    public List<SubmissionDto> getAllSubmissions(UUID userId, UUID formId) throws Exception {
        if (userRepository.existsById(userId) && formRepository.existsById(formId)) {
            List<Submission> submissions = submissionRepository.findAll();
            return SubmissionMapper.submissionListToSubmissionDtoList(submissions);
        } else {
            throw new Exception("Useer or form not found");
        }
    }

    public void addNewSubmission(UUID userId, UUID formID, CreateSubmissionDto createSubmissionDto) throws Exception {
        if (userRepository.existsById(userId) && formRepository.existsById(formID)) {

            Submission submission = new Submission();
            submission.setCnp(createSubmissionDto.getCnp());
            submission.setUserName(createSubmissionDto.getUserName());
            submission.setSeries(createSubmissionDto.getSeries());
            submission.setNumber(createSubmissionDto.getNumber());
            submission.setCreationDate(LocalDate.now());
            submission.setForm(formRepository.findFormById(formID));
            submissionRepository.save(submission);
        } else {
            throw new Exception("User not found or form not found ");
        }
    }

    public List<SubmissionDto> getSubmissionsOfForm(UUID formID, UUID userId) throws Exception {
        if (userRepository.existsById(userId) && formRepository.existsById(formID)) {
            return SubmissionMapper
                    .submissionListToSubmissionDtoList(formRepository.findFormById(formID).getFormSubmissions());
        } else {
            throw new Exception("User not found or form not found ");
        }
    }

    public void deleteSubmission(UUID submissionID, UUID userId, UUID formId) throws Exception {
        if (userRepository.existsById(userId) && formRepository.existsById(formId)) {
            submissionRepository.deleteById(submissionID);
        } else {
            throw new Exception("User or form not found!");
        }
    }
}
