INSERT INTO `customer` (`name`,`email`,`mobile_number`, `password`, `role`,`creation_date`)
VALUES ('Sashank','sashank1991@live.com','9876548337', '$2a$10$AgIjLUXhd1TXjIXo3Eii5eGuy1aAYJCo.pJmGzmSZKLvl2FqIf7l.', 'admin', CURDATE());

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (1, 1, 'VIEWACCOUNT');

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (2, 1, 'VIEWCARDS');

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (3, 1, 'VIEWLOANS');

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (4, 1, 'VIEWBALANCE');

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (5, 1, 'ROLE_USER');

INSERT INTO `authorities` (`id`, `customer_id`, `name`)
VALUES (6, 1, 'ROLE_ADMIN');


INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `creation_date`)
VALUES (1, 1865764534, 'Savings', '123 Main Street, New York', CURDATE());


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


INSERT INTO `loans` ( `loan_number`, `customer_id`, `start_date`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `creation_date`)
VALUES ( 1, 1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13');

INSERT INTO `loans` ( `loan_number`, `customer_id`, `start_date`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `creation_date`)
VALUES ( 2, 1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06');

INSERT INTO `loans` ( `loan_number`, `customer_id`, `start_date`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `creation_date`)
VALUES ( 3, 1, '2018-02-14', 'Home', 50000, 10000, 40000, '2018-02-14');

INSERT INTO `loans` ( `loan_number`, `customer_id`, `start_date`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `creation_date`)
VALUES ( 4, 1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');



INSERT INTO `cards` ( `card_id`, `card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `creation_date`)
VALUES ( 1, '4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURDATE());

INSERT INTO `cards` ( `card_id`, `card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `creation_date`)
VALUES ( 2, '3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURDATE());

INSERT INTO `cards` ( `card_id`, `card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `creation_date`)
VALUES ( 3, '2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURDATE());



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



