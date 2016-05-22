CREATE TABLE IF NOT EXISTS users (
  id serial PRIMARY KEY,
  email varchar(255) NOT NULL UNIQUE,
  first_name varchar(255),
  last_name varchar(255),
  password varchar(255) NOT NULL,
  created_at timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS job_offers (
  id serial PRIMARY KEY,
  title varchar(255),
  description text NOT NULL,
  created_at timestamp NOT NULL,
  user_id integer REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS job_applications (
  id serial PRIMARY KEY,
  description text NOT NULL,
  created_at timestamp NOT NULL,
  user_id integer REFERENCES users(id),
  job_offer_id integer REFERENCES job_offers(id),
  UNIQUE(user_id, job_offer_id)
);

CREATE TABLE IF NOT EXISTS posts (
  id serial PRIMARY KEY,
  title text NOT NULL,
  description text NOT NULL,
  created_at timestamp NOT NULL,
  user_id integer REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS skills (
  id serial PRIMARY KEY,
  name text UNIQUE NOT NULL,
  created_at timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS user_skills (
  id serial PRIMARY KEY,
  user_id integer REFERENCES users(id),
  skill_id integer REFERENCES skills(id),
  created_at timestamp
);

CREATE TABLE IF NOT EXISTS job_offer_skills (
  id serial PRIMARY KEY,
  job_offer_id integer REFERENCES job_offers(id),
  skill_id integer REFERENCES skills(id),
  created_at timestamp
);

