INSERT INTO BOOK
VALUES (1, 'Предметно-ориентированное проектирование (DDD). Структуризация сложных программных систем');

INSERT INTO AUTHOR
VALUES (1, 'Эрик Эванс');
INSERT INTO BOOK_TO_AUTHOR
VALUES (1, 1);

INSERT INTO GENRE
VALUES (1, 'Разработка и алгоритмы');
INSERT INTO BOOK_TO_GENRE
VALUES (1, 1);
INSERT INTO GENRE
VALUES (2, 'Компьютерная литература');
INSERT INTO BOOK_TO_GENRE
VALUES (1, 2);

INSERT INTO BOOK
VALUES (2, 'Благие знамения');

INSERT INTO BOOK
VALUES (4, 'Мор, ученик смерти');

INSERT INTO AUTHOR
VALUES (2, 'Нил Гейман');
INSERT INTO BOOK_TO_AUTHOR
VALUES (2, 2);

INSERT INTO AUTHOR
VALUES (3, 'Терри Пратчетт');
INSERT INTO BOOK_TO_AUTHOR
VALUES (2, 3);

INSERT INTO BOOK_TO_AUTHOR
VALUES (4, 3);

INSERT INTO GENRE
VALUES (3, 'Фэнтези');
INSERT INTO BOOK_TO_GENRE
VALUES (2, 3);
INSERT INTO GENRE
VALUES (4, 'Юмор');
INSERT INTO BOOK_TO_GENRE
VALUES (2, 4);
INSERT INTO GENRE
VALUES (5, 'Комедия');
INSERT INTO BOOK_TO_GENRE
VALUES (2, 5);
INSERT INTO GENRE
VALUES (6, 'Ужасы');
INSERT INTO BOOK_TO_GENRE
VALUES (2, 6);

INSERT INTO BOOK
VALUES (3, 'Spring in Action');

INSERT INTO COMMENT
VALUES (1, 'Не читал , но книга не понравилсь', 1);

INSERT INTO COMMENT
VALUES (2, 'Эта книга должна стоять на полке у каждого программиста', 1);