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
    project_id integer primary key,
    name varchar(64) not null,
    team_lead varchar(128),
    team_lead_avatar_url varchar(128),
    num_issues integer
    category varchar(64),
    created_at varchar(64),
);

create table if not exists Issues (
    id integer primary key, 
    name varchar(64),
    project_name varchar(10) refernces Projects(name),
    team_type varchar(25),
    status varchar(25),
    creation_date varchar(25),
    creation_time varchar(25),
    updated_date varchar(25),
    updated_time varchar(25),
    due_date varchar(25),
    due_time varchar(25),
    story_point float,
    sub_type varchar(25),
    priority varchar(25),
    resolution varchar(25),
    assignee varchar(25),
    assignee_avatar_url varchar(128)
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