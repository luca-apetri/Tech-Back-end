package com.IntelligentForms.Intelligent_Forms_FCR.Submission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class Submission {
    private UUID submissionId;
    private UUID submissionForm;
    private Map<String, Map<String, String>> submissions;

    public Submission(@JsonProperty("SubmissionID") UUID submissionId,
                      @JsonProperty("SubmissionForm") UUID submissionForm,
                      @JsonProperty("SubmissionValues") Object submissions) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Map<String,String>> submissionMap = objectMapper.convertValue(submissions, Map.class);
        this.submissionId = submissionId;
        this.submissionForm = submissionForm;
        this.submissions = submissionMap;
    }

    public Map<String, ?> getSubmissions() {
        return submissions;
    }

    public UUID getSubmissionId() {
        return submissionId;
    }

    public UUID getSubmissionForm() {
        return submissionForm;
    }


    @Override
    public String toString() {
        return "Submission{" +
                "submissionId=" + submissionId +
                ", submissionForm=" + submissionForm +
                ", submissions=" + submissions.toString() +
                '}';
    }

    public String mapToSqlQuery()
    {
        StringBuilder returnString = new StringBuilder("'{");
        for(String key: submissions.keySet()){
            returnString.append("\"" + key + "\":" + mapToString(submissions.get(key)) + ", ");
        }
        returnString.delete(returnString.length() - 2, returnString.length()).append("}'");
        return returnString.toString();
    }

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
