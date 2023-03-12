package com.IntelligentForms.Intelligent_Forms_FCR.Submission;

import com.IntelligentForms.Intelligent_Forms_FCR.Form.Form;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class SubmissionRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public SubmissionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Submission> SelectAllSubmissions(){
        String sql = "SELECT * FROM submissions";
        List<Submission> submissions = jdbcTemplate.query(sql, getUserRowMapper());
        return submissions;
    }

    public int insertSubmission(Submission submission)
    {
        String sql = "INSERT INTO SUBMISSIONS (" +
                "\"SubmissionID\", " +
                "\"SubmissionForm\", " +
                "\"SubmissionValues\") " +
                "VALUES(" +
                "'" + submission.getSubmissionId() + "', " +
                "'" + submission.getSubmissionForm() + "', "
                + submission.mapToSqlQuery() + ");";

                return jdbcTemplate.update(sql);
    }

    private static RowMapper<Submission> getUserRowMapper() {
        return (resultSet, i) -> {
            UUID submissionID = UUID.fromString(resultSet.getString("SubmissionID"));
            UUID submissionForm = UUID.fromString(resultSet.getString("SubmissionForm"));

            JSONObject submissionValues = new JSONObject( resultSet.getString("SubmissionValues"));

            Map<String, Object> submissions = submissionValues.toMap();



            return new Submission(submissionID, submissionForm, submissions);
        };
    }
}
