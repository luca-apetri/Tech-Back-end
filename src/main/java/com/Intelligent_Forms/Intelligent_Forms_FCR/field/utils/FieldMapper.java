package com.Intelligent_Forms.Intelligent_Forms_FCR.field.utils;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto.CreateFieldDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.field.Field;

import java.util.ArrayList;
import java.util.List;

public class FieldMapper {
    public static List<Field> createFieldListDtoToFieldList(List<CreateFieldDto> dto) {
        List<Field> fields = new ArrayList<>();
        for (CreateFieldDto field : dto
        ) {
            fields.add(new Field(null, field.getName(), field.getContent(),null));
        }
        return fields;
    }
}
