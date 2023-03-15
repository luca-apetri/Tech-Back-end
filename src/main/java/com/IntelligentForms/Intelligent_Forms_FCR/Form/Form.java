package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Form {
    private UUID formId;
    private String formName;
    private UUID formOwner;
    private Map<String, ArrayList<?>> dynamicFields;
    private UUID[] formSubmissions;

    public Form(@JsonProperty("FormID") UUID formId,
                @JsonProperty("FormName") String formName,
                @JsonProperty("FormOwner") UUID formOwner,
                @JsonProperty("DynamicFields") Map<String, ArrayList<?>> dynamicFields,
                @JsonProperty("FormSubmissions") UUID[] formSubmissions) {
        //Map<String, ?> dynamicFieldMap = dynamicFields.to;

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

    public Map<String, ArrayList<?>> getDynamicFields() {
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

    public String formatDynamicFields()
    {
        String returnString = "";
        try{
            returnString = new ObjectMapper().writeValueAsString(dynamicFields);
        }catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
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
