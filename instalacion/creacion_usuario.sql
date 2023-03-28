CREATE USER 'test'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON test . * TO 'test'@'localhost';
FLUSH PRIVILEGES;