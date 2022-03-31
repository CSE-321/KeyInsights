
create table if not exists Users (
    user_id integer primary key,
    email varchar(128) not null
);

create table if not exists Server (
    server_id varchar(64) primary key
);

create table if not exists Projects (
    project_id varchar(10) primary key,
    name varchar(64) not null,
    team_lead varchar(128),
    created_at timestamp not null,
    num_issues integer 
);

create table if not exists Issues (
    issue_id char(10) primary key,
    project_id varchar(10) references Projects(project_id),
    team_type varchar(25),
    sub_type varchar(25),
    story_point float,
    priority varchar(25),
    resolution varchar(25),
    status varchar(25)
);

create table if not exists NotificationSettings (
    setting_id int primary key,
    user_id int references User(user_id),
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