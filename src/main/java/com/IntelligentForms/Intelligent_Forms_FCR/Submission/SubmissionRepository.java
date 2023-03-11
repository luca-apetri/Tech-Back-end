package com.IntelligentForms.Intelligent_Forms_FCR.Submission;

import com.IntelligentForms.Intelligent_Forms_FCR.Form.Form;
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
        // String sql = "SELECT userid, Nume, Prenume, Forms, Adresa, CompanyName, FiscalCode, AccountType, Email, Parola FROM users";
        String sql = "SELECT * FROM submissions";
        List<Submission> submissions = jdbcTemplate.query(sql, getUserRowMapper());
        return submissions;
    }

    private static RowMapper<Submission> getUserRowMapper() {
        return (resultSet, i) -> {
            UUID submissionID = UUID.fromString(resultSet.getString("SubmissionID"));
            UUID submissionForm = UUID.fromString(resultSet.getString("SubmissionForm"));

            JSONObject submissionValues = new JSONObject( resultSet.getString("SubmissionValues"));
//            JSONArray submissionValuesArray = new JSONArray(submissionValues.toMap());

            Map<String, ?> submissions = submissionValues.toMap();



            return new Submission(submissionID, submissionForm, submissions);
        };
    }
}
