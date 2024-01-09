
CREATE TABLE users (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(30),
                      password VARCHAR(100),
                      email VARCHAR(50),
                      nickname VARCHAR(30),
                      status VARCHAR(20),
                      imageUrl VARCHAR(255)

);
ALTER TABLE users ADD COLUMN role VARCHAR(15);