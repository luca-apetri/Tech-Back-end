package com.IntelligentForms.Intelligent_Forms_FCR.User;

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

    private static RowMapper<User> getUserRowMapper() {
        return (resultSet, i) -> {
            UUID userID = UUID.fromString(resultSet.getString("userid"));
            String nume = resultSet.getString("Nume");
            String prenume = resultSet.getString("Prenume");
            String formsString = resultSet.getString("Forms");

            //Conversie din String in Array de UUID
            String[] formsStringSplit = formsString.replace("{", "").replace("}","").strip().split(",");
            UUID[] forms = new UUID[formsStringSplit.length];
            for(int iterator = 0; iterator < forms.length; iterator++)
            {
                forms[iterator] = UUID.fromString(formsStringSplit[iterator]);
            }

            String adresa = resultSet.getString("Adresa");
            String companyName = resultSet.getString("CompanyName");
            String fiscalCode = resultSet.getString("FiscalCode");
            User.AccountType accountType;
            if(resultSet.getString("AccountType") == "Company")
                accountType = User.AccountType.COMPANY;
            else {
                accountType = User.AccountType.PUBLIC_INSTITUTION;
            }
            String email = resultSet.getString("Email");
            String parola = resultSet.getString("Parola");

            return new User(userID, nume, prenume, forms, adresa, companyName, fiscalCode, accountType, email, parola);
        };
    }
}
