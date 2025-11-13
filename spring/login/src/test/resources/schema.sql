create table if not exists "login_user" (
    id bigint primary key auto_increment,
    email varchar(64) not null unique,
    password varchar(32) not null,
    authority varchar(32) not null);
