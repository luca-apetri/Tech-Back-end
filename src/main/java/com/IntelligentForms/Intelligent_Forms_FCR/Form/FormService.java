package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public void addNewForm(Form form) {

        UUID formID = UUID.randomUUID();
        int success = formRepository.insertForm(form, formID);
        if(success == 1)
        {
           formRepository.insertFormIntoUser(formID, form.getFormOwner());
        }
    }

    public List<Form> getFormsOfUser(UUID userID) {
        return formRepository.getFormsOfUser(userID);
    }


}
