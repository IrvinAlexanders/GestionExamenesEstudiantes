CREATE database schoolApp;
use schoolApp;
CREATE table Questions(
Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
statement VARCHAR(40) NOT NULL default 'descripción_pregunta',
option_a BOOLEAN NOT NULL default false,
opc_b BOOLEAN NULL default false,
opc_c BOOLEAN NULL default false,
opc_d BOOLEAN NULL default false,
Question_value INT NOT NULL default 0
);

CREATE table exam (
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
score INT NOT NULL default 0,
questionQuantity INT NOT NULL default 0,
question_id INT NOT NULL,
CONSTRAINT fk_question FOREIGN KEY(question_id) REFERENCES Question(Id)
);

CREATE table student(
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(30) NOT NULL default ' ',
age INT NOT NULL default 0,
city VARCHAR(50) NOT NULL default ' ',
time_zone TIMESTAMP NULL,
exam_id BIGINT NOT NULL,
CONSTRAINT fk_exame FOREIGN KEY(exam_id) REFERENCES exam(id),
CONSTRAINT chkedad CHECK(edad >0)
);