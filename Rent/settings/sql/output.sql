-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: rent
-- ------------------------------------------------------
-- Server version	5.7.31
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO,POSTGRESQL' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table "addition"
--

DROP TABLE IF EXISTS "addition";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE addition (
  user_id integer NOT NULL,
  "first_name" varchar(30) DEFAULT NULL,
  "last_name" varchar(30) DEFAULT NULL,
  "phone" varchar(15) DEFAULT NULL,
  "discount" int DEFAULT '0',
  "balance" double DEFAULT '0',
  PRIMARY KEY ("user_id"),
  CONSTRAINT "fk_addition_user1" FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE CASCADE ON UPDATE NO ACTION
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "addition"
--

LOCK TABLES "addition" WRITE;
/*!40000 ALTER TABLE "addition" DISABLE KEYS */;
INSERT INTO "addition" VALUES (1,'Admin','Adminovich','+375292224455',100,99999),(2,'Moder','Moderovich','+375331112233',0,0),(3,'Ilya','Ivanov','+375112233333',0,1000),(4,'Michail','Sidorov','+375665558833',5,2000),(5,'Vasiliy','Voevodov','+375001112233',0,500),(6,'Kiz','Kizonov','+358912392402',0,0);
/*!40000 ALTER TABLE "addition" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "history"
--

DROP TABLE IF EXISTS "history";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "history" (
  "history_id" int(11) NOT NULL,
  "scooter_id" int(11) NOT NULL,
  "user_id" int(11) NOT NULL,
  "tariff_id" int(11) DEFAULT NULL,
  "subscription_id" int(11) DEFAULT NULL,
  "date_of_ride" date NOT NULL,
  "final_cost" double DEFAULT NULL,
  "time_start" time NOT NULL,
  "time_end" time DEFAULT NULL,
  "is_closed" tinyint(1) NOT NULL,
  PRIMARY KEY ("history_id"),
  KEY "fk_history_scooter1_idx" ("scooter_id"),
  KEY "fk_history_user1_idx" ("user_id"),
  KEY "fk_history_tariff_idx" ("tariff_id"),
  KEY "fk_history_subscription_idx" ("subscription_id"),
  CONSTRAINT "fk_history_scooter1" FOREIGN KEY ("scooter_id") REFERENCES "scooter" ("scooter_id") ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT "fk_history_subscription" FOREIGN KEY ("subscription_id") REFERENCES "subscription" ("subscription_id") ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT "fk_history_tariff" FOREIGN KEY ("tariff_id") REFERENCES "tariff" ("tariff_id") ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT "fk_history_user1" FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE NO ACTION ON UPDATE NO ACTION
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "history"
--

LOCK TABLES "history" WRITE;
/*!40000 ALTER TABLE "history" DISABLE KEYS */;
INSERT INTO "history" VALUES (1,1,3,NULL,NULL,'2020-06-19',45,'13:00:00','14:00:00',1),(2,2,5,NULL,NULL,'2020-06-19',44,'12:00:00','12:45:00',1),(3,3,4,NULL,1,'2020-06-19',0,'15:00:00','15:10:00',1);
/*!40000 ALTER TABLE "history" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "rental_point"
--

DROP TABLE IF EXISTS "rental_point";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "rental_point" (
  "point_id" int(11) NOT NULL,
  "address" varchar(45) NOT NULL,
  "town_id" int(11) DEFAULT NULL,
  PRIMARY KEY ("point_id"),
  KEY "fk_rental_point_town1_idx" ("town_id"),
  CONSTRAINT "fk_rental_point_town1" FOREIGN KEY ("town_id") REFERENCES "town" ("town_id") ON DELETE CASCADE ON UPDATE NO ACTION
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "rental_point"
--

LOCK TABLES "rental_point" WRITE;
/*!40000 ALTER TABLE "rental_point" DISABLE KEYS */;
INSERT INTO "rental_point" VALUES (1,'prospekt Kosmonavtov 2',4),(2,'prospekt Kleckova 18',4),(3,'vulica Shorsa 88',4),(4,'vylica Pobedi 22',4),(5,'pereulok Fanipolskiy 53',5),(6,'ulitsa mikhasya lynkova 87/2',5),(7,'ul leonida bedy 34',5);
/*!40000 ALTER TABLE "rental_point" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "role"
--

DROP TABLE IF EXISTS "role";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "role" (
  "role_id" int(11) NOT NULL,
  "role" varchar(10) NOT NULL,
  PRIMARY KEY ("role_id")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "role"
--

LOCK TABLES "role" WRITE;
/*!40000 ALTER TABLE "role" DISABLE KEYS */;
INSERT INTO "role" VALUES (1,'ADMIN'),(2,'USER'),(3,'MODER');
/*!40000 ALTER TABLE "role" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "scooter"
--

DROP TABLE IF EXISTS "scooter";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "scooter" (
  "scooter_id" int(11) NOT NULL,
  "model" varchar(45) NOT NULL,
  "rental_point_id" int(11) DEFAULT NULL,
  "status_id" int(11) DEFAULT NULL,
  PRIMARY KEY ("scooter_id"),
  KEY "status_fk_idx" ("status_id"),
  KEY "fk_scooter_rental_point1_idx" ("rental_point_id"),
  CONSTRAINT "fk_scooter_rental_point1" FOREIGN KEY ("rental_point_id") REFERENCES "rental_point" ("point_id") ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT "status_fk" FOREIGN KEY ("status_id") REFERENCES "status_scooter" ("status_id") ON DELETE SET NULL ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "scooter"
--

LOCK TABLES "scooter" WRITE;
/*!40000 ALTER TABLE "scooter" DISABLE KEYS */;
INSERT INTO "scooter" VALUES (1,'Kugoo S3',1,1),(2,'Kugoo S3',1,2),(3,'Kugoo S3',1,3),(4,'Kugoo S3',1,4),(5,'Kugoo S3',1,5),(6,'Kugoo S3',1,6),(7,'Kugoo S3',5,1),(8,'Kugoo S3',5,1),(9,'Kugoo S3',5,1),(10,'Kugoo S3',5,1),(11,'Kugoo S3',5,1),(12,'Kugoo S3',2,1),(13,'Kugoo S3',2,1),(14,'Kugoo S3',2,1),(15,'Kugoo S3',2,1),(16,'Kugoo S3',2,3);
/*!40000 ALTER TABLE "scooter" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "status_scooter"
--

DROP TABLE IF EXISTS "status_scooter";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "status_scooter" (
  "name" varchar(45) NOT NULL,
  "status_id" int(11) NOT NULL,
  PRIMARY KEY ("status_id")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "status_scooter"
--

LOCK TABLES "status_scooter" WRITE;
/*!40000 ALTER TABLE "status_scooter" DISABLE KEYS */;
INSERT INTO "status_scooter" VALUES ('Stay',1),('Broken',2),('Need repair',3),('Need charge',4),('Ride',5),('Reserve',6);
/*!40000 ALTER TABLE "status_scooter" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "subscription"
--

DROP TABLE IF EXISTS "subscription";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "subscription" (
  "subscription_id" int(11) NOT NULL,
  "user_id" int(11) NOT NULL,
  "time_left" bigint(20) NOT NULL,
  "subs_info_id" int(11) NOT NULL,
  PRIMARY KEY ("subscription_id"),
  KEY "fk_subscription_info1_idx" ("subs_info_id"),
  KEY "fk_subscription_user1" ("user_id"),
  CONSTRAINT "fk_subscription_info1" FOREIGN KEY ("subs_info_id") REFERENCES "subscription_info" ("subscription_id") ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT "fk_subscription_user1" FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE CASCADE ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "subscription"
--

LOCK TABLES "subscription" WRITE;
/*!40000 ALTER TABLE "subscription" DISABLE KEYS */;
INSERT INTO "subscription" VALUES (1,3,500000000000,1),(2,4,600000000000,1),(3,5,900000000000,1),(4,4,900000000000,4);
/*!40000 ALTER TABLE "subscription" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "subscription_info"
--

DROP TABLE IF EXISTS "subscription_info";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "subscription_info" (
  "subscription_id" int(11) NOT NULL,
  "name" varchar(45) NOT NULL,
  "cost" double NOT NULL,
  "time" bigint(20) NOT NULL,
  PRIMARY KEY ("subscription_id")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "subscription_info"
--

LOCK TABLES "subscription_info" WRITE;
/*!40000 ALTER TABLE "subscription_info" DISABLE KEYS */;
INSERT INTO "subscription_info" VALUES (1,'One hour subscription',45,3600000000000),(2,'Five hour subscrtiption',220,18000000000000),(3,'Ten hour subscription ',400,36000000000000),(4,'One day subscription',750,86400000000000);
/*!40000 ALTER TABLE "subscription_info" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "tariff"
--

DROP TABLE IF EXISTS "tariff";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "tariff" (
  "tariff_id" int(11) NOT NULL,
  "name" varchar(45) NOT NULL,
  "cost" double NOT NULL,
  "duration" bigint(20) NOT NULL,
  PRIMARY KEY ("tariff_id")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "tariff"
--

LOCK TABLES "tariff" WRITE;
/*!40000 ALTER TABLE "tariff" DISABLE KEYS */;
INSERT INTO "tariff" VALUES (1,'Minutes',0.2,60000000000),(2,'10 minutes',18,600000000000),(3,'1 hour',50,6000000000000);
/*!40000 ALTER TABLE "tariff" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "town"
--

DROP TABLE IF EXISTS "town";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "town" (
  "town_id" int(11) NOT NULL,
  "name" varchar(45) NOT NULL,
  PRIMARY KEY ("town_id")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "town"
--

LOCK TABLES "town" WRITE;
/*!40000 ALTER TABLE "town" DISABLE KEYS */;
INSERT INTO "town" VALUES (1,'Brest'),(2,'Vitebsk'),(3,'Gomel'),(4,'Grodno'),(5,'Minsk'),(6,'Mogilev');
/*!40000 ALTER TABLE "town" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "user"
--

DROP TABLE IF EXISTS "user";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "user" (
  "user_id" int(11) NOT NULL,
  "login" varchar(45) NOT NULL,
  "password" varchar(60) NOT NULL,
  PRIMARY KEY ("user_id"),
  UNIQUE KEY "login_UNIQUE" ("login")
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "user"
--

LOCK TABLES "user" WRITE;
/*!40000 ALTER TABLE "user" DISABLE KEYS */;
INSERT INTO "user" VALUES (1,'admin','$2a$10$0L8lKyZ02zyJdQ3RNgXIhOJFs07oGSjz6ov4ba0nlrM6rWt9SSOSO'),(2,'moder','$2a$10$Eaz1fwU/Rn7yMms1o9Ej2ubZ/E4lyv3zQmYysSxFSy983ZNMohh8.'),(3,'ilya777','$2a$10$hGQPNwz.aFBsbH3GCOHcN.KZELVJ6QfQBog3bZ3C8/LPbZjtmX1W6'),(4,'mishka0','$2a$10$Zo5Qqa0ADZDcqHHgJQZ37ek4Nr5fNwm/dW0kGEPzCgAFxuSUAKTZu'),(5,'vasya999','$2a$10$5gHOSM50nH78rhEUZPgk0.rZ4WoVsvh/sgEmfqn6csG4FQFEww8hu'),(6,'kiz21','$2a$10$1CwDDjS1UU3Z2GXJPaTWtOfqhyKkCrMLXOw0izlMeYIGeJ7L0Mg6G');
/*!40000 ALTER TABLE "user" ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table "user_role"
--

DROP TABLE IF EXISTS "user_role";
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE "user_role" (
  "role_id" int(11) NOT NULL,
  "user_id" int(11) NOT NULL,
  PRIMARY KEY ("role_id","user_id"),
  KEY "fk_users_roles_user_idx" ("user_id"),
  KEY "fk_users_roles_roles1_idx" ("role_id"),
  CONSTRAINT "fk_users_roles_roles1" FOREIGN KEY ("role_id") REFERENCES "role" ("role_id") ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT "fk_users_roles_user" FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE CASCADE ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table "user_role"
--

LOCK TABLES "user_role" WRITE;
/*!40000 ALTER TABLE "user_role" DISABLE KEYS */;
INSERT INTO "user_role" VALUES (1,1),(3,1),(3,2),(2,3),(2,4),(2,5),(2,6);
/*!40000 ALTER TABLE "user_role" ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-08  9:15:09
