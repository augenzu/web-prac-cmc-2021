begin;


-- extra tables for types

create table if not exists app_type (
  app_type_id serial primary key,
  app_type_name varchar(30) unique not null check (app_type_name != '')
);

create table if not exists "status" (
  status_id serial primary key,
  status_name varchar(20) unique not null check (status_name != '')
);


-- main tables

create table if not exists good (
  good_id serial primary key,
  app_type_id int not null references app_type (app_type_id)
    on delete restrict
    on update cascade,
  good_name varchar(100) not null check (good_name != ''),
  good_price numeric not null check (good_price > 0),
  good_company varchar(50) check (good_company != ''),
  good_assembly_place varchar(50) check (good_assembly_place != ''),
  good_quantity int not null check (good_quantity >= 0) default 1,
  good_description text
);

create table if not exists "user" (
  user_id serial primary key,
  user_name varchar(70) not null check (user_name != ''),
  user_address varchar(50) not null check (user_address != ''),
  user_email varchar(30) not null check (user_email != ''),
  user_number varchar(20)
);

create table if not exists "order" (
  order_id serial primary key,
  user_id int not null references "user" (user_id)
    on delete cascade
    on update cascade,
  order_time timestamp not null,
  status_id int not null references "status" (status_id)
    on delete restrict
    on update cascade,
  order_delivery_address varchar(50) not null check (order_delivery_address != ''),
  order_delivery_date date
);

create table if not exists order_good (
  order_good_id serial primary key,
  order_id int not null references "order" (order_id)
    on delete cascade
    on update cascade,
  good_id int not null references good (good_id)
    on delete restrict
    on update cascade,
  order_good_quantity int not null check (order_good_quantity > 0),
  unique (order_id, good_id)
);


commit;
