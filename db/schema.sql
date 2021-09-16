CREATE TABLE post(
    id SERIAL PRIMARY KEY,
	name TEXT,
	created timestamp
);

CREATE TABLE candidate (
   id SERIAL PRIMARY KEY,
   name TEXT,
   photoFileName Text,
   cityId int,
   created timestamp
);

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name TEXT,
  email TEXT UNIQUE,
  password TEXT
);

CREATE TABLE cities (
    id SERIAL PRIMARY KEY,
    name TEXT
);