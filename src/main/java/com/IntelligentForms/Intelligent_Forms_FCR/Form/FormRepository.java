package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import com.IntelligentForms.Intelligent_Forms_FCR.User.UserRepository;
import com.IntelligentForms.Intelligent_Forms_FCR.exception.ApiRequestException;
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
        List<Form> forms = jdbcTemplate.query(sql, getUserRowMapper());
        return forms;
    }

    public int insertForm(Form form, UUID formID)
    {
        String sql = "INSERT INTO FORMS (" +
                "\"FormID\", " +
                "\"FormName\", " +
                "\"FormOwner\", " +
                "\"DynamicFields\", " +
                "\"FormSubmissions\"" +
                ") " +
                "VALUES(" +
                "'" + formID + "', " +
                "'" + form.getFormName() + "', " +
                "'" + form.getFormOwner() + "', " +
                "'" + form.formatDynamicFields() + "', " +
                "'" + form.getSubmissionsString() + "'" +
                ");";
        System.out.println(sql);
        try {
            return jdbcTemplate.update(sql);
        }catch (DataIntegrityViolationException e)
        {
            e.printStackTrace();
            throw new ApiRequestException("Utilizatorul cu Id:" + form.getFormOwner() + " nu exista");
        }

    }

    private static RowMapper<Form> getUserRowMapper() {
        return (resultSet, i) -> {
            UUID formID = UUID.fromString(resultSet.getString("formid"));
            String nume = resultSet.getString("formName");
            UUID formOwner = UUID.fromString(resultSet.getString("formOwner"));
            String submissionsString = resultSet.getString("formSubmissions");

            //Conversie din String in Array de UUID
            UUID[] submissions = UserRepository.ArrayFromString(submissionsString);
            JSONObject dynamicFields = new JSONObject(resultSet.getString("dynamicFields"));
            Map<String, ?> dynamicFieldMap = dynamicFields.toMap();
            //System.out.println(dynamicFields.toString());
            return new Form(formID, nume, formOwner, (Map<String, ArrayList<?>>) dynamicFieldMap, submissions);
        };
    }
    public List<Form> getFormsOfUser(UUID userID) {
        String sql = "SELECT * FROM forms WHERE \"FormOwner\" = '" + userID.toString() + "';";
        List<Form> forms= jdbcTemplate.query(sql, getUserRowMapper());
        return forms;
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
}

