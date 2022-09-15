create table message (
    id          integer,
    name        varchar(8096) not null,
    photo       varchar,
    keyboard_id integer,
    file        varchar,
    file_type   varchar,
    language_id integer
);

create table button (
    id              integer      not null,
    name            varchar(300) not null,
    command_id      integer default 0,
    url             varchar(4096),
    request_contact Boolean default false,
    message_id      integer,
    lang_id         integer
);

INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (1, 'Команда не найдена', null, 1, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (1, 'Команда не найдена', null, 1, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (2, 'Добро пожаловать', null, 1, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (2, 'онлайн кеңесшісіне қош келдіңіз!', null, 1, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (3, '✏️', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (3, '✏️', null, null, null, null, 2);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) values (4, concat('Интерфейс тілін таңдаңыз.',E'\n','-------------------------------',E'\n','Выберите язык интерфейса.'), null, 2, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) values (4, concat('Интерфейс тілін таңдаңыз.',E'\n','-------------------------------',E'\n','Выберите язык интерфейса.'), null, 2, null, null, 2);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) values (4, concat('Интерфейс тілін таңдаңыз.',E'\n','-------------------------------',E'\n','Выберите язык интерфейса.'), null, 2, null, null, 3);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (6, 'Администрирование', null, 3, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (6, 'Администрирование', null, 3, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (7, '❌', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (7, '❌', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (8, '🔎', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (8, '🔎', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (9, '/del', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (9, '/del', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (10, 'Добро пожаловать', null, 5, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (10, 'онлайн кеңесшісіне қош келдіңіз!', null, 5, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (11, '•	Көше: Н.Назарбаев  50.
•	<b>Тел. / факс:</b> 224-20-00;221-66-11.
•	<b>Пошта:</b> centr_almaty@mail.ru
•	<b>Сайт:</b> cz-almaty.kz
•	<b>Жұмыс кестесі:</b>  Дүйсенбі-Жұма  9:00-18:00, Түскі үзіліс 13:00-14:00, Сенбі-Жексенбі демалыс.
•	<b>Жұмыспен қамту орталығының әлеуметтік желілеріне сілтемелер:</b> www.facebook.com/cznalmaty/  www.instagram.com/centr_zanyatosti_almaty/
•	<b>Картасы:</b>', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (11, '•	Улица: Н.Назарбаева 50
•	<b>Тел./факс:</b> 224-20-00;221-66-11.
•	<b>Почта:</b> centr_almaty@mail.ru
•	<b>Сайт:</b> cz-almaty.kz
•	<b>График работы:</b>По будням  9:00-18:00, обеденный перерыв 13:00-14:00, Суббота, Воскресенье выходной.
•	<b>Ссылки на социальные сети Центра занятости:</b> www.facebook.com/cznalmaty/  www.instagram.com/centr_zanyatosti_almaty/
•	<b>Карта:</b>', null, null, null, null, 1);


INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (15, 'Список меню доступных для редактирования', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (15, 'Список меню доступных для редактирования', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (16, 'Выберите что нужно отредактировать', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (16, 'Выберите что нужно отредактировать', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (17, '<b>Ссылки в виде кнопок:</b>', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (17, '<b>Ссылки в виде кнопок:</b>', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (18, 'Нету доступа', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (18, 'Нету доступа', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (19, 'Для данной кнопки не предусмотрено сообщение', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (19, 'Для данной кнопки не предусмотрено сообщение', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (20, 'О нас', null, 14, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (20, 'Бізтуралы', null, 14, null, null, 2);

INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (22, 'Введите название (не более 100 символов).', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (22, 'Введите название (не более 100 символов).', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (24, 'Толық <b> аты-жөніңізді</b> жазыңыз
-------------------------------
Напишите, пожалуйста, своё полное <b> Ф.И.О. </b> ⬇️', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (24, 'Толық <b> аты-жөніңізді</b> жазыңыз
-------------------------------
Напишите, пожалуйста, своё полное <b> Ф.И.О. </b> ⬇️', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (25, '❌', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (25, '❌', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (26, '🔎', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (26, '🔎', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (27, '/del', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (27, '/del', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (28, 'Пользователь не зарегистрирован в данном боте', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (28, 'Пользователь не зарегистрирован в данном боте', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (29, 'Пользователь уже администратор', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (29, 'Пользователь уже администратор', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (30, 'Список администраторов:

%s
Чтобы добавить нового пользователя, отправьте контакт пользователя. Он должен быть зарегистрированным.', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (30, 'Список администраторов:

%s
Чтобы добавить нового пользователя, отправьте контакт пользователя. Он должен быть зарегистрированным.', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (31, 'Должен быть минимум 1 администратор.', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (31, 'Должен быть минимум 1 администратор.', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (32, 'Название не может состоять только из цифр, введите новое название.', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (32, 'Название не может состоять только из цифр, введите новое название.', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (33, 'Такое название уже используется', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (33, 'Такое название уже используется', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (34, 'Для изменения названия введите:
name: Новое название
Для изменения ссылки напишите
link: Новая ссылка', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (34, 'Для изменения названия введите:
name: Новое название
Для изменения ссылки напишите
link: Новая ссылка', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (35, 'Ваш файл успешно отправлен', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (35, 'Ваш файл успешно отправлен', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (36, 'Список меню доступных для редактирования:', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (36, 'Список меню доступных для редактирования:', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (37, '<b>Ссылки в виде кнопок:</b>', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (37, '<b>Ссылки в виде кнопок:</b>', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (38, '<b>Название кнопки:</b> %s
<b>Сообщение:</b>

%s  %s

Текущая версия для <b>%s</b> языка:
Добавьте фото или файл чтобы прикрепить к сообщению. Для редактирования используйте кнопки.', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (38, '<b>Название кнопки:</b> %s
<b>Сообщение:</b>

%s  %s

Текущая версия для <b>%s</b> языка:
Добавьте фото или файл чтобы прикрепить к сообщению. Для редактирования используйте кнопки.', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (39, '<b>Название кнопки:</b> %s
<b>Сообщение:</b>

%s

Текущая версия для <b>%s</b> языка:
Добавьте фото или файл чтобы прикрепить к сообщению. Для редактирования используйте кнопки.', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (39, '<b>Название кнопки:</b> %s
<b>Сообщение:</b>

%s

Текущая версия для <b>%s</b> языка:
Добавьте фото или файл чтобы прикрепить к сообщению. Для редактирования используйте кнопки.', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (40, 'Для данной кнопки отсутствует такая возможность', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (40, 'Для данной кнопки отсутствует такая возможность', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (41, 'Введите название (не более 100 символов).', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (41, 'Введите название (не более 100 символов).', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (42, 'Введите новый текст', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (42, 'Введите новый текст', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (43, 'Отправьте файл', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (43, 'Отправьте файл', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (44, 'Выберите что нужно отредактировать:', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (44, 'Выберите что нужно отредактировать:', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (45, 'Для данной кнопки не предусмотрено сообщение', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (45, 'Для данной кнопки не предусмотрено сообщение', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (46, '<i>Изложите суть жалобы: ⬇</i>', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (46, '<i>Шағымның мәнін көрсетіңіз: ⬇</i>', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (47, '<i><b>Ваше жалоба отправлена</b></i>', null, 1, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (47, '<i><b>Сіздің шағымыңыз жіберілді</b></i>', null, 1, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (48, '<b>Байланыс нөмірін жіберу</b> батырмасын басыңыз⬇️', null, 12, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (48, 'Нажмите кнопку <b> Отправить контакт </b> ⬇️', null, 12, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (49, 'Предложения/жалобы', null, 6, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (49, 'Ұсыныстар / шағымдар', null, 6, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (50, 'Неверный формат данных', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (50, 'Деректердің форматы жарамсыз', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (51, '<i>Напишите свое предложение :</i>', null, null, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (51, '<i>Өз ұсынысыңызды жазыңыз :</i>', null, null, null, null, 2);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (52, '<i><b>Ваше предложение отправлено</b></i>', null, 1, null, null, 1);
INSERT INTO message (id, name, photo, keyboard_id, file, file_type, language_id) VALUES (52, '<i><b>Сіздің ұсынысыңыз жіберілді</b></i>', null, 1, null, null, 2);


insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (100,'Отправьте список в формате excel', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (100,'Отправьте список в формате excel', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (101,'Файл больше 50 Мб. Бот не может прочитать такой большой файл', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (101,'Файл больше 50 Мб. Бот не может прочитать такой большой файл', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (102,'Не могу прочитать файл, убедитесь что это файл Excel, и повторите попытку', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (102,'Не могу прочитать файл, убедитесь что это файл Excel, и повторите попытку', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (103,'Лист с именем <b> %s </b>не найден', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (103,'Лист с именем <b> %s </b>не найден', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (104,concat('Данные сохранены.', E'\n', 'Добавлено %d сотрудника.'), null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (104,concat('Данные сохранены.', E'\n', 'Добавлено %d сотрудника.'), null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (105,'Услуги', null, 7, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (105,'Қызметтер', null, 7, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (106,'ЦЗН г.Алматы просит <b>Вас пройти первичную регистрацию</b> по ссылке чтобы просмотреть <b>все актуальные вакансии</b>', null, 125, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (106,'<b>Өзекті бос жұмыс орындары</b> туралы толық ақпарат алу үшін, Алматы қ. ЖКО сізді сілтемесі бойынша <b>бастапқы тіркелуден</b> өтуіңізді сұрайды', null, 125, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (107,'Напишите, пожалуйста, своё полное <b> Ф.И.О. </b> ⬇', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (107,'Толық <b> аты-жөніңізді</b> жазыңыз', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (108,'<i><b>Введите ИИН ⬇</b></i>', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (108,'<i><b>ЖСН енгізіңіз ⬇</b></i>', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (109,'<i><b>ИИН должен состоять только из цифр</b></i>', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (109,'<i><b>ЖСН тек қана</b></i>', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (110,'Вы уже зарегистрированы как Лицо ищущий работу, желаете ли вы перерегистрироваться?', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (110,'Сіз жұмыс іздеуші ретінде тіркелдіңіз, қайта тіркеуді қалайсыз ба?', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (111,'Услуги ЦЗН', null, 9, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (111,'ЖҚО Қызметтер', null, 9, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (112,'Адрес регистрации (страна, область, город, район, населенный пункт, наименование  улицы,номер дома, номер квартиры):', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (112,'Тіркеу мекенжайы (елі, облысы, қаласы, ауданы, елді мекені, көше атауы,үй нөмірі, пәтер нөмірі):', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (113,'Уровень образования:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (113,'Білім деңгейі:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (114,'Дошкольное;Начальное (с 1 по 4 класс);Нет образования;Основное среднее (с 1 по 9 класс);Среднее общеее (с 1 по 11/12 классы);Начальное профессиональное;Среднее специальное;Незаконченное высшее;Высшее;Послевузовское (магистр, доктор, phd, интерн);Послесреднее', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (114,'Мектепке дейінгі;Бастауыш (1-ден 4-сыныпқа дейін);Білімі жоқ;Негізгі орта (1-ден 9-сыныпқа дейін);Орташа жалпы (1 11/12 сыныптар);Бастапқы кәсіби;Арнайы орта;Аяқталмаған жоғары;Жоғары;Жоғары оқу орнынан кейінгі (магистр, доктор, phd, интерн);Ортадан кейінгі', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (115,'Наименование специальности:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (115,'Мамандық атауы:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (116,'Целевая группа', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (116,'Мақсатты топ:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (117,'Воспитанникии выпускники дет. домов, оставшихся без попечения родителей;лица предпенсионного возраста (за два года до пенсии);инвалиды;лица, освобожденные из мест лишения свободы и (или) прошедшие принудительное лечение;лица, состоящие на учете службы пробации;одинокие, многодетные родители, воспитывающие несовершеннолетних детей;лица, по уходу за ребенком до 7 лет,ребенком-инвалидом,инвалидами 1-2 групп;оралманы;малообеспеченные (граждане у которых среднедушевой доход семьи не превышает 70% от прожиточного минимума);лица, пострадавшие в результате акта терроризма, и лица, участвовавшие в его пресечении;многодетная семья (от 4 и более детей);лица, являющиеся должниками по алиментам;получатель адресной социальной помощи;Не выбрано', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (117,'Балалар үйлерінің, жетім балалар мен ата-анасының қамқорлығынсыз қалған балаларға арналған мектеп-интернаттардың түлектері мен тәрбиеленушілері;зейнеткерлік жас алдындағы тұлғалар (зейнеткерлікке шығуға екі жыл қалған);мүгедектер;бас бостандығынанайыруорындарынанбосатылғанжәне (немесе) мәжбүрлепемдеуденөткенадамдар;пробация қызметінің есебінде тұрған адамдар;кәмелетке толмаған балаларды тәрбиелеп отырған жалғызілікті, көп балалы ата-аналар;7 жасқа дейінгі балаларға, мүгедек балаға, бірінші және екінші топтағы мүгедектерге күтім жасайтын адамдар;оралмандар;терроризм актісінің салдарынан зардап шеккен адамдар және оның жолын кесуге қатысқан адамдар;аз қамтылғандар (отбасының жан басына шаққандағы орташа табысы ең төменгі күнкөріс деңгейінің 70% - ынан аспайтын азаматтар);көп балалы отбасы (4 және одан да көп балалардан);алимент бойынша борышкерлер болып табылатын тұлғалар;атаулы әлеуметтік көмек алушы;Таңдалмаған', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (118,'Мобильный телефон:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (118,'Ұялы телефон:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (119,'Контактный телефон:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (119,'Байланыс телефоны:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (120,'E-mail:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (120,'E-mail:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (121,'Причина незанятости:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (121,'Жұмыссыздықтың себебі:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (122,'По соглашению сторон;По собственному желанию;По окончанию срока договора;По сокращению численности штата;Не работал после учебного заведения', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (122,'Тараптардың келісімі бойынша;Өз қалауы бойынша;Шарт мерзімі аяқталғаннан кейін;Штат санын қысқарту бойынша;Оқу орнынан кейін жұмыс істемеді', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (123,'Категория состояния:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (123,'Жай-күй санаты:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (124,'Не работавшие: Год и более;Никогда не работал;Менее месяца;До 3 мес;От 3 до 6 мес;От 6 до 12 мес;Обучающиеся:Студент;Школьник старших классов (9-10 класс)', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (124,'Жұмыс істемегендер:Жыл;Ешқашан жұмыс істемеді;Бір айдан аз;3 айға дейін бойынша;3 айдан 6 айға дейін;6 айдан 12 айға дейін;Білім алушылар:Студент;Жоғары сынып оқушысы (9-10 сынып)', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (125,'Цель учета:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (125,'Есеп мақсаты:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (126,'Для временного трудоустройства;Для постоянного трудоустройства;Для обучения', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (126,'Уақытша жұмысқа орналасу үшін;Тұрақты жұмысқа орналасу үшін;Оқу үшін', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (127,'Дата незанятости:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (127,'Жұмыспен қамтылмаған күні:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (128,'Дата регистрации:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (128,'Тіркелген күні: ', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (129,'Активные меря содействия занятости', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (129,'Жұмыспен қамтуға жәрдемдесудің белсенді іс-шаралары', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (130,'Желаемая профессия:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (130,'Қалаған мамандық:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (131,'Уточнение по должности:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (131,'Лауазым бойынша нақтылау:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (132,'Желаемая минимальная оплата труда:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (132,'Қалаған ең төменгі еңбекақы:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (133,'Желаемая максимальная оплата труда:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (133,'Қалаған ең жоғары еңбекақы:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (134,'Режим работы:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (134,'Жұмыс тәртібі:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (135,'Полн. р. День;Неполн. р. День;Надомная работа;Посменный', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (135,'толық жұмыс күні;толық емес жұмыс күні;үйдегі жұмыс;ауысымды', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (136,'Выберите сотрудника:', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (136,'Қызметшіні таңдаңыз:', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (137,'Нажмите next чтобы пролистать дальше по листу сотрудников или previous чтобы вернутся к предыдущему ', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (137,'Нажмите next чтобы пролистать дальше по листу сотрудников или previous чтобы вернутся к предыдущему', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (138,'Общественные работы;Молодежная практика;Социальные рабочие места (республикансий бюджет);Социальные рабочие места (местный бюджет);Постоянная работа;Курсы профессионального обучения', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (138,'Қоғамдық жұмыстар; Жастар практикасы;Әлеуметтік жұмыс орындары (республикалық бюджет); Әлеуметтік жұмыс орындары (жергілікті бюджет); Тұрақты жұмыс; Кәсіптік оқыту курстары', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (139,'Активные меры содействия занятости', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (139,'Жұмыспен қамтуға жәрдемдесудің белсенді шаралары', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (140,'Введите через запятую Профессию, должность, Организацию', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (140,'Введите через запятую Профессию, должность, Организацию', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (141,'Личный Кабинет', null, 15, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (141,'Жеке Кабинет', null, 15, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (142,'Подтвердить / Отказать', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (142,'Растау / Бас Тарту', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (143,'Оцените работу сотрудника', null, null, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (143,'Қызметкердің жұмысын бағалаңыз', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (144, 'Номер вашего запроса № %s
<b><i>Оцените работу сотрудника выбрав вариант по кнопкам ниже</i></b>', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (144, 'Сіздің сұрауыңыздың нөмірі № %s
<b><i>Төмендегі батырмалар бойынша опцияны таңдау арқылы Қызметкердің жұмысын бағалаңыз</i></b>', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (145, 'Номер вашего запроса № %s', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (145, 'Сіздің сұрауыңыздың нөмірі № %s', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (146, 'approved', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (146, 'approved', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (147, 'appeal was deny', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (147, 'appeal was deny', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (148, 'Ваше обращение было отклонено пожалуйста переотправьте его через кнопку "Услуги" и соответствующую услугу(ваш запрос будет найден по ИИН автоматически)', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (148, 'Сіздің сұранысыңыз қабылданбады, оны «Қызметтер» батырмасы және тиісті қызмет арқылы қайта жіберіңіз (сұраныс ЖСН арқылы автоматты түрде табылады)', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (149, 'Спасибо, ваша оценка принята!', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (149, 'Рахмет, сіздің бағалауыңыз қабылданды!', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (150, 'Информация', null, 17, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (150, 'Ақпарат', null, 17, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (151, 'Редактирование', null, 18, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (151, 'Түзету', null, 18, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (152, 'choose department', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (152, 'choose department', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (153, 'choose department', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (153, 'choose department', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (154, 'Дополнить данные заявления;Вам нужно получить услугу в качестве безработного (для выдачи справки);Вам нужно получить услугу в качестве лица, ищущего работу (для регистрации безработного);Вам нужно получить услугу в качестве безработного (для направления на работу);Клиент взял билет не на ту операцию;Клиент является студентом;Клиент является школьником;Клиент является ИП;Клиент является учредителем ТОО;Клиент яляется лицом по уходом за ребенком до 3-х лет;Клиент является нетрудоспособным (письменное заявление либо имеется справка);Клиент не проявляет интерес к поиску работы (письменное заявление);Клиент является пенсионером по возрасту;Клиент не имеет регистрации по месту жительства в городе Алматы;Клиент не достиг шестнадцатилетнего возраста;Клиент является работающим по ТД, в том числе выполняющие работу за оплату на условиях полного либо неполного рабочего времени или имеющие иную оплачиваемую работу;Клиент представил документы, содержащие заведомо ложные сведения об отсутствии работы и заработка (дохода), а также другие недостоверные сведения;Клиент не предоставил документ удостоверяющий личность', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (154, 'Тұжырымдарды толықтырыңыз;Сіз жұмыссыз ретінде қызметті алуыңыз керек (анықтама беру үшін);Сізге жұмыс іздеуші ретінде қызмет алу қажет (жұмыссызды тіркеу үшін);Сіз жұмыссыз ретінде қызмет алуыңыз керек (жұмысқа орналастыру үшін);Тапсырыс беруші билетті  дұрыс емес операция үшін алды;Тапсырыс беруші студент болып табылды;Тапсырыс беруші мектеп оқушысы болып табылды;Тапсырыс беруші ЖК болып табылды;Тапсырыс берушы ЖШС иегері болып табылды;Тапсырыс беруші  3 жасқа дейінгі балаға күтім жасайтын адам;Тапсырып беруші әрекетке қабілетсіз адам (жазбаша өтініш немесе сертификат);Тапсырыс беруші жұмыс табуға қызығушылық танытпайды (жазбаша өтініш);Тапсырыс беруші - жасы бойынша зейнеткер;Тапсырыс берушінің Алматы қаласында тұрғылықты тіркеуі жоқ;Он алты жасқа толмаған;КШ бойынша жұмыс істейді, оның ішінде ақы төлеу бойынша толық немесе толық емес жұмыс күнін орындайтындар немесе басқа ақылы жұмыс барлар;Тапсырыс беруші жұмыстың болмауы және кірістің (кірістің) жоқтығы туралы қасақана жалған ақпаратты, сондай-ақ басқа да дұрыс емес ақпаратты қамтитын құжаттарды ұсынған;Клиент жеке басын куәландыратын құжатты ұсынбаған', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (155, 'Причина:', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (155, 'Себебі::', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (156, 'Просьба направить файл с БИН организаций', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (156, 'Кәсіпорындардың БСН қамтыған файлын жіберіңіз', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (157, 'file added', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (157, 'file added', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (158, 'Укажите БИН компании из списка в файле', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (158, 'Файлдағы тізімнен ұйымның БСН-ін көрсетіңіз', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (159, 'По данным мерам организаций не было найдено, пожалуйста, уточните у сотрудника и попробуйте перерегистрироваться', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (159, 'Осы шараларға сәйкес ұйымдар табылған жоқ, қызметкермен тексеріп, қайта тіркеуге тырысыңыз', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (160, 'По данной целевой группе зарегистрированных пользователей нет', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (160, 'Бұл мақсатты топқа тіркелген қолданушылар жоқ', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (161, 'Введите текст рассылки', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (161, 'Хабарлама мәтінін енгізіңіз', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (162, 'Рассылка отправлена', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (162, 'Хабарлама жіберілді', null, null, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (163, 'О чат-боте', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (163, 'Чат-бот туралы', null, null, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (164,'Перейдите по ссылке чтобы попасть в чат для ЛИР', null, 139, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (164,'ЖІРТ чатына кіру үшін сілтемені басыңыз', null, 139, null, null, 2);

insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (165,'Заказать услугу онлайн', null, 19, null, null, 1);
insert into message (id, name, photo, keyboard_id, file, file_type, language_id)    values (165,'Қызметке онлайн тапсырыс', null, 19, null, null, 2);

insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (166, 'Компания Qbots  была основана в 2017 году и занимается разработкой подсистемного программного обеспечения для мессенджера Telegram. Это автоматизированная интеллектуальная система общения с пользователями. Мы реализовали более 50 крупных проектов для 10 отраслей экономики как, государственные организации, национальные компании, где бот предназначен для построения эффективной коммуникации между руководством и сотрудниками компании для внутреннего менеджмента  с количеством в штате от 100 до 200 000 работников. Основные функции онлайн-консультанта: делать рассылку по нужным направлениям за пару кликов, фиксировать все действия пользователя и автоматический формировать готовые отчеты в любом формате (Word, PDF, Excellи.т.д), у сотрудника будет единая информационная площадка, где он  сможет узнать про новости, объявления, вакансии, конкурсы, контакты служб, о проектах компании, оставлять свое предложение руководству компании и т.д..
Мы предлагаем Вам уникальный мультифункциональный инструмент. Вы нам ставите задачу под Ваш персональный бот, мы в свою очередь разрабатываем структуру и логику бота, который будет Вашим персональным виртуальным ассистентом.
Адрес: 050000, г. Алматы, ул. Байзакова 127, офис 4,
e-mail: qbots2020@gmail.com,
номер: 8 778 349 97 94
БИН: 170540017769
www.qbots.kz', null, null, null, null, 1);
insert into message(id, name, photo, keyboard_id, file, file_type, language_id) VALUES (166, 'Компания Qbots  была основана в 2017 году и занимается разработкой подсистемного программного обеспечения для мессенджера Telegram. Это автоматизированная интеллектуальная система общения с пользователями. Мы реализовали более 50 крупных проектов для 10 отраслей экономики как, государственные организации, национальные компании, где бот предназначен для построения эффективной коммуникации между руководством и сотрудниками компании для внутреннего менеджмента  с количеством в штате от 100 до 200 000 работников. Основные функции онлайн-консультанта: делать рассылку по нужным направлениям за пару кликов, фиксировать все действия пользователя и автоматический формировать готовые отчеты в любом формате (Word, PDF, Excellи.т.д), у сотрудника будет единая информационная площадка, где он  сможет узнать про новости, объявления, вакансии, конкурсы, контакты служб, о проектах компании, оставлять свое предложение руководству компании и т.д..
Мы предлагаем Вам уникальный мультифункциональный инструмент. Вы нам ставите задачу под Ваш персональный бот, мы в свою очередь разрабатываем структуру и логику бота, который будет Вашим персональным виртуальным ассистентом.
Адрес: 050000, г. Алматы, ул. Байзакова 127, офис 4,
e-mail: qbots2020@gmail.com,
номер: 8 778 349 97 94
БИН: 170540017769
www.qbots.kz', null, null, null, null, 2);

-----------------------------------------------------------------------------------
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (1, '/start', 2, null, false, 4, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (1, '/start', 2, null, false, 4, 1);


INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (5, '🌎 О нас', 1, null, false, 20, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (5, '🌎 Бізтуралы', 1, null, false, 20, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (6, '🇰🇿 Қазақ тілінде', 3, null, false, null, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (6, '🇰🇿 Қазақ тілінде', 3, null, false, null, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (7, '🇷🇺 На русском языке', 3, null, false, null, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (7, '🇷🇺 На русском языке', 3, null, false, null, 1);


INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (9, '🔁 Тілді ауыстыру', 1, null, false, 4, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (9, '🔁 Сменить язык', 1, null, false, 4, 1);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (10, '🏛 Бастапқы мәзір', 1, null, false, 2, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (10, '🏛 Главное меню', 1, null, false, 2, 1);


INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (13, '🌐 О чат-боте', 1, null, false, 166, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (13, '🌐 Чат-бот туралы', 1, null, false, 166, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (14, '📝 Предложения/жалобы', 1, null, false, 49, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (14, '📝 Ұсыныстар / шағымдар', 1, null, false, 49, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (15, '/admin', 15, null, false, 6, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (15, '/admin', 15, null, false, 6, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (16, '🛠️ Редактор админов', 6, null, false, null, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (16, '🛠️ Редактор админов', 6, null, false, null, 1);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (17, '⚒ Редактор меню', 7, null, false, null, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (17, '⚒ Редактор меню', 7, null, false, null, 1);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (18, '🔧🤷‍♂️ Редактор опроса', 3, null, false, 49, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (18, '🔧🤷‍♂️ Редактор опроса', 3, null, false, 49, 2);


INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (20, '🆕 Новости', 1, null, false, 153, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (20, '🆕 Жаңалықтар', 1, null, false, 153, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (21, '🈴 Структура', 14, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (21, '🈴 Құрылымы', 14, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (22, '☎️Контакты', 9, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (22, '☎️Байланыстар', 9, null, false, null, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (23, '🔄 Сменить язык', null, null, false, null, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (23, '🔄 Тілді ауыстыру', null, null, false, null, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (24, '↩️Назад в админку', 15, null, false, 6, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (24, '↩️Назад в админку', 15, null, false, 6, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (35, '🤷‍♂️ Опрос', 11, null, false, 44, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (35, '🤷‍♂️ Сауалнама', 11, null, false, 44, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (36, '📃 Шағымдар', 8, null, false, 45, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (36, '📃 Жалобы', 8, null, false, 45, 1);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (37, '📬 Сіздің ұсыныстарыңыз', 8, null, false, 46, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (37, '📬 Ваши предложения', 8, null, false, 46, 1);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (38, '/uniSex', 5, null , false, null, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (38, '/uniSex', 5, null , false, null, 2);


INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (41, '✅ Принять', 0, null, false, null, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (41, '✅ принять', 0, null, false, null, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (42, '❌ Отменить', 1, null, false, 2, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (42, '❌ Отменить', 1, null, false, 2, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (43, 'Направить контактный номер', null, null, true, null, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (43, 'Байланыс нөміріңізді жіберіңіз', null, null, true, null, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (44, 'Отменить', 0, null, false, null, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (44, 'Отменить', 0, null, false, null, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (45, '💡 Информация', 15, null, false, 150, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (45, '💡 Информация', 15, null, false, 150, 1);


INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (48, '✅ Готово', 0, null, false, null, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (48, '✅ Готово', 0, null, false, null, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (49, '➕ Добавить опрос', null , null, false, null, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (49, '➕ Добавить опрос', null , null, false, null, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (50, '⚙️ Редактировать опрос', null , null, false, null, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (50, '⚙️ Редактировать опрос', null , null, false, null, 1);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (51, '🖊 Изменить название', null, null, false, null, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (51, '🖊 Изменить название', null, null, false, null, 1);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (52, '❓ Изменить вопрос', null, null, false, null, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (52, '❓ Изменить вопрос', null, null, false, null, 1);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (53, '➕ Добавить вариант', null, null, false, null, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (53, '➕ Добавить вариант', null, null, false, null, 1);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (54, '❌ Удалить', null, null, false, null, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (54, '❌ Удалить', null, null, false, null, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (55, '🗨 Изменить варианты', null, null, false, null, 2);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (55, '🗨 Изменить варианты', null, null, false, null, 1);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (56, 'Hазад', null, null, false, null, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (56, 'Артқа қайту', null, null, false, null, 2);

INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (57, 'Опросы', null , null, false, null, 1);
INSERT INTO button (ID, NAME, COMMAND_ID, URL, REQUEST_CONTACT, MESSAGE_ID, LANG_ID) VALUES (57, 'Опросы', null , null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (58, 'Изменить название кнопки', null, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (58, 'Изменить название кнопки', null, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (59, 'Изменить сообщение', null, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (59, 'Хатты өзгерту', null, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (60, '✅ Добавить файл', null, null, false, null, 2);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (60, '✅ Добавить файл', null, null, false, null, 1);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (61, '❌ Удалить файл', null, null, false, null, 2);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (61, '❌ Удалить файл', null, null, false, null, 1);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (62, '🔜 Далее', null, null, false, null, 2);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (62, '🔜 Далее', null, null, false, null, 1);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (100, '📔 Услуги', 1, null, false, 105, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (100, '📔 Қызметтер', 1, null, false, 105, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (101, 'Заказать услугу онлайн', 1, null, false, 165, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (101, 'Қызметке онлайн тапсырыс беру', 1, null, false, 165, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (102, 'Виды услуг', null, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (102, 'Қызмет түрлері', null, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (103, '🔎 Найти вакансию/найти специалиста', 1, null, false, 106, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (103, '🔎 Бос жұмысорнын табу / маман табу', 1, null, false, 106, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (104, '🎫 Личный Кабинет', 1, null, false, 141, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (104, '🎫 Жеке Кабинет', 1, null, false, 141, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (105, '💬 Чат для лиц ищущих работу', 1, null, false, 164, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (105, '💬 Жұмыс іздеушілерге арналған Чат', 1, null, false, 164, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (106, 'Коммуникация', 19, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (106, 'Коммуникация', 19, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (107, 'Электронный журнал', 13, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (107, 'Электрондық журнал', 13, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (108, 'Хранилище', 11, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (108, 'Қойма', 11, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (109, 'Рассылка', 22, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (109, 'Тарату', 22, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (110, '⚙️Редактирование', 15, null, false, 151, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (110, '⚙️Редакциялау', 15, null, false, 151, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (111, 'Рассылка админ', 19, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (111, 'Тарату админ', 19, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (112, '🗂 Отчёты', 16, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (112, '🗂 Есептер', 16, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (113, 'Предложение / Жалобы админ', 17, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (113, 'Ұсыныстар / шағымдар админ', 17, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (114, '📖 Целевая группа', 20, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (114, '📖 Мақсатты топ', 20, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (115, 'Загрузить вакансии МП', 21, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (115, 'ЖП бойынша бос жұмыс орындарын жүктеңіз', 21, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (116, 'Редактор отчёта', null, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (116, 'Есеп редакторы', null, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (117, '🔧 Редактор сотрудников', 4, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (117, '🔧 Қызметкерлер редакторы', 4, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (118, 'Редактор "Новости"', null, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (118, '"Жаңалықтар" редакторы', null, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (119, 'Редактор "Структура"', null, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (119, '"Құрылымы" редакторы', null, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (120, 'Редактор "Контакты"', null, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (120, '"Байланыстар" редакторы', null, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (121, 'Запросить отчёт"', null, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (121, 'Есептіc сұрау', null, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (122, 'Изменить отчёт', null, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (122, 'Есепті өзгерту', null, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (123, 'Услуги ЦЗН', 1, null, false, 111, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (123, 'ЖҚО Қызметтер', 1, null, false, 111, 2);



INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (125, 'Ссылка на вакансии', null, 't.me/enbek_bot', false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (125, 'Бос орындарға сілтеме', null, 't.me/enbek_bot', false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (126, 'Регистрация в качестве лица, ищущего работу', 10, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (126, 'Жұмыс іздеп жүрген адам ретінде тіркелу', 10, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (127, 'Регистрация в качестве безработного', 10, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (127, 'Жұмыссыз ретінде тіркеу', 10, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (128, 'Направление безработных граждан на участие в активных мерах содействия занятости', 10, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (128, 'Жұмыссыз азаматтарды жұмыспен қамтуға жәрдемдесудің белсенді шараларына қатысуға жіберу', 10, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (129, 'Выдача справки в качестве безработного', 10, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (129, 'Жұмыссыз ретінде анықтама беру', 10, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (130, 'Next', 0, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (130, 'Next', 0, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (131, 'Previous', 0, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (131, 'Previous', 0, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (132, 'Подтвердить', 0, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (132, 'Растау', 0, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (133, 'Отказать', 0, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (133, 'Бас Тарту', 0, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (134, 'Очень хорошо', 12, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (134, 'Өтежақсы', 12, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (135, 'Хорошо', 12, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (135, 'Жақсы', 12, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (136, 'Удовлетворительно', 12, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (136, 'Қанағаттанарлық', 12, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (137, 'Плохо', 12, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (137, 'Жаман', 12, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (138, 'Очень плохо', 12, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (138, 'Өтежаман', 12, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (139, 'Ссылка на чат для лир', null, 't.me/ChatForLIR', false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (139, 'ЖІРТ чатына сілтеме', null, 't.me/ChatForLIR', false, null, 2);


INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (140, 'Регистрация в качестве лица, ищущего работу(Ссылка)', null, 'https://egov.kz/cms/ru/services/job_search/pass034_mtszn', false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (140, 'Жұмыс іздеп жүрген адам ретінде тіркелу(Ссылка)', null, 'https://egov.kz/cms/ru/services/job_search/pass034_mtszn', false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (141, 'Регистрация в качестве безработного(Ссылка)', null, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (141, 'Жұмыссыз ретінде тіркеу(Ссылка)', null, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (142, 'Направление безработных граждан на участие в активных мерах содействия занятости(Ссылка)', null, null, false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (142, 'Жұмыссыз азаматтарды жұмыспен қамтуға жәрдемдесудің белсенді шараларына қатысуға жіберу(Ссылка)', null, null, false, null, 2);

INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (143, 'Выдача справки в качестве безработного(Ссылка)', null, 'https://egov.kz/cms/ru/services/job_search/pass190-3_mtszn', false, null, 1);
INSERT INTO button (id, name, command_id, url, request_contact, message_id, lang_id) VALUES (143, 'Жұмыссыз ретінде анықтама беру(Ссылка)', null, 'https://egov.kz/cms/ru/services/job_search/pass190-3_mtszn', false, null, 2);





