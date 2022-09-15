create sequence hibernate_sequence start with 1 increment by 1;

create table properties (
    id          integer not null,
    name        varchar(4096),
    value       varchar(4096),
    latitude    varchar(500),
    longitude   varchar(500),primary key (id)
);



insert into properties (id, name, value)        values (1, 'botUsername',   'SrochnoTestBot');
insert into properties (id, name, value)        values (2, 'botToken',      '1286841576:AAHlh4roBm0lSobvwCZldYTGo2776Jqxqok');

insert into properties (id,latitude, longitude) values (3, '43.2618',     '76.9462');

