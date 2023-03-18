create table dynamicFields
(
    id      uuid not null,
    content varchar(255),
    name    varchar(255),
    form_id uuid,
    primary key (id)
);

create table form
(
    id                    uuid not null,
    data_retention_period int4 not null,
    form_name             varchar(255),
    user_id               uuid,
    dynamic_fields_id     uuid,
    submission_id         uuid,
    primary key (id)
);


create table submission
(
    id            uuid not null,
    cnp           varchar(255),
    creation_date date,
    name          varchar(255),
    series        varchar(255),
    form_id       uuid,
    primary key (id)
);

create table users
(
    id           uuid not null,
    account_type int4,
    address      varchar(255),
    company_name varchar(255),
    email        varchar(255),
    fiscal_code  varchar(255),
    name         varchar(255),
    password     varchar(255),
    surname      varchar(255),
    primary key (id)
);

alter table dynamicFields
    add constraint form_dynamic_fields
        foreign key (form_id)
            references form (id);

alter table submission
    add constraint form_submission
        foreign key (form_id)
            references form (id);
alter table form
    add constraint user_form
        foreign key (user_id)
            references users (id);
