SET FOREIGN_KEY_CHECKS = 0; 

TRUNCATE TABLE `roomreservationdb`.`users_authorities`;
TRUNCATE TABLE `roomreservationdb`.`users`;
TRUNCATE TABLE `roomreservationdb`.`authorities`;
TRUNCATE TABLE `roomreservationdb`.`classrooms`;
TRUNCATE TABLE `roomreservationdb`.`buildings`;
TRUNCATE TABLE `roomreservationdb`.`semesters`;
TRUNCATE TABLE `roomreservationdb`.`subjects`;
TRUNCATE TABLE `roomreservationdb`.`statuses`;
TRUNCATE TABLE `roomreservationdb`.`messages`;
TRUNCATE TABLE `roomreservationdb`.`reservation_dates`;
TRUNCATE TABLE `roomreservationdb`.`reservations`;

INSERT INTO `roomreservationdb`.`statuses` (`id`, `message`, `name`) VALUES ('1', 'A foglalás elfogadva', 'ACCEPTED');
INSERT INTO `roomreservationdb`.`statuses` (`id`, `message`, `name`) VALUES ('2', 'A foglalás ellenőrzés alatt', 'PENDING');
INSERT INTO `roomreservationdb`.`statuses` (`id`, `message`, `name`) VALUES ('3', 'A foglalás elutasítva', 'DECLINED');

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

INSERT INTO `roomreservationdb`.`semesters` (`id`, `start_date`, `name`, `end_date`, `opened`) VALUES ('1', '2020-02-03', '2019-2020/2', '2020-09-02', false);
INSERT INTO `roomreservationdb`.`semesters` (`id`, `start_date`, `name`, `end_date`, `opened`) VALUES ('2', '2019-09-03', '2019-2020/1', '2020-02-02', true);
INSERT INTO `roomreservationdb`.`semesters` (`id`, `start_date`, `name`, `end_date`, `opened`) VALUES ('3', '2019-02-03', '2018-2019/2', '2019-09-02', true);
INSERT INTO `roomreservationdb`.`semesters` (`id`, `start_date`, `name`, `end_date`, `opened`) VALUES ('4', '2018-09-03', '2018-2019/1', '2019-02-02', true);
INSERT INTO `roomreservationdb`.`semesters` (`id`, `start_date`, `name`, `end_date`, `opened`) VALUES ('5', '2018-02-03', '2017-2018/2', '2018-09-02', false);
INSERT INTO `roomreservationdb`.`semesters` (`id`, `start_date`, `name`, `end_date`, `opened`) VALUES ('6', '2017-09-03', '2017-2018/1', '2018-02-02', false);
INSERT INTO `roomreservationdb`.`semesters` (`id`, `start_date`, `name`, `end_date`, `opened`) VALUES ('7', '2017-02-03', '2016-2017/2', '2017-09-02', false);
INSERT INTO `roomreservationdb`.`semesters` (`id`, `start_date`, `name`, `end_date`, `opened`) VALUES ('8', '2016-09-03', '2016-2017/1', '2017-02-02', false);
INSERT INTO `roomreservationdb`.`semesters` (`id`, `start_date`, `name`, `end_date`, `opened`) VALUES ('9', '2016-02-03', '2015-2016/2', '2016-09-02', false);

INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('1', 'IP-08cAN1E', 'Analízis 1 EA');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('2', 'IP-08cAN1G', 'Analízis 1 Gyakorlat');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('3', 'IP-08cAN2E', 'Analízis 2 EA');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('4', 'IP-08cAN2G', 'Analízis 2 Gyakorlat');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('5', 'IP-08DM1E', 'Diszkrét Matematika 1 EA');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('6', 'IP-08DM1G', 'Diszkrét Matematika 1 Gyakorlat');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('7', 'IP-08cAB1E', 'Adatbázisok 1 EA');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('8', 'IP-08cAB1G', 'Adatbázisok 1 Gyakorlat');
INSERT INTO `roomreservationdb`.`subjects` (`id`, `code`, `name`) VALUES ('9', 'IP-08cALKG', 'Alkalmazások fejlesztése');
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

INSERT INTO `roomreservationdb`.`users` (`id`, `email`, `name`, `password`, `username`) VALUES ('1', 'testing1@testing.com',	'Teszt Gábor', 'itsOnlyATest', 'tesztGabi');
INSERT INTO `roomreservationdb`.`users` (`id`, `email`, `name`, `password`, `username`) VALUES ('2', 'testing2@testing.com',	'Teszt János', 'itsOnlyATest', 'tesztJani');
INSERT INTO `roomreservationdb`.`users` (`id`, `email`, `name`, `password`, `username`) VALUES ('3', 'admin@admin.com',	'admin', '$2a$10$FDir/QmrywMY6j1pvbKxau4rph7zWYkviwqvoRI4S1aja3ktxjiau', 'admin');

INSERT INTO `roomreservationdb`.`users_authorities` (`user_id`, `authority_id`) VALUES (1, 1);
INSERT INTO `roomreservationdb`.`users_authorities` (`user_id`, `authority_id`) VALUES (2, 1);
INSERT INTO `roomreservationdb`.`users_authorities` (`user_id`, `authority_id`) VALUES (3, 1);
INSERT INTO `roomreservationdb`.`users_authorities` (`user_id`, `authority_id`) VALUES (3, 2);

INSERT INTO `roomreservationdb`.`reservations` (`reservation_type`, `id`, `note`, `name`, `classroom`, `status`, `user`, `semester`, `subject`) VALUES ('CLASS',1,'Analízis 1 EA foglalás','',13,1,1,4,1);
INSERT INTO `roomreservationdb`.`reservations` (`reservation_type`, `id`, `note`, `name`, `classroom`, `status`, `user`, `semester`, `subject`) VALUES ('CLASS',2,'Analízis 2 Gyakorlat','',17,2,2,4,4);
INSERT INTO `roomreservationdb`.`reservations` (`reservation_type`, `id`, `note`, `name`, `classroom`, `status`, `user`, `semester`, `subject`) VALUES ('CLASS',3,'Kémbiz','',7,3,2,3,10);
INSERT INTO `roomreservationdb`.`reservations` (`reservation_type`, `id`, `note`, `name`, `classroom`, `status`, `user`, `semester`, `subject`) VALUES ('EVENT',4,'Ez egy teszt esemény','BSc Záróvizsga',2,1,1,null, null);
INSERT INTO `roomreservationdb`.`reservations` (`reservation_type`, `id`, `note`, `name`, `classroom`, `status`, `user`, `semester`, `subject`) VALUES ('EVENT',5,'Ez egy teszt esemény','Diplomaosztó',5,2,2,null, null);
INSERT INTO `roomreservationdb`.`reservations` (`reservation_type`, `id`, `note`, `name`, `classroom`, `status`, `user`, `semester`, `subject`) VALUES ('EVENT',6,'Ez egy teszt esemény','Vendégelőadás',14,3,1,null, null);

INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (1,'2018-10-01 11:45:00','2018-10-01 10:15:00',1);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (2,'2018-10-02 21:00:00','2018-10-02 19:45:00',2);

INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (3,'2018-02-09 14:00:00','2018-02-09 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (4,'2018-02-16 14:00:00','2018-02-16 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (5,'2018-02-23 14:00:00','2018-02-23 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (6,'2018-03-02 14:00:00','2018-03-02 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (7,'2018-03-09 14:00:00','2018-03-09 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (8,'2018-03-16 14:00:00','2018-03-16 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (9,'2018-03-23 14:00:00','2018-03-23 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (10,'2018-03-30 14:00:00','2018-03-30 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (11,'2018-04-06 14:00:00','2018-04-06 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (12,'2018-04-13 14:00:00','2018-04-13 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (13,'2018-04-20 14:00:00','2018-04-20 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (14,'2018-04-27 14:00:00','2018-04-27 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (15,'2018-05-04 14:00:00','2018-05-04 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (16,'2018-05-11 14:00:00','2018-05-11 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (17,'2018-05-18 14:00:00','2018-05-18 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (18,'2018-05-25 14:00:00','2018-05-25 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (19,'2018-06-01 14:00:00','2018-06-01 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (20,'2018-06-08 14:00:00','2018-06-08 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (21,'2018-06-15 14:00:00','2018-06-15 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (22,'2018-06-22 14:00:00','2018-06-22 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (23,'2018-06-29 14:00:00','2018-06-29 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (24,'2018-07-06 14:00:00','2018-07-06 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (25,'2018-07-13 14:00:00','2018-07-13 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (26,'2018-07-20 14:00:00','2018-07-20 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (27,'2018-07-27 14:00:00','2018-07-27 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (28,'2018-08-03 14:00:00','2018-08-03 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (29,'2018-08-10 14:00:00','2018-08-10 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (30,'2018-08-17 14:00:00','2018-08-17 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (31,'2018-08-24 14:00:00','2018-08-24 10:15:00',3);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (32,'2018-08-31 14:00:00','2018-08-31 10:15:00',3);

INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (33,'2019-01-04 16:30:00','2019-01-04 08:30:00',4);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (34,'2019-01-25 14:00:00','2019-01-25 12:00:00',5);
INSERT INTO `roomreservationdb`.`reservation_dates` (`id`, `end`, `start`, `reservation`) VALUES (35,'2019-01-25 13:00:00','2019-01-25 12:00:00',6);

INSERT INTO `roomreservationdb`.`messages` (`id`, `message`, `unread`, `recipient`, `sender`) VALUES (1,'Ez egy teszt üzenet Gábortól Jánosnak',true,1,2);
INSERT INTO `roomreservationdb`.`messages` (`id`, `message`, `unread`, `recipient`, `sender`) VALUES (2,'Ez egy teszt üzenet Jánostól Gábornak',false,2,1);
INSERT INTO `roomreservationdb`.`messages` (`id`, `message`, `unread`, `recipient`, `sender`) VALUES (3,'Ez egy teszt rendszerüzenet',false,1,null);
INSERT INTO `roomreservationdb`.`messages` (`id`, `message`, `unread`, `recipient`, `sender`) VALUES (4,'Ez egy teszt üzenet Jánostól az adminisztrátor számára',false,3,2);

SET FOREIGN_KEY_CHECKS = 1; 
