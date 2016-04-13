CREATE TABLE users (
  id serial PRIMARY KEY,
  email varchar(255) NOT NULL UNIQUE,
  first_name varchar(255),
  last_name varchar(255),
  password varchar(255) NOT NULL,
  created_at timestamp NOT NULL
);

INSERT INTO users (email, first_name, last_name, password, created_at) VALUES('matias.gualino@gmail.com', 'Matias', 'Gualino', 'paw', current_timestamp);