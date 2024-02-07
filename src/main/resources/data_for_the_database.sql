DELETE FROM articles;
DELETE FROM micro_skills;
DELETE FROM technologies;
DELETE
FROM professions;
DELETE FROM users;

INSERT INTO professions (id, name)
VALUES (1, 'Software Developer'),
       (2, 'Data Scientist'),
       (3, 'Web Developer'),
       (4, 'Mobile App Developer'),
       (5, 'Database Administrator');

INSERT INTO technologies (id, name, profession_id)
VALUES (1, 'Java', 1),
       (2, 'Python', 1),
       (3, 'React.js', 3),
       (4, 'Data Science', 2),
       (5, 'Flutter', 4);

INSERT INTO micro_skills (microskill_id, name, photo, creationdate, description, learningtime, tags, level, rating,
                          views, technology_id)
VALUES (1, 'Java Programming', 'java.jpg', '2022-01-01', 'Learn Java programming language', '2 weeks', 'BACKEND',
        'INTERMEDIATE', 4.5, 100, 1),
       (2, 'Python Programming', 'python.jpg', '2022-02-01', 'Learn Python programming language', '3 weeks', 'BACKEND',
        'INTERMEDIATE', 4.0, 120, 2),
       (3, 'React.js', 'react.jpg', '2022-03-01', 'Building user interfaces with React', '4 weeks', 'BACKEND',
        'INTERMEDIATE', 4.8, 150, 3),
       (4, 'Data Science Fundamentals', 'data-science.jpg', '2022-04-01', 'Fundamental concepts of data science',
        '5 weeks', 'BACKEND', 'INTERMEDIATE', 4.2, 80, 4),
       (5, 'Flutter App Development', 'flutter.jpg', '2022-05-01', 'Building cross-platform mobile apps with Flutter',
        '6 weeks', 'BACKEND', 'INTERMEDIATE', 4.6, 110, 5);

INSERT INTO articles (id, articlename, textofarticle, microskill_id)
VALUES (1, 'Introduction to Java', 'This is a Java introduction article', 1),
       (2, 'Python Basics', 'Learn the basics of Python programming', 2),
       (3, 'Web Development with React', 'Building modern web apps with React', 3),
       (4, 'Data Science Essentials', 'Introduction to data science concepts', 4),
       (5, 'Mobile App Development', 'Creating mobile apps using Flutter', 5);

INSERT INTO users (username, password, email, nickname, status, image_url, role)
VALUES 
  ('john_doe', 'password123', 'john.doe@example.com', 'JohnDoe', true, 'john.jpg', 'USER'),
  ('jane_smith', 'secret456', 'jane.smith@example.com', 'JaneSmith', true, 'jane.jpg', 'USER'),
  ('admin_user', 'adminpass', 'admin@example.com', 'AdminUser', true, 'admin.jpg', 'ADMIN'),
  ('guest_user', 'guestpass', 'guest@example.com', 'GuestUser', false, 'guest.jpg', 'USER'),
  ('test_user', 'testpass', 'test@example.com', 'TestUser', true, 'test.jpg', 'USER');
  