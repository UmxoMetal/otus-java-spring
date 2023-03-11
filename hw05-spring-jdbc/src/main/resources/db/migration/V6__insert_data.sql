INSERT INTO BOOK
VALUES (NEXTVAL('BOOK_SEQ'),
        'Предметно-ориентированное проектирование (DDD). Структуризация сложных программных систем');
INSERT INTO AUTHOR
VALUES (NEXTVAL('AUTHOR_SEQ'), 'Эрик Эванс');
INSERT INTO GENRE
VALUES (NEXTVAL('GENRE_SEQ'), 'Разработка и алгоритмы');
INSERT INTO BOOK_TO_AUTHOR
VALUES (CURRVAL('AUTHOR_SEQ'), CURRVAL('BOOK_SEQ'));
INSERT INTO BOOK_TO_GENRE
VALUES (CURRVAL('BOOK_SEQ'), CURRVAL('GENRE_SEQ'));