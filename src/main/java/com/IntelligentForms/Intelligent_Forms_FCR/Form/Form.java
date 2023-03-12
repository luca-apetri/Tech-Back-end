package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

public class Form {
    private UUID formId;
    private String formName;
    private UUID formOwner;
    private Map<String, ?> dynamicFields;
    private UUID[] formSubmissions;

    public Form(@JsonProperty("FormID") UUID formId,
                @JsonProperty("FormName") String formName,
                @JsonProperty("FormOwner") UUID formOwner,
                @JsonProperty("DynamicFields") JSONObject dynamicFields,
                @JsonProperty("FormSubmissions") UUID[] formSubmissions) {
        Map<String, ?> dynamicFieldMap = dynamicFields.toMap();

        this.formId = formId;
        this.formName = formName;
        this.formOwner = formOwner;
        this.dynamicFields = dynamicFieldMap;
        this.formSubmissions = formSubmissions;
    }

    public UUID getFormId() {
        return formId;
    }

    public String getFormName() {
        return formName;
    }

    public UUID getFormOwner() {
        return formOwner;
    }

    public Map<String, ?> getDynamicFields() {
        return dynamicFields;
    }
    public UUID[] getFormSubmissions() {
        return formSubmissions;
    }

    @Override
    public String toString() {
        return "Form{" +
                "formId=" + formId +
                ", formName='" + formName + '\'' +
                ", formOwner=" + formOwner +
                ", dynamicFields=" + dynamicFields.toString() +
                ", formSubmissions=" + Arrays.toString(formSubmissions) +
                '}';
    }
}
