\c postgres

---------- create db ----------

drop database if exists appliances;

create database appliances;

\connect appliances

set client_encoding = 'UTF8';


---------- create tables ----------

begin;


-- extra types

create type appliance as enum ( 
    'coffee_maker',
    'cooktop',
    'fridge',
    'microwave',
    'tv',
    'vacuum_cleaner',
    'washer'
);

-- order status
create type status as enum ( 
    'complete',
    'delivered',
    'processing'
);


-- tables

create table if not exists good (
    good_id serial primary key,
    type appliance not null,
    name varchar(100) not null check (name != ''),
    price numeric not null check (price > 0),
    company varchar(50) check (company != ''),
    assembly_place varchar(50) check (assembly_place != ''),
    quantity int not null check (quantity >= 0) default 1,
    characteristics jsonb,
    description text
);

create table if not exists "user" (
    user_id serial primary key,
    name varchar(70) not null check (name != ''),
    address varchar(50) not null check (address != ''),
    email varchar(30) not null check (email != ''),
    number varchar(20)
);

create table if not exists "order" (
    order_id serial primary key,
    user_id int not null references "user" (user_id)
            on delete cascade
            on update cascade,
    ordering_time timestamp not null,
    current_status status not null,
    delivery_address varchar(50) not null check (delivery_address != ''),
    delivery_time date
);

create table if not exists order_good (
    order_id int not null references "order" (order_id)
            on delete cascade
            on update cascade,
    good_id int not null references good (good_id)
            on delete restrict
            on update cascade,
    quantity int not null check (quantity > 0),
    primary key (order_id, good_id)
);


commit;
