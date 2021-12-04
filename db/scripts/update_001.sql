CREATE TABLE if not exists post (
    id SERIAL PRIMARY KEY,
	name varchar(255),
	created timestamp
);

CREATE TABLE if not exists candidate (
   id SERIAL PRIMARY KEY,
   name varchar(255),
   photoFileName varchar(255),
   cityId int,
   created timestamp
);

CREATE TABLE if not exists users (
  id SERIAL PRIMARY KEY,
  name varchar(255),
  email varchar(255) UNIQUE,
  password varchar(255)
);

CREATE TABLE if not exists cities (
    id SERIAL PRIMARY KEY,
    name varchar(255)
);