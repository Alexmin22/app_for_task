create database app_for_tasks default charset utf8mb4;

USE app_for_tasks;

create TABLE IF NOT EXISTS consumers (
  id int NOT NULL AUTO_INCREMENT,
  firstname varchar(150),
  surname varchar(150) not null,
  password varchar(200) not null,
  PRIMARY KEY (id)
);


create TABLE IF NOT EXISTS tasks (
   id int NOT NULL AUTO_INCREMENT,
   taskname varchar(100) not null,
   taskdesc varchar(300),
   status varchar(100) not null,
   deadline timestamp not null,
   PRIMARY KEY (id)
);

create TABLE IF NOT EXISTS consumer_task (
   consumer_id int NOT NULL,
   task_id int NOT NULL,
   PRIMARY KEY (consumer_id, task_id),
   CONSTRAINT consumer_task_consumer_fk FOREIGN KEY (consumer_id) REFERENCES consumers (id) ON delete CASCADE ON update NO ACTION,
   CONSTRAINT consumer_task_task_fk FOREIGN KEY (task_id) REFERENCES tasks (id) ON delete CASCADE ON update NO ACTION
);

create TABLE IF NOT EXISTS consumer_role (
   consumer_id int NOT NULL,
   role varchar(100) NOT NULL,
   PRIMARY KEY (consumer_id, role),
   CONSTRAINT consumer_role_consumer_fk FOREIGN KEY (consumer_id) REFERENCES consumers (id) ON delete CASCADE ON update NO ACTION
);



insert into consumers (firstname, surname, password)
values
    ('Yury', 'Gagarin', '$2a$12$la1Fv1MmD1th7U7.vMSjh.ThZ.Y0f3lDP3x/JYxQ.msqlnEm4uif.'),
    ('Alexander', 'Pushkin', '$2a$12$MMABusI.NMW1rrVkYd1OG.toslRP7MwI1ULN36bJMN5lseAziPL6W'),
    ('Alexander', 'Suvorov', '$2a$12$4XpBuSt/Gd0UghcQi2GaveLermiGJwJ7brmlmN6yKn5rpBf2pKCou');

insert into tasks (taskname, taskdesc, status, deadline)
values
    ('Погулять с собакой', null, 'TASK_FINISHED', '2000-01-01 08:10:00'),
    ('Прочитать Войну и мир', 'Книга в шкафу на второй полке', 'SCHEDULED_TASK', '2030-01-01 12:15:00'),
    ('Учить китайский язык', null, 'AT_WORK', '2023-06-16 15:00:00'),
    ('Посмотреть новый сериал', 'Найти с хорошим дубляжом и качеством, посмотреть на выходных', 'AT_WORK', '2023-02-22 18:00:00');

SET FOREIGN_KEY_CHECKS=0;

insert into consumer_task (consumer_id, task_id)
values
    (1, 1),
    (1, 4),
    (2, 3),
    (3, 2);

insert into consumer_role (consumer_id, role)
values
    (1, 'ROLE_USER'),
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER'),
    (3, 'ROLE_USER');