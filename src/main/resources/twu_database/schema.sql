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

create table event(
    event_id int not null IDENTITY,
    presentation_id int,
    venue varchar(50),
    date_time varchar(50),
    constraint pk_event primary key (event_id)

)
