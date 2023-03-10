package com.IntelligentForms.Intelligent_Forms_FCR.User;

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

    public User(UUID userId, String nume, String prenume, UUID[] forms, String adresa, String companyName, String fiscalCode, AccountType accountType, String email, String parola) {
        this.userId = userId;
        this.nume = nume;
        this.prenume = prenume;
        this.forms = forms;
        this.adresa = adresa;
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
