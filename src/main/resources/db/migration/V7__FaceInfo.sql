create table face_info (
    id                          serial,
    full_name                   varchar ,
    iin                         bigint not null,
    address                     varchar (4096),
    education_level             varchar,
    profession_name             varchar,
    target_group                varchar,
    phone_number                varchar,
    contact_number              varchar,
    email                       varchar,
    jobless_reason              varchar,
    condition_category          varchar,
    accounting_target           varchar,
    jobless_date                varchar ,
    registration_date           timestamp,
    active_measures             varchar,
    wish_profession             varchar,
    clarification_by_position   varchar,
    wish_min_salary             varchar,
    wish_max_salary             varchar,
    working_regime              varchar,
    reject_reason               varchar,
    chat_id                     bigint,
    employee_chat_id            bigint,primary key (id)
);
create table face_type (
    id                          serial,
    iin                         bigint not null,
    target_group                varchar,
    chat_id                     bigint,primary key (id)
);

create table form (
    id                          serial,
    iin                         bigint not null ,
    service_type                varchar ,
    measures_type               varchar ,
    file                        varchar ,
    file_name                   varchar ,
    employee_chat_id            bigint,
    client_chat_id              bigint,
    rate                        varchar ,
    face_info_id                int,
    reject_reason               varchar ,
    measures_bin                varchar ,
    service_date                timestamp ,primary key (id)
);
create table prof_file(
    id                          serial,
    measures_type               varchar,
    file                        varchar,
    chat_id                     bigint,primary key (id)
);