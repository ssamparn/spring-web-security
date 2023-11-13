DROP TABLE IF EXISTS authorities CASCADE;
DROP TABLE IF EXISTS account_transactions CASCADE;
DROP TABLE IF EXISTS loans CASCADE;
DROP TABLE IF EXISTS cards CASCADE;
DROP TABLE IF EXISTS notice CASCADE;
DROP TABLE IF EXISTS contact_messages CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS customer CASCADE;

-- Custom User Table if we don't want to rely on spring jdbc authentication

CREATE TABLE `customer` (
    `customer_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `mobile_number` varchar(20) NOT NULL,
    `password` varchar(500) NOT NULL,
    `role` varchar(100) NOT NULL,
    `creation_date` date DEFAULT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE `authorities` (
    `id` int NOT NULL AUTO_INCREMENT,
    `customer_id` int NOT NULL,
    `name` varchar(50) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `customer_id` (`customer_id`),
    CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
);

CREATE TABLE `accounts` (
    `customer_id` int NOT NULL,
    `account_number` int NOT NULL,
    `account_type` varchar(100) NOT NULL,
    `branch_address` varchar(200) NOT NULL,
    `creation_date` date DEFAULT NULL,
    PRIMARY KEY (`account_number`),
    KEY `customer_id` (`customer_id`),
    CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

CREATE TABLE `account_transactions` (
    `transaction_id` varchar(200) NOT NULL,
    `account_number` int NOT NULL,
    `customer_id` int NOT NULL,
    `transaction_date` date NOT NULL,
    `transaction_summary` varchar(200) NOT NULL,
    `transaction_type` varchar(100) NOT NULL,
    `transaction_amount` int NOT NULL,
    `closing_balance` int NOT NULL,
    `creation_date` date DEFAULT NULL,
    PRIMARY KEY (`transaction_id`),
    KEY `customer_id` (`customer_id`),
    KEY `account_number` (`account_number`),
    CONSTRAINT `accounts_ibfk_2` FOREIGN KEY (`account_number`) REFERENCES `accounts` (`account_number`) ON DELETE CASCADE,
    CONSTRAINT `acct_user_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);


CREATE TABLE `loans` (
    `loan_number` int NOT NULL AUTO_INCREMENT,
    `customer_id` int NOT NULL,
    `start_date` date NOT NULL,
    `loan_type` varchar(100) NOT NULL,
    `total_loan` int NOT NULL,
    `amount_paid` int NOT NULL,
    `outstanding_amount` int NOT NULL,
    `creation_date` date DEFAULT NULL,
    PRIMARY KEY (`loan_number`),
    KEY `customer_id` (`customer_id`),
    CONSTRAINT `loan_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

CREATE TABLE `cards` (
    `card_id` int NOT NULL AUTO_INCREMENT,
    `card_number` varchar(100) NOT NULL,
    `customer_id` int NOT NULL,
    `card_type` varchar(100) NOT NULL,
    `total_limit` int NOT NULL,
    `amount_used` int NOT NULL,
    `available_amount` int NOT NULL,
    `creation_date` date DEFAULT NULL,
    PRIMARY KEY (`card_id`),
    KEY `customer_id` (`customer_id`),
    CONSTRAINT `card_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

CREATE TABLE `notice` (
    `notice_id` int NOT NULL AUTO_INCREMENT,
    `notice_summary` varchar(200) NOT NULL,
    `notice_details` varchar(500) NOT NULL,
    `notice_begin_date` date NOT NULL,
    `notice_end_date` date DEFAULT NULL,
    `creation_date` date DEFAULT NULL,
    `update_date` date DEFAULT NULL,
    PRIMARY KEY (`notice_id`)
);

CREATE TABLE `contact_messages` (
    `contact_id` varchar(50) NOT NULL,
    `contact_name` varchar(50) NOT NULL,
    `contact_email` varchar(100) NOT NULL,
    `subject` varchar(500) NOT NULL,
    `message` varchar(2000) NOT NULL,
    `creation_date` date DEFAULT NULL,
    PRIMARY KEY (`contact_id`)
);


