create table admin (
    id serial,
    chat_id bigint,
    comment varchar(500) not null, primary key (id)
);
create table employee(
    id serial,
    full_name varchar,
    phone_number varchar not null ,
    depart varchar,
    chat_id bigint,primary key (id)
);
insert into admin(chat_id, comment) values (343117746, 'tzombie');
