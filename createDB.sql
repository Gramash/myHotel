DROP TABLE IF exists products;
DROP TABLE IF EXISTS users ;


CREATE TABLE products (
roomNo int PRIMARY KEY NOT NULL UNIQUE,
sleeps int NOT NULL,
price double NOT NULL,
image varchar(200) Not null
) engine = InnoDB default charset = utf8;

create table users(
user_id int PRIMARY KEY NOT NULL unique auto_increment,
login varchar(15) NOT NULL unique,
name varchar(15) NOT NULL,
password varchar(15) not null,
email varchar(30) not null unique,
role varchar(15) not null default 'CUSTOMER'
)engine = innoDB default charset = utf8;

INSERT INTO products VALUES (1, 1, 100.0, 'https://i.pinimg.com/736x/f4/2e/fc/f42efcfe58f2d63eee9aa6602d036c00--manhattan-apartment-coffered-ceilings.jpg');
INSERT INTO products VALUES (2, 3, 200.0, 'https://i.pinimg.com/736x/f9/00/94/f9009498a7eb6ed37002f549b82d2410--living-room-apartment-apartment-therapy.jpg');
INSERT INTO products VALUES (3, 2, 150.0, 'http://cypressequity.com/images/gibson/gibson-500x600-details-01.jpg');

insert into users values (default, 'reygo', 'george', 'gibsonlp', 'garmash.g.k@gmail.com', 'MANAGER');
insert into users values (default, 'customer', 'barak', '123', 'obama@gmail.com', DEFAULT);