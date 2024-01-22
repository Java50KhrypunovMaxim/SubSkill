CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(30),
                       password VARCHAR(100),
                       email VARCHAR(50),
                       nickname VARCHAR(30),
                       status ENUM('ONLINE', 'OFFLINE'),
                       image_Url VARCHAR(255),
                       role ENUM('USER', 'ADMIN')
);
CREATE TABLE admins (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(30),
                       password VARCHAR(100),
                       email VARCHAR(50),
                       nickname VARCHAR(30),
                       status ENUM('ONLINE', 'OFFLINE'),
                       image_Url VARCHAR(255),
                       role ENUM('USER', 'ADMIN')
);
CREATE TABLE technologies (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(255) NOT NULL
);

CREATE TABLE professions (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(255) NOT NULL
);
CREATE TABLE profession_technologies (
                                         profession_id BIGINT,
                                         technology_id BIGINT,
                                         PRIMARY KEY (profession_id, technology_id),
                                         FOREIGN KEY (profession_id) REFERENCES professions(id),
                                         FOREIGN KEY (technology_id) REFERENCES technologies(id)
);

CREATE TABLE micro_skills (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              technology_id BIGINT,
                              FOREIGN KEY (technology_id) REFERENCES technologies(id));

