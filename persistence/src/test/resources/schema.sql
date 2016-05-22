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

CREATE INDEX IF NOT EXISTS index_job_offers_on_user_id ON job_offers(user_id);

CREATE TABLE IF NOT EXISTS job_applications (
  id serial PRIMARY KEY,
  description text NOT NULL,
  created_at timestamp NOT NULL,
  user_id integer REFERENCES users(id),
  job_offer_id integer REFERENCES job_offers(id),
  UNIQUE(user_id, job_offer_id)
);

CREATE INDEX IF NOT EXISTS index_job_applications_on_user_id ON job_applications(user_id);
CREATE INDEX IF NOT EXISTS index_job_applications_on_job_offer_id ON job_applications(job_offer_id);

CREATE TABLE IF NOT EXISTS posts (
  id serial PRIMARY KEY,
  title text NOT NULL,
  description text NOT NULL,
  created_at timestamp NOT NULL,
  user_id integer REFERENCES users(id)
);

CREATE INDEX IF NOT EXISTS index_posts_on_user_id ON posts(user_id);

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

CREATE INDEX IF NOT EXISTS index_user_skills_on_user_id ON user_skills(user_id);
CREATE INDEX IF NOT EXISTS index_user_skills_on_skill_id ON user_skills(user_id);

CREATE TABLE IF NOT EXISTS job_offer_skills (
  id serial PRIMARY KEY,
  job_offer_id integer REFERENCES job_offers(id),
  skill_id integer REFERENCES skills(id),
  created_at timestamp
);

CREATE INDEX IF NOT EXISTS index_job_offer_skills_on_job_offer_id ON job_offer_skills(job_offer_id);
CREATE INDEX IF NOT EXISTS index_job_offer_skills_on_skill_id ON job_offer_skills(skill_id);
