CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(30),
                       password VARCHAR(100),
                       email VARCHAR(50),
                       nickname VARCHAR(30),
                       status BOOLEAN,
                       image_Url VARCHAR(255),
                       role ENUM('USER', 'ADMIN')
);
CREATE TABLE articles (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          articleName VARCHAR(255) NOT NULL,
                          textOfArticle TEXT NOT NULL,
                          micro_skill_id BIGINT,
                          FOREIGN KEY (micro_skill_id) REFERENCES micro_skills(id)
);

CREATE TABLE technologies (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(100) NOT NULL
);

CREATE TABLE professions (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(100) NOT NULL
);

CREATE TABLE profession_technologies (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         profession_id BIGINT,
                                         technology_id BIGINT,
                                         FOREIGN KEY (profession_id) REFERENCES professions(id),
                                         FOREIGN KEY (technology_id) REFERENCES technologies(id)
);
CREATE TABLE micro_skills (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(100) NOT NULL,
                              rating DOUBLE,
                              photo TEXT(255),
                              technology_id BIGINT,
                              FOREIGN KEY (technology_id) REFERENCES technologies(id)
);