create table keyboard (
    id          int not null constraint KEYBOARD_ID_UINDEX unique,
    button_ids  varchar(4096),
    inline      boolean default false,
    comment     varchar(4096),
    constraint KEYBOARD_PK primary key (ID)
);


INSERT INTO keyboard (ID, BUTTON_IDS, INLINE, COMMENT) VALUES (1, '100,103;5,14;105,13;9', false, 'Главное меню для пользователя');
INSERT INTO keyboard (ID, BUTTON_IDS, INLINE, COMMENT) VALUES (2, '7;6', false, 'Выбор языка при старте (6 add awter)');
INSERT INTO keyboard (ID, BUTTON_IDS, INLINE, COMMENT) VALUES (3, '45,110;111,10', false, 'Меню "/admin"');
INSERT INTO keyboard (id, button_ids, inline, comment) VALUES (4, '58,59;60,61;23,62', false, 'Меню "Редактор кнопок"');
INSERT INTO keyboard (ID, BUTTON_IDS, INLINE, COMMENT) VALUES (5, '100,103;5,14;104,105;9', false, 'Главное меню для сотрудника');
INSERT INTO keyboard (ID, BUTTON_IDS, INLINE, COMMENT) VALUES (6, '36,37;10', false, 'Предложения/жалобы');
INSERT INTO keyboard (ID, BUTTON_IDS, INLINE, COMMENT) VALUES (7, '123,101;10', false, 'Услуги');
INSERT INTO keyboard (ID, BUTTON_IDS, INLINE, COMMENT) VALUES (8, '41,42', false, 'Принять или откланить');
INSERT INTO keyboard (ID, BUTTON_IDS, INLINE, COMMENT) VALUES (9, '126,127;128,129;10', false, 'услуги');
INSERT INTO keyboard (ID, BUTTON_IDS, INLINE, COMMENT) VALUES (10, '130,131', true, 'Next или Prev');
INSERT INTO keyboard (ID, BUTTON_IDS, INLINE, COMMENT) VALUES (12,'43', false, 'Меню "Поделится контактом"');
insert into keyboard(id, button_ids, inline, comment)  VALUES (13,'134,135,136;137,138',   true ,  'Меню "Оценки"');
insert into keyboard(id, button_ids, inline, comment)  VALUES (14,'20,21;22,10',   false ,  'Меню "О нас"');
insert into keyboard(id, button_ids, inline, comment)  VALUES (15,'106,107;108,109;115,10',   false ,  'Меню "Личный Кабинет"');
insert into keyboard(id, button_ids, inline, comment)  VALUES (16,'132,133',   true ,  'Меню "Подтвердить / Отказать"');
insert into keyboard(id, button_ids, inline, comment)  VALUES (17,'112,113;114,24',   false ,  'Меню "Инфа"');
insert into keyboard(id, button_ids, inline, comment)  VALUES (18,'16,17;116,117;119,24',   false ,  'Меню "ред"');
insert into keyboard(id, button_ids, inline, comment)  VALUES (19,'140;143',   true ,  'Ссылка на услуги онлайн');
insert into keyboard(id, button_ids, inline, comment)  VALUES (44,'44',   false ,  'Кнопка"Отменить"');
insert into keyboard(id, button_ids, inline, comment)  VALUES (125,'125',   true ,  'Ссылка на вакансии');
insert into keyboard(id, button_ids, inline, comment)  VALUES (139,'139',   true ,  'Ссылка на чат для лир');

