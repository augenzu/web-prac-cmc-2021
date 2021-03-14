begin;


-- extra types

create type appliance_type as enum ( 
  'coffee_maker',
  'cooktop',
  'fridge',
  'microwave',
  'tv',
  'vacuum_cleaner',
  'washer'
);

-- order status
create type status_type as enum ( 
  'complete',
  'delivered',
  'processing'
);


-- tables

create table if not exists good (
  good_id serial primary key,
  good_type appliance_type not null,
  good_name varchar(100) not null check (good_name != ''),
  good_price numeric not null check (good_price > 0),
  good_company varchar(50) check (good_company != ''),
  good_assembly_place varchar(50) check (good_assembly_place != ''),
  good_quantity int not null check (good_quantity >= 0) default 1,
  good_characteristics jsonb,
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
  order_status status_type not null,
  order_delivery_address varchar(50) not null check (order_delivery_address != ''),
  order_delivery_time date
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
