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



INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (1, 1, 'VIEWACCOUNT');

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (2, 1, 'VIEWCARDS');

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (3, 1, 'VIEWLOANS');

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (4, 1, 'VIEWBALANCE');

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (7, 2, 'VIEWACCOUNT');

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (8, 2, 'VIEWCARDS');

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (9, 2, 'VIEWLOANS');

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (10, 2, 'VIEWBALANCE');