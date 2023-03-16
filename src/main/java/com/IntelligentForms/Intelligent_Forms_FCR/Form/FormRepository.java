package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import com.IntelligentForms.Intelligent_Forms_FCR.User.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class FormRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public FormRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Form> SelectAllForms(){
        String sql = "SELECT * FROM forms";
        List<Form> forms = jdbcTemplate.query(sql, getFormRowMapper());
        return forms;
    }

    public int insertForm(Form form, UUID formID)
    {
        String sql = "INSERT INTO FORMS (" +
                "\"FormID\", " +
                "\"FormName\", " +
                "\"FormOwner\", " +
                "\"DynamicFields\", " +
                "\"FormSubmissions\", " +
                "\"DataRetentionPeriod\"" +
                ") " +
                "VALUES(" +
                "'" + formID + "', " +
                "'" + form.getFormName() + "', " +
                "'" + form.getFormOwner() + "', " +
                "'" + form.formatDynamicFields() + "', " +
                "'" + form.getSubmissionsString() + "', " +
                "'" + form.getDataRetentionPeriod() + "'" +
                ");";
        System.out.println(sql);
        try {
            return jdbcTemplate.update(sql);
        }catch (DataIntegrityViolationException e)
        {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    return 0;
    }

    private static RowMapper<Form> getFormRowMapper() {
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
    public List<Form> getFormsOfUser(UUID userID) {
        String sql = "SELECT * FROM forms WHERE \"FormOwner\" = '" + userID.toString() + "';";
        List<Form> forms= jdbcTemplate.query(sql, getFormRowMapper());
        return forms;
    }

    private Form getFormById(UUID formID)
    {
        String sql = "SELECT * FROM forms WHERE \"FormID\" = '" + formID + "';";
        List<Form> forms= jdbcTemplate.query(sql, getFormRowMapper());
        return forms.get(0);
    }

    public void insertFormIntoUser(UUID formID, UUID userID)
    {
        String sqlUpdate = "" + "UPDATE Users SET \"Forms\" = \"Forms\" || '{\"" + formID + "\"}' WHERE \"UserID\" = '" + userID + "';";
        //System.out.println(sqlUpdate);
        jdbcTemplate.update(sqlUpdate);
    }

    public void udpateForm(Form form) {
        String sql = "" +
                "UPDATE Forms SET \"FormName\" = '" + form.getFormName() + "', " +
                "\"DynamicFields\" = '" + form.formatDynamicFields() + "' " +
                "WHERE \"FormID\" = '" + form.getFormId() + "';";

        jdbcTemplate.update(sql);
    }

    public void removeFormFromUser(UUID formID, UUID userID)
    {
        String sqlUpdate = "" + "UPDATE Users SET \"Forms\" = ARRAY_REMOVE(\"Forms\", '" + formID + "') WHERE \"UserID\" = '" + userID + "';";
        //System.out.println(sqlUpdate);
        jdbcTemplate.update(sqlUpdate);
    }

    public void deleteForm(UUID formID) {
        Form form = getFormById(formID);
        removeFormFromUser(formID , form.getFormOwner());
        String sql = "" + "DELETE FROM SUBMISSIONS WHERE \"SubmissionForm\" = '" + formID + "';" +
                "DELETE FROM FORMS WHERE \"FormID\" = '" + formID + "';";
        jdbcTemplate.update(sql);
    }
}

