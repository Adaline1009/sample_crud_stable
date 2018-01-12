CREATE DATABASE CRUD;

CREATE SEQUENCE doc_id_sequence
  INCREMENT BY 1
  MINVALUE 1;

CREATE TABLE DOC(
  doc_id BIGINT DEFAULT nextval('doc_id_sequence') NOT NULL,
  name VARCHAR(300) NOT NULL,
  code BIGINT NULL,
  kind VARCHAR(100) NOT NULL,
  date DATE,
  CONSTRAINT doc_primary_key PRIMARY KEY(doc_id)
);

INSERT INTO DOC(name, code, kind, date) VALUES ('Документ_1', 1, 'Инструкция', now());
INSERT INTO DOC(name, code, kind, date) VALUES ('Документ_2', 2, 'Отчет', now());
INSERT INTO DOC(name, code, kind, date) VALUES ('Документ_3', 3, 'Приказ', now());
INSERT INTO DOC(name, code, kind, date) VALUES ('Документ_4', 4, 'Доклад', now());
INSERT INTO DOC(name, code, kind, date) VALUES ('Документ_5', 5, 'Инструкция', now());