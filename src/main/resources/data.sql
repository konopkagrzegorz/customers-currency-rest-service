DROP TABLE `customers`;

CREATE TABLE `customers` (
    `id` int(11)  not null AUTO_INCREMENT,
    `first_name` varchar(255) not null,
    `last_name` varchar(255)not null,
  PRIMARY KEY (`id`)
);
DROP TABLE `currency`;
CREATE TABLE `currency` (
    `id` int(11)  not null AUTO_INCREMENT,
    `name` varchar(255) not null,
PRIMARY KEY (`id`)
);


insert into `customers` (`id`, `first_name`, `last_name`) values (1, 'Jarrett', 'Scotchbourouge');
insert into `customers` (`id`, `first_name`, `last_name`) values (2, 'Kalila', 'Bernollet');
insert into `customers` (`id`, `first_name`, `last_name`) values (3, 'Shayna', 'Jeannenet');
insert into `customers` (`id`, `first_name`, `last_name`) values (4, 'Reba', 'Peteri');
insert into `customers` (`id`, `first_name`, `last_name`) values (5, 'Frazier', 'Berney');
insert into `customers` (`id`, `first_name`, `last_name`) values (6, 'Winnie', 'Concklin');
insert into `customers` (`id`, `first_name`, `last_name`) values (7, 'Eula', 'Olliver');
insert into `customers` (`id`, `first_name`, `last_name`) values (8, 'Mil', 'Antowski');
insert into `customers` (`id`, `first_name`, `last_name`) values (9, 'Haze', 'Burgum');
insert into `customers` (`id`, `first_name`, `last_name`) values (10, 'Alisander', 'Prenty');
insert into `customers` (`id`, `first_name`, `last_name`) values (11, 'Marys', 'Nuccii');
insert into `customers` (`id`, `first_name`, `last_name`) values (12, 'Ulberto', 'Cutbush');
insert into `customers` (`id`, `first_name`, `last_name`) values (13, 'Gus', 'Speke');
insert into `customers` (`id`, `first_name`, `last_name`) values (14, 'Joy', 'Wescott');
insert into `customers` (`id`, `first_name`, `last_name`) values (15, 'Phelia', 'Jeskin');
insert into `customers` (`id`, `first_name`, `last_name`) values (16, 'Reinwald', 'Mates');
insert into `customers` (`id`, `first_name`, `last_name`) values (17, 'Kippar', 'Jaulme');
insert into `customers` (`id`, `first_name`, `last_name`) values (18, 'Brett', 'Crone');
insert into `customers` (`id`, `first_name`, `last_name`) values (19, 'Fred', 'Leaney');
insert into `customers` (`id`, `first_name`, `last_name`) values (20, 'Chico', 'Yurocjhin');

insert into `currency` (`id`, `name`) values (1, 'UAH');
insert into `currency` (`id`, `name`) values (2, 'CAD');
insert into `currency` (`id`, `name`) values (3, 'PLN');
insert into `currency` (`id`, `name`) values (4, 'IDR');
insert into `currency` (`id`, `name`) values (5, 'VEF');
insert into `currency` (`id`, `name`) values (6, 'UAH');
insert into `currency` (`id`, `name`) values (7, 'MXN');
insert into `currency` (`id`, `name`) values (8, 'CNY');
insert into `currency` (`id`, `name`) values (9, 'VND');
insert into `currency` (`id`, `name`) values (10, 'VND');

