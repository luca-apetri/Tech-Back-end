package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("forms")
public class FormController {
    FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping
    public List<Form> getAllForms(){
        return formService.getAllForms();
    }

    @GetMapping(path = "{UserID}")
    public List<Form> getFormsOfUser(@PathVariable("UserID") UUID userID)
    {
        return formService.getFormsOfUser(userID);
    }

    @DeleteMapping(path = "{FormID}")
    public void deleteForm(@PathVariable("FormID") UUID formID)
    {
        formService.deleteForm(formID);
    }

    @PostMapping
    public void addNewForm(@RequestBody Form form)
    {
        if(form.getFormId() == null)
            formService.addNewForm(form);
        else
            formService.updateForm(form);
    }

}
