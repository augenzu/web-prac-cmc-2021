-- TODOs:

-- FKs for goods!!!
--      do we really need this:
--      foreign key (good_id, good_type) references good (good_id, good_type)
--      or one field to one field (good_id only) is enough???
-- triggers (smth about goods)!!!
-- specify good_types as defaults for every specific good ('tv' for tv and so one) - DONE

-- checks, not nulls and other constraints - DONE?
-- add to FKs on delete, on update - DONE (except all the goods tables)
-- add extra fields to tv, fridge, ...

-- get ER with dbeaver - HALF-DONE

-- !!!
-- ALL THE GOOD TABLES - TO ONE, WITH JSONB AS <CHARACTERISTICS>
-- !!!


\c postgres

drop database if exists appliances;

create database appliances;

\connect appliances

set client_encoding = 'UTF8';
