package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Form {
    private UUID formId;
    private String formName;
    private UUID formOwner;
    private Map<String, ArrayList<Sectiune>> dynamicFields;
    private String formText;
    private UUID[] formSubmissions;

    public Form(@JsonProperty("FormID") UUID formId,
                @JsonProperty("FormName") String formName,
                @JsonProperty("FormOwner") UUID formOwner,
                @JsonProperty("DynamicFields") Map<String, ArrayList<Sectiune>> dynamicFields,
                @JsonProperty("FormText") String formText,
                @JsonProperty("FormSubmissions") UUID[] formSubmissions) {
        //Map<String, ?> dynamicFieldMap = dynamicFields.to;

        this.formId = formId;
        this.formName = formName;
        this.formOwner = formOwner;
        this.dynamicFields = dynamicFields;
        this.formSubmissions = formSubmissions;
        this.formText = formText;

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

    public Map<String, ArrayList<Sectiune>> getDynamicFields() {
        return dynamicFields;
    }

    public UUID[] getFormSubmissions() {
        return formSubmissions;
    }


    public String getFormText() {
        return formText;
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

    public String formatDynamicFields()
    {
        String returnString = dynamicFields.toString();
        returnString = returnString.replace("=", "\":").replace("section","\"section");
        return returnString;
    }

    public String getSubmissionsString()
    {
        if(formSubmissions != null && formSubmissions.length != 0) {
            StringBuilder returnString = new StringBuilder("{");
            for (int i = 0; i < formSubmissions.length; i++) {
                returnString.append("\"" + formSubmissions[i].toString() + "\", ");
            }
            returnString.delete(returnString.length() - 2, returnString.length()).append("}");
            return returnString.toString();
        }
        return "{}";
    }

//    public String mapToSqlQuery()
//    {
//        StringBuilder returnString = new StringBuilder("'{");
//        for(String key: dynamicFields.keySet()){
//            returnString.append("\"" + key + "\":" + mapToString((Map<String, String>) dynamicFields.get(key)) + ", ");
//        }
//        returnString.delete(returnString.length() - 2, returnString.length()).append("}'");
//        return returnString.toString();
//    }

    public String mapToString(Map<String, String> map)
    {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (String key: map.keySet()
        ) {
            stringBuilder.append("\"" + key + "\":\"" + map.get(key) + "\", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");
        return stringBuilder.toString();
    }


}
