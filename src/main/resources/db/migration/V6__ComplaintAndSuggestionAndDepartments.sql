create table complaint (
    id serial,
    full_name varchar,
    phone_number varchar,
    text varchar,
    post_date timestamp , primary key (id)
);
create table suggestion(
    id serial,
    full_name varchar,
    phone_number varchar,
    text varchar,
    post_date timestamp , primary key (id)
);
create table departments(
    id serial,
    department_name varchar,
    phone_number varchar,
    email varchar,
    department_head varchar , primary key (id)
);

insert into departments(department_name,department_head) values ('директор', 'Иманкулова Диляра Бауржановна');
insert into departments(department_name,department_head) values ('заместитель директора', 'Наурызбаева Сауле Акылбековна');
insert into departments(department_name,department_head) values ('заместитель директора', 'Ауталиф Нұржан Сарсембайұлы ');
insert into departments(department_name,department_head) values ('заместитель директора', 'Сандаев Думан Турсынович');
insert into departments(department_name, phone_number,email,department_head) values ('Приёмная директора', '390-88-17','info@cz-almaty.kz','Иманкулова Диляра Бауржановна');
insert into departments(department_name, phone_number,email,department_head) values ('Отдел организационной и кадровой работы', '391-21-92','otdel_kadrov_czn@bk.ru','Каримова Гульжан Гариповна');
insert into departments(department_name, phone_number,email,department_head) values ('Отдел бухгалтерского учета', '390-88-91 ','buh@cz-almaty.kz','Кылышбаева Гульнур Аскаровна');
insert into departments(department_name, phone_number,email,department_head) values ('Референт (пресс-секретарь)', '391-10-24','press@cz-almaty.kz','Ташибаева Асель Нурлановна - Референт (пресс-секретарь)');
insert into departments(department_name, phone_number,email,department_head) values ('Канцелярия', '390-88-15','info@cz-almaty.kz','Кариева Айгуль Турехановна   (специалист)                       ');
insert into departments(department_name, phone_number,email,department_head) values ('Отдел правового обеспечения и государственных закупок', '391-20-34','pravo@cz-almaty.kz,    goszakup@cz-almaty.kz','Кульшиков Нурдаулет Тулегенович');
insert into departments(department_name, phone_number,email,department_head) values ('Отдел контроля качества', '390-88-71','control@cz-almaty.kz','Сандыбаев Серікболсын Галиддинұлы');
insert into departments(department_name, phone_number,email,department_head) values ('Отдел мониторинга', '390-88-76','monitoring@cz-almaty.kz','Сагынбаева Маржан Жанатовна');
insert into departments(department_name, phone_number,email,department_head) values ('Отдел прогнозирования', '390-88-45','prognoz@cz-almaty.kz','Иманалиева Альбина Касымхановна');
insert into departments(department_name, phone_number,email,department_head) values ('Отдел профессионального обучения', '390-88-06 390-79-38','prof.edu@cz-almaty.kz','Мирғалиева Құралай Бейбітқызы');
insert into departments(department_name, phone_number,email,department_head) values ('Приёмная Отдел постоянных рабочих мест', '390-88-30 390-88-51 391-09-27 390-88-47 391-20-42','prm@cz-almaty.kz','Жукенова Алтынай Аязбаевна');
insert into departments(department_name, phone_number,email,department_head) values ('Отдел временных рабочих мест', '390-79-41','vrm@cz-almaty.kz','Ауелова Гаухар Жумабековна');
insert into departments(department_name, phone_number,email,department_head) values ('Отдел поддержки соискателей', '390-88-19','gosuslugi@cz-almaty.kz','Жаңабаев Әділет Дәулетұлы ');
insert into departments(department_name, phone_number,email) values ('Call-центр', '390-88-79 221-66-11 224-20-00 391-20-33 391-20-36','hotline@cz-almaty.kz');
insert into departments(department_name, email,department_head) values ('Отдел оказания социальной помощи','prognoz@cz-almaty.kz','Казыбаева Гульзира Утемысовна');