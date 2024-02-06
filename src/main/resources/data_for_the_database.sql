DELETE FROM articles;
DELETE FROM micro_skills;
DELETE FROM professions;
DELETE FROM technologies;
DELETE FROM users;


INSERT INTO articles (articlename, textofarticle, microskill)
VALUES 
  ('Introduction to Java', 'This is a Java introduction article', 1),
  ('Python Basics', 'Learn the basics of Python programming', 2),
  ('Web Development with React', 'Building modern web apps with React', 3),
  ('Data Science Essentials', 'Introduction to data science concepts', 4),
  ('Mobile App Development', 'Creating mobile apps using Flutter', 5);

INSERT INTO micro_skills (name, photo, creationdate, description, learningtime, tags, level, rating, views, technology_id)
VALUES 
  ('Java Programming', 'java.jpg', '2022-01-01', 'Learn Java programming language', '2 weeks', 'Programming', 'Intermediate', 4.5, 100, 1),
  ('Python Programming', 'python.jpg', '2022-02-01', 'Learn Python programming language', '3 weeks', 'Programming', 'Beginner', 4.0, 120, 2),
  ('React.js', 'react.jpg', '2022-03-01', 'Building user interfaces with React', '4 weeks', 'Web Development', 'Advanced', 4.8, 150, 3),
  ('Data Science Fundamentals', 'data-science.jpg', '2022-04-01', 'Fundamental concepts of data science', '5 weeks', 'Data Science', 'Intermediate', 4.2, 80, 4),
  ('Flutter App Development', 'flutter.jpg', '2022-05-01', 'Building cross-platform mobile apps with Flutter', '6 weeks', 'Mobile Development', 'Advanced', 4.6, 110, 5);

INSERT INTO professions (name)
VALUES 
  ('Software Developer'),
  ('Data Scientist'),
  ('Web Developer'),
  ('Mobile App Developer'),
  ('Database Administrator');

INSERT INTO technologies (name, profession_id)
VALUES 
  ('Java', 1),
  ('Python', 1),
  ('React.js', 3),
  ('Data Science', 2),
  ('Flutter', 4);

INSERT INTO users (username, password, email, nickname, status, image_url, role)
VALUES 
  ('john_doe', 'password123', 'john.doe@example.com', 'JohnDoe', true, 'john.jpg', 'USER'),
  ('jane_smith', 'secret456', 'jane.smith@example.com', 'JaneSmith', true, 'jane.jpg', 'USER'),
  ('admin_user', 'adminpass', 'admin@example.com', 'AdminUser', true, 'admin.jpg', 'ADMIN'),
  ('guest_user', 'guestpass', 'guest@example.com', 'GuestUser', false, 'guest.jpg', 'USER'),
  ('test_user', 'testpass', 'test@example.com', 'TestUser', true, 'test.jpg', 'USER');
  