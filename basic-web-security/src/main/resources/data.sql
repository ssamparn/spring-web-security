INSERT into USERS (username, first_name, last_name, password, email, enabled)
values ('Sashank', 'Sashank', 'Samantray','{noop}password','sashank1703@gmail.com', true);

INSERT into USERS (username, first_name, last_name, password, email, enabled)
values ('Admin', 'Admin', 'Admin','{noop}adminsecret','admin1703@gmail.com', true);

INSERT into USERS (username, first_name, last_name, password, email, enabled)
values ('User', 'User', 'User','{noop}usersecret','user1703@gmail.com', true);



INSERT into AUTHORITIES (username, authority)
values ( 'Sashank', 'USER' );

INSERT into AUTHORITIES (username, authority)
values ( 'Admin', 'ADMIN' );

INSERT into AUTHORITIES (username, authority)
values ( 'User', 'USER' );