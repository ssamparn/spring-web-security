-- Don't Refer this file
create database astabank;
use astabank;

drop table `customer`;
drop table `accounts`;
drop table `account_transactions`;
drop table `loans`;
drop table `cards`;
drop table `notice_details`;
drop table `contact_messages`;

-- users and authorities tables are compliant with the specification of Spring Security's JdbcDaoImpl.
-- To be referred for default spring securities jdbc dao implementation.

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