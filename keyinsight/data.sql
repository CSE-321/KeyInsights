---- ======================================================
---- Database Script
---- ======================================================

-- Create tables
create table Users (
    id integer primary key,
    username varchar(128) unique not null,
    password varchar(64) not null,
    email varchar(128) unique not null,
    team varchar(128) not null
);

create table Projects (
    id integer primary key
    name varchar(128) unique not null,
    type varchar(128) not null,
    server varchar(128) not null
);

create table Servers (
    id integer primary key
    name varchar(128) unique not null,
    type varchar(128) not null,
    project varchar(128) not null
);

-- Insert data into tables
insert into Users values ('John123', 'u43oqo43quotjasd', 'john123@wd.com', 'backend');

-- Queries
select username, email
from Users
where team = 'backend';