CREATE TABLE users (
  id serial PRIMARY KEY,
  email varchar(255) NOT NULL UNIQUE,
  first_name varchar(255),
  last_name varchar(255),
  password varchar(255) NOT NULL,
  created_at timestamp NOT NULL
);
