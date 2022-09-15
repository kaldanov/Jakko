create table users(
    id          serial,
    chat_id     bigint not null,
    full_name   varchar(255),
    phone       varchar(255),
    email       varchar(255),
    username    varchar(500), primary key (id)
);

create table LANGUAGE_USER (
    id          integer not null,
    chat_id     bigint not null,
    language_id integer not null, primary key (id)
);