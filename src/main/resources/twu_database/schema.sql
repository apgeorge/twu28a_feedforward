create table user (
    user_id int not null,
    name varchar(80) null,
    constraint pk_user primary key (user_id)
);

create table presentation(
     id int NOT NULL IDENTITY,
     title varchar(50),
     description varchar(500),
     owner varchar(50),
     PRIMARY KEY (id)
);
