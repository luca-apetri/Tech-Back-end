package com.Intelligent_Forms.Intelligent_Forms_FCR.Form;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto.CreateFieldDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto.CreateFormDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto.ReturnFormDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.utils.FormMapper;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Submission.SubmissionRepository;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.UserRepository;
import com.Intelligent_Forms.Intelligent_Forms_FCR.field.Field;
import com.Intelligent_Forms.Intelligent_Forms_FCR.field.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;
    private final UserRepository userRepository;
    private final SubmissionRepository submissionRepository;
    private final FieldRepository fieldRepository;

    public List<ReturnFormDto> getAllForms() {
        List<ReturnFormDto> forms = FormMapper.formListToReturnFormDtoList(formRepository.findAll());
        for (ReturnFormDto form : forms
        ) {
            form.setDynamicFields(fieldRepository.findAllByForm_Id(form.getId()));
            //TODO MAKE DTO FOR DYNAMIC FIELDS

        }
        return forms;
    }

    public void addNewForm(CreateFormDto createFormDto) {
        Form form = FormMapper.createFormDtoToForm(createFormDto);
        form = formRepository.save(form);

        List<Field> fields = new ArrayList<>();
        for (CreateFieldDto fieldDto : createFormDto.getDynamicFields()
        ) {
            Field fieldToSave = new Field();
            fieldToSave.setName(fieldDto.getName());
            fieldToSave.setContent(fieldDto.getContent());
            fields.add(fieldRepository.save(fieldToSave));
        }
        form.setDynamicFields(fields);
        formRepository.save(form);
    }


    public void updateForm(CreateFormDto form) {
        formRepository.save(FormMapper.createFormDtoToForm(form));
    }

    public void deleteForm(UUID formID) {
        formRepository.deleteById(formID);
    }
}
