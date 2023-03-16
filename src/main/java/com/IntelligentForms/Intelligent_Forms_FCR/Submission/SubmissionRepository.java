package com.IntelligentForms.Intelligent_Forms_FCR.Submission;

import com.IntelligentForms.Intelligent_Forms_FCR.Form.Form;
import com.IntelligentForms.Intelligent_Forms_FCR.User.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Submission> submissions = jdbcTemplate.query(sql, getSubmissionRowMapper());
        return submissions;
    }

    public int insertSubmission(Submission submission, UUID submissionID)
    {
        String sql = "INSERT INTO SUBMISSIONS (" +
                "\"SubmissionID\", " +
                "\"SubmissionForm\", " +
                "\"CreationDate\", " +
                "\"DataRetentionPeriod\", " +
                "\"SubmissionValues\") " +
                "VALUES(" +
                "'" + submissionID +"', " +
                "'" + submission.getSubmissionForm() + "', " +
                "'" + LocalDate.now() + "', " +
                "'" + getDataRetentionPeriodOfSubmission(submission) + "', "
                + submission.mapToSqlQuery() + ");";

        try {
            return jdbcTemplate.update(sql);
        }catch (DataIntegrityViolationException e)
        {
            System.err.println("Exception occurred: " + e.getMessage());
        }
        return 0;
    }

    private static RowMapper<Submission> getSubmissionRowMapper() {
        return (resultSet, i) -> {
            UUID submissionID = UUID.fromString(resultSet.getString("SubmissionID"));
            UUID submissionForm = UUID.fromString(resultSet.getString("SubmissionForm"));
            LocalDate creationDate = LocalDate.parse(resultSet.getString("CreationDate"));
            JSONObject submissionValues = new JSONObject( resultSet.getString("SubmissionValues"));

            Map<String, Object> submissions = submissionValues.toMap();



            return new Submission(submissionID, submissionForm, submissions, creationDate);
        };
    }

    private static RowMapper<Form> formRowMapper() {
        return (resultSet, i) -> {
            UUID formID = UUID.fromString(resultSet.getString("formid"));
            String nume = resultSet.getString("formName");
            UUID formOwner = UUID.fromString(resultSet.getString("formOwner"));
            String submissionsString = resultSet.getString("formSubmissions");
            int dataRetentionPeriod = resultSet.getInt("DataRetentionPeriod");

            //Conversie din String in Array de UUID
            UUID[] submissions = UserRepository.ArrayFromString(submissionsString);
            JSONObject dynamicFields = new JSONObject(resultSet.getString("dynamicFields"));
            Map<String, ?> dynamicFieldMap = dynamicFields.toMap();
            //System.out.println(dynamicFields.toString());
            return new Form(formID, nume, formOwner, (Map<String, ArrayList<?>>) dynamicFieldMap, submissions, dataRetentionPeriod);
        };
    }

    public List<Submission> getSubmissionsOfForm(UUID formID) {
        String sql = "SELECT * FROM submissions WHERE \"SubmissionForm\" = '" + formID.toString() + "';";
        List<Submission> submissions= jdbcTemplate.query(sql, getSubmissionRowMapper());
        return submissions;
    }

    public int getDataRetentionPeriodOfSubmission(Submission submission) {
        String sql = "SELECT * FROM Forms WHERE \"FormID\" = '" + submission.getSubmissionForm() + "';";
        System.out.println(sql);

        List<Form> forms = jdbcTemplate.query(sql, formRowMapper());
        return forms.get(0).getDataRetentionPeriod();
    }


    public void insertSubmissionIntoForm(UUID submissionID, UUID formID)
    {
        String sqlUpdate = "" + "UPDATE Forms SET \"FormSubmissions\" = \"FormSubmissions\" || '{\"" + submissionID + "\"}' WHERE \"FormID\" = '" + formID + "';";
        //System.out.println(sqlUpdate);
        jdbcTemplate.update(sqlUpdate);
    }

    public void deleteOldSubmissions()
    {
        String sql = "" + "DELETE FROM SUBMISSIONS WHERE (current_date - \"CreationDate\") > \"DataRetentionPeriod\";";
        jdbcTemplate.update(sql);
    }
}
