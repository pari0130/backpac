CREATE TABLE IF NOT EXISTS user (
  user_no bigint NOT NULL AUTO_INCREMENT,
  user_email varchar(100) NOT NULL,
  user_name varchar(20) NOT NULL,
  user_nic varchar(30) NOT NULL,
  user_pw varchar(255) NOT NULL,
  user_phone int(20) NOT NULL,
  user_gender varchar(2) DEFAULT NULL,
  PRIMARY KEY (user_no, user_email)
);
