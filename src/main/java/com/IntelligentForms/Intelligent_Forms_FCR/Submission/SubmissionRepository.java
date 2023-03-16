package com.IntelligentForms.Intelligent_Forms_FCR.Submission;

import com.IntelligentForms.Intelligent_Forms_FCR.exception.ApiRequestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
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

    public int insertSubmission(Submission submission, UUID submissionID)
    {
        String sql = "INSERT INTO SUBMISSIONS (" +
                "\"SubmissionID\", " +
                "\"SubmissionForm\", " +
                "\"CreationDate\", " +
                "\"SubmissionValues\") " +
                "VALUES(" +
                "'" + submissionID +"', " +
                "'" + submission.getSubmissionForm() + "', " +
                "'" + LocalDate.now() + "', "
                + submission.mapToSqlQuery() + ");";

        try {
            return jdbcTemplate.update(sql);
        }catch (DataIntegrityViolationException e)
        {
            throw new ApiRequestException("Formularul cu Id:" + submission.getSubmissionForm() + " nu exista");
        }
    }

    private static RowMapper<Submission> getUserRowMapper() {
        return (resultSet, i) -> {
            UUID submissionID = UUID.fromString(resultSet.getString("SubmissionID"));
            UUID submissionForm = UUID.fromString(resultSet.getString("SubmissionForm"));
            LocalDate creationDate = LocalDate.parse(resultSet.getString("CreationDate"));
            JSONObject submissionValues = new JSONObject( resultSet.getString("SubmissionValues"));

            Map<String, Object> submissions = submissionValues.toMap();



            return new Submission(submissionID, submissionForm, submissions, creationDate);
        };
    }

    public List<Submission> getSubmissionsOfForm(UUID formID) {
        String sql = "SELECT * FROM submissions WHERE \"SubmissionForm\" = '" + formID.toString() + "';";
        List<Submission> submissions= jdbcTemplate.query(sql, getUserRowMapper());
        return submissions;
    }

    public void insertSubmissionIntoForm(UUID submissionID, UUID formID)
    {
        String sqlUpdate = "" + "UPDATE Forms SET \"FormSubmissions\" = \"FormSubmissions\" || '{\"" + submissionID + "\"}' WHERE \"FormID\" = '" + formID + "';";
        //System.out.println(sqlUpdate);
        jdbcTemplate.update(sqlUpdate);
    }
}
