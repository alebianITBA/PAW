CREATE TABLE skills (
  id serial PRIMARY KEY,
  name text UNIQUE NOT NULL,
  created_at timestamp NOT NULL
);

CREATE TABLE user_skills (
  id serial PRIMARY KEY,
  user_id integer REFERENCES users(id),
  skill_id integer REFERENCES skills(id)
);

ALTER TABLE user_skills DROP COLUMN created_at;

CREATE INDEX index_user_skills_on_user_id ON user_skills(user_id);
CREATE INDEX index_user_skills_on_skill_id ON user_skills(user_id);

CREATE TABLE job_offer_skills (
  id serial PRIMARY KEY,
  job_offer_id integer REFERENCES job_offers(id),
  skill_id integer REFERENCES skills(id)
);

ALTER TABLE job_offer_skills DROP COLUMN created_at;

CREATE INDEX index_job_offer_skills_on_job_offer_id ON job_offer_skills(job_offer_id);
CREATE INDEX index_job_offer_skills_on_skill_id ON job_offer_skills(skill_id);
