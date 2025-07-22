create database chat;

create table IF NOT EXISTS user (
    id int AUTO_INCREMENT PRIMARY KEY,
    surname varchar(30) NOT NULL,
    name varchar(15) NOT NULL,
    patronymic varchar(20),
    birthday_year int CHECK (birthday_date BETWEEN 1910 AND 2025),
    username varchar(15) NOT NULL,
    password varchar(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table IF NOT EXISTS role (
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(30) NOT NULL UNIQUE
);

create table IF NOT EXISTS user_role (
    user_id int NOT NULL,
    role_id int NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

create table IF NOT EXISTS chat (
    id int AUTO_INCREMENT PRIMARY KEY,
    author_id int NOT NULL,
    interlocutor_id int NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (interlocutor_id) REFERENCES user(id)
);

create table IF NOT EXISTS message (
    message_id int AUTO_INCREMENT PRIMARY KEY,
    chat_id int NOT NULL,
    author_id int NOT NULL,
    message_text varchar(100) NOT NULL,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    FOREIGN KEY (chat_id) REFERENCES chat(id)
);