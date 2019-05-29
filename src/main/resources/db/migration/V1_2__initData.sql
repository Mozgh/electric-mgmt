insert into t_user(id, username, name, password, status) value (1, "admin", "管理员", "admin", 0);

insert into t_factory(id, name, description, status) value (1, "太原山水", "太原山水", 0);

insert into t_workshop(id, factoryId, name, description, status) value (1, 1, "车间1", "车间1", 0);
insert into t_supply_cricuit(id, workshopId, name) value (1, 1, "3#");