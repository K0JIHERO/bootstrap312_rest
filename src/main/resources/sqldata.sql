create table users(
    id bigint not null primary key auto_increment,
    name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null)
    ENGINE = InnoDB;

create table roles(
    id bigint not null primary key auto_increment,
    role varchar(255) not null unique)
    ENGINE = InnoDB;

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),

    UNIQUE (user_id, role_id)
)
    ENGINE = InnoDB;


INSERT INTO users
VALUES (1, 'user', 'user', 'user@user.com', 'user');
INSERT INTO users
VALUES (2, 'admin', 'admin', 'admin@admin.com', 'admin');

INSERT INTO roles
VALUES (1, 'ROLE_USER');
INSERT INTO roles
VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles
VALUES (1, 1);
INSERT INTO user_roles
VALUES (2, 2);
