DELETE
FROM articles;

DELETE
FROM review;
DELETE
FROM save_microskill;
DELETE
FROM saved_microskill;
DELETE
FROM micro_skills;
DELETE
FROM technologies;
DELETE
FROM professions;
DELETE
FROM users;
DELETE
FROM cart;





INSERT INTO professions (profession_id,name) VALUES
                                   (1,'DEVELOPMENT'),
                                   (2,'BUSINESS'),
                                   (3,'IT_SOFTWARE'),
                                   (4,'DESIGN');

INSERT INTO technologies (profession_id,name ) VALUES
                                                   (89,'Java'),
                                                   (90,'Python'),
                                                   (91,'React.js'),
                                                   (92,'Data Science');
INSERT INTO users (username, password, email, status, image_url, role) VALUES
                                                                           ('test_user', '$2a$12$X7yEccnTctNxzrsLI46Wn.U6zT7YZN9QaNheqpVHuYC9JXo2uL14a', 'user1@example.com', 'ONLINE', 'user.jpg', 'USER'),
                                                                           ('admin_user', '$2a$12$X7yEccnTctNxzrsLI46Wn.U6zT7YZN9QaNheqpVHuYC9JXo2uL14a', 'admin@example.com', 'ONLINE', 'admin.jpg', 'ADMIN');

INSERT INTO cart (user_id) VALUES
                               (264),
                               (265),
                               (264),
                               (265),
                               (264);

INSERT INTO micro_skills (name, photo, creationDate, description, learning_time, level, rating, popularity, views, price, lesson_count, about_skill, last_update_time, technology_id, cart_id) VALUES

                                                                                                                                                                                                   ('Python Fundamentals', 'python.jpg', '2022-02-01', 'Fundamental concepts of Python programming', '3 weeks', 'INTERMEDIATE', 4.2, 100.0, 2000, 29.99, 12, 'Learn the fundamental concepts of Python programming language', '2022-02-28 09:30:00', 164, 336),
                                                                                                                                                                                                   ('Web Development with React.js', 'react.jpg', '2022-03-01', 'Build modern web applications using React.js', '4 weeks', 'INTERMEDIATE', 4.8, 150.0, 1800, 39.99, 15, 'Learn how to build modern web applications using React.js framework', '2022-03-31 15:45:00', 165, 337),
                                                                                                                                                                                                   ('Introduction to Data Science', 'data-science.jpg', '2022-04-01', 'Fundamental concepts of data science', '5 weeks', 'INTERMEDIATE', 4.6, 130.0, 1600, 34.99, 13, 'Learn the fundamental concepts of data science and analysis', '2022-04-30 11:20:00', 168, 338),
                                                                                                                                                                                                   ('Mobile App Development with Flutter', 'flutter.jpg', '2022-05-01', 'Develop cross-platform mobile applications using Flutter', '6 weeks', 'ADVANCED', 4.9, 180.0, 1900, 49.99, 18, 'Learn how to develop cross-platform mobile applications using Flutter framework', '2022-05-31 14:00:00', 170, 339);
INSERT INTO articles (articlename, textofarticle, microskill_id) VALUES
                                                                     ('Introduction to Java', 'This is a Java introduction article', 80),
                                                                     ('Python Basics', 'Learn the basics of Python programming', 81),
                                                                     ('Web Development with React', 'Building modern web apps with React', 82),
                                                                     ('Data Science Essentials', 'Introduction to data science concepts', 83),
                                                                     ('Mobile App Development', 'Creating mobile apps using Flutter', 83);



INSERT INTO review (text, rating, microskill_id, user_id) VALUES
                                                              ('Great course', 4.5, 80, 279),
                                                              ('Good course', 4.0, 82, 279),
                                                              ('Excellent course', 4.8, 83, 279),
                                                              ('Very good course', 4.2, 82, 279),
                                                              ('Awesome course', 4.6, 83, 279);

INSERT INTO saved_microskill (user_id) VALUES
                                           (279),
                                           (279),
                                           (279),
                                           (279),
                                           (279);

INSERT INTO save_microskill (save_microskill_id, microskill_id) VALUES
                                                                    (26, 80),
                                                                    (27, 81),
                                                                    (28, 82),
                                                                    (29, 83),
                                                                    (30, 83);

