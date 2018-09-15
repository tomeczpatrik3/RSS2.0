SET FOREIGN_KEY_CHECKS = 0; 

TRUNCATE TABLE `roomreservationdb`.`users_authorities`;
TRUNCATE TABLE `roomreservationdb`.`users`;
TRUNCATE TABLE `roomreservationdb`.`authorities`;
TRUNCATE TABLE `roomreservationdb`.`classrooms`;
TRUNCATE TABLE `roomreservationdb`.`buildings`;
TRUNCATE TABLE `roomreservationdb`.`semesters`;
TRUNCATE TABLE `roomreservationdb`.`subjects`;

INSERT INTO `roomreservationdb`.`authorities` (`id`, `name`) VALUES ('1', 'ROLE_USER');
INSERT INTO `roomreservationdb`.`authorities` (`id`, `name`) VALUES ('2', 'ROLE_ADMIN');
INSERT INTO `roomreservationdb`.`authorities` (`id`, `name`) VALUES ('3', 'ROLE_GUEST');

INSERT INTO `roomreservationdb`.`buildings` (`id`, `name`) VALUES ('1', 'ÉSZAKI');
INSERT INTO `roomreservationdb`.`buildings` (`id`, `name`) VALUES ('2', 'DÉLI');

INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('1', '300', 0, 1, 'Bolyai János előadó', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('2', '250', 0, 1, 'Mogyoródi József előadó', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('3', '250', 0, 1, 'Kitaibel Pál előadó', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('4', '200', 0, 1, 'Szabó József előadó', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('5', '200', 0, 1, 'Lóczy Lajos előadó', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('6', '200', 0, 1, 'Fejér Lipót előadó', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('7', '20', 0, 1, 'Erdos Pál terem', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('8', '30', 0, 1, 'Kárteszi Ferenc terem', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('9', '25', 0, 1, 'Turán Pál terem', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('10', '15', 0, 1, 'Sárfalvi Béla terem', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('11', '25', 0, 1, 'Gallai Tibor terem', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('12', '30', 0, 1, 'Konig terem', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('13', '50', 0, 1, 'Jánossy Lajos terem', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('14', '150', 0, 1, 'Ortvay terem', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('15', '250', 0, 1, 'Eötvös terem', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('16', '40', 0, 1, 'Marx terem', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('17', '35', 0, 1, 'Jedlik Ányos terem', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('18', '40', 0, 1, 'Ruff terem', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('19', '50', 0, 1, 'Kajtár terem', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('20', '150', 0, 1, 'Gróh terem', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('21', '150', 0, 1, 'Bruckner terem', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('22', '200', 0, 1, 'Than Károly terem', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('23', '30', 1, 1, '3.124', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('24', '20', 1, 1, '7.102', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('25', '30', 1, 1, '7.13 Sulinet labor', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('26', '40', 1, 1, '7.33', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('27', '25', 1, 1, '7.55', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('28', '35', 1, 1, '7.57', 1);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('29', '30', 1, 1, '00-411 (PC 7)', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('30', '40', 1, 1, '00-412 (PC 6)', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('31', '35', 1, 1, '00-725', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('32', '45', 1, 1, '00-803 Programozási Nyelvi labor', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('33', '25', 1, 1, '00-803/2 Programozási Nyelvi Labor/2', 2);
INSERT INTO `roomreservationdb`.`classrooms` (`id`, `chairs`, `has_pc`, `has_projector`, `name`, `building`) VALUES ('34', '100', 1, 1, '00-807 Adatbázis labor', 2);

INSERT INTO `roomreservationdb`.`semesters` (`id`, `end_date`, `name`, `start_date`) VALUES ('1', '2017-09-03 00:00:00', '2017-2018/1', '2018-02-02 23:59:59');
INSERT INTO `roomreservationdb`.`semesters` (`id`, `end_date`, `name`, `start_date`) VALUES ('2', '2018-02-03 00:00:00', '2017-2018/2', '2018-09-02 23:59:59');
INSERT INTO `roomreservationdb`.`semesters` (`id`, `end_date`, `name`, `start_date`) VALUES ('3', '2017-02-03 00:00:00', '2016-2017/2', '2017-09-02 23:59:59');
INSERT INTO `roomreservationdb`.`semesters` (`id`, `end_date`, `name`, `start_date`) VALUES ('4', '2016-09-03 00:00:00', '2016-2017/1', '2017-02-02 23:59:59');
INSERT INTO `roomreservationdb`.`semesters` (`id`, `end_date`, `name`, `start_date`) VALUES ('5', '2016-02-03 00:00:00', '2015-2016/2', '2016-09-02 23:59:59');

INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('1', 'IP-08cAN1E ', 'Analízis 1 EA');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('2', 'IP-08cAN1G', 'Analízis 1 Gyakorlat');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('3', 'IP-08cAN2E', 'Analízis 2 EA');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('4', 'IP-08cAN2G', 'Analízis 2 Gyakorlat');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('5', 'IP-08DM1E ', 'Diszkrét Matematika 1 EA');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('6', 'IP-08DM1G', 'Diszkrét Matematika 1 Gyakorlat');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('7', 'IP-08cAB1E ', 'Adatbázisok 1 EA');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('8', 'IP-08cAB1G', 'Adatbázisok 1 Gyakorlat');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('9', 'IP-08cALKG ', 'Alkalmazások fejlesztése');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('10', 'kembiztk17ea', 'Kémiai biztonságtechnika');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('11', 'uzemlatk17za', 'Üzemlátogatás');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('12', 'szvetlen1k17ea', 'Szervetlen kémia (1)');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('13', 'altkemk17ea', 'Általános kémia');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('14', 'altkemszamk17ga', 'Általános kémia szám.gyak.');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('15', 'kalkfm17ea', 'Kalkulus');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('16', 'vektorf17ea', 'Vektorszámítás');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('17', 'szamalapf18la', 'Számítógépes alapismeretek');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('18', 'fiznum1f18la', 'A fizika numerikus módszerei I.');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('19', 'progalapf17va', 'Programozási alapismeretek');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('20', 'evobiob17ea', 'Evolúcióbiológia EA');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('21', 'biogeob17ea', 'Biogeográfia EA');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('22', 'etolo1b17ea', 'Etológia 1 EA');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('23', 'mikrobb18ea', 'Mikrobiológia EA');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('24', 'mikrobb18la', 'Mikrobiológiai gyakorlat GY');


/*
-- admin - admin
INSERT INTO `roomreservationdb`.`users` (`id`, `email`, `name`, `password`, `username`) VALUES ('1', 'admin@admin.com',	'ADMIN', '$2a$10$I2RfKrz8AonAKpxm.8OWS.AWnIg7iwnay81awm3AB0lotLlDOXMZm', 'admin');

INSERT INTO `roomreservationdb`.`users_authorities` (`authority_id`, `user_id`) VALUES ('1', '1');
INSERT INTO `roomreservationdb`.`users_authorities` (`authority_id`, `user_id`) VALUES ('2', '1');
INSERT INTO `roomreservationdb`.`users_authorities` (`authority_id`, `user_id`) VALUES ('3', '1');
*/

SET FOREIGN_KEY_CHECKS = 1; 
