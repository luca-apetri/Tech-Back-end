package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("forms")
public class FormController {
    private JSONObject jsonObject = new JSONObject();
    private UUID[] arrayUUID = new UUID[]{UUID.randomUUID(), UUID.randomUUID()};
    @GetMapping
    private List<Form> getAllForms()
    {
        return List.of(new Form(UUID.randomUUID(), "nume", UUID.randomUUID(), jsonObject, arrayUUID));
    }
}
