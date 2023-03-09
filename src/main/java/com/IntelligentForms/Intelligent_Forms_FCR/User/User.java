package com.IntelligentForms.Intelligent_Forms_FCR.User;

import java.util.UUID;

public class User {
    private UUID userId;
    private String nume;
    private String prenume;
    private String adresa;
    private UUID[] forms;
    private String companyName;
    private String fiscalCode;
    public AccountType accountType;
    private String email;
    private String parola;
    enum AccountType {
        PUBLIC_INSTITUTION, COMPANY
    }

    public User(UUID userId, String nume, String prenume, String adresa, UUID[] forms, String companyName, String fiscalCode, AccountType accountType, String email, String parola) {
        this.userId = userId;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.forms = forms;
        this.companyName = companyName;
        this.fiscalCode = fiscalCode;
        this.accountType = accountType;
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
}
