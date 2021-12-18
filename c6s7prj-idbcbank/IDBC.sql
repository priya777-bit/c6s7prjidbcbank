create database IDBC;
use IDBC;

-- Creating Customer Table ..

create table customer(
	customer_id int auto_increment,
    customer_name char(30),
    customer_dob date,
    customer_phoNo int,
    customer_email char(30),
    customer_add char(40),
    primary key(customer_id)
);

drop table customer;

alter table customer auto_increment = 100000;

select * from customer;
select * from account;
select * from transaction;



insert into customer (customer_name,customer_dob,customer_phoNo,customer_email,customer_add) values
('praju','2001-02-27',8303030,'g@ggmail.com','pune'); select last_insert_id();

-- Creating Account Table ..

create table Account
(
	account_no mediumint auto_increment,
    account_type char(20),
    account_balance int,
    account_open_date date,
    primary key(account_no),
    customer_id int,
    foreign key(customer_id)references customer(customer_id)
);

-- Modify Amount Balance ..

alter table account
modify account_balance int default 0;

-- Modify Account Date .. 

alter table account
modify account_open_date date default(current_date());

-- Alter Table To Change Datatype ..

alter table Account
modify column account_no bigint auto_increment;

-- Show Account Class ..

select * from Account;

-- Set Date To Default..

alter table Account 
alter account_open_date set default (current_date);

-- Drop Constraint For Date ..

alter table Account 
alter column account_open_date drop default;

-- Describe Account ..

describe Account;

-- Alter Set Auto Increment ..

alter table Account auto_increment = 100000;

alter table Account 
auto_increment = 100000000000;

-- Insert Into Account Table ..

insert into Account (account_type,account_balance,account_open_date,customer_id) values('savings',3000,default,100000);

-- Show Account Table ..

select * from Account;

-- Create Transaction Table ..

create table Transaction
(
	transaction_id mediumint auto_increment,
    transaction_date date default(current_date),
    transaction_type char(20),
    transaction_mode char(20),
    transaction_balance int,
    account_no bigint,
    foreign key(account_no)references Account(account_no),
    primary key(transaction_id)
);

-- Inserting Into Transaction Table ..

insert into transaction (transaction_date,transaction_type,transaction_mode,transaction_balance,account_no) values
(default,'debit','online',2000,100000000000);

-- Alter Table Transaction To Set Transaction Id ..

alter table Transaction auto_increment=1;

-- Drop Tranaction Table ..

drop table transaction;

-- Show Transaction table ..

select * from transaction;