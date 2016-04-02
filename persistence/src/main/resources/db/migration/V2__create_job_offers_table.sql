CREATE TABLE job_offers (
  id serial PRIMARY KEY,
  title varchar(255) NOT NULL,
  description text NOT NULL,
  created_at timestamp NOT NULL,
  user_id integer REFERENCES users(id)
);

CREATE UNIQUE INDEX index_job_offers_on_user_id ON job_offers(user_id);
