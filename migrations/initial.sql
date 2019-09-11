CREATE DATABASE comics_world_dev;

create table album
(
    id             serial
        constraint album_pk
            primary key,
    title          varchar,
    lead_character varchar,
    lang           varchar,
    publisher      varchar,
    pages          int,
    published_on   date,
    country        varchar

);

