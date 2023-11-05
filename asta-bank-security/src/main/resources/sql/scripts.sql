create database astabank;

use astabank;

-- users and authorities tables are compliant with the specification of Spring Security's JdbcDaoImpl.

CREATE TABLE `users` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `username` VARCHAR(45) NOT NULL,
     `password` VARCHAR(45) NOT NULL,
     `enabled` INT NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE `authorities` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(45) NOT NULL,
    `authority` varchar(45) NOT NULL,
PRIMARY KEY (`id`));

INSERT IGNORE INTO `users` VALUES (NULL, 'asta-user', 'userpassword', '1');
INSERT IGNORE INTO `authorities` VALUES (NULL, 'asta-user', 'write');


-- Custom User Table if we don't want to rely on spring jdbc authentication

CREATE TABLE `customer` (
     `id` int NOT NULL AUTO_INCREMENT,
     `email` varchar(45) NOT NULL,
     `pwd` varchar(200) NOT NULL,
     `role` varchar(45) NOT NULL,
     PRIMARY KEY (`id`)
);

INSERT INTO `customer` (`email`, `pwd`, `role`)
VALUES ('sashank1991@gmail.com', 'asta-password', 'admin');