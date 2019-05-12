create table t_electric_data
(
  id             int auto_increment
    primary key,
  circuitId      int      null,
  electricityA    double   null,
  electricityB    double   null,
  electricityC    double   null,
  voltageA       double   null,
  voltageB       double   null,
  voltageC       double   null,
  activePower    double   null,
  reactivePower  double   null,
  apparentPower  double   null,
  powerFactor    double   null,
  electricEnergy double   null,
  time           datetime null
);

create table t_factory
(
  id          int auto_increment
    primary key,
  name        varchar(128)  null,
  description varchar(1024) null,
  status      int           null,
  constraint t_factory_name_uindex
  unique (name)
);

create table t_supply_cricuit
(
  id         int auto_increment
    primary key,
  workshopId int         null,
  name       varchar(64) null
);

create table t_user
(
  id       int auto_increment
    primary key,
  username varchar(64) not null,
  password varchar(64) not null,
  phone    varchar(12) null,
  email    varchar(64) null,
  status   int         null,
  name     varchar(64) null,
  constraint t_user_username_uindex
  unique (username)
);

create table t_workshop
(
  id          int auto_increment
    primary key,
  factoryId   int           null,
  name        varchar(128)  null,
  description varchar(1024) null,
  status      int           null
);

