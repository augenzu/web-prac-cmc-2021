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


-- common table to store all the good_ids
create table if not exists good (
    good_id int primary key,
    good_type appliance not null,
    unique (good_id, good_type)  -- need this to be able to have FK referencing to this pair
);


-- empty table to inherit from
create table if not exists good_prototype (
    good_id serial not null,
    good_type appliance not null,
    price numeric not null check (price > 0),
    company varchar(50) check (company != ''),
    assembly_place varchar(50) check (assembly_place != ''),
    quantity int not null check (quantity >= 0) default 1
);


-- tables for specific goods

create table if not exists coffee_maker (
    primary key (good_id),
    check (good_type = 'coffee_maker'),
    foreign key (good_id, good_type) references good (good_id, good_type)
) inherits (good_prototype);

alter table coffee_maker alter column good_type set default 'coffee_maker';

create table if not exists cooktop (
    primary key (good_id),
    check (good_type = 'cooktop'),
    foreign key (good_id, good_type) references good (good_id, good_type)
) inherits (good_prototype);

alter table cooktop alter column good_type set default 'cooktop';

create table if not exists fridge (
    primary key (good_id),
    check (good_type = 'fridge'),
    foreign key (good_id, good_type) references good (good_id, good_type)
) inherits (good_prototype);

alter table fridge alter column good_type set default 'fridge';

create table if not exists microwave (
    primary key (good_id),
    check (good_type = 'microwave'),
    foreign key (good_id, good_type) references good (good_id, good_type)
) inherits (good_prototype);

alter table microwave alter column good_type set default 'microwave';

create table if not exists tv (
    primary key (good_id),
    check (good_type = 'tv'),
    foreign key (good_id, good_type) references good (good_id, good_type)
) inherits (good_prototype);

alter table tv alter column good_type set default 'tv';

create table if not exists vacuum_cleaner (
    primary key (good_id),
    check (good_type = 'vacuum_cleaner'),
    foreign key (good_id, good_type) references good (good_id, good_type)
) inherits (good_prototype);

alter table vacuum_cleaner alter column good_type set default 'vacuum_cleaner';

create table if not exists washer (
    primary key (good_id),
    check (good_type = 'washer'),
    foreign key (good_id, good_type) references good (good_id, good_type)
) inherits (good_prototype);

alter table washer alter column good_type set default 'washer';


-- tables for users and orders

create table if not exists "user" (
    user_id serial primary key,
    user_name varchar(50) not null check (user_name != ''),
    address varchar(50) not null check (address != ''),
    email varchar(30) not null check (email != ''),
    number char(11)
);

create table if not exists "order" (
    order_id serial primary key,
    user_id int not null references "user" (user_id)
            on delete cascade
            on update cascade,
    ordering_time timestamp not null,
    current_status status not null,
    delivery_address varchar(50) not null check (delivery_address != ''),
    delivery_time timestamp
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