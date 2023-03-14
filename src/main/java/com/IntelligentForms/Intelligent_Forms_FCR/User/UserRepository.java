package com.IntelligentForms.Intelligent_Forms_FCR.User;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> SelectAllUsers(){
       // String sql = "SELECT userid, Nume, Prenume, Forms, Adresa, CompanyName, FiscalCode, AccountType, Email, Parola FROM users";
        String sql = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(sql, getUserRowMapper());
        return users;
    }

    int insertUser(User user) {
        String sql = "insert into users (" +
                "\"UserID\", " +
                "\"Nume\", " +
                "\"Prenume\", " +
                "\"Forms\", " +
                "\"Adresa\", " +
                "\"CompanyName\", " +
                "\"FiscalCode\", " +
                "\"AccountType\", " +
                "\"Email\", " +
                "\"Parola\") " +
                "values (" +
                "uuid_generate_v4(), " +
                "'" + user.getNume() + "', " +
                "'" + user.getPrenume() + "', " +
                "'" + user.getFormsString() + "', " +
                "'" + user.getAdresa() + "', " +
                "'" + user.getCompanyName() + "', " +
                "'" + user.getFiscalCode() + "', " +
                "'" + user.getAccountType() + "', " +
                "'" + user.getEmail() + "', " +
                "'" + user.getParola() +
                "');";

        //System.out.println(sql);

        return jdbcTemplate.update(sql);

    }

    boolean isEmailTaken(String email)
    {
        String sql = "" +
                "SELECT EXISTS ( " +
                " SELECT 1 " +
                " FROM USERS " +
                " WHERE \"Email\" = ?" +
                ");";
        return jdbcTemplate.queryForObject(sql, new Object[] {email}, (resultSet, i)->resultSet.getBoolean(1));
    }

    private static RowMapper<User> getUserRowMapper() {
        return (resultSet, i) -> {
            UUID userID = UUID.fromString(resultSet.getString("UserID"));
            String nume = resultSet.getString("Nume");
            String prenume = resultSet.getString("Prenume");

//            String formsString = resultSet.getString("Forms");

            //Conversie din String in Array de UUID
//            UUID[] forms = ArrayFromString(formsString);
            UUID[] forms = ArrayFromString(resultSet.getString("Forms"));

            String adresa = resultSet.getString("Adresa");
            String companyName = resultSet.getString("CompanyName");
            String fiscalCode = resultSet.getString("FiscalCode");
            String accountType = resultSet.getString("AccountType");

            String email = resultSet.getString("Email");
            String parola = resultSet.getString("Parola");


            return new User(userID, nume, prenume, forms, adresa, companyName, fiscalCode, accountType, email, parola);
        };
    }



    public static UUID[] ArrayFromString(String formsString) {
        String[] formsStringSplit = formsString.replace("{", "").replace("}","").strip().split(",");
        UUID[] forms = new UUID[formsStringSplit.length];
        for(int iterator = 0; iterator < forms.length; iterator++)
        {
            forms[iterator] = UUID.fromString(formsStringSplit[iterator]);
        }
        return forms;
    }


    public User getUserById(UUID userID) {
        String sql = "SELECT * FROM users WHERE \"UserID\" = '" + userID.toString() + "';";
        List<User> user= jdbcTemplate.query(sql, getUserRowMapper());
        return user.get(0);
    }
}
