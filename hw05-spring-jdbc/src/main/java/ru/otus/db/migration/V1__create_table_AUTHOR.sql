CREATE TABLE IF NOT EXISTS AUTHOR
(
    AUT_ID IDENTITY PRIMARY KEY,
    AUTHOR_FIO VARCHAR(255) NOT NULL UNIQUE
);
COMMENT
ON TABLE AUTHOR IS 'Таблица авторов';
COMMENT
ON COLUMN AUTHOR.AUT_ID IS 'Идентификатор';
COMMENT
ON COLUMN AUTHOR.AUTHOR_FIO IS 'ФИО автора';

CREATE SEQUENCE AUTHOR_SEQ
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1
    START WITH 10
    NOCYCLE;