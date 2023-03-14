package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import com.IntelligentForms.Intelligent_Forms_FCR.User.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    public int insertForm(Form form)
    {
        String sql = "INSERT INTO FORMS (" +
                "\"FormID\", " +
                "\"FormName\", " +
                "\"FormOwner\", " +
                "\"DynamicFields\", " +
                "\"FormSubmissions\", " +
                "\"FormText\") " +
                "VALUES(" +
                "uuid_generate_v4(), " +
                "'" + form.getFormName() + "', " +
                "'" + form.getFormOwner() + "', " +
                "'" + form.getSubmissionsString() + "', " +
                "'" + form.getFormSubmissions() + "', " +
                "'" + form.getFormText() + "', " +
                form.mapToSqlQuery() + ");";

        return jdbcTemplate.update(sql);
    }

    private static RowMapper<Form> getUserRowMapper() {
        return (resultSet, i) -> {
            UUID formID = UUID.fromString(resultSet.getString("formid"));
            String nume = resultSet.getString("formName");
            UUID formOwner = UUID.fromString(resultSet.getString("formOwner"));
            String submissionsString = resultSet.getString("formSubmissions");
            String formText = resultSet.getString("formText");

            //Conversie din String in Array de UUID
            UUID[] submissions = UserRepository.ArrayFromString(submissionsString);

            JSONObject dynamicFields = new JSONObject( resultSet.getString("dynamicFields"));

            return new Form(formID, nume, formOwner, dynamicFields, formText, submissions);
        };
    }
}

