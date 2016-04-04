CREATE TABLE posts (
  id serial PRIMARY KEY,
  title text NOT NULL,
  description text NOT NULL,
  created_at timestamp NOT NULL,
  user_id integer REFERENCES users(id)
);

CREATE INDEX index_posts_on_user_id ON posts(user_id);
