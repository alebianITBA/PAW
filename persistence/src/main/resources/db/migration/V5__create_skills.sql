CREATE TABLE skills (
  id serial PRIMARY KEY,
  name text UNIQUE NOT NULL,
  created_at timestamp NOT NULL
);

CREATE TABLE user_skills (
  id serial PRIMARY KEY,
  user_id integer REFERENCES users(id),
  skill_id integer REFERENCES skills(id),
  created_at timestamp NOT NULL
);

CREATE INDEX index_user_skills_on_user_id ON user_skills(user_id);
CREATE INDEX index_user_skills_on_skill_id ON user_skills(user_id);

CREATE TABLE job_offer_skills (
  id serial PRIMARY KEY,
  job_offer_id integer REFERENCES job_offers(id),
  skill_id integer REFERENCES skills(id),
  created_at timestamp NOT NULL
);

CREATE INDEX index_job_offer_skills_on_job_offer_id ON job_offer_skills(job_offer_id);
CREATE INDEX index_job_offer_skills_on_skill_id ON job_offer_skills(skill_id);

INSERT INTO skills (name, created_at) VALUES ('Algorithms', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Analytics', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Android', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Applications', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Blogging', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Business', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Business Analysis', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Business Intelligence', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Business Storytelling', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Coaching', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Cloud Computing', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Communication', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Computer', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Consulting', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Content', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Content Management', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Content Marketing', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Content Strategy', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Data Analysis', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Data Analytics', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Data Engineering', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Data Mining', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Data Science', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Data Warehousing', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Database Administration', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Database Management', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Digital Marketing', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Digital Media', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Economics', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Editing', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Executive', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Event Planning', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Game Development', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Hadoop', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Health Care', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Hiring', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Hospitality', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Human Resources', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Information Management', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Information Security', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Information Technology', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('iPhone', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Java', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Job Specific Skills', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Legal', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Leadership ', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Mac', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Management', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Marketing', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Market Research', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Media Planning', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Microsoft Office Skills', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Mobile Apps', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Mobile Development', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Negotiation', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Network and Information Security', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Newsletters', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Online Marketing', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Presentation', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Project Management', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Public  Relations', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Recruiting', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Relationship Management', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Research', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Risk Management', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Search Engine Optimization ', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Social Media', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Social Media Management', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Social Networking', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Software', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Software Engineering', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Software Management', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Strategic Planning', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Strategy', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Tech Skills Listed by Job', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Tech Support', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Technical', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Training', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('UI / UX', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('User Testing', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Web Content', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Web Development', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Web Programming', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('WordPress', current_timestamp);
INSERT INTO skills (name, created_at) VALUES ('Writing', current_timestamp);