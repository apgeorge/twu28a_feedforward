create table if not exists user (
    user_id int not null,
    name varchar(80) null,
    constraint pk_user primary key (user_id)
);


create table if not exists presentation(
     id int GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
     title varchar(50),
     time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     description varchar(500),
     owner varchar(50),
     CONSTRAINT uc_presentation UNIQUE (owner,title)
);

create table if not exists talk(
    talk_id int  GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    presentation_id int,
    venue varchar(50),
    time_of_talk TIMESTAMP,
);

create table if not exists feedback(
    feedback_id int GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    feedback_comment varchar(500),
    talk_id int,
    attendee varchar(50),
    attendee_mail varchar(100),
    time_at_creation  TIMESTAMP
);

alter table talk add last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

