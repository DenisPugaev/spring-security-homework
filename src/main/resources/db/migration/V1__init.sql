create table users (
  id                    bigserial,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table authorities (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_authorities (
  user_id               bigint not null,
  authorities_id               int not null,
  primary key (user_id, authorities_id),
  foreign key (user_id) references users (id),
  foreign key (authorities_id) references authorities (id)
);

insert into authorities (name)
values
('READ_ACCESS'), ('WRITE_ACCESS'),('DELETE_ACCESS');

insert into users (username, password, email)
values
('admin', '$2a$12$u6zrHHWO8bHtOPGtrMfi4u2PiWizmlGT1DKMYoqTUsN6wD4bl0npW', 'admin@mail.com'),
('cat', '$2a$12$OULV8tEmg5LSgUfuE.ybEO302mDN07DI6iVZgpK12JlWTUlS6cWyW', 'cat@mail.com'),
('gus', '$2a$12$06.hgjQoAtReO.WBlOcSIuvkOJksPt7da/iTb8wiJwZMs1hn1DQCy', 'gus@mail.com');

insert into users_authorities (user_id, authorities_id)
values
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(3, 1);