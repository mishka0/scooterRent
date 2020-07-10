-- -----------------------------------------------------
-- Data for table `rent`.`addition`
-- -----------------------------------------------------
START TRANSACTION;
USE `rent`;
INSERT INTO `rent`.`addition` (`user_id`, `first_name`, `last_name`, `phone`, `discount`, `balance`) VALUES (DEFAULT, 'Admin', 'Adminovich', '+375292224455', 100, 99999);
INSERT INTO `rent`.`addition` (`user_id`, `first_name`, `last_name`, `phone`, `discount`, `balance`) VALUES (DEFAULT, 'Moder', 'Moderovich', '+375331112233', 0, 0);
INSERT INTO `rent`.`addition` (`user_id`, `first_name`, `last_name`, `phone`, `discount`, `balance`) VALUES (DEFAULT, 'Ilya', 'Ivanov', '+375112233333', 0, 1000);
INSERT INTO `rent`.`addition` (`user_id`, `first_name`, `last_name`, `phone`, `discount`, `balance`) VALUES (DEFAULT, 'Michail', 'Sidorov', '+375665558833', 5, 2000);
INSERT INTO `rent`.`addition` (`user_id`, `first_name`, `last_name`, `phone`, `discount`, `balance`) VALUES (DEFAULT, 'Vasiliy', 'Voevodov', '+375001112233', 0, 500);
INSERT INTO `rent`.`addition` (`user_id`, `first_name`, `last_name`, `phone`, `discount`, `balance`) VALUES (DEFAULT, 'Kiz', 'Kizonov', '+358912392402', 0, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`history`
-- -----------------------------------------------------
START TRANSACTION;
USE `rent`;
INSERT INTO `rent`.`history` (`history_id`, `scooter_id`, `user_id`, `date_of_ride`, `time_ride`, `final_cost`, `is_subscription`) VALUES (DEFAULT, 1, 3, '2020:06:19', 600000000000, 45, 0);
INSERT INTO `rent`.`history` (`history_id`, `scooter_id`, `user_id`, `date_of_ride`, `time_ride`, `final_cost`, `is_subscription`) VALUES (DEFAULT, 2, 5, '2020:06:19', 90000000, 44, 0);
INSERT INTO `rent`.`history` (`history_id`, `scooter_id`, `user_id`, `date_of_ride`, `time_ride`, `final_cost`, `is_subscription`) VALUES (DEFAULT, 3, 4, '2020:06:19', 90000000, 0, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`rental_point`
-- -----------------------------------------------------
START TRANSACTION;
USE `rent`;
INSERT INTO `rent`.`rental_point` (`point_id`, `address`, `town_id`) VALUES (1, 'prospekt Kosmonavtov 2', 4);
INSERT INTO `rent`.`rental_point` (`point_id`, `address`, `town_id`) VALUES (2, 'prospekt Kleckova 18', 4);
INSERT INTO `rent`.`rental_point` (`point_id`, `address`, `town_id`) VALUES (3, 'vulica Shorsa 88', 4);
INSERT INTO `rent`.`rental_point` (`point_id`, `address`, `town_id`) VALUES (4, 'vylica Pobedi 22', 4);
INSERT INTO `rent`.`rental_point` (`point_id`, `address`, `town_id`) VALUES (DEFAULT, 'pereulok Fanipolskiy 53', 5);
INSERT INTO `rent`.`rental_point` (`point_id`, `address`, `town_id`) VALUES (DEFAULT, 'ulitsa mikhasya lynkova 87/2', 5);
INSERT INTO `rent`.`rental_point` (`point_id`, `address`, `town_id`) VALUES (DEFAULT, 'ul leonida bedy 34', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `rent`;
INSERT INTO `rent`.`role` (`role_id`, `role`) VALUES (DEFAULT, 'ADMIN');
INSERT INTO `rent`.`role` (`role_id`, `role`) VALUES (DEFAULT, 'USER');
INSERT INTO `rent`.`role` (`role_id`, `role`) VALUES (DEFAULT, 'MODER');

COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`scooter`
-- -----------------------------------------------------
START TRANSACTION;
USE `rent`;
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 1, 1);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 1, 2);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 1, 3);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 1, 4);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 1, 5);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 1, 6);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 5, 1);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 5, 1);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 5, 1);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 5, 1);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 5, 1);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 2, 1);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 2, 1);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 2, 1);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 2, 1);
INSERT INTO `rent`.`scooter` (`scooter_id`, `model`, `rental_point_id`, `status_id`) VALUES (DEFAULT, 'Kugoo S3', 2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`status_scooter`
-- -----------------------------------------------------
START TRANSACTION;
USE `rent`;
INSERT INTO `rent`.`status_scooter` (`name`, `status_id`) VALUES ('Stay', DEFAULT);
INSERT INTO `rent`.`status_scooter` (`name`, `status_id`) VALUES ('Broken', DEFAULT);
INSERT INTO `rent`.`status_scooter` (`name`, `status_id`) VALUES ('Need repair', DEFAULT);
INSERT INTO `rent`.`status_scooter` (`name`, `status_id`) VALUES ('Need charge', DEFAULT);
INSERT INTO `rent`.`status_scooter` (`name`, `status_id`) VALUES ('Ride', DEFAULT);
INSERT INTO `rent`.`status_scooter` (`name`, `status_id`) VALUES ('Reserve', DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`subscription`
-- -----------------------------------------------------
START TRANSACTION;
USE `rent`;
INSERT INTO `rent`.`subscription` (`subscription_id`, `user_id`, `time_left`, `subs_info_id`) VALUES (1, 3, 500000000000, 1);
INSERT INTO `rent`.`subscription` (`subscription_id`, `user_id`, `time_left`, `subs_info_id`) VALUES (2, 4, 600000000000, 1);
INSERT INTO `rent`.`subscription` (`subscription_id`, `user_id`, `time_left`, `subs_info_id`) VALUES (3, 5, 900000000000, 1);
INSERT INTO `rent`.`subscription` (`subscription_id`, `user_id`, `time_left`, `subs_info_id`) VALUES (4, 4, 900000000000, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`subscription_info`
-- -----------------------------------------------------
START TRANSACTION;
USE `rent`;
INSERT INTO `rent`.`subscription_info` (`subscription_id`, `name`, `cost`, `time`) VALUES (1, 'One hour subscription', 45, 3600000000000);
INSERT INTO `rent`.`subscription_info` (`subscription_id`, `name`, `cost`, `time`) VALUES (2, 'Five hour subscrtiption', 220, 18000000000000);
INSERT INTO `rent`.`subscription_info` (`subscription_id`, `name`, `cost`, `time`) VALUES (3, 'Ten hour subscription ', 400, 36000000000000);
INSERT INTO `rent`.`subscription_info` (`subscription_id`, `name`, `cost`, `time`) VALUES (4, 'One day subscription', 750, 86400000000000);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`tariff`
-- -----------------------------------------------------
START TRANSACTION;
USE `rent`;
INSERT INTO `rent`.`tariff` (`tariff_id`, `name`, `cost`, `duration`) VALUES (1, 'Minutes', 0.2, 60000000000);
INSERT INTO `rent`.`tariff` (`tariff_id`, `name`, `cost`, `duration`) VALUES (DEFAULT, '10 minutes', 18, 600000000000);
INSERT INTO `rent`.`tariff` (`tariff_id`, `name`, `cost`, `duration`) VALUES (DEFAULT, '1 hour', 50, 6000000000000);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`town`
-- -----------------------------------------------------
START TRANSACTION;
USE `rent`;
INSERT INTO `rent`.`town` (`town_id`, `name`) VALUES (1, 'Brest');
INSERT INTO `rent`.`town` (`town_id`, `name`) VALUES (2, 'Vitebsk');
INSERT INTO `rent`.`town` (`town_id`, `name`) VALUES (3, 'Gomel');
INSERT INTO `rent`.`town` (`town_id`, `name`) VALUES (4, 'Grodno');
INSERT INTO `rent`.`town` (`town_id`, `name`) VALUES (5, 'Minsk');
INSERT INTO `rent`.`town` (`town_id`, `name`) VALUES (6, 'Mogilev');

COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `rent`;
INSERT INTO `rent`.`user` (`user_id`, `login`, `password`) VALUES (DEFAULT, 'admin', '$2a$10$0L8lKyZ02zyJdQ3RNgXIhOJFs07oGSjz6ov4ba0nlrM6rWt9SSOSO');
INSERT INTO `rent`.`user` (`user_id`, `login`, `password`) VALUES (DEFAULT, 'moder', '$2a$10$Eaz1fwU/Rn7yMms1o9Ej2ubZ/E4lyv3zQmYysSxFSy983ZNMohh8.');
INSERT INTO `rent`.`user` (`user_id`, `login`, `password`) VALUES (DEFAULT, 'ilya777', '$2a$10$hGQPNwz.aFBsbH3GCOHcN.KZELVJ6QfQBog3bZ3C8/LPbZjtmX1W6');
INSERT INTO `rent`.`user` (`user_id`, `login`, `password`) VALUES (DEFAULT, 'mishka0', '$2a$10$Zo5Qqa0ADZDcqHHgJQZ37ek4Nr5fNwm/dW0kGEPzCgAFxuSUAKTZu');
INSERT INTO `rent`.`user` (`user_id`, `login`, `password`) VALUES (DEFAULT, 'vasya999', '$2a$10$5gHOSM50nH78rhEUZPgk0.rZ4WoVsvh/sgEmfqn6csG4FQFEww8hu');
INSERT INTO `rent`.`user` (`user_id`, `login`, `password`) VALUES (DEFAULT, 'kiz21', '$2a$10$1CwDDjS1UU3Z2GXJPaTWtOfqhyKkCrMLXOw0izlMeYIGeJ7L0Mg6G');

COMMIT;


-- -----------------------------------------------------
-- Data for table `rent`.`user_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `rent`;
INSERT INTO `rent`.`user_role` (`role_id`, `user_id`) VALUES (1, 1);
INSERT INTO `rent`.`user_role` (`role_id`, `user_id`) VALUES (3, 2);
INSERT INTO `rent`.`user_role` (`role_id`, `user_id`) VALUES (2, 3);
INSERT INTO `rent`.`user_role` (`role_id`, `user_id`) VALUES (2, 4);
INSERT INTO `rent`.`user_role` (`role_id`, `user_id`) VALUES (2, 5);
INSERT INTO `rent`.`user_role` (`role_id`, `user_id`) VALUES (2, 6);
INSERT INTO `rent`.`user_role` (`role_id`, `user_id`) VALUES (3, 1);

COMMIT;
