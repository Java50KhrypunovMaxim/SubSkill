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
FROM cart;
DELETE
FROM users;



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


INSERT INTO users (user_id, username, password, email, status, image, role)
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
        'Learn how to develop cross-platform mobile applications using Flutter framework', '2022-05-31 14:00:00', 11),
       (20, 'Advanced Python Programming', 'advanced-python.jpg', '2022-03-15',
        'Advanced concepts and techniques in Python programming', '4 weeks', 'ADVANCED', 4.5, 120.0, 2200, 44.99, 14,
        'Explore advanced concepts and techniques in Python programming language', '2022-04-10 10:15:00', 10),

       (21, 'Django Web Development', 'django.jpg', '2022-04-15',
        'Build web applications using the Django framework', '5 weeks', 'ADVANCED', 4.7, 140.0, 2100, 39.99, 16,
        'Learn how to build powerful web applications using the Django framework', '2022-05-10 13:45:00', 10),

       (22, 'State Management in React', 'state-management.jpg', '2022-03-20',
        'Effective state management techniques in React.js', '3 weeks', 'INTERMEDIATE', 4.6, 110.0, 1800, 29.99, 10,
        'Master state management techniques for efficient React.js development', '2022-04-10 12:30:00', 11),

       (23, 'React Native Mobile Development', 'react-native.jpg', '2022-04-20',
        'Develop mobile applications using React Native', '4 weeks', 'INTERMEDIATE', 4.8, 130.0, 1900, 34.99, 12,
        'Learn to build mobile apps for iOS and Android using React Native', '2022-05-20 14:30:00', 11),

       (24, 'Machine Learning Fundamentals', 'machine-learning.jpg', '2022-05-15',
        'Introduction to machine learning concepts and algorithms', '6 weeks', 'INTERMEDIATE', 4.4, 150.0, 2000, 49.99,
        18,
        'Explore the fundamentals of machine learning and its applications', '2022-06-15 11:00:00', 12),

       (25, 'Data Visualization with Matplotlib', 'matplotlib.jpg', '2022-06-20',
        'Creating informative visualizations using Matplotlib', '3 weeks', 'INTERMEDIATE', 4.7, 100.0, 1700, 29.99, 10,
        'Master the art of data visualization using Matplotlib library', '2022-07-10 09:45:00', 12),

       (26, 'Advanced Flutter Techniques', 'advanced-flutter.jpg', '2022-07-15',
        'Advanced development techniques in Flutter framework', '5 weeks', 'ADVANCED', 4.9, 160.0, 2300, 54.99, 15,
        'Explore advanced Flutter techniques for cross-platform app development', '2022-08-10 14:45:00', 13),

       (27, 'Firebase Integration with Flutter', 'firebase-flutter.jpg', '2022-08-20',
        'Integrating Firebase services into Flutter applications', '4 weeks', 'INTERMEDIATE', 4.6, 120.0, 1900, 39.99,
        12,
        'Learn to integrate Firebase for real-time databases and authentication in Flutter', '2022-09-20 12:30:00', 13);

INSERT INTO cart (cart_id, user_id)
VALUES (10, 10),
       (11, 10);

INSERT INTO cart_microskill (cart_id, microskill_id)
VALUES (10, 10),
       (11, 11);

INSERT INTO articles (id, article_name, text, microskill_id)
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