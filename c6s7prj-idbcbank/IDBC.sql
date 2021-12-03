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

-- Set Date To Default..

alter table Account 
alter account_open_date set default '0000-00-00';

-- Drop Constraint For Date ..



-- Alter Set Auto Increment ..

alter table Account auto_increment = 100000000000;

-- Create Transaction Table ..

create table Transaction
(
	
);
