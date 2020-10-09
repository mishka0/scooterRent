-- -----------------------------------------------------
-- Data for table `rent`.`addition`
-- -----------------------------------------------------
BEGIN;
ALTER TABLE rent.public.addition DISABLE TRIGGER ALL;
INSERT INTO rent.public.addition (user_id, first_name, last_name, phone, discount, balance) VALUES
(1, 'Admin', 'Adminovich', '+375292224455', 100, 99999),
(2, 'Moder', 'Moderovich', '+375331112233', 0, 0),
(3, 'Ilya', 'Ivanov', '+375112233333', 0, 1000),
(4, 'Michail', 'Sidorov', '+375665558833', 5, 2000),
(5, 'Vasiliy', 'Voevodov', '+375001112233', 0, 500),
(6, 'Kiz', 'Kizonov', '+358912392402', 0, 0);
ALTER TABLE rent.public.addition ENABLE TRIGGER ALL;
COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`history`
-- -----------------------------------------------------
BEGIN;
ALTER TABLE rent.public.history DISABLE TRIGGER ALL ;
INSERT INTO rent.public.history (history_id, scooter_id, user_id, tariff_id, subscription_id, date_of_ride, final_cost, time_start, time_end, is_closed) VALUES
(1, 1, 3, NULL, NULL, '2020-06-19', 45, '13:00:00', '14:00:00', '1'),
(2, 2, 5, NULL, NULL, '2020-06-19', 44, '12:00:00', '12:45:00', '1'),
(3, 3, 4, NULL, 1, '2020-06-19', 0, '15:00:00', '15:10:00', '1');
ALTER TABLE rent.public.history ENABLE TRIGGER ALL;
COMMIT;



-- -----------------------------------------------------
-- Data for table `rent`.`rental_point`
-- -----------------------------------------------------
BEGIN;
ALTER TABLE rent.public.rental_point DISABLE TRIGGER ALL ;
INSERT INTO rent.public.rental_point (point_id, address, town_id) VALUES (1, 'prospekt Kosmonavtov 2', 4),
(2, 'prospekt Kleckova 18', 4),
(3, 'vulica Shorsa 88', 4),
(4, 'vylica Pobedi 22', 4),
(5, 'pereulok Fanipolskiy 53', 5),
(6, 'ulitsa mikhasya lynkova 87/2', 5),
(7, 'ul leonida bedy 34', 5);
ALTER TABLE rent.public.rental_point ENABLE TRIGGER ALL;
COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`role`
-- -----------------------------------------------------
BEGIN;
ALTER TABLE rent.public.role DISABLE TRIGGER ALL ;
INSERT INTO rent.public.role (role_id, role) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'MODER');
ALTER TABLE rent.public.role ENABLE TRIGGER ALL;
COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`scooter`
-- -----------------------------------------------------
BEGIN;
ALTER TABLE rent.public.scooter DISABLE TRIGGER ALL;
INSERT INTO rent.public.scooter (scooter_id, model, rental_point_id, status_id) VALUES
(1, 'Kugoo S3', 1, 1),
(2, 'Kugoo S3', 1, 2),
(3, 'Kugoo S3', 1, 3),
(4, 'Kugoo S3', 1, 4),
(5, 'Kugoo S3', 1, 5),
(6, 'Kugoo S3', 1, 6),
(7, 'Kugoo S3', 5, 1),
(8, 'Kugoo S3', 5, 1),
(9, 'Kugoo S3', 5, 1),
(10, 'Kugoo S3', 5, 1),
(11, 'Kugoo S3', 5, 1),
(12, 'Kugoo S3', 2, 1),
(13, 'Kugoo S3', 2, 1),
(14, 'Kugoo S3', 2, 1),
(15, 'Kugoo S3', 2, 1),
(16, 'Kugoo S3', 2, 3);
ALTER TABLE rent.public.scooter ENABLE TRIGGER ALL;
COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`status_scooter`
-- -----------------------------------------------------
BEGIN;
ALTER TABLE rent.public.status_scooter DISABLE TRIGGER ALL;
INSERT INTO rent.public.status_scooter (name, status_id) VALUES
('Stay', 1),
('Broken', 2),
('Need repair', 3),
('Need charge', 4),
('Ride', 5),
('Reserve', 6);
ALTER TABLE rent.public.status_scooter ENABLE TRIGGER ALL;
COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`subscription`
-- -----------------------------------------------------
BEGIN;
ALTER TABLE rent.public.subscription DISABLE TRIGGER ALL;
INSERT INTO rent.public.subscription (subscription_id, user_id, time_left, subs_info_id) VALUES
(1, 3, 500000000000, 1),
(2, 4, 600000000000, 1),
(3, 5, 900000000000, 1),
(4, 4, 900000000000, 4);
ALTER TABLE rent.public.subscription ENABLE TRIGGER ALL;
COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`subscription_info`
-- -----------------------------------------------------
BEGIN;
ALTER TABLE rent.public.subscription_info DISABLE TRIGGER ALL;
INSERT INTO rent.public.subscription_info (subscription_id, name, cost, time) VALUES
(1, 'One hour subscription', 45, 3600000000000),
(2, 'Five hour subscrtiption', 220, 18000000000000),
(3, 'Ten hour subscription ', 400, 36000000000000),
(4, 'One day subscription', 750, 86400000000000);
ALTER TABLE rent.public.subscription_info ENABLE TRIGGER ALL;
COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`tariff`
-- -----------------------------------------------------
BEGIN;
ALTER TABLE rent.public.subscription_info DISABLE TRIGGER ALL;
INSERT INTO rent.public.tariff (tariff_id, name, cost, duration) VALUES (1, 'Minutes', 0.2, 60000000000),
(2, '10 minutes', 18, 600000000000),
(3, '1 hour', 50, 6000000000000);
ALTER TABLE rent.public.subscription_info ENABLE TRIGGER ALL;
COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`town`
-- -----------------------------------------------------
BEGIN;
ALTER TABLE rent.public.town DISABLE TRIGGER ALL;
INSERT INTO rent.public.town (town_id, name) VALUES
(1, 'Brest'),
(2, 'Vitebsk'),
(3, 'Gomel'),
(4, 'Grodno'),
(5, 'Minsk'),
(6, 'Mogilev'),
(7, 'Moscow');
ALTER TABLE rent.public.town ENABLE TRIGGER ALL;
COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`user`
-- -----------------------------------------------------
BEGIN;
ALTER TABLE rent.public.user DISABLE TRIGGER ALL;
INSERT INTO rent.public.user (user_id, login, password) VALUES
(1, 'admin', '$2a$10$0L8lKyZ02zyJdQ3RNgXIhOJFs07oGSjz6ov4ba0nlrM6rWt9SSOSO'),
(2, 'moder', '$2a$10$Eaz1fwU/Rn7yMms1o9Ej2ubZ/E4lyv3zQmYysSxFSy983ZNMohh8.'),
(3, 'ilya777', '$2a$10$hGQPNwz.aFBsbH3GCOHcN.KZELVJ6QfQBog3bZ3C8/LPbZjtmX1W6'),
(4, 'mishka0', '$2a$10$Zo5Qqa0ADZDcqHHgJQZ37ek4Nr5fNwm/dW0kGEPzCgAFxuSUAKTZu'),
(5, 'vasya999', '$2a$10$5gHOSM50nH78rhEUZPgk0.rZ4WoVsvh/sgEmfqn6csG4FQFEww8hu'),
(6, 'kiz21', '$2a$10$1CwDDjS1UU3Z2GXJPaTWtOfqhyKkCrMLXOw0izlMeYIGeJ7L0Mg6G');
ALTER TABLE rent.public.user ENABLE TRIGGER ALL;
COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`user_role`
-- -----------------------------------------------------
BEGIN;
ALTER TABLE rent.public.user_role DISABLE TRIGGER ALL;
INSERT INTO rent.public.user_role (role_id, user_id) VALUES (1, 1),
(3, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(3, 1);
ALTER TABLE rent.public.user_role ENABLE TRIGGER ALL;
COMMIT;