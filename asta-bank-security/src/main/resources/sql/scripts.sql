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
    `customer_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `mobile_number` varchar(20) NOT NULL,
    `password` varchar(500) NOT NULL,
    `role` varchar(100) NOT NULL,
    `creation_date` date DEFAULT NULL,
    PRIMARY KEY (`customer_id`)
);

INSERT INTO `customer` (`name`,`email`,`mobile_number`, `password`, `role`,`creation_date`)
VALUES ('Sashank','sashank1991@live.com','9876548337', '$2a$10$AgIjLUXhd1TXjIXo3Eii5eGuy1aAYJCo.pJmGzmSZKLvl2FqIf7l.', 'admin', CURDATE());

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

INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `creation_date`)
VALUES (1, 1865764534, 'Savings', '123 Main Street, New York', CURDATE());

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



INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_date`, `transaction_summary`, `transaction_type`,`transaction_amount`,
                                    `closing_balance`, `creation_date`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 7 DAY), 'Coffee Shop', 'Withdrawal', 30,34500, DATE_SUB(CURDATE(), INTERVAL 7 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_date`, `transaction_summary`, `transaction_type`,`transaction_amount`,
                                    `closing_balance`, `creation_date`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 6 DAY), 'Uber', 'Withdrawal', 100,34400, DATE_SUB(CURDATE(), INTERVAL 6 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_date`, `transaction_summary`, `transaction_type`,`transaction_amount`,
                                    `closing_balance`, `creation_date`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 5 DAY), 'Self Deposit', 'Deposit', 500,34900, DATE_SUB(CURDATE(), INTERVAL 5 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_date`, `transaction_summary`, `transaction_type`,`transaction_amount`,
                                    `closing_balance`, `creation_date`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 4 DAY), 'Ebay', 'Withdrawal', 600,34300, DATE_SUB(CURDATE(), INTERVAL 4 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_date`, `transaction_summary`, `transaction_type`,`transaction_amount`,
                                    `closing_balance`, `creation_date`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 'OnlineTransfer', 'Deposit', 700,35000, DATE_SUB(CURDATE(), INTERVAL 2 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_date`, `transaction_summary`, `transaction_type`,`transaction_amount`,
                                    `closing_balance`, `creation_date`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'Amazon.com', 'Withdrawal', 100,34900, DATE_SUB(CURDATE(), INTERVAL 1 DAY));


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

INSERT INTO `loans` ( `loan_number`, `customer_id`, `start_date`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `creation_date`)
VALUES ( 1, 1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13');

INSERT INTO `loans` ( `loan_number`, `customer_id`, `start_date`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `creation_date`)
VALUES ( 2, 1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06');

INSERT INTO `loans` ( `loan_number`, `customer_id`, `start_date`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `creation_date`)
VALUES ( 3, 1, '2018-02-14', 'Home', 50000, 10000, 40000, '2018-02-14');

INSERT INTO `loans` ( `loan_number`, `customer_id`, `start_date`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `creation_date`)
VALUES ( 4, 1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');

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

INSERT INTO `cards` ( `card_id`, `card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `creation_date`)
VALUES ( 1, '4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURDATE());

INSERT INTO `cards` ( `card_id`, `card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `creation_date`)
VALUES ( 2, '3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURDATE());

INSERT INTO `cards` ( `card_id`, `card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `creation_date`)
VALUES ( 3, '2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURDATE());

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

INSERT INTO `notice` ( `notice_id`, `notice_summary`, `notice_details`, `notice_begin_date`, `notice_end_date`, `creation_date`, `update_date`)
VALUES ( '1', 'Home Loan Interest rates reduced', 'Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice` ( `notice_id`, `notice_summary`, `notice_details`, `notice_begin_date`, `notice_end_date`, `creation_date`, `update_date`)
VALUES ( '2', 'Net Banking Offers', 'Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice` ( `notice_id`, `notice_summary`, `notice_details`, `notice_begin_date`, `notice_end_date`, `creation_date`, `update_date`)
VALUES ( '3', 'Mobile App Downtime', 'The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice` ( `notice_id`, `notice_summary`, `notice_details`, `notice_begin_date`, `notice_end_date`, `creation_date`, `update_date`)
VALUES ( '4', 'E Auction notice', 'There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice` ( `notice_id`, `notice_summary`, `notice_details`, `notice_begin_date`, `notice_end_date`, `creation_date`, `update_date`)
VALUES ( '5', 'Launch of Millennia Cards', 'Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice` ( `notice_id`, `notice_summary`, `notice_details`, `notice_begin_date`, `notice_end_date`, `creation_date`, `update_date`)
VALUES ( '6', 'COVID-19 Insurance', 'EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

CREATE TABLE `contact_messages` (
    `contact_id` varchar(50) NOT NULL,
    `contact_name` varchar(50) NOT NULL,
    `contact_email` varchar(100) NOT NULL,
    `subject` varchar(500) NOT NULL,
    `message` varchar(2000) NOT NULL,
    `creation_date` date DEFAULT NULL,
    PRIMARY KEY (`contact_id`)
);

