delete table if exists Users;
delete table if exists Servers;
delete table if exists Projects;
delete table if exists Issues;
delete table if exists NotificationSettings;
delete table if exists ProjectAccess;
delete table if exists ServerAccess;

create table if not exists Users (
    user_id integer primary key,
    email varchar(128) not null
);

create table if not exists Servers (
    server_id varchar(64) primary key
);

create table if not exists Projects (
    id int primary key,
    name varchar(64) not null,
    teamLead varchar(128),
    teamLeadAvatarUrl varchar(128),
    numIssues integer
    createdDate varchar(64)
);

create table if not exists Issues (
    id int primary key, 
    name varchar(64),
    projectName varchar(10) refernces Projects(name),
    teamType varchar(25),
    status varchar(25),
    creationDate varchar(25),
    creationTime varchar(25),
    updatedDate varchar(25),
    updatedTime varchar(25),
    dueDate varchar(25),
    dueTime varchar(25),
    storyPoint float,
    subType varchar(25),
    priority varchar(25),
    resolution varchar(25),
    assignee varchar(25),
    assigneeAvatarUrl varchar(128)
);

create table if not exists NotificationSettings (
    setting_id int primary key,
    user_id int references User(user_id),
    project_id varchar(10) references Projects(project_id),
    critical_ticket_notify_time int not null,
    unchanged_sprint_notify_time int not null,
    notify_unfinished_ticket boolean not null,
    project_report_interval int not null,
    workload_report_interval int not null
);

create table if not exists ProjectAccess (
    project_access_id int primary key,
    user_id int references User(user_id),
    project_id varchar(10) references Projects(project_id)
);

create table if not exists ServerAccess (
    server_access_id int primary key,
    user_id int references User(user_id),
    server_id varchar(64) references Server(server_id)
);