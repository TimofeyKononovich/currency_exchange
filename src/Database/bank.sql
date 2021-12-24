create database Currency_exchange;
drop database Currency_exchange;
use Currency_exchange;
create table  members (
id int not null AUTO_INCREMENT,
LoginDate varchar(255) NOT NULL,
Perpassword varchar(255) NOT NULL,
USD double(10,4) NOT NULL,
RUB double(10,4) NOT NULL,
EU double(10,4) NOT NULL,
PRIMARY KEY(id), 
CONSTRAINT UC_PERSON UNIQUE (id,LoginDate, Perpassword)
);

create table ExchangeValue (
id int not null AUTO_INCREMENT,
buyUSD double(10,4) NOT NULL,
buyRUB double(10,4) NOT NULL,
buyEU double(10,4) NOT NULL,
sellUSD double(10,4) NOT NULL,
sellRUB double(10,4) NOT NULL,
sellEU double(10,4) NOT NULL,
PRIMARY KEY(id)
);

create table ExHistory (
LoginDate varchar(255) NOT NULL,
ValueIn double(10,4) NOT NULL,
CurIn varchar(255) NOT NULL,
CurOut varchar(255) NOT NULL,
ValueOut double(10,4) NOT NULL,
DayEx varchar(255) NOT NULL
);