CREATE TABLE job_applications (
  id serial PRIMARY KEY,
  description text NOT NULL,
  created_at timestamp NOT NULL,
  user_id integer REFERENCES users(id),
  job_offer_id integer REFERENCES job_offers(id),
  UNIQUE(user_id, job_offer_id)
);

CREATE UNIQUE INDEX index_job_applications_on_user_id ON job_applications(user_id);
CREATE UNIQUE INDEX index_job_applications_on_job_offer_id ON job_applications(job_offer_id);
