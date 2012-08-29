create table user (
    user_id int not null,
    name varchar(80) null,
    constraint pk_user primary key (user_id)
);

create table presentation(
     id int NOT NULL IDENTITY,
     title varchar(50),
     time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     description varchar(500),
     owner varchar(50),
     PRIMARY KEY (id),
     CONSTRAINT uc_presentation UNIQUE (owner,title)
);

create table talk(
    talk_id int not null IDENTITY,
    presentation_id int,
    venue varchar(50),
    date_ varchar(20),
    time_ varchar(20),
    constraint pk_talk primary key (talk_id)

)

create table feedback(
    feedback_id int not null IDENTITY,
    feedback_comment varchar(500),
    talk_id int,
    attendee varchar(50),
    attendee_mail varchar(100),
    constraint pk_feedback primary key(feedback_id)
)
