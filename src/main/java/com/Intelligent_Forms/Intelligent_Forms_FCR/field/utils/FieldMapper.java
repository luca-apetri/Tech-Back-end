package com.Intelligent_Forms.Intelligent_Forms_FCR.field.utils;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto.CreateFieldDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.field.DynamicFields;
import com.Intelligent_Forms.Intelligent_Forms_FCR.field.dto.FieldsDto;

import java.util.ArrayList;
import java.util.List;

public class FieldMapper {
    private FieldMapper(){}
    public static List<DynamicFields> createFieldListDtoToFieldList(List<CreateFieldDto> dto) {
        List<DynamicFields> dynamicFields = new ArrayList<>();
        for (CreateFieldDto field : dto
        ) {
            dynamicFields.add(new DynamicFields(null, field.getName(), field.getContent(),null));
        }
        return dynamicFields;
    }
    public static List<FieldsDto> fieldListToFieldDtoList(List<DynamicFields> dynamicFields) {
        List<FieldsDto> fieldDto = new ArrayList<>();
        for (DynamicFields dynamicField : dynamicFields
        ) {
            fieldDto.add(new FieldsDto(dynamicField.getId(), dynamicField.getName(), dynamicField.getContent()));
        }
        return fieldDto;
    }
}
