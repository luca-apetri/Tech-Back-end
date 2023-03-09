package com.IntelligentForms.Intelligent_Forms_FCR.Submission;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("submissions")
public class SubmissionController {
    @GetMapping
    private List<Submission> getAllSubmissions()
    {
        JSONObject jsonObject = new JSONObject();
        return List.of(new Submission(UUID.randomUUID(), UUID.randomUUID(),jsonObject ));
    }
}
