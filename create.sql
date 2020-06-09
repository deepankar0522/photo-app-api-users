create table photoapp.users (id bigint not null, email varchar(50) not null, encrypted_password varchar(255) not null, first_name varchar(20) not null, last_name varchar(20) not null, user_id varchar(50) not null, primary key (id))
alter table photoapp.users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
alter table photoapp.users add constraint UK_6efs5vmce86ymf5q7lmvn2uuf unique (user_id)
create sequence hibernate_sequence start with 1 increment by 1
create table photoapp.users (id bigint not null, email varchar(50) not null, encrypted_password varchar(255) not null, first_name varchar(20) not null, last_name varchar(20) not null, user_id varchar(50) not null, primary key (id))
alter table photoapp.users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
alter table photoapp.users add constraint UK_6efs5vmce86ymf5q7lmvn2uuf unique (user_id)
create sequence hibernate_sequence start with 1 increment by 1
create sequence hibernate_sequence start with 1 increment by 1
create table users (id bigint not null, email varchar(50) not null, encrypted_password varchar(255) not null, first_name varchar(20) not null, last_name varchar(20) not null, user_id varchar(50) not null, primary key (id))
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
alter table users add constraint UK_6efs5vmce86ymf5q7lmvn2uuf unique (user_id)
