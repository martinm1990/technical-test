CREATE TABLE super_hero(
    id int NOT NULL auto_increment,
    name varchar(100) not null,
    age smallint,
    skill varchar(250),
    PRIMARY KEY(id)
);

CREATE TABLE auth_role(
                          id int NOT NULL auto_increment,
                          name varchar(100) not null,
                          PRIMARY KEY(id)
);

CREATE TABLE auth_user(
                          id int NOT NULL auto_increment,
                          username varchar(100) not null,
                          password varchar(100) not null,
                          email varchar(100) null,
                          enabled tinyint not null default 0,
                          PRIMARY KEY(id)
);

CREATE TABLE auth_user_roles(
                                auth_user_id int NOT NULL,
                                auth_role_id int NOT NULL,
                                PRIMARY KEY(auth_user_id,auth_role_id)
);