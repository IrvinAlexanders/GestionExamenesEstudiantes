create database schoolApp;
use schoolApp;
create table Questions(
Id int not null auto_increment primary key,
statement varchar(40) not null default 'descripción_pregunta',
option_a boolean not null default false,
opc_b boolean null default false,
opc_c boolean null default false,
opc_d boolean null default false,
Question_value int not null default 0
);
create table exam (
id bigint not null auto_increment primary key,
score int not null default 0,
questionQuantity int not null default 0,
question_id int not null,
constraint fk_question foreign key(question_id) references Question(Id)
);

create table student(
id bigint not null auto_increment primary key,
name varchar(30) not null default ' ',
age int not null default 0,
city varchar(50) not null default ' ',
time_zone timestamp null,
exam_id bigint not null,
constraint fk_exame foreign key(exam_id) references exam(id),
constraint chkedad check(edad >0)
);