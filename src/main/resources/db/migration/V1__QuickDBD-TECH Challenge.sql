-- Exported from QuickDBD: https://www.quickdatabasediagrams.com/
-- Link to schema: https://app.quickdatabasediagrams.com/#/d/x0kpHJ
-- NOTE! If you have used non-SQL datatypes in your design, you will have to change these here.

-- Modify this code to update the DB schema diagram.
-- To reset the sample schema, replace everything with
-- two dots ('..' - without quotes).
-- Table documentation comment 1 (try the PDF/RTF export)

CREATE TABLE IF NOT EXISTS "users" (
                        "UserID" uuid   NOT NULL UNIQUE,
                        "Nume" varchar(100)   NOT NULL,
                        "Prenume" varchar(100)   NOT NULL,
                        "Forms" uuid[],
                        "Adresa" varchar(100)   NOT NULL,
                        "CompanyName" varchar(100),
                        "FiscalCode" varchar(100),
                        "AccountType" varchar(20)   NOT NULL,
                        "Email" varchar(100)   NOT NULL,
                        "Parola" varchar(50)   NOT NULL,
                        CONSTRAINT "pk_User" PRIMARY KEY (
                                                          "UserID"
                            )
);

CREATE TABLE IF NOT EXISTS "forms" (
                         "FormID" uuid   NOT NULL UNIQUE ,
                         "FormName" varchar(50)   NOT NULL,
                         "FormOwner" uuid   NOT NULL,
                         "DynamicFields" json   NOT NULL,
                         "FormSubmissions" uuid[],
                         "FormText" varchar(2000)
);

CREATE TABLE IF NOT EXISTS "submissions" (
                               "SubmissionID" uuid   NOT NULL UNIQUE ,
                               "SubmissionForm" uuid   NOT NULL,
                               "SubmissionValues" json   NOT NULL
);

ALTER TABLE "forms" ADD CONSTRAINT "fk_Forms_FormOwner" FOREIGN KEY("FormOwner")
    REFERENCES "users" ("UserID");
ALTER TABLE "submissions" ADD CONSTRAINT "fk_Submissions_SubmissionForm" FOREIGN KEY("SubmissionForm")
    REFERENCES "forms" ("FormID");


