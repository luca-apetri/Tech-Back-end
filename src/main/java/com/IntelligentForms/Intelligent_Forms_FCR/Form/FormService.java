package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService
{
    FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Autowired
    public List<Form> getAllForms()
    {
        return formRepository.SelectAllForms();
    }
}
