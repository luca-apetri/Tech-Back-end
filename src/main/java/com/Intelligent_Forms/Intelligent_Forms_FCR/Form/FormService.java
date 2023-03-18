package com.Intelligent_Forms.Intelligent_Forms_FCR.Form;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto.CreateFieldDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto.CreateFormDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto.ReturnFormDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.utils.FormMapper;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.SubmissionRepository;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.util.SubmissionMapper;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.User;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.UserRepository;
import com.Intelligent_Forms.Intelligent_Forms_FCR.field.DynamicFields;
import com.Intelligent_Forms.Intelligent_Forms_FCR.field.FieldRepository;
import com.Intelligent_Forms.Intelligent_Forms_FCR.field.utils.FieldMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;
    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;


    public List<ReturnFormDto> getAllForms(UUID userId) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<ReturnFormDto> forms = FormMapper.formListToReturnFormDtoList(formRepository.findAllByUser_Id(userId));
            for (ReturnFormDto form : forms
            ) {
                form.setDynamicFields(FieldMapper.fieldListToFieldDtoList(fieldRepository.findAllByForm_Id(form.getId())));
                form.setFormSubmissions(SubmissionMapper.submissionListToSubmissionDtoList(submissionRepository.
                        findAllByForm_Id(form.getId())));

            }
            return forms;
        }
        else{
            throw new Exception("Invalid user id!");
        }
    }

    public void addNewForm(CreateFormDto createFormDto, UUID userId) throws Exception {
        Form form = FormMapper.createFormDtoToForm(createFormDto);
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            form.setUser(user);

            List<DynamicFields> dynamicFields = new ArrayList<>();
            for (CreateFieldDto fieldDto : createFormDto.getDynamicFields()
            ) {
                DynamicFields dynamicFieldsToSave = new DynamicFields();
                dynamicFieldsToSave.setName(fieldDto.getName());
                dynamicFieldsToSave.setContent(fieldDto.getContent());
                dynamicFields.add(fieldRepository.save(dynamicFieldsToSave));
            }
            form.setDynamicFields(dynamicFields);
            formRepository.save(form);
        } else {
            throw new Exception("User not found!");
        }
    }


    public void updateForm(CreateFormDto form) {
        formRepository.save(FormMapper.createFormDtoToForm(form));
    }

    public void deleteForm(UUID formID) {
        formRepository.deleteById(formID);
    }
}
