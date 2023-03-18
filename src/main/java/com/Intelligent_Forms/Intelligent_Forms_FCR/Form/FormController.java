package com.Intelligent_Forms.Intelligent_Forms_FCR.Form;

import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto.CreateFormDto;
import com.Intelligent_Forms.Intelligent_Forms_FCR.Form.dto.ReturnFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("forms")
public class FormController {
    private final FormService formService;
    @GetMapping
    public List<ReturnFormDto> getAllForms(){
        return formService.getAllForms();
    }

    @DeleteMapping(path = "{FormID}")
    public void deleteForm(@PathVariable("FormID") UUID formID)
    {
        formService.deleteForm(formID);
    }

    @PostMapping
    public void addNewForm(@RequestBody CreateFormDto form)
    {
        formService.addNewForm(form);
    }

}
