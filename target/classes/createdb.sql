CREATE DATABASE userDB;

CREATE TABLE users (
id INT(20) NOT NULL auto_increment,
  name VARCHAR(30),
  login VARCHAR(20),
  email VARCHAR(30),
  password VARCHAR (30),
  date DATE,
  PRIMARY KEY  (id));

