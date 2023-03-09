package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import java.util.UUID;
import org.json.JSONObject;

public class Form {
    private UUID formId;
    private String formName;
    private UUID formOwner;
    private JSONObject dynamicFields;
    private UUID[] formSubmissions;

    public Form(UUID formId, String formName, UUID formOwner, JSONObject dynamicFields, UUID[] formSubmissions) {
        this.formId = formId;
        this.formName = formName;
        this.formOwner = formOwner;
        this.dynamicFields = dynamicFields;
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

    public JSONObject getDynamicFields() {
        return dynamicFields;
    }

    public UUID[] getFormSubmissions() {
        return formSubmissions;
    }
}
