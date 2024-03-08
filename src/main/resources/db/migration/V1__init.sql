CREATE TABLE IF NOT EXISTS articles
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    article_name  VARCHAR(255)                            NOT NULL,
    text          TEXT                                    NOT NULL,
    microskill_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_articles PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cart
(
    cart_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id BIGINT,
    CONSTRAINT pk_cart PRIMARY KEY (cart_id)
);

CREATE TABLE IF NOT EXISTS cart_microskill
(
    cart_id       BIGINT NOT NULL,
    microskill_id BIGINT NOT NULL,
    CONSTRAINT pk_cart_microskill PRIMARY KEY (cart_id, microskill_id)
);

CREATE TABLE IF NOT EXISTS micro_skill_tags
(
    micro_skill_id BIGINT NOT NULL,
    tag            VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS micro_skills
(
    microskill_id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name             TEXT,
    photo            TEXT,
    creation_date    date,
    description      TEXT,
    learning_time    VARCHAR(255),
    level            VARCHAR(255),
    rating           DOUBLE PRECISION,
    popularity       DOUBLE PRECISION,
    views            INTEGER,
    price            DOUBLE PRECISION,
    lesson_count     INTEGER,
    about_skill      TEXT,
    last_update_time TIMESTAMP WITHOUT TIME ZONE,
    technology_id    BIGINT,
    cart_id          BIGINT,
    CONSTRAINT pk_micro_skills PRIMARY KEY (microskill_id)
);

CREATE TABLE IF NOT EXISTS profession_technologies
(
    profession_id BIGINT NOT NULL,
    technology_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS professions
(
    profession_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name          VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_professions PRIMARY KEY (profession_id)
);

CREATE TABLE IF NOT EXISTS review
(
    review_id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    text          VARCHAR(255)                            NOT NULL,
    rating        DOUBLE PRECISION                        NOT NULL,
    microskill_id BIGINT,
    user_id       BIGINT,
    CONSTRAINT pk_review PRIMARY KEY (review_id)
);

CREATE TABLE IF NOT EXISTS save_microskill
(
    microskill_id      BIGINT NOT NULL,
    save_microskill_id BIGINT NOT NULL,
    CONSTRAINT pk_save_microskill PRIMARY KEY (microskill_id, save_microskill_id)
);

CREATE TABLE IF NOT EXISTS saved_microskill
(
    save_microskill_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id            BIGINT,
    CONSTRAINT pk_saved_microskill PRIMARY KEY (save_microskill_id)
);

CREATE TABLE IF NOT EXISTS technologies
(
    technology_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name          VARCHAR(255)                            NOT NULL,
    profession_id BIGINT,
    CONSTRAINT pk_technologies PRIMARY KEY (technology_id)
);

CREATE TABLE IF NOT EXISTS users
(
    user_id  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    username VARCHAR(255)                            NOT NULL,
    password VARCHAR(255)                            NOT NULL,
    email    VARCHAR(255)                            NOT NULL,
    status   VARCHAR(255),
    image    TEXT,
    role     VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE micro_skills
    ADD CONSTRAINT FK_MICRO_SKILLS_ON_CART FOREIGN KEY (cart_id) REFERENCES cart (cart_id);

ALTER TABLE micro_skills
    ADD CONSTRAINT FK_MICRO_SKILLS_ON_TECHNOLOGY FOREIGN KEY (technology_id) REFERENCES technologies (technology_id);

ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_MICROSKILL FOREIGN KEY (microskill_id) REFERENCES micro_skills (microskill_id);

ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE saved_microskill
    ADD CONSTRAINT FK_SAVED_MICROSKILL_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE technologies
    ADD CONSTRAINT FK_TECHNOLOGIES_ON_PROFESSION FOREIGN KEY (profession_id) REFERENCES professions (profession_id);

ALTER TABLE cart_microskill
    ADD CONSTRAINT fk_carmic_on_cart FOREIGN KEY (cart_id) REFERENCES cart (cart_id);

ALTER TABLE cart_microskill
    ADD CONSTRAINT fk_carmic_on_micro_skill FOREIGN KEY (microskill_id) REFERENCES micro_skills (microskill_id);

ALTER TABLE micro_skill_tags
    ADD CONSTRAINT fk_micro_skill_tags_on_micro_skill FOREIGN KEY (micro_skill_id) REFERENCES micro_skills (microskill_id);

ALTER TABLE profession_technologies
    ADD CONSTRAINT fk_protec_on_profession FOREIGN KEY (profession_id) REFERENCES professions (profession_id);

ALTER TABLE profession_technologies
    ADD CONSTRAINT fk_protec_on_technology FOREIGN KEY (technology_id) REFERENCES technologies (technology_id);

ALTER TABLE save_microskill
    ADD CONSTRAINT fk_savmic_on_micro_skill FOREIGN KEY (microskill_id) REFERENCES micro_skills (microskill_id);

ALTER TABLE save_microskill
    ADD CONSTRAINT fk_savmic_on_save_microskill FOREIGN KEY (save_microskill_id) REFERENCES saved_microskill (save_microskill_id);

DROP SEQUENCE article_id_seq CASCADE;

DROP SEQUENCE cart_id_seq CASCADE;

DROP SEQUENCE micro_skill_id_seq CASCADE;

DROP SEQUENCE profession_id_seq CASCADE;

DROP SEQUENCE review_id_seq CASCADE;

DROP SEQUENCE save_microskill_id_seq CASCADE;

DROP SEQUENCE saved_microskill_id_seq CASCADE;

DROP SEQUENCE technology_id_seq CASCADE;

DROP SEQUENCE user_id_seq CASCADE;