CREATE TABLE IF NOT EXISTS user (
    user_no bigint NOT NULL AUTO_INCREMENT,
    user_email varchar(100) NOT NULL,
    user_name varchar(20) NOT NULL,
    user_nic varchar(30) NOT NULL,
    user_pw varchar(255) NOT NULL,
    user_phone varchar(20) NOT NULL,
    user_gender varchar(2) DEFAULT NULL,
    PRIMARY KEY (user_no, user_email)
);

CREATE TABLE IF NOT EXISTS product (
    product_id bigint NOT NULL AUTO_INCREMENT,
    product_desc text NOT NULL,
    product_name varchar(100) NOT NULL,
    create_dt datetime(6) DEFAULT NULL,
    update_dt datetime(6) DEFAULT NULL,
    PRIMARY KEY (product_id)
);

CREATE TABLE IF NOT EXISTS user_order (
  order_no bigint NOT NULL AUTO_INCREMENT,
  order_id varchar(12) NOT NULL,
  product_id bigint(20) NOT NULL,
  user_no bigint(20) NOT NULL,
  create_dt datetime(6) DEFAULT NULL,
  update_dt datetime(6) DEFAULT NULL,
  PRIMARY KEY (order_no),
  UNIQUE KEY uk_order_id (order_id)
);

CREATE TABLE IF NOT EXISTS user_roles (
  user_user_no bigint NOT NULL,
  roles varchar(255) DEFAULT NULL,
  KEY fk_user_role_no (user_user_no),
  CONSTRAINT fk_user_role_no FOREIGN KEY (user_user_no) REFERENCES user (user_no)
);
