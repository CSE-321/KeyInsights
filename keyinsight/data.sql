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
    id integer primary key,
    name varchar(128) unique not null,
    type varchar(128) not null,
    server varchar(128) not null
);

create table Servers (
    id integer primary key,
    name varchar(128) unique not null,
    type varchar(128) not null,
    project varchar(128) not null
);

create table KPI (
    id integer primary key,
    total_jira_num integer not null,
    total_jira_story_points integer not null,
    closed_jira_num integer not null,
    wip_jira_num integer not null,
    not_started_jira_num integer not null,
    percentage_bugs float not null
);

-- Insert data into tables
insert into Users values ('John123', 'u43oqo43quotjasd', 'john123@wd.com', 'backend');
insert into Projects values ('B8X4', 'Software', 'Server1');
insert into Servers values('Server1', 'Software','B8X4');
insert into KPI values ('100','50','10','20','70','7.8');


-- Queries
select username, email
from Users
where team = 'backend';

select project 
from Servers, Projects
where Servers.name = Projects.server;

select server
from Projects, Servers
where Projects.server = Servers.name

select total_jira_num
from KPI;