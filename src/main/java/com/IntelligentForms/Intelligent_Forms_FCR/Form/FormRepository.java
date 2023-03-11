package com.IntelligentForms.Intelligent_Forms_FCR.Form;

import com.IntelligentForms.Intelligent_Forms_FCR.User.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
        // String sql = "SELECT userid, Nume, Prenume, Forms, Adresa, CompanyName, FiscalCode, AccountType, Email, Parola FROM users";
        String sql = "SELECT * FROM forms";
        List<Form> forms = jdbcTemplate.query(sql, getUserRowMapper());
        return forms;
    }

    private static RowMapper<Form> getUserRowMapper() {
        return (resultSet, i) -> {
            UUID formID = UUID.fromString(resultSet.getString("formid"));
            String nume = resultSet.getString("formName");
            UUID formOwner = UUID.fromString(resultSet.getString("formOwner"));
            String submissionsString = resultSet.getString("formSubmissions");

            //Conversie din String in Array de UUID
            String[] submissionsStringSplit = submissionsString.replace("{", "").replace("}","").strip().split(",");
            UUID[] submissions = new UUID[submissionsStringSplit.length];
            for(int iterator = 0; iterator < submissions.length; iterator++)
            {
                submissions[iterator] = UUID.fromString(submissionsStringSplit[iterator]);
            }

            JSONObject dynamicFields = new JSONObject( resultSet.getString("dynamicFields"));
            Map<String , ?> dynamicFieldMap = dynamicFields.toMap();



            return new Form(formID, nume, formOwner, dynamicFieldMap, submissions);
        };
    }
}

