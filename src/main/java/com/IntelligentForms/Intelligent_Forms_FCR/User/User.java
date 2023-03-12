package com.IntelligentForms.Intelligent_Forms_FCR.User;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.UUID;

public class User {
    private UUID userId;
    private String nume;
    private String prenume;
    private UUID[] forms;
    private String adresa;
    private String companyName;
    private String fiscalCode;
    public AccountType accountType;
    private String email;
    private String parola;
    public enum AccountType {
        PUBLIC_INSTITUTION, COMPANY
    }

    public User(@JsonProperty("UserID") UUID userId,
                @JsonProperty("Nume") String nume,
                @JsonProperty("Prenume") String prenume,
                @JsonProperty("Forms") UUID[] forms,
                @JsonProperty("Adresa") String adresa,
                @JsonProperty("CompanyName") String companyName,
                @JsonProperty("FiscalCode") String fiscalCode,
                @JsonProperty("AccountType") String accountType,
                @JsonProperty("Email") String email,
                @JsonProperty("Parola") String parola)
    {
        AccountType accountType1;
        if(accountType == "Company")
            accountType1 = AccountType.COMPANY;
        else accountType1 = AccountType.PUBLIC_INSTITUTION;

        this.userId = userId;
        this.nume = nume;
        this.prenume = prenume;
        this.forms = forms;
        this.adresa = adresa;
        this.companyName = companyName;
        this.fiscalCode = fiscalCode;
        this.accountType = accountType1;
        this.email = email;
        this.parola = parola;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public UUID[] getForms() {
        return forms;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getEmail() {
        return email;
    }

    public String getParola() {
        return parola;
    }

    // Returneaza forms[] ca si un Array formatat pentru SQL
    public String getFormsString()
    {
        String returnString = "{";
        for(int i = 0; i < forms.length - 1; i++)
        {
            returnString = returnString + ("\"" + forms[i].toString() + "\", ");
        }
        returnString = returnString + ("\"" + forms[forms.length - 1].toString() + "\"}");
        return returnString;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", forms=" + Arrays.toString(forms) +
                ", adresa='" + adresa + '\'' +
                ", companyName='" + companyName + '\'' +
                ", fiscalCode='" + fiscalCode + '\'' +
                ", accountType=" + accountType +
                ", email='" + email + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }
}
