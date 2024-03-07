DELETE
FROM articles;
DELETE
FROM review;
DELETE
FROM save_microskill;
DELETE
FROM saved_microskill;
DELETE
FROM cart_microskill;
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



CREATE SEQUENCE IF NOT EXISTS profession_id_seq START 10;
CREATE SEQUENCE IF NOT EXISTS technology_id_seq START 10;
CREATE SEQUENCE IF NOT EXISTS user_id_seq START 10;
CREATE SEQUENCE IF NOT EXISTS cart_id_seq START 10;
CREATE SEQUENCE IF NOT EXISTS micro_skill_id_seq START 10;
CREATE SEQUENCE IF NOT EXISTS article_id_seq START 10;
CREATE SEQUENCE IF NOT EXISTS review_id_seq START 10;
CREATE SEQUENCE IF NOT EXISTS saved_microskill_id_seq START 10;
CREATE SEQUENCE IF NOT EXISTS save_microskill_id_seq START 10;


INSERT INTO professions (profession_id, name)
VALUES (10, 'DEVELOPMENT'),
       (11, 'BUSINESS'),
       (12, 'IT_SOFTWARE'),
       (13, 'DESIGN');

INSERT INTO technologies (technology_id, profession_id, name)
VALUES (10, 10, 'Java'),
       (11, 10, 'Python'),
       (12, 11, 'React.js'),
       (13, 12, 'Data Science');


INSERT INTO users (user_id, username, password, email, status, image_url, role)
VALUES (10, 'test_user', '$2a$12$X7yEccnTctNxzrsLI46Wn.U6zT7YZN9QaNheqpVHuYC9JXo2uL14a',
        'user1@example.com', 'ONLINE', 'user.jpg', 'USER'),
       (11, 'admin_user', '$2a$12$X7yEccnTctNxzrsLI46Wn.U6zT7YZN9QaNheqpVHuYC9JXo2uL14a',
        'admin@example.com', 'ONLINE', 'admin.jpg', 'ADMIN');

INSERT INTO micro_skills (microskill_id, name, photo, creation_date, description, learning_time, level, rating,
                          popularity, views, price, lesson_count, about_skill, last_update_time, technology_id)
VALUES (10, 'Python Fundamentals', 'python.jpg', '2022-02-01',
        'Fundamental concepts of Python programming', '3 weeks', 'INTERMEDIATE', 4.2, 100.0, 2000, 29.99, 12,
        'Learn the fundamental concepts of Python programming language', '2022-02-28 09:30:00', 10),

       (11, 'Web Development with React.js', 'react.jpg', '2022-03-01',
        'Build modern web applications using React.js', '4 weeks', 'INTERMEDIATE', 4.8, 150.0, 1800, 39.99, 15,
        'Learn how to build modern web applications using React.js framework', '2022-03-31 15:45:00', 10),

       (12, 'Introduction to Data Science', 'data-science.jpg', '2022-04-01',
        'Fundamental concepts of data science', '5 weeks', 'INTERMEDIATE', 4.6, 130.0, 1600, 34.99, 13,
        'Learn the fundamental concepts of data science and analysis', '2022-04-30 11:20:00', 10),

       (13, 'Mobile App Development with Flutter', 'flutter.jpg', '2022-05-01',
        'Develop cross-platform mobile applications using Flutter', '6 weeks', 'ADVANCED', 4.9, 180.0, 1900, 49.99, 18,
        'Learn how to develop cross-platform mobile applications using Flutter framework', '2022-05-31 14:00:00', 11);

INSERT INTO cart (cart_id, user_id)
VALUES (10, 10),
       (11, 10);

INSERT INTO cart_microskill (cart_id, microskill_id)
VALUES (10, 10),
       (11, 11);

INSERT INTO articles (id, articlename, textofarticle, microskill_id)
VALUES (10, 'Python Basics', 'Learn the basics of Python programming', 10),
       (11, 'Web Development with React', 'Building modern web apps with React', 11),
       (12, 'Data Science Essentials', 'Introduction to data science concepts', 12),
       (13, 'Mobile App Development', 'Creating mobile apps using Flutter', 13);

INSERT INTO review (review_id, text, rating, microskill_id, user_id)
VALUES (10, 'Great course', 4.5, 10, 10),
       (11, 'Good course', 4.0, 11, 10),
       (12, 'Excellent course', 4.8, 12, 11),
       (13, 'Very good course', 4.2, 13, 10),
       (14, 'Awesome course', 4.6, 10, 11);

INSERT INTO saved_microskill (save_microskill_id, user_id)
VALUES (10, 10),
       (11, 11),
       (12, 11),
       (13, 10);

INSERT INTO save_microskill (save_microskill_id, microskill_id)
VALUES (10, 11),
       (11, 12),
       (12, 10),
       (13, 13);