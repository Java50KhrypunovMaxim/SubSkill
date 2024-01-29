
delete from users;
INSERT INTO users (username, password, email, nickname, status, image_url, role)
VALUES
  ('user1', 'password1', 'user1@example.com', 'nickname1', true, 'image1.jpg', 'USER'),
  ('user2', 'password2', 'user2@example.com', 'nickname2', true, 'image2.jpg', 'USER'),
  ('user3', 'password3', 'user3@example.com', 'nickname3', true, 'image3.jpg', 'USER'),
  ('user4', 'password4', 'user4@example.com', 'nickname4', true, 'image4.jpg', 'USER'),
  ('user5', 'password5', 'user5@example.com', 'nickname5', true, 'image5.jpg', 'USER');
delete from articles;
INSERT INTO articles (article_name, text_of_article, id_of_skills)
VALUES
  ('Article1', 'Text of Article 11', 1),
  ('Article2', 'Text of Article 22', 2),
  ('Article3', 'Text of Article 33', 3),
  ('Article4', 'Text of Article 44', 4),
  ('Article5', 'Text of Article 55', 5);