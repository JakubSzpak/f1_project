create table race_details
(
    season          text,
    round           text,
    race_name       text,
    circuit_name    text,
    circuit_city    text,
    circuit_country text,
    race_date       text,
    race_time       text
);

alter table race_details
    owner to postgres;

create table race_results
(
    position         text,
    name             text,
    surname          text,
    constructor_name text,
    status           text,
    time             text
);

alter table race_results
    owner to postgres;

create table constructor_ranking
(
    position         text,
    constructor_name text,
    nationality      text,
    points           text,
    wins             text
);

alter table constructor_ranking
    owner to postgres;

