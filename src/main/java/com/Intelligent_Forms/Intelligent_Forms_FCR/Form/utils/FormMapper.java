package com.Intelligent_Forms.Intelligent_Forms_FCR.Form.utils;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.Form;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto.CreateFormDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto.ReturnFormDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.User.utils.UserMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class FormMapper {
    public static Form createFormDtoToForm(CreateFormDto dto) {
        Form form = new Form();
        form.setFormName(dto.getFormName());
        form.setDataRetentionPeriod(30);
        return form;
    }

    public static List<ReturnFormDto> formListToReturnFormDtoList(List<Form> forms) {
        List<ReturnFormDto> formDtos = new ArrayList<>();
        for (
                Form form : forms
        ) {
            formDtos
                    .add(
                            new ReturnFormDto(
                                    form.getId(),
                                    form.getFormName(),
                                    form.getDataRetentionPeriod(),
                                    form.getDynamicFields(),
                                    form.getFormSubmissions()));
        }
        return formDtos;
    }

}
