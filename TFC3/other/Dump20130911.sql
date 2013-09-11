CREATE DATABASE  IF NOT EXISTS `tfc_dev3` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tfc_dev3`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: tfc_dev3
-- ------------------------------------------------------
-- Server version	5.5.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tfc_activities`
--

DROP TABLE IF EXISTS `tfc_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_activities` (
  `activity_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `activity` varchar(255) NOT NULL,
  `module` varchar(255) NOT NULL,
  `created_on` datetime NOT NULL,
  `deleted` tinyint(12) NOT NULL DEFAULT '0',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_activities`
--

LOCK TABLES `tfc_activities` WRITE;
/*!40000 ALTER TABLE `tfc_activities` DISABLE KEYS */;
INSERT INTO `tfc_activities` VALUES (1,1,'logged in from: 127.0.0.1','users','2013-08-25 15:18:29',0),(2,1,'Created Module: Noticias : 127.0.0.1','modulebuilder','2013-08-25 15:20:17',0),(3,1,'Created record with ID: 1 : 127.0.0.1','noticias','2013-08-25 15:20:26',0),(4,1,'Updated record with ID: 1 : 127.0.0.1','noticias','2013-08-25 15:20:39',0),(5,1,'Deleted Module: Noticias : 127.0.0.1','builder','2013-08-25 15:20:55',0),(6,1,'Created Module: Noticias : 127.0.0.1','modulebuilder','2013-08-25 15:22:04',0),(7,1,'Created record with ID: 1 : 127.0.0.1','noticias','2013-08-25 15:22:12',0),(8,1,'created a new User: gabriel','users','2013-08-25 15:39:34',0),(9,1,'modified user: gabriel','users','2013-08-25 15:40:06',0),(10,1,'logged out from: 127.0.0.1','users','2013-08-25 15:40:31',0),(11,2,'logged in from: 127.0.0.1','users','2013-08-25 15:40:41',0),(12,2,'logged out from: 127.0.0.1','users','2013-08-25 15:40:50',0),(13,1,'logged in from: 127.0.0.1','users','2013-08-25 15:41:01',0),(14,1,'logged out from: 127.0.0.1','users','2013-08-25 15:41:50',0),(15,2,'logged in from: 127.0.0.1','users','2013-08-25 15:42:02',0),(16,1,'logged in from: 127.0.0.1','users','2013-08-25 20:50:08',0),(17,1,'Created record with ID: 1 : 127.0.0.1','noticias','2013-08-25 20:52:08',0),(18,1,'Created record with ID: 2 : 127.0.0.1','noticias','2013-08-25 20:56:14',0),(19,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 20:56:43',0),(20,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 20:57:25',0),(21,1,'Created record with ID: 3 : 127.0.0.1','noticias','2013-08-25 20:59:07',0),(22,1,'Created record with ID: 4 : 127.0.0.1','noticias','2013-08-25 21:13:07',0),(23,1,'Created record with ID: 5 : 127.0.0.1','noticias','2013-08-25 21:13:13',0),(24,1,'Created record with ID: 6 : 127.0.0.1','noticias','2013-08-25 21:13:18',0),(25,1,'Created record with ID: 7 : 127.0.0.1','noticias','2013-08-25 21:28:24',0),(26,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 21:29:46',0),(27,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 21:29:53',0),(28,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 21:32:19',0),(29,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 21:32:28',0),(30,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 21:33:45',0),(31,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 21:34:01',0),(32,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 21:34:06',0),(33,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 21:34:13',0),(34,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 21:34:48',0),(35,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 21:36:15',0),(36,1,'Updated record with ID: 2 : 127.0.0.1','noticias','2013-08-25 21:36:17',0),(37,1,'Created Module: Equipos : 127.0.0.1','modulebuilder','2013-08-25 21:53:49',0),(38,1,'Created record with ID: 29 : 127.0.0.1','equipos','2013-08-25 21:57:12',0),(39,1,'Created record with ID: 30 : 127.0.0.1','equipos','2013-08-25 21:58:40',0),(40,1,'Created record with ID: 31 : 127.0.0.1','equipos','2013-08-25 22:10:29',0),(41,1,'Updated record with ID: 28 : 127.0.0.1','equipos','2013-08-25 22:10:45',0),(42,1,'Updated record with ID: 27 : 127.0.0.1','equipos','2013-08-25 22:11:04',0),(43,1,'Updated record with ID: 27 : 127.0.0.1','equipos','2013-08-25 22:11:14',0),(44,1,'Updated record with ID: 26 : 127.0.0.1','equipos','2013-08-25 22:11:23',0),(45,1,'Updated record with ID: 1 : 127.0.0.1','equipos','2013-08-25 22:11:29',0),(46,1,'Updated record with ID: 2 : 127.0.0.1','equipos','2013-08-25 22:11:37',0),(47,1,'Updated record with ID: 3 : 127.0.0.1','equipos','2013-08-25 22:11:45',0),(48,1,'Updated record with ID: 4 : 127.0.0.1','equipos','2013-08-25 22:11:52',0),(49,1,'Updated record with ID: 5 : 127.0.0.1','equipos','2013-08-25 22:11:59',0),(50,1,'Updated record with ID: 6 : 127.0.0.1','equipos','2013-08-25 22:12:06',0),(51,1,'Updated record with ID: 7 : 127.0.0.1','equipos','2013-08-25 22:12:15',0),(52,1,'Updated record with ID: 8 : 127.0.0.1','equipos','2013-08-25 22:12:37',0),(53,1,'Updated record with ID: 9 : 127.0.0.1','equipos','2013-08-25 22:12:44',0),(54,1,'Updated record with ID: 10 : 127.0.0.1','equipos','2013-08-25 22:12:51',0),(55,1,'Updated record with ID: 1 : 127.0.0.1','equipos','2013-08-25 22:15:50',0),(56,1,'Updated record with ID: 1 : 127.0.0.1','equipos','2013-08-25 22:15:56',0),(57,1,'Created Module: Jugadores : 127.0.0.1','modulebuilder','2013-08-25 22:19:22',0),(58,1,'Created record with ID: 250 : 127.0.0.1','jugadores','2013-08-25 22:20:30',0),(59,1,'Deleted record with ID: 250 : 127.0.0.1','jugadores','2013-08-25 22:21:04',0),(60,1,'Created record with ID: 251 : 127.0.0.1','jugadores','2013-08-25 22:21:12',0),(61,1,'Created record with ID: 252 : 127.0.0.1','jugadores','2013-08-25 22:26:00',0),(62,1,'Updated record with ID: 252 : 127.0.0.1','jugadores','2013-08-25 22:27:11',0),(63,1,'Updated record with ID: 252 : 127.0.0.1','jugadores','2013-08-25 22:27:22',0),(64,1,'Created record with ID: 253 : 127.0.0.1','jugadores','2013-08-25 22:34:01',0),(65,1,'Created record with ID: 254 : 127.0.0.1','jugadores','2013-08-25 22:34:30',0),(66,1,'Updated record with ID: 254 : 127.0.0.1','jugadores','2013-08-25 22:36:20',0),(67,1,'Updated record with ID: 254 : 127.0.0.1','jugadores','2013-08-25 22:36:29',0),(68,2,'logged in from: 201.213.4.99','users','2013-08-25 22:38:39',0),(69,2,'logged out from: 201.213.4.99','users','2013-08-25 22:38:45',0),(70,2,'logged in from: 201.213.4.99','users','2013-08-25 22:39:26',0),(71,2,'logged out from: 201.213.4.99','users','2013-08-25 22:40:28',0),(72,1,'logged in from: 127.0.0.1','users','2013-08-27 03:27:29',0),(73,1,'App settings saved from: 127.0.0.1','core','2013-08-27 03:28:01',0),(74,1,'Created Module: Administrar Torneos : 127.0.0.1','modulebuilder','2013-08-27 03:31:32',0),(75,1,'Created record with ID: 3 : 127.0.0.1','administrar_torneos','2013-08-27 03:33:25',0),(76,1,'Created record with ID: 4 : 127.0.0.1','administrar_torneos','2013-08-27 03:40:41',0),(77,1,'Updated record with ID: 4 : 127.0.0.1','administrar_torneos','2013-08-27 03:46:49',0),(78,1,'Updated record with ID: 4 : 127.0.0.1','administrar_torneos','2013-08-27 03:47:54',0),(79,1,'Created record with ID: 5 : 127.0.0.1','administrar_torneos','2013-08-27 04:05:27',0),(80,1,'Created record with ID: 6 : 127.0.0.1','administrar_torneos','2013-08-27 04:05:42',0),(81,1,'Updated record with ID: 6 : 127.0.0.1','administrar_torneos','2013-08-27 04:12:40',0),(82,1,'Created record with ID: 7 : 127.0.0.1','administrar_torneos','2013-08-27 04:56:35',0),(83,1,'logged in from: 127.0.0.1','users','2013-08-29 02:22:54',0),(84,1,'Created record with ID: 8 : 127.0.0.1','administrar_torneos','2013-08-29 02:55:32',0),(85,1,'Created record with ID: 9 : 127.0.0.1','administrar_torneos','2013-08-29 02:55:52',0),(86,1,'Created record with ID: 10 : 127.0.0.1','administrar_torneos','2013-08-29 02:56:20',0),(87,1,'Created record with ID: 11 : 127.0.0.1','administrar_torneos','2013-08-29 02:56:41',0),(88,1,'Created record with ID: 12 : 127.0.0.1','administrar_torneos','2013-08-29 02:57:05',0),(89,1,'Created record with ID: 13 : 127.0.0.1','administrar_torneos','2013-08-29 02:57:37',0),(90,2,'logged in from: 186.23.96.234','users','2013-08-29 03:42:19',0),(91,2,'Created record with ID: 14 : 186.23.96.234','administrar_torneos','2013-08-29 03:50:08',0),(92,1,'Created record with ID: 15 : 127.0.0.1','administrar_torneos','2013-08-29 04:00:46',0),(93,1,'Created record with ID: 16 : 127.0.0.1','administrar_torneos','2013-08-29 04:01:15',0),(94,1,'Created record with ID: 17 : 127.0.0.1','administrar_torneos','2013-08-29 04:01:44',0),(95,1,'Created record with ID: 18 : 127.0.0.1','administrar_torneos','2013-08-29 04:04:00',0),(96,1,'Created record with ID: 19 : 127.0.0.1','administrar_torneos','2013-08-29 04:14:18',0),(97,1,'Created record with ID: 20 : 127.0.0.1','administrar_torneos','2013-08-29 04:16:28',0),(98,1,'Created record with ID: 21 : 127.0.0.1','administrar_torneos','2013-08-29 04:17:08',0),(99,1,'Created record with ID: 22 : 127.0.0.1','administrar_torneos','2013-08-29 04:20:06',0),(100,1,'logged in from: 127.0.0.1','users','2013-08-31 20:21:52',0),(101,1,'Created record with ID: 3 : 127.0.0.1','administrar_torneos','2013-08-31 20:52:27',0),(102,1,'Created record with ID: 4 : 127.0.0.1','administrar_torneos','2013-08-31 20:53:00',0),(103,1,'Updated record with ID: 4 : 127.0.0.1','administrar_torneos','2013-08-31 21:06:21',0),(104,1,'Updated record with ID: 4 : 127.0.0.1','administrar_torneos','2013-08-31 21:06:35',0),(105,1,'logged in from: 127.0.0.1','users','2013-09-04 00:27:54',0),(106,1,'Created record with ID: 5 : 127.0.0.1','administrar_torneos','2013-09-04 01:31:30',0),(107,1,'logged in from: 127.0.0.1','users','2013-09-05 02:55:40',0),(108,1,'Created record with ID: 5 : 127.0.0.1','administrar_torneos','2013-09-05 03:11:01',0),(109,1,'Created record with ID: 6 : 127.0.0.1','administrar_torneos','2013-09-05 03:23:30',0),(110,1,'Created record with ID: 8 : 127.0.0.1','administrar_torneos','2013-09-05 03:27:45',0),(111,1,'Created record with ID: 9 : 127.0.0.1','administrar_torneos','2013-09-05 03:31:19',0),(112,1,'Created Module: Administrar Partidos : 127.0.0.1','modulebuilder','2013-09-05 03:42:01',0),(113,1,'Created record with ID: 17 : 127.0.0.1','administrar_partidos','2013-09-05 03:42:40',0),(114,1,'Created record with ID: 18 : 127.0.0.1','administrar_partidos','2013-09-05 03:42:50',0),(115,1,'Deleted Module: Administrar_Partidos : 127.0.0.1','builder','2013-09-05 03:43:53',0),(116,1,'Created Module: Administrar Partidos : 127.0.0.1','modulebuilder','2013-09-05 03:44:47',0),(117,1,'Created record with ID: 1 : 127.0.0.1','administrar_partidos','2013-09-05 03:44:56',0),(118,1,'Created record with ID: 2 : 127.0.0.1','administrar_partidos','2013-09-05 03:48:37',0),(119,1,'Updated record with ID: 2 : 127.0.0.1','administrar_partidos','2013-09-05 03:52:25',0),(120,1,'logged in from: 127.0.0.1','users','2013-09-05 14:51:36',0),(121,1,'Updated record with ID: 9 : 127.0.0.1','administrar_torneos','2013-09-05 15:07:54',0),(122,1,'Created record with ID: 1 : 127.0.0.1','administrar_partidos','2013-09-05 15:46:39',0),(123,1,'logged in from: 127.0.0.1','users','2013-09-06 01:41:54',0),(124,1,'Created record with ID: 2 : 127.0.0.1','administrar_partidos','2013-09-06 02:11:31',0),(125,1,'Updated record with ID: 2 : 127.0.0.1','administrar_partidos','2013-09-06 02:12:24',0),(126,1,'Created record with ID: 3 : 127.0.0.1','administrar_partidos','2013-09-06 02:13:34',0),(127,1,'Created record with ID: 4 : 127.0.0.1','administrar_partidos','2013-09-06 02:14:33',0),(128,1,'Created record with ID: 8 : 127.0.0.1','administrar_partidos','2013-09-06 02:26:28',0),(129,1,'Created record with ID: 9 : 127.0.0.1','administrar_partidos','2013-09-06 02:28:50',0),(130,1,'Created record with ID: 10 : 127.0.0.1','administrar_partidos','2013-09-06 02:29:41',0),(131,1,'Created record with ID: 11 : 127.0.0.1','administrar_partidos','2013-09-06 02:32:06',0),(132,1,'Created record with ID: 12 : 127.0.0.1','administrar_partidos','2013-09-06 02:34:01',0),(133,1,'Created record with ID: 13 : 127.0.0.1','administrar_partidos','2013-09-06 02:34:44',0),(134,1,'Created record with ID: 14 : 127.0.0.1','administrar_partidos','2013-09-06 02:38:07',0),(135,1,'Created record with ID: 15 : 127.0.0.1','administrar_partidos','2013-09-06 02:52:53',0),(136,1,'Updated record with ID: 15 : 127.0.0.1','administrar_partidos','2013-09-06 02:53:02',0),(137,1,'logged in from: 127.0.0.1','users','2013-09-06 16:02:31',0),(138,1,'Updated record with ID: 15 : 127.0.0.1','administrar_partidos','2013-09-06 17:03:57',0),(139,1,'Updated record with ID: 15 : 127.0.0.1','administrar_partidos','2013-09-06 17:13:10',0),(140,1,'Updated record with ID: 15 : 127.0.0.1','administrar_partidos','2013-09-06 17:14:45',0),(141,1,'Deleted record with ID: 15 : 127.0.0.1','administrar_partidos','2013-09-06 17:15:03',0),(142,1,'Created record with ID: 16 : 127.0.0.1','administrar_partidos','2013-09-06 17:15:47',0),(143,1,'Updated record with ID: 16 : 127.0.0.1','administrar_partidos','2013-09-06 17:15:54',0),(144,1,'Updated record with ID: 16 : 127.0.0.1','administrar_partidos','2013-09-06 17:16:02',0),(145,1,'logged in from: 200.69.240.117','users','2013-09-06 17:16:50',0),(146,1,'Updated record with ID: 1 : 127.0.0.1','administrar_torneos','2013-09-06 17:16:56',0),(147,1,'Updated record with ID: 2 : 127.0.0.1','administrar_torneos','2013-09-06 17:17:04',0),(148,1,'logged out from: 200.69.240.117','users','2013-09-06 17:32:06',0),(149,1,'logged in from: 127.0.0.1','users','2013-09-10 00:06:37',0),(150,1,'Created record with ID: 11 : 127.0.0.1','administrar_torneos','2013-09-10 00:28:25',0),(151,1,'Created record with ID: 12 : 127.0.0.1','administrar_torneos','2013-09-10 00:30:00',0),(152,1,'Created record with ID: 13 : 127.0.0.1','administrar_torneos','2013-09-10 00:31:33',0),(153,1,'Created record with ID: 14 : 127.0.0.1','administrar_torneos','2013-09-10 00:31:51',0),(154,1,'Created Module: Administracion Tabla Posiciones : 127.0.0.1','modulebuilder','2013-09-10 00:38:55',0),(155,1,'Updated record with ID: 1 : 127.0.0.1','administracion_tabla_posiciones','2013-09-10 01:12:52',0),(156,1,'Updated record with ID: 1 : 127.0.0.1','administracion_tabla_posiciones','2013-09-10 01:13:15',0),(157,1,'Updated record with ID: 1 : 127.0.0.1','administracion_tabla_posiciones','2013-09-10 01:13:34',0),(158,1,'Updated record with ID: 1 : 127.0.0.1','administracion_tabla_posiciones','2013-09-10 01:23:30',0),(159,1,'Updated record with ID: 1 : 127.0.0.1','administracion_tabla_posiciones','2013-09-10 01:23:36',0),(160,1,'Created record with ID: 15 : 127.0.0.1','administrar_torneos','2013-09-10 01:35:02',0),(161,1,'Updated record with ID: 15 : 127.0.0.1','administrar_torneos','2013-09-10 01:35:39',0),(162,1,'Updated record with ID: 42 : 127.0.0.1','administrar_partidos','2013-09-10 01:37:39',0),(163,1,'logged in from: 127.0.0.1','users','2013-09-11 00:14:29',0),(164,1,'Updated record with ID: 1 : 127.0.0.1','administrar_torneos','2013-09-11 00:20:14',0),(165,1,'Updated record with ID: 2 : 127.0.0.1','administrar_torneos','2013-09-11 00:20:19',0),(166,1,'Created record with ID: 16 : 127.0.0.1','administrar_torneos','2013-09-11 00:22:05',0),(167,1,'Created record with ID: 17 : 127.0.0.1','administrar_torneos','2013-09-11 01:01:04',0),(168,1,'Created record with ID: 18 : 127.0.0.1','administrar_torneos','2013-09-11 01:07:32',0),(169,1,'Created record with ID: 19 : 127.0.0.1','administrar_torneos','2013-09-11 01:08:54',0);
/*!40000 ALTER TABLE `tfc_activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_banner`
--

DROP TABLE IF EXISTS `tfc_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_banner` (
  `idbanner` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL,
  `titulo` varchar(200) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idbanner`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_banner`
--

LOCK TABLES `tfc_banner` WRITE;
/*!40000 ALTER TABLE `tfc_banner` DISABLE KEYS */;
/*!40000 ALTER TABLE `tfc_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_email_queue`
--

DROP TABLE IF EXISTS `tfc_email_queue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_email_queue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `to_email` varchar(128) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `message` text NOT NULL,
  `alt_message` text,
  `max_attempts` int(11) NOT NULL DEFAULT '3',
  `attempts` int(11) NOT NULL DEFAULT '0',
  `success` tinyint(1) NOT NULL DEFAULT '0',
  `date_published` datetime DEFAULT NULL,
  `last_attempt` datetime DEFAULT NULL,
  `date_sent` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_email_queue`
--

LOCK TABLES `tfc_email_queue` WRITE;
/*!40000 ALTER TABLE `tfc_email_queue` DISABLE KEYS */;
/*!40000 ALTER TABLE `tfc_email_queue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_equipo`
--

DROP TABLE IF EXISTS `tfc_equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_equipo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) DEFAULT NULL,
  `logo_grande` varchar(200) DEFAULT NULL,
  `logo_chico` varchar(200) DEFAULT NULL,
  `pagina_web` varchar(200) DEFAULT NULL,
  `ciudad` varchar(200) DEFAULT NULL,
  `foto` varchar(200) DEFAULT NULL,
  `cantidad_partidos_perdidos` int(11) DEFAULT NULL,
  `cantidad_tarjetas_rojas` int(11) DEFAULT NULL,
  `cantidad_tarjetas_amarillas` int(11) DEFAULT NULL,
  `cantidad_goles_en_contra` int(11) DEFAULT NULL,
  `cantidad_goles_a_favor` int(11) DEFAULT NULL,
  `cantidad_partidos_empatados` int(11) DEFAULT NULL,
  `cantidad_partidos_ganados` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_equipo`
--

LOCK TABLES `tfc_equipo` WRITE;
/*!40000 ALTER TABLE `tfc_equipo` DISABLE KEYS */;
INSERT INTO `tfc_equipo` VALUES (1,'Wilde FC','/assets/imgs/default/team/defaultTeamLogo.png','','','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(2,'El Paso','/assets/imgs/default/team/defaultTeamLogo.png','','','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(3,'Ritmo y Sustancias','/assets/imgs/default/team/defaultTeamLogo.png','','','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(4,'Los Pibes del Mate','/assets/imgs/default/team/defaultTeamLogo.png','','','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(5,'Sarandi FC','/assets/imgs/default/team/defaultTeamLogo.png','','','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(6,'Piedra que late','/assets/imgs/default/team/defaultTeamLogo.png','','','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(7,'Los Palos de VC','/assets/imgs/default/team/defaultTeamLogo.png','','','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(8,'Koalas','/assets/imgs/default/team/defaultTeamLogo.png','','','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(9,'Me Pesan PC','/assets/imgs/default/team/defaultTeamLogo.png','','','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(10,'Buenos Muchachos','/assets/imgs/default/team/defaultTeamLogo.png','','','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(11,'El Barrio','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(12,'General Subin','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(13,'Buenos Aires FC','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(14,'Ymanac','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(15,'Deportivo Branca','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(16,'Electrolux','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(17,'Los Dogos','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(18,'El Manza no Juega','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(19,'Los Pibes de la 35','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(20,'Los Pibes','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(21,'Los Gedes de River','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(22,'Santos de Acre','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(23,'San Martin','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(24,'Los pibes de pingui','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(25,'El Selectivo','/assets/imgs/default/team/defaultTeamLogo.png',NULL,NULL,NULL,'/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(26,'Perfigom','/assets/imgs/default/team/defaultTeamLogo.png',NULL,'','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(27,'Dragones','/assets/imgs/default/team/defaultTeamLogo.png',NULL,'','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(28,'Chingolo City','/assets/imgs/default/team/defaultTeamLogo.png','','','','/assets/imgs/default/team/defaultTeamPhoto.png',0,0,0,0,0,0,0),(31,'asdf','','','','','',0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `tfc_equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_equipo_historial_campeonatos`
--

DROP TABLE IF EXISTS `tfc_equipo_historial_campeonatos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_equipo_historial_campeonatos` (
  `idequipo` int(11) NOT NULL,
  `idtorneo` int(11) DEFAULT NULL,
  `posicion` int(11) DEFAULT NULL,
  PRIMARY KEY (`idequipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_equipo_historial_campeonatos`
--

LOCK TABLES `tfc_equipo_historial_campeonatos` WRITE;
/*!40000 ALTER TABLE `tfc_equipo_historial_campeonatos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tfc_equipo_historial_campeonatos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_equipos_torneo`
--

DROP TABLE IF EXISTS `tfc_equipos_torneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_equipos_torneo` (
  `idtorneo` int(11) NOT NULL,
  `idequipo` int(11) NOT NULL,
  `baja` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idtorneo`,`idequipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_equipos_torneo`
--

LOCK TABLES `tfc_equipos_torneo` WRITE;
/*!40000 ALTER TABLE `tfc_equipos_torneo` DISABLE KEYS */;
INSERT INTO `tfc_equipos_torneo` VALUES (1,1,0),(1,3,0),(1,8,0),(1,9,0),(1,10,0),(1,11,0),(1,12,0),(1,15,0),(1,17,0),(1,25,0),(2,2,0),(2,5,0),(2,7,0),(2,13,0),(2,14,0),(2,16,0),(2,18,0),(2,19,0),(2,27,0),(2,28,0),(15,4,0),(15,5,0),(15,6,0),(15,7,0),(15,8,0),(15,9,0),(19,4,0),(19,5,0),(19,6,0);
/*!40000 ALTER TABLE `tfc_equipos_torneo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_estadisticas_jugador_por_torneo`
--

DROP TABLE IF EXISTS `tfc_estadisticas_jugador_por_torneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_estadisticas_jugador_por_torneo` (
  `idtorneo` int(11) NOT NULL,
  `idequipo` int(11) NOT NULL,
  `idjugador` int(11) NOT NULL,
  `cantidad_goles` int(11) DEFAULT NULL,
  `cantidad_tarjetas_amarillas` int(11) DEFAULT NULL,
  `cantidad_tarjetas_rojas` int(11) DEFAULT NULL,
  `cantidad_partidos_jugados` int(11) DEFAULT NULL,
  `cantidad_goles_en_contra` int(11) DEFAULT NULL,
  PRIMARY KEY (`idtorneo`,`idjugador`,`idequipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_estadisticas_jugador_por_torneo`
--

LOCK TABLES `tfc_estadisticas_jugador_por_torneo` WRITE;
/*!40000 ALTER TABLE `tfc_estadisticas_jugador_por_torneo` DISABLE KEYS */;
INSERT INTO `tfc_estadisticas_jugador_por_torneo` VALUES (1,3,20,6,0,0,0,0),(1,3,21,10,0,0,0,0),(1,3,28,0,2,1,0,0),(1,8,77,11,0,0,0,0),(1,8,80,0,2,0,0,0),(1,9,90,0,0,0,9,25),(1,9,93,6,0,0,0,0),(1,9,95,14,2,0,0,0),(1,9,100,6,2,0,0,0),(1,10,108,7,0,0,0,0),(1,10,113,10,0,0,0,0),(1,10,114,10,0,0,0,0),(1,11,116,7,0,0,0,0),(1,12,134,6,0,0,0,0),(1,12,136,0,1,0,0,0),(1,12,137,0,1,1,0,0),(1,12,138,26,0,0,0,0),(1,12,140,7,0,0,0,0),(1,12,144,0,0,0,9,27),(1,15,169,10,0,0,0,0),(1,15,172,8,0,0,0,0),(1,15,173,7,0,0,0,0),(1,17,189,0,1,1,0,0),(1,17,191,12,0,0,0,0),(1,17,192,0,1,0,0,0),(1,17,194,0,1,0,0,0),(1,17,195,7,0,0,0,0),(1,17,197,0,0,0,9,22),(1,17,225,0,3,1,0,0),(1,25,229,8,0,0,0,0),(1,25,230,11,0,0,0,0),(2,2,6,6,0,0,0,0),(2,2,7,5,0,0,0,0),(2,2,13,5,0,0,0,0),(2,2,15,0,1,0,0,0),(2,7,65,0,0,0,9,27),(2,7,66,15,1,0,0,0),(2,7,72,31,0,0,0,0),(2,7,73,4,0,0,0,0),(2,7,74,5,1,0,0,0),(2,13,146,14,0,0,0,0),(2,13,148,0,1,0,0,0),(2,13,150,0,1,0,0,0),(2,13,152,32,0,0,0,0),(2,13,153,0,1,0,0,0),(2,13,154,0,1,0,9,20),(2,13,155,4,0,0,0,0),(2,14,160,10,0,0,0,0),(2,14,161,5,0,0,0,0),(2,14,164,11,0,0,0,0),(2,14,165,0,1,0,0,0),(2,16,177,11,0,0,0,0),(2,16,180,8,0,0,0,0),(2,16,181,5,0,0,0,0),(2,16,183,0,0,0,9,18),(2,16,185,7,0,0,0,0),(2,16,186,5,1,0,0,0),(2,18,208,22,0,0,0,0),(2,19,211,0,1,0,0,0),(2,19,216,6,0,0,0,0);
/*!40000 ALTER TABLE `tfc_estadisticas_jugador_por_torneo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_estadisticas_partido`
--

DROP TABLE IF EXISTS `tfc_estadisticas_partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_estadisticas_partido` (
  `idpartido` int(11) NOT NULL,
  `idequipo` int(11) NOT NULL,
  `idjugador` int(11) NOT NULL,
  `accion` int(11) NOT NULL COMMENT 'acciones: gol, tarjeta amarilla, tarjeta roja',
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`accion`,`idjugador`,`idequipo`,`idpartido`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_estadisticas_partido`
--

LOCK TABLES `tfc_estadisticas_partido` WRITE;
/*!40000 ALTER TABLE `tfc_estadisticas_partido` DISABLE KEYS */;
INSERT INTO `tfc_estadisticas_partido` VALUES (15,4,29,1,1),(15,4,32,1,2),(15,4,35,1,3),(15,6,50,1,2),(15,6,54,1,3),(15,4,31,2,2),(15,4,34,2,1),(15,6,51,2,1),(15,6,58,2,1),(15,4,30,3,1),(15,6,51,3,1),(15,6,54,3,1);
/*!40000 ALTER TABLE `tfc_estadisticas_partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_jugador`
--

DROP TABLE IF EXISTS `tfc_jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_jugador` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre_completo` varchar(200) DEFAULT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `apellido` varchar(200) DEFAULT NULL,
  `delegado` tinyint(1) DEFAULT NULL,
  `idequipo` int(11) DEFAULT NULL,
  `cantidad_tarjetas_amarillas` int(11) DEFAULT NULL,
  `cantidad_partidos_jugados` int(11) DEFAULT NULL,
  `cantidad_tarjetas_rojas` int(11) DEFAULT NULL,
  `cantidad_goles` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_jugador`
--

LOCK TABLES `tfc_jugador` WRITE;
/*!40000 ALTER TABLE `tfc_jugador` DISABLE KEYS */;
INSERT INTO `tfc_jugador` VALUES (1,'Chain Leonel','Leonel','Chain',1,1,NULL,NULL,NULL,NULL),(2,'Albanesi Ezequiel','Ezequiel','Albanesi',1,2,NULL,NULL,NULL,NULL),(3,'Acevedo Braian','Brian','Acevedo',0,2,NULL,NULL,NULL,NULL),(4,'Orellana Federico','Federico','Orellana',0,2,NULL,NULL,NULL,NULL),(5,'Gutierrez Javier','Javier','Gutierrez',0,2,NULL,NULL,NULL,NULL),(6,'Simon Damian','Damian','Simon',0,2,NULL,NULL,NULL,NULL),(7,'Riquelme Gabriel','Gabriel','Riquelme',0,2,NULL,NULL,NULL,NULL),(8,'Daspet Emiliano','Emiliano','Daspet',0,2,NULL,NULL,NULL,NULL),(9,'Daspet Emanuel','Emanuel','Daspet',0,2,NULL,NULL,NULL,NULL),(10,'Retamozo Matias','Matias','Retamozo',0,2,NULL,NULL,NULL,NULL),(11,'Sotelo Maximiliano','Maximiliano','Sotelo',0,2,NULL,NULL,NULL,NULL),(12,'Fernandez Joel','Joel','Fernandez',0,2,NULL,NULL,NULL,NULL),(13,'Cardozo Jonatan','Jonatan','Cardozo',0,2,NULL,NULL,NULL,NULL),(14,'Farias Miguel','Miguel','Farias',0,2,NULL,NULL,NULL,NULL),(15,'Aguirre Juan','Juan','Aguirre',0,2,NULL,NULL,NULL,NULL),(16,'Monasterio Rub?n','Rub?n','Monasterio',0,3,NULL,NULL,NULL,NULL),(17,'Veron Gast?n','Gast?n','Veron',0,3,NULL,NULL,NULL,NULL),(18,'Chiappe Fernando','Fernando','Chiappe',0,3,NULL,NULL,NULL,NULL),(19,'Monasterio Mat?as','Mat?as','Monasterio',0,3,NULL,NULL,NULL,NULL),(20,'Gonzalez Marcos','Marcos','Gonzalez',0,3,NULL,NULL,NULL,NULL),(21,'Monasterio Lucas','Lucas','Monasterio',1,3,NULL,NULL,NULL,NULL),(22,'Pe?a Andr?s','Andr?s','Pe?a',0,3,NULL,NULL,NULL,NULL),(23,'Azcona German','German','Azcona',0,3,NULL,NULL,NULL,NULL),(24,'Forte Andr?s','Andr?s','Forte',0,3,NULL,NULL,NULL,NULL),(25,'Seliar Fernando','Fernando','Seliar',0,3,NULL,NULL,NULL,NULL),(26,'Blanco Luca','Luca','Blanco',0,3,NULL,NULL,NULL,NULL),(27,'Rojas Diego','Diego','Rojas',0,3,NULL,NULL,NULL,NULL),(28,'Sanchez Matias','Matias','Sanchez',0,3,NULL,NULL,NULL,NULL),(29,'Frias Franco','Franco','Frias',0,4,NULL,NULL,NULL,NULL),(30,'Galarza Alejandro','Alejandro','Galarza',0,4,NULL,NULL,NULL,NULL),(31,'Araujo Jonhatan','Jonhatan','Araujo',0,4,NULL,NULL,NULL,NULL),(32,'Da Luz Jonhatan','Jonhatan','Da Luz',0,4,NULL,NULL,NULL,NULL),(33,'Robledo Matias','Matias','Robledo',0,4,NULL,NULL,NULL,NULL),(34,'Jaure Matias','Matias','Jaure',0,4,NULL,NULL,NULL,NULL),(35,'Yustes Andres','Andres','Yustes',0,4,NULL,NULL,NULL,NULL),(36,'Sansaro Hernan','Hernan','Sansaro',0,4,NULL,NULL,NULL,NULL),(37,'Fleitas Nicolas','Nicolas','Fleitas',0,4,NULL,NULL,NULL,NULL),(38,'Velasquez Braian','Braian','Velasquez',0,4,NULL,NULL,NULL,NULL),(39,'Torres Ivan','Ivan','Torres',0,4,NULL,NULL,NULL,NULL),(40,'Gonzalo Villegas','Gonzalo','Villegas',1,4,NULL,NULL,NULL,NULL),(41,'Nahuel Villafa?e','Nahuel','Villafa?e',1,5,NULL,NULL,NULL,NULL),(42,'Cacerez Ulises','Ulises','Cacerez',0,5,NULL,NULL,NULL,NULL),(43,'Cacerez Leonardo','Leonardo','Cacerez',0,5,NULL,NULL,NULL,NULL),(44,'Cacerez Abel','Abel','Cacerez',0,5,NULL,NULL,NULL,NULL),(45,'Torres Diego','Diego','Torres',0,5,NULL,NULL,NULL,NULL),(46,'Ibarra Fernando','Fernando','Ibarra',0,5,NULL,NULL,NULL,NULL),(47,'Ramrirez Cristian','Cristian','Ramrirez',0,5,NULL,NULL,NULL,NULL),(48,'Maximiliano Fernandez','Maximiliano','Fernandez',0,5,NULL,NULL,NULL,NULL),(49,'Carlos Prieto','Carlos','Prieto',0,5,NULL,NULL,NULL,NULL),(50,'Esquivel Elias E.','Elias E.','Esquivel',0,6,NULL,NULL,NULL,NULL),(51,'Adaro Marcos S.','Marcos S.','Adaro',0,6,NULL,NULL,NULL,NULL),(52,'Gimenez Emanuel','Emanuel','Gimenez',0,6,NULL,NULL,NULL,NULL),(53,'Gomes Cristian M.','Cristian M.','Gomes',0,6,NULL,NULL,NULL,NULL),(54,'Pereyra Hugo R.','Hugo R.','Pereyra',1,6,NULL,NULL,NULL,NULL),(55,'Pereyra Lucas M.','Lucas M.','Pereyra',0,6,NULL,NULL,NULL,NULL),(56,'Diaz Ezequiel','Ezequiel','Diaz',0,6,NULL,NULL,NULL,NULL),(57,'Pereyra Mariano E.','Mariano E.','Pereyra',0,6,NULL,NULL,NULL,NULL),(58,'Pereyra Guillermo A.','Guillermo A.','Pereyra',0,6,NULL,NULL,NULL,NULL),(59,'Diaz Walter','Walter','Diaz',0,6,NULL,NULL,NULL,NULL),(60,'Pereyra Jose L.','Jose L.','Pereyra',0,6,NULL,NULL,NULL,NULL),(61,'Chazarreta Marcelo G.','Marcelo G.','Chazarreta',0,6,NULL,NULL,NULL,NULL),(62,'Pardo Damian L.','Damian L.','Pardo',0,6,NULL,NULL,NULL,NULL),(63,'Gonzales Leandro','Leandro','Gonzales',0,6,NULL,NULL,NULL,NULL),(64,'Scapuzzi Ramiro','Ramiro','Scapuzzi',0,7,NULL,NULL,NULL,NULL),(65,'Vicente Gerard','Gerard','Vicente',1,7,NULL,NULL,NULL,NULL),(66,'Medina Emiliano','Emiliano','Medina',0,7,NULL,NULL,NULL,NULL),(67,'Dos Santos Simon','Simon','Dos Santos',0,7,NULL,NULL,NULL,NULL),(68,'Verdes Javier','Javier','Verdes',0,7,NULL,NULL,NULL,NULL),(69,'Ledesma Nahuel','Nahuel','Ledesma',0,7,NULL,NULL,NULL,NULL),(70,'Vanlezuela Nicolas','Nicolas','Vanlezuela',0,7,NULL,NULL,NULL,NULL),(71,'Vallejos Alan','Alan','Vallejos',0,7,NULL,NULL,NULL,NULL),(72,'Llovet Braian','Braian','Llovet',0,7,NULL,NULL,NULL,NULL),(73,'Bernal Matias','Matias','Bernal',0,7,NULL,NULL,NULL,NULL),(74,'Arrieta Franco','Franco','Arrieta',0,7,NULL,NULL,NULL,NULL),(75,'Barrios Alan','Alan','Barrios',0,7,NULL,NULL,NULL,NULL),(76,'Acu?a Cristian','Cristian','Acu?a',0,7,NULL,NULL,NULL,NULL),(77,'Prieto David','David','Prieto',1,8,NULL,NULL,NULL,NULL),(78,'Bianchi Diego','Diego','Bianchi',0,8,NULL,NULL,NULL,NULL),(79,'Vera Federico','Federico','Vera',0,8,NULL,NULL,NULL,NULL),(80,'Kondolf Federico','Federico','Kondolf',0,8,NULL,NULL,NULL,NULL),(81,'Prieto Hernan','Hernan','Prieto',0,8,NULL,NULL,NULL,NULL),(82,'Ferrin Ignacio','Ignacio','Ferrin',0,8,NULL,NULL,NULL,NULL),(83,'Elizalde Joaqu?n','Joaqu?n','Elizalde',0,8,NULL,NULL,NULL,NULL),(84,'Bestard Lautaro','Lautaro','Bestard',0,8,NULL,NULL,NULL,NULL),(85,'Pereyra Lucas','Lucas','Pereyra',0,8,NULL,NULL,NULL,NULL),(86,'Gonzalez Marcelo','Marcelo','Gonzalez',0,8,NULL,NULL,NULL,NULL),(87,'Arbizu Matias','Matias','Arbizu',0,8,NULL,NULL,NULL,NULL),(88,'Coppola Miguel','Miguel','Coppola',0,8,NULL,NULL,NULL,NULL),(89,'Bestard Nahuel','Nahuel','Bestard',0,8,NULL,NULL,NULL,NULL),(90,'Vitale Ezequiel','Ezequiel','Vitale',0,9,NULL,NULL,NULL,NULL),(91,'Fortunato Sergio','Sergio','Fortunato',0,9,NULL,NULL,NULL,NULL),(92,'Rodriguez Tomas','Tomas','Rodriguez',0,9,NULL,NULL,NULL,NULL),(93,'Elizalde Vera Julian','Elizalde Vera','Julian',0,9,NULL,NULL,NULL,NULL),(94,'Fortunato Claudio','Claudio','Fortunato',0,9,NULL,NULL,NULL,NULL),(95,'Puzzi Lucas','Lucas','Puzzi',1,9,NULL,NULL,NULL,NULL),(96,'Ramundo Hernan','Hernan','Ramundo',0,9,NULL,NULL,NULL,NULL),(97,'Sosa Facundo','Facundo','Sosa',0,9,NULL,NULL,NULL,NULL),(98,'Trasende Fernando','Fernando','Trasende',0,9,NULL,NULL,NULL,NULL),(99,'Gonzalez Franco','Franco','Gonzalez',0,9,NULL,NULL,NULL,NULL),(100,'Abdala Matias','Matias','Abdala',0,9,NULL,NULL,NULL,NULL),(101,'Fortunato Leonardo','Leonardo','Fortunato',0,9,NULL,NULL,NULL,NULL),(102,'Renna Guido','Guido','Renna',0,9,NULL,NULL,NULL,NULL),(103,'Vitale Marcos','Marcos','Vitale',0,9,NULL,NULL,NULL,NULL),(104,'Mendieta Leonardo','Leonardo','Mendieta',0,10,NULL,NULL,NULL,NULL),(105,'Baez Daniel','Daniel','Baez',0,10,NULL,NULL,NULL,NULL),(106,'Rojas Maximilkiano','Maximilkiano','Rojas',0,10,NULL,NULL,NULL,NULL),(107,'Morelli Luis','Luis','Morelli',0,10,NULL,NULL,NULL,NULL),(108,'Vazquez Nahuel','Nahuel','Vazquez',0,10,NULL,NULL,NULL,NULL),(109,'Rosanova Ariel','Ariel','Rosanova',1,10,NULL,NULL,NULL,NULL),(110,'Maio Matias','Matias','Maio',0,10,NULL,NULL,NULL,NULL),(111,'Lamas Emanuel','Emanuel','Lamas',0,10,NULL,NULL,NULL,NULL),(112,'Damian Diaz','Damian','Diaz',0,10,NULL,NULL,NULL,NULL),(113,'Vazquez Gabriel','Gabriel','Vazquez',0,10,NULL,NULL,NULL,NULL),(114,'Puzzi Matias','Matias','Puzzi',0,10,NULL,NULL,NULL,NULL),(115,'Cordoba Gustavo','Gustavo','Cordoba',1,11,NULL,NULL,NULL,NULL),(116,'Ra?a Nahuel','Nahuel','Ra?a',0,11,NULL,NULL,NULL,NULL),(117,'Debeva Lucas','Lucas','Debeva',0,11,NULL,NULL,NULL,NULL),(118,'Montero Ivan','Ivan','Montero',0,11,NULL,NULL,NULL,NULL),(119,'Pellini Santiago','Santiago','Pellini',0,11,NULL,NULL,NULL,NULL),(120,'Cassinotti Agustin','Cassinotti','Agustin',0,11,NULL,NULL,NULL,NULL),(121,'Crisca Pablo','Pablo','Crisca',0,11,NULL,NULL,NULL,NULL),(122,'Horacio Carlos','Carlos','Horacio',0,11,NULL,NULL,NULL,NULL),(123,'Corahes Horacio','Horario','Corahes',0,11,NULL,NULL,NULL,NULL),(124,'Bordon Nicolas','Nicolas','Bordon',0,11,NULL,NULL,NULL,NULL),(125,'Farias Lucas','Lucas','Farias',0,11,NULL,NULL,NULL,NULL),(126,'Castro Federico','Federico','Castro',0,11,NULL,NULL,NULL,NULL),(127,'Gonzalez Franco','Franco','Gonzalez',0,11,NULL,NULL,NULL,NULL),(128,'Maciel Alex','Alex','Maciel',0,11,NULL,NULL,NULL,NULL),(129,'Quiroga Agustin','Agustin','Quiroga',0,11,NULL,NULL,NULL,NULL),(130,'Fernandez Rodrigo','Rodrigo','Fernandez',0,11,NULL,NULL,NULL,NULL),(131,'Fernandez Cristian','Cristian','Fernandez',0,11,NULL,NULL,NULL,NULL),(132,'Constanzo Luciano','Luciano','Constanzo',0,11,NULL,NULL,NULL,NULL),(133,'Cardozo Nicolas','Nicolas','Cardozo',0,11,NULL,NULL,NULL,NULL),(134,'Loberme Federico','Federico','Loberme',0,12,NULL,NULL,NULL,NULL),(135,'Loberme Leandro','Leandro','Loberme',1,12,NULL,NULL,NULL,NULL),(136,'Glizj Fernando','Fernando','Glizj',0,12,NULL,NULL,NULL,NULL),(137,'Loberme Hernan','Hernan','Loberme',0,12,NULL,NULL,NULL,NULL),(138,'Pereyra Federico','Federico','Pereyra',0,12,NULL,NULL,NULL,NULL),(139,'Ripoli Federico','Federico','Ripoli',0,12,NULL,NULL,NULL,NULL),(140,'Mendoza Jonathan','Jonathan','Mendoza',0,12,NULL,NULL,NULL,NULL),(141,'Loberme Alejandro','Alejandro','Loberme',0,12,NULL,NULL,NULL,NULL),(142,'Najol Sebastian','Sebastian','Najol',0,12,NULL,NULL,NULL,NULL),(143,'Rodriguez Daniel','Daniel','Rodriguez',0,12,NULL,NULL,NULL,NULL),(144,'Mussutto Matias','Matias','Mussutto',0,12,NULL,NULL,NULL,NULL),(145,'De Ferrari Nacho','Nacho','De Ferrari',0,13,NULL,NULL,NULL,NULL),(146,'Fanuela Emiliano','Emiliano','Fanuela',0,13,NULL,NULL,NULL,NULL),(147,'Fernandez Leo','Leo','Fernandez',0,13,NULL,NULL,NULL,NULL),(148,'Cereijo Gaston','Gaston','Cereijo',0,13,NULL,NULL,NULL,NULL),(149,'Cadaveira Gustavo','Gustavo','Cadaveira',0,13,NULL,NULL,NULL,NULL),(150,'Perez Lucas','Lucas','Perez',0,13,NULL,NULL,NULL,NULL),(151,'Martinez Hernan','Hernan','Martinez',0,13,NULL,NULL,NULL,NULL),(152,'Mastronardi Juan Manuel','Juan Manuel','Mastronardi',1,13,NULL,NULL,NULL,NULL),(153,'Cereijo Matias','Matias','Cereijo',0,13,NULL,NULL,NULL,NULL),(154,'Testi Ezequiel','Ezequiel','Testi',0,13,NULL,NULL,NULL,NULL),(155,'Zerda Francisco','Francisco','Zerda',0,13,NULL,NULL,NULL,NULL),(156,'Arancibra Ignacio','Ignacio','Arancibra',1,14,NULL,NULL,NULL,NULL),(157,'Lucero Maximiliano','Lucero','Maximiliano',0,14,NULL,NULL,NULL,NULL),(158,'Spano Marcelo','Marcelo','Spano',0,14,NULL,NULL,NULL,NULL),(159,'Murua Ariel','Ariel','Murua',0,14,NULL,NULL,NULL,NULL),(160,'Ferrari Nahuel','Nahuel','Ferrari',0,14,NULL,NULL,NULL,NULL),(161,'Vazquez Nicolas','Nicolas','Vazquez',0,14,NULL,NULL,NULL,NULL),(162,'Cid Juan','Juan','Cid',0,14,NULL,NULL,NULL,NULL),(163,'Trippi Hugo','Hugo','Trippi',0,14,NULL,NULL,NULL,NULL),(164,'Brinco Matias','Matias','Brinco',0,14,NULL,NULL,NULL,NULL),(165,'Lucero Lucas','Lucas','Lucero',0,14,NULL,NULL,NULL,NULL),(166,'Rosell? Tabar?','Tabar?','Rosell?',1,15,NULL,NULL,NULL,NULL),(167,'Scarpino Rodrigo','Rodrigo','Scarpino',0,15,NULL,NULL,NULL,NULL),(168,'De Rose Federico','Federico','De Rose',0,15,NULL,NULL,NULL,NULL),(169,'Vallejos Leandro','Leandro','Vallejos',0,15,NULL,NULL,NULL,NULL),(170,'Cainero Ariel','Ariel','Cainero',0,15,NULL,NULL,NULL,NULL),(171,'Di Giorgio Juli?n','Juli?n','Di Giorgio',0,15,NULL,NULL,NULL,NULL),(172,'Sironi Mauro','Mauro','Sironi',0,15,NULL,NULL,NULL,NULL),(173,'Cantero Javier','Javier','Cantero',0,15,NULL,NULL,NULL,NULL),(174,'Velazquez Mario','Mario','Velazquez',0,15,NULL,NULL,NULL,NULL),(175,'Sandez Diego','Diego','Sandez',0,15,NULL,NULL,NULL,NULL),(176,'Jimenez Ignacio','Ignacio','Jimenez',0,15,NULL,NULL,NULL,NULL),(177,'Maciel David','David','Maciel',0,16,NULL,NULL,NULL,NULL),(178,'Alzaraz Pablo','Pablo','Alzaraz',0,16,NULL,NULL,NULL,NULL),(179,'Mambrin Raul','Raul','Mambrin',0,16,NULL,NULL,NULL,NULL),(180,'Gramado Alejandro','Alejandro','Gramado',0,16,NULL,NULL,NULL,NULL),(181,'Olmos Cristian','Cristian','Olmos',0,16,NULL,NULL,NULL,NULL),(182,'Gil Ruben','Ruben','Gil',0,16,NULL,NULL,NULL,NULL),(183,'Castillo Juan','Juan','Castillo',0,16,NULL,NULL,NULL,NULL),(184,'Gil Gabriel','Gabriel','Gil',1,16,NULL,NULL,NULL,NULL),(185,'Oliva Omar','Omar','Oliva',0,16,NULL,NULL,NULL,NULL),(186,'Castro Diego','Diego','Castro',0,16,NULL,NULL,NULL,NULL),(187,'Lugones Marcos','Marcos','Lugones',0,17,NULL,NULL,NULL,NULL),(188,'Anselmo Matias','Matias','Anselmo',0,17,NULL,NULL,NULL,NULL),(189,'Campo Fernando','Fernando','Campo',1,17,NULL,NULL,NULL,NULL),(190,'Neila Nicolas','Nicolas','Neila',0,17,NULL,NULL,NULL,NULL),(191,'Reynoso Manuel','Manuel','Reynoso',0,17,NULL,NULL,NULL,NULL),(192,'Barrios Matias','Matias','Barrios',0,17,NULL,NULL,NULL,NULL),(193,'Vieytes Santiago','Santiago','Vieytes',0,17,NULL,NULL,NULL,NULL),(194,'Servin Alejandro','Alejandro','Servin',0,17,NULL,NULL,NULL,NULL),(195,'Carrazco Juan','Juan','Carrazco',0,17,NULL,NULL,NULL,NULL),(196,'Campo Jorge','Jorge','Campo',0,17,NULL,NULL,NULL,NULL),(197,'Domi?i Luciano','Luciano','Domi?i',0,17,NULL,NULL,NULL,NULL),(198,'Morales Gerardo','Gerardo','Morales',1,18,NULL,NULL,NULL,NULL),(199,'Zerda Pablo','Pablo','Zerda',0,18,NULL,NULL,NULL,NULL),(200,'Arena Walter','Walter','Arena',0,18,NULL,NULL,NULL,NULL),(201,'Vega Maximiliano','Maximiliano','Vega',0,18,NULL,NULL,NULL,NULL),(202,'Morales Lautaro','Lautaro','Morales',0,18,NULL,NULL,NULL,NULL),(203,'Gonzalez Maximiliano','Gonzalez Maximiliano','Maximiliano',0,18,NULL,NULL,NULL,NULL),(204,'Riso Ariel','Ariel','Riso',0,18,NULL,NULL,NULL,NULL),(205,'Arias Sebastian','Sebastian','Arias',0,18,NULL,NULL,NULL,NULL),(206,'Maia Jonatan','Jonatan','Maia',0,18,NULL,NULL,NULL,NULL),(207,'Touceda Alberto','Alberto','Touceda',0,18,NULL,NULL,NULL,NULL),(208,'Nievas Fernando','Fernando','Nievas',0,18,NULL,NULL,NULL,NULL),(209,'Velazquez Pablo','Pablo','Velazquez',1,19,NULL,NULL,NULL,NULL),(210,'Bruzzeze Nicolas','Nicolas','Bruzzeze',0,19,NULL,NULL,NULL,NULL),(211,'Velarde Luciano','Luciano','Velarde',0,19,NULL,NULL,NULL,NULL),(212,'Giacalone Franco','Franco','Giacalone',0,19,NULL,NULL,NULL,NULL),(213,'Gomez Braian','Braian','Gomez',0,19,NULL,NULL,NULL,NULL),(214,'Emanuelli Rodrigo','rodrigo','Emanuelli',0,19,NULL,NULL,NULL,NULL),(215,'Perez Martin','Martin','Perez',0,19,NULL,NULL,NULL,NULL),(216,'Petinato Roberto','Roberto','Petinato',0,19,NULL,NULL,NULL,NULL),(217,'Mangiamele Ezequiel','Ezequiel','Mangiamele',1,20,NULL,NULL,NULL,NULL),(218,'Rolon Ivan','Ivan','Rolon',0,20,NULL,NULL,NULL,NULL),(219,'Olivera Cristian','Cristian','Olivera',0,20,NULL,NULL,NULL,NULL),(220,'Duarte Luis','Luis','Duarte',0,20,NULL,NULL,NULL,NULL),(221,'Diaz Hector','Hector','Diaz',0,20,NULL,NULL,NULL,NULL),(222,'Holgado Matias','Matias','Holgado',0,20,NULL,NULL,NULL,NULL),(223,'Barreto Ezequiel','Ezequiel','Barreto',0,20,NULL,NULL,NULL,NULL),(224,'Rodriguez Javier','Javier','Rodriguez',0,20,NULL,NULL,NULL,NULL),(225,'Martin Alejandro','Alejandro','Martin',1,21,NULL,NULL,NULL,NULL),(226,'Jonatan','Jonatan','',1,22,NULL,NULL,NULL,NULL),(227,'Tolosa Gabriel','Gabriel','Tolosa',1,23,NULL,NULL,NULL,NULL),(228,'Goro Leo','Leo','Goro',1,24,NULL,NULL,NULL,NULL),(229,'Garcia Ramiro','Ramiro','Garcia',0,25,NULL,NULL,NULL,NULL),(230,'Vallejo Erik','Erik','Vallejo',0,25,NULL,NULL,NULL,NULL),(231,'Ayala Cristian','Cristian','Ayala',0,25,NULL,NULL,NULL,NULL),(232,'Romano Jonathan','Jonathan','Romano',0,25,NULL,NULL,NULL,NULL),(233,'Miranda Yonatan','Yonatan','Miranda',0,25,NULL,NULL,NULL,NULL),(234,'Vazquez Matias','Matias','Vazquez',1,25,NULL,NULL,NULL,NULL),(235,'Cespi Claudio','Claudio','Cespi',0,25,NULL,NULL,NULL,NULL),(236,'Martinez Ezequiel','Ezequiel','Martinez',0,25,NULL,NULL,NULL,NULL),(237,'Hermo Pablo','Pablo','Hermo',0,25,NULL,NULL,NULL,NULL),(238,'Paz Alejandro','Alejandro','Paz',0,25,NULL,NULL,NULL,NULL),(239,'Romano Lucas','Lucas','Romano',0,25,NULL,NULL,NULL,NULL),(240,'Ledesma Camilo','Camilo','Ledesma',1,26,NULL,NULL,NULL,NULL),(241,'Vazquez Damian','Damian','Vazquez',0,26,NULL,NULL,NULL,NULL),(242,'Ojeda Lucas','Lucas','Ojeda',0,26,NULL,NULL,NULL,NULL),(243,'Navarro Eduardo','Eduardo','Navarro',0,26,NULL,NULL,NULL,NULL),(244,'Vazquez Marcelo','Marcelo','Vazquez',0,26,NULL,NULL,NULL,NULL),(245,'Toledo Cristian','Cristian','Toledo',0,26,NULL,NULL,NULL,NULL),(246,'Ovielar Daniel','Daniel','Ovielar',0,26,NULL,NULL,NULL,NULL),(247,'Navarro Angel','Angel','Navarro',1,27,NULL,NULL,NULL,NULL),(248,'Lopez Hector','Hector','Lopez',0,27,NULL,NULL,NULL,NULL),(249,'Bogado Horacio Manuel','Horacio Manuel','Bogado',1,28,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tfc_jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_login_attempts`
--

DROP TABLE IF EXISTS `tfc_login_attempts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_login_attempts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(40) NOT NULL,
  `login` varchar(50) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_login_attempts`
--

LOCK TABLES `tfc_login_attempts` WRITE;
/*!40000 ALTER TABLE `tfc_login_attempts` DISABLE KEYS */;
/*!40000 ALTER TABLE `tfc_login_attempts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_noticias`
--

DROP TABLE IF EXISTS `tfc_noticias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_noticias` (
  `idnoticias` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idtorneo` int(11) DEFAULT NULL,
  `titulo` varchar(200) DEFAULT NULL,
  `contenido` text,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`idnoticias`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_noticias`
--

LOCK TABLES `tfc_noticias` WRITE;
/*!40000 ALTER TABLE `tfc_noticias` DISABLE KEYS */;
INSERT INTO `tfc_noticias` VALUES (2,1,'asd','<p><span style=\"color:#FFD700\">asdasda</span></p>\r\n','2013-08-07 11:40:00');
/*!40000 ALTER TABLE `tfc_noticias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_partido`
--

DROP TABLE IF EXISTS `tfc_partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_partido` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT NULL,
  `idtorneo` int(11) DEFAULT NULL,
  `idfase` int(11) DEFAULT NULL,
  `idequipo1` int(11) DEFAULT NULL,
  `idequipo2` int(11) DEFAULT NULL,
  `goles_equipo1` int(11) DEFAULT NULL,
  `goles_equipo2` int(11) DEFAULT NULL,
  `fecha_torneo` int(11) DEFAULT NULL,
  `jugado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_partido`
--

LOCK TABLES `tfc_partido` WRITE;
/*!40000 ALTER TABLE `tfc_partido` DISABLE KEYS */;
INSERT INTO `tfc_partido` VALUES (42,'2013-09-20 00:00:00',15,1,7,9,0,0,0,0),(43,NULL,15,2,6,4,NULL,NULL,2,NULL),(44,NULL,15,3,8,5,NULL,NULL,3,NULL),(45,NULL,15,1,9,5,NULL,NULL,1,NULL),(46,NULL,15,2,4,8,NULL,NULL,2,NULL),(47,NULL,15,3,7,6,NULL,NULL,3,NULL),(48,NULL,15,1,6,9,NULL,NULL,1,NULL),(49,NULL,15,2,8,7,NULL,NULL,2,NULL),(50,NULL,15,3,5,4,NULL,NULL,3,NULL),(51,NULL,15,1,9,4,NULL,NULL,1,NULL),(52,NULL,15,2,7,5,NULL,NULL,2,NULL),(53,NULL,15,3,6,8,NULL,NULL,3,NULL),(54,NULL,15,1,8,9,NULL,NULL,1,NULL),(55,NULL,15,2,5,6,NULL,NULL,2,NULL),(56,NULL,15,3,4,7,NULL,NULL,3,NULL),(94,NULL,19,1,5,6,NULL,NULL,1,NULL),(95,NULL,19,1,4,5,NULL,NULL,2,NULL),(96,NULL,19,1,6,4,NULL,NULL,3,NULL),(97,NULL,19,1,4,6,NULL,NULL,4,NULL),(98,NULL,19,1,5,4,NULL,NULL,5,NULL),(99,NULL,19,1,6,5,NULL,NULL,6,NULL);
/*!40000 ALTER TABLE `tfc_partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_permissions`
--

DROP TABLE IF EXISTS `tfc_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_permissions` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(100) NOT NULL,
  `status` enum('active','inactive','deleted') DEFAULT 'active',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_permissions`
--

LOCK TABLES `tfc_permissions` WRITE;
/*!40000 ALTER TABLE `tfc_permissions` DISABLE KEYS */;
INSERT INTO `tfc_permissions` VALUES (2,'Site.Content.View','Allow users to view the Content Context','active'),(3,'Site.Reports.View','Allow users to view the Reports Context','active'),(4,'Site.Settings.View','Allow users to view the Settings Context','active'),(5,'Site.Developer.View','Allow users to view the Developer Context','active'),(6,'Bonfire.Roles.Manage','Allow users to manage the user Roles','active'),(7,'Bonfire.Users.Manage','Allow users to manage the site Users','active'),(8,'Bonfire.Users.View','Allow users access to the User Settings','active'),(9,'Bonfire.Users.Add','Allow users to add new Users','active'),(10,'Bonfire.Database.Manage','Allow users to manage the Database settings','active'),(11,'Bonfire.Emailer.Manage','Allow users to manage the Emailer settings','active'),(12,'Bonfire.Logs.View','Allow users access to the Log details','active'),(13,'Bonfire.Logs.Manage','Allow users to manage the Log files','active'),(14,'Bonfire.Emailer.View','Allow users access to the Emailer settings','active'),(15,'Site.Signin.Offline','Allow users to login to the site when the site is offline','active'),(16,'Bonfire.Permissions.View','Allow access to view the Permissions menu unders Settings Context','active'),(17,'Bonfire.Permissions.Manage','Allow access to manage the Permissions in the system','active'),(18,'Bonfire.Roles.Delete','Allow users to delete user Roles','active'),(19,'Bonfire.Modules.Add','Allow creation of modules with the builder.','active'),(20,'Bonfire.Modules.Delete','Allow deletion of modules.','active'),(21,'Permissions.Administrator.Manage','To manage the access control permissions for the Administrator role.','active'),(22,'Permissions.Editor.Manage','To manage the access control permissions for the Editor role.','active'),(24,'Permissions.User.Manage','To manage the access control permissions for the User role.','active'),(25,'Permissions.Developer.Manage','To manage the access control permissions for the Developer role.','active'),(27,'Activities.Own.View','To view the users own activity logs','active'),(28,'Activities.Own.Delete','To delete the users own activity logs','active'),(29,'Activities.User.View','To view the user activity logs','active'),(30,'Activities.User.Delete','To delete the user activity logs, except own','active'),(31,'Activities.Module.View','To view the module activity logs','active'),(32,'Activities.Module.Delete','To delete the module activity logs','active'),(33,'Activities.Date.View','To view the users own activity logs','active'),(34,'Activities.Date.Delete','To delete the dated activity logs','active'),(35,'Bonfire.UI.Manage','Manage the Bonfire UI settings','active'),(36,'Bonfire.Settings.View','To view the site settings page.','active'),(37,'Bonfire.Settings.Manage','To manage the site settings.','active'),(38,'Bonfire.Activities.View','To view the Activities menu.','active'),(39,'Bonfire.Database.View','To view the Database menu.','active'),(40,'Bonfire.Migrations.View','To view the Migrations menu.','active'),(41,'Bonfire.Builder.View','To view the Modulebuilder menu.','active'),(42,'Bonfire.Roles.View','To view the Roles menu.','active'),(43,'Bonfire.Sysinfo.View','To view the System Information page.','active'),(44,'Bonfire.Translate.Manage','To manage the Language Translation.','active'),(45,'Bonfire.Translate.View','To view the Language Translate menu.','active'),(46,'Bonfire.UI.View','To view the UI/Keyboard Shortcut menu.','active'),(47,'Bonfire.Update.Manage','To manage the Bonfire Update.','active'),(48,'Bonfire.Update.View','To view the Developer Update menu.','active'),(49,'Bonfire.Profiler.View','To view the Console Profiler Bar.','active'),(50,'Bonfire.Roles.Add','To add New Roles','active'),(55,'Noticias.Content.View','','active'),(56,'Noticias.Content.Create','','active'),(57,'Noticias.Content.Edit','','active'),(58,'Noticias.Content.Delete','','active'),(59,'Equipos.Content.View','','active'),(60,'Equipos.Content.Create','','active'),(61,'Equipos.Content.Edit','','active'),(62,'Equipos.Content.Delete','','active'),(63,'Jugadores.Content.View','','active'),(64,'Jugadores.Content.Create','','active'),(65,'Jugadores.Content.Edit','','active'),(66,'Jugadores.Content.Delete','','active'),(67,'Administrar_Torneos.Content.View','','active'),(68,'Administrar_Torneos.Content.Create','','active'),(69,'Administrar_Torneos.Content.Edit','','active'),(70,'Administrar_Torneos.Content.Delete','','active'),(87,'Administrar_Partidos.Content.View','','active'),(88,'Administrar_Partidos.Content.Create','','active'),(89,'Administrar_Partidos.Content.Edit','','active'),(90,'Administrar_Partidos.Content.Delete','','active'),(91,'Administracion_Tabla_Posiciones.Content.View','','active'),(92,'Administracion_Tabla_Posiciones.Content.Create','','active'),(93,'Administracion_Tabla_Posiciones.Content.Edit','','active'),(94,'Administracion_Tabla_Posiciones.Content.Delete','','active');
/*!40000 ALTER TABLE `tfc_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_role_permissions`
--

DROP TABLE IF EXISTS `tfc_role_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_role_permissions` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_role_permissions`
--

LOCK TABLES `tfc_role_permissions` WRITE;
/*!40000 ALTER TABLE `tfc_role_permissions` DISABLE KEYS */;
INSERT INTO `tfc_role_permissions` VALUES (1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,24),(1,25),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41),(1,42),(1,43),(1,44),(1,45),(1,46),(1,47),(1,48),(1,49),(1,50),(1,55),(1,56),(1,57),(1,58),(1,59),(1,60),(1,61),(1,62),(1,63),(1,64),(1,65),(1,66),(1,67),(1,68),(1,69),(1,70),(1,87),(1,88),(1,89),(1,90),(1,91),(1,92),(1,93),(1,94),(2,2),(2,3),(2,55),(2,56),(2,57),(2,58),(2,59),(2,60),(2,61),(2,62),(2,63),(2,64),(2,65),(2,66),(2,67),(2,68),(2,69),(2,70),(6,2),(6,3),(6,4),(6,5),(6,6),(6,7),(6,8),(6,9),(6,10),(6,11),(6,12),(6,13),(6,49);
/*!40000 ALTER TABLE `tfc_role_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_roles`
--

DROP TABLE IF EXISTS `tfc_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(60) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `default` tinyint(1) NOT NULL DEFAULT '0',
  `can_delete` tinyint(1) NOT NULL DEFAULT '1',
  `login_destination` varchar(255) NOT NULL DEFAULT '/',
  `deleted` int(1) NOT NULL DEFAULT '0',
  `default_context` varchar(255) NOT NULL DEFAULT 'content',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_roles`
--

LOCK TABLES `tfc_roles` WRITE;
/*!40000 ALTER TABLE `tfc_roles` DISABLE KEYS */;
INSERT INTO `tfc_roles` VALUES (1,'Administrator','Has full control over every aspect of the site.',0,0,'',0,'content'),(2,'Editor','Can handle day-to-day management, but does not have full power.',1,1,'',0,'content'),(4,'User','This is the default user with access to login.',0,0,'',0,'content'),(6,'Developer','Developers typically are the only ones that can access the developer tools. Otherwise identical to Administrators, at least until the site is handed off.',0,1,'',0,'content');
/*!40000 ALTER TABLE `tfc_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_schema_version`
--

DROP TABLE IF EXISTS `tfc_schema_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_schema_version` (
  `type` varchar(40) NOT NULL,
  `version` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_schema_version`
--

LOCK TABLES `tfc_schema_version` WRITE;
/*!40000 ALTER TABLE `tfc_schema_version` DISABLE KEYS */;
INSERT INTO `tfc_schema_version` VALUES ('administracion_tabla_posiciones_',1),('administrar_partidos_',1),('administrar_torneos_',1),('app_',0),('core',35),('equipos_',1),('jugadores_',1),('noticias_',1);
/*!40000 ALTER TABLE `tfc_schema_version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_sessions`
--

DROP TABLE IF EXISTS `tfc_sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_sessions` (
  `session_id` varchar(40) NOT NULL DEFAULT '0',
  `ip_address` varchar(45) NOT NULL DEFAULT '0',
  `user_agent` varchar(120) NOT NULL,
  `last_activity` int(10) unsigned NOT NULL DEFAULT '0',
  `user_data` text,
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_sessions`
--

LOCK TABLES `tfc_sessions` WRITE;
/*!40000 ALTER TABLE `tfc_sessions` DISABLE KEYS */;
/*!40000 ALTER TABLE `tfc_sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_settings`
--

DROP TABLE IF EXISTS `tfc_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_settings` (
  `name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `module` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `value` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `unique - name` (`name`),
  KEY `index - name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_settings`
--

LOCK TABLES `tfc_settings` WRITE;
/*!40000 ALTER TABLE `tfc_settings` DISABLE KEYS */;
INSERT INTO `tfc_settings` VALUES ('auth.allow_name_change','core','1'),('auth.allow_register','core','0'),('auth.allow_remember','core','1'),('auth.do_login_redirect','core','1'),('auth.login_type','core','email'),('auth.name_change_frequency','core','1'),('auth.name_change_limit','core','1'),('auth.password_force_mixed_case','core','0'),('auth.password_force_numbers','core','0'),('auth.password_force_symbols','core','0'),('auth.password_min_length','core','8'),('auth.password_show_labels','core','0'),('auth.remember_length','core','1209600'),('auth.use_extended_profile','core','0'),('auth.use_usernames','core','1'),('auth.user_activation_method','core','0'),('form_save','core.ui','ctrl+s/⌘+s'),('goto_content','core.ui','alt+c'),('mailpath','email','/usr/sbin/sendmail'),('mailtype','email','text'),('protocol','email','mail'),('sender_email','email','hortelanobruno@gmail.com'),('site.languages','core','a:3:{i:0;s:7:\"english\";i:1;s:7:\"persian\";i:2;s:10:\"portuguese\";}'),('site.list_limit','core','25'),('site.show_front_profiler','core','1'),('site.show_profiler','core','1'),('site.status','core','1'),('site.system_email','core','hortelanobruno@gmail.com'),('site.title','core','TFC'),('smtp_host','email',''),('smtp_pass','email',''),('smtp_port','email',''),('smtp_timeout','email',''),('smtp_user','email',''),('updates.bleeding_edge','core','1'),('updates.do_check','core','1');
/*!40000 ALTER TABLE `tfc_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_sponsor`
--

DROP TABLE IF EXISTS `tfc_sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_sponsor` (
  `idsponsor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titulo` varchar(200) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `image_hover` varchar(200) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idsponsor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_sponsor`
--

LOCK TABLES `tfc_sponsor` WRITE;
/*!40000 ALTER TABLE `tfc_sponsor` DISABLE KEYS */;
/*!40000 ALTER TABLE `tfc_sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_torneo`
--

DROP TABLE IF EXISTS `tfc_torneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_torneo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) DEFAULT NULL,
  `categoria` int(11) DEFAULT NULL,
  `logo_chico` varchar(200) DEFAULT NULL,
  `logo_grande` varchar(200) DEFAULT NULL,
  `cantidad_tarjetas_amarillas` int(11) DEFAULT NULL,
  `cantidad_fechas` int(11) DEFAULT NULL,
  `cantidad_partidos` int(11) DEFAULT NULL,
  `cantidad_equipos` int(11) DEFAULT NULL,
  `cantidad_tarjetas_rojas` int(11) DEFAULT NULL,
  `cantidad_goles` int(11) DEFAULT NULL,
  `cantidad_equipos_ascienden` int(11) DEFAULT NULL,
  `cantidad_equipos_descienden` int(11) DEFAULT NULL,
  `archivado` tinyint(1) DEFAULT NULL,
  `informaciongeneral` text,
  `reglamento` text,
  `ida_y_vuelta_grupo` tinyint(1) DEFAULT NULL,
  `ida_y_vuelta_llave` tinyint(1) DEFAULT NULL,
  `group_size` int(11) DEFAULT NULL,
  `cant_pass_to_llave` int(11) DEFAULT NULL,
  `cant_fases` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_torneo`
--

LOCK TABLES `tfc_torneo` WRITE;
/*!40000 ALTER TABLE `tfc_torneo` DISABLE KEYS */;
INSERT INTO `tfc_torneo` VALUES (1,'Primera A Apertura 2013',1,'/assets/uploads/pages_2d5f74974a92c68008605371d23ef378.png','/assets/uploads/pages_2d5f74974a92c68008605371d23ef378.png',0,0,0,0,0,0,0,0,1,'<p><br />\r\nE&#39; in arrivo la terza edizione della COPPA CALCIOTTO, l&#39;unica competizione che riunisce tutte le gloriose squadre del TORNEOCALCIOTTO.<br />\r\n<br />\r\nAlla CoppaCalciotto parteciperanno 27 squadre, divise in 7 gironi da 4 squadre.<br />\r\nOgni girone avr&agrave; un proprio giorno di appartenenza!<br />\r\n<br />\r\nPasseranno alla fase finale le prime 2 classificate di ogni girone.<br />\r\n<br />\r\nIl costo per le squadre iscritte al TORNEOCALCIOTTO 16&deg;edizione &egrave; GRATIS (da consegnare solo la cauzione pari a 80&euro;).<br />\r\n<br />\r\nPer le altre squadre, invece, il costo di iscrizione &egrave; di soli 40&euro;!<br />\r\n<br />\r\nStart: Lunedi 1 Luglio<br />\r\nFinish: Mercoledi 31 luglio<br />\r\n<br />\r\nPREMIO<br />\r\nLa squadra vincitrice avr&agrave; accesso alla Fase Nazionale del Circuito 24H.<br />\r\nLe finali si terranno a Cesenatico, il 7 e 8 Settembre 2013!<br />\r\n<br />\r\nChe aspetti? Quest&#39;estate non rimanere a guardare l&#39;evento pi&ugrave; caldo del panorama calcistico amatoriale<br />\r\n<br />\r\nInfo: 3381031943 (Andrea)<br />\r\nasdarend@hotmail.it -&nbsp;<a href=\"http://www.facebook.com/l.php?u=http%3A%2F%2Fwww.asdarend.it&amp;h=bAQGzEyNc&amp;s=1\" rel=\"nofollow nofollow\" target=\"_blank\">www.asdarend.it</a></p>\r\n','',0,0,0,0,3),(2,'Primera B Apertura 2013',1,'/assets/uploads/pages_c8c01b2fda0e0705affe90458b86478b.png','/assets/uploads/pages_2d5f74974a92c68008605371d23ef378.png',0,0,0,0,0,0,0,0,1,'','',0,0,0,0,3),(15,'Torneo A',1,'','',0,0,0,0,0,0,0,0,0,'','',0,0,0,0,5),(19,'Torneo B',1,'','',0,6,6,3,0,0,0,0,0,'','',1,0,0,0,1);
/*!40000 ALTER TABLE `tfc_torneo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_torneo_reglamento`
--

DROP TABLE IF EXISTS `tfc_torneo_reglamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_torneo_reglamento` (
  `idtorneo` int(11) NOT NULL,
  `reglamento` longtext,
  PRIMARY KEY (`idtorneo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_torneo_reglamento`
--

LOCK TABLES `tfc_torneo_reglamento` WRITE;
/*!40000 ALTER TABLE `tfc_torneo_reglamento` DISABLE KEYS */;
INSERT INTO `tfc_torneo_reglamento` VALUES (1,'ZIO E RIPRESA DEL GIUOCO</strong></p><ol>    <li>Preliminari<br>• La scelta della metà del rettangolo di giuoco viene sorteggiata con una moneta. La squadra che vince il sorteggio sceglie la porta contro cui attaccherà nel primo periodo di giuoco. L\'altra squadra ha diritto a battere il calcio d\'inizio della gara.<br>• La squadra che vince il sorteggio ha diritto a battere il calcio d\'inizio del secondo tempo della gara.<br>Nel secondo periodo della gara le squadre invertono le rispettive metà del rettangolo da giuoco ed attaccano in direzione della porta opposta.<br>•Ogni squadra deve far scendere in campo 6 giocatori. Si gioca con 8 giocatori per squadra</li>    <li>Il calcio d\'inizio<br>• Il calcio d\'inizio è un modo di iniziare la gara o di riprendere il giuoco:<br>a) all\'inizio della gara;<br>b) dopo che una rete è stata segnata;<br>c) all\'inizio del secondo periodo di giuoco;<br>•Una rete può essere segnata direttamente su calcio d\'inizio.</li>    <li>Procedura<br>• La procedura per il calcio d\'inizio è la seguente:<br>a) tutti i calciatori devono trovarsi nelle rispettive metà del rettangolo di giuoco;<br>b) i calciatori della squadra che non batte il calcio d\'inizio devono posizionarsi ad una distanza di almeno m. 1 dal pallone fino a quando questo non sia in giuoco;<br>c) il pallone deve essere fermo al suolo nel punto centrale del rettangolo di giuoco;<br>d) l\'arbitro emette il fischio che autorizza il calcio d\'inizio;<br>e) il pallone è in giuoco quando viene calciato e si muove in avanti;<br>f) il calciatore che batte il calcio d\'inizio non può toccare nuovamente il pallone prima che lo abbia toccato un altro calciatore;<br>g) quando una squadra segna una rete, il calcio d\'inizio spetta all\'altra squadra.</li>    <li>Infrazioni e Sanzioni<br>• Se il calciatore che ha battuto il calcio d\'inizio giuoca una seconda volta il pallone prima che questo sia stato toccato o giuocato da un altro calciatore dovrà essere accordato un calcio di punizione indiretto a favore della squadra avversaria, dal punto in cui è stata commessa l\'infrazione.<br>• Per qualsiasi altra infrazione commessa nel battere il calcio d\'inizio, quest\'ultimo deve essere ripetuto.</li>    <li>Rimessa in giuoco del pallone<br>• Dopo un\'interruzione temporanea di giuoco provocata da una causa non prevista dalle Regole del Giuoco la gara deve essere ripresa con una rimessa in giuoco del pallone da parte dell\'arbitro.</li>    <li>Procedura per la rimessa in giuoco del pallone<br>• La procedura è la seguente:<br>a) l’ arbitro lascia cadere il pallone nel punto in cui si trovava al momento dell\'interruzione, salvo che si tratti dell\'area di rigore; in tale caso deve farlo cadere sulla linea dell\'area di rigore, nel punto più vicino a quello in cui si trovava al momento dell\'interruzione.<br>b) il giuoco riprende non appena il pallone tocca il suolo.</li>    <li>Infrazioni e Sanzioni<br>• La rimessa del pallone deve essere ripetuta:<br>a) se il pallone viene toccato da un calciatore prima di entrare in contatto con il suolo;<br>b) se il pallone esce dal rettangolo di giuoco dopo il contatto con il suolo, senza essere stato toccato da un calciatore.</li></ol><p><strong>REGOLA 20 - PALLONE IN GIUOCO E NON IN GIUOCO</strong></p><ol>    <li>Pallone non in giuoco<br>Il pallone non è in giuoco quando:<br>a) ha interamente oltrepassato, sia in terra sia in aria, una linea laterale o una linea di porta;<br>b) il giuoco è stato interrotto dall\'arbitro;<br>c) tocca il soffitto.</li>    <li>Pallone in giuoco<br>Il pallone è in giuoco in tutti gli altri casi ivi compreso quando:<br>a) rimbalza nel rettangolo di giuoco dopo aver colpito un palo o la sbarra trasversale della porta;<br>b) rimbalza su uno degli arbitri che si trova all\'interno del rettangolo di giuoco.</li></ol><p><strong>REGOLA 21 - SEGNATURA DI UNA RETE</strong></p><ol>    <li>Segnatura di una rete<br>Salvo le eccezioni previste dalle Regole del Giuoco, una rete risulta segnata quando il pallone ha interamente superato la linea di porta, tra i pali e sotto la sbarra trasversale, sempreché non sia stato lanciato, portato o colpito intenzionalmente con la mano o con il braccio da un calciatore della squadra attaccante, portiere compreso.</li>    <li>Squadra vincente<br>La squadra che ha segnato il maggior numero di reti durante una gara, risulta vincente. Se non è stata segnata nessuna rete o se le squadre hanno segnato un eguale numero di reti, la gara risulta pari.</li>    <li>Regole della competizione<br>Per le partite che si concludono in parità nelle fasi finali, le regole della competizione prevedono i calci di rigore senza tempi supplementari.</li></ol><p><strong>REGOLA 22 - FALLI E COMPORTAMENTO ANTISPORTIVO</strong></p><ol>    <li>I falli ed i comportamenti antisportivi devono essere puniti come segue:<br>• Calcio di punizione diretto<br>Alla squadra avversaria viene accordato un calcio di punizione diretto quando un calciatore commette uno dei seguenti sei falli:<br>a) dare o tentare di dare un calcio ad un avversario;<br>b) fare o tentare di fare uno sgambetto all\'avversario;<br>c) saltare su un avversario;<br>d) caricare un avversario, anche con la spalla;<br>e) colpire o tentare di colpire un avversario<br>f) spingere un avversario.<br>• Alla squadra avversaria viene accordato un calcio di punizione diretto anche quando un calciatore commette una delle seguenti infrazioni:<br>g) trattiene un avversario;<br>h) sputa contro un avversario;<br>i) tenta di giocare il pallone intervenendo in scivolata da tergo su un avversario che sia in possesso del pallone o che sia in procinto di giocarlo (contrasto da tergo scivolato). Questa norma non si applica al portiere che si trova nella propria area di rigore, purché egli non giochi in maniera imprudente, spericolata o con sproporzionata vigoria;<br>l) tocca deliberatamente il pallone con le mani. Questa norma non si applica al portiere che si trova nella propria area di rigore.<br>Il calcio di punizione diretto va battuto dal punto in cui è stato commesso il fallo.</li>    <li>Calcio di rigore<br>Viene assegnato un calcio di rigore quando un calciatore commette uno dei suddetti falli all\'interno della propria area di rigore, indipendentemente dalla posizione del pallone, purchè lo stesso sia in giuoco.</li>    <li>Calcio di punizione indiretto<br>Alla squadra avversaria viene assegnato un calcio di punizione indiretto quando il portiere commette una delle seguenti infrazioni:<br>a) dopo essersi spossessato del pallone, lo riceve di ritorno da un compagno prima che abbia superato la linea mediana o senza che sia stato giocato o toccato da un avversario;<br>b) tocca o controlla il pallone con le mani (nella propria area di rigore) dopo che questo gli sia stato volontariamente passato con i piedi, da un compagno di squadra;<br>c) tocca o controlla con le mani (nella propria area di rigore) il pallone passatogli direttamente su una rimessa dalla linea laterale, effettuata da un compagno di squadra;<br>Alla squadra avversaria viene assegnato un calcio di punizione indiretto, che dovrà essere battuto dal punto in cui è stata commessa l\'infrazione, anche quando, secondo il parere dell\'arbitro, un calciatore:<br>d) giuoca in modo pericoloso;<br>e) impedisce intenzionalmente la progressione di un avversario senza che il pallone sia giocato;<br>f) ostacola il portiere nell\'atto di liberarsi del pallone che ha tra le mani;<br>h) commette qualsiasi altra infrazione per la quale il giuoco viene interrotto per ammonire o espellere un calciatore.<br>Il calcio di punizione indiretto deve essere battuto nel punto in cui è stata commessa l\'infrazione, salvo che questa non sia stata commessa all\'interno della propria area di rigore. In questo caso il calcio di punizione dovrà essere battuto da un punto della linea dell\'area di rigore che sia il più vicino possibile a quello dove è stata commessa l\'infrazione.</li>    <li>Sanzioni disciplinari<br>— Falli passibili di ammonizione —<br>Un calciatore deve essere ammonito con il cartellino giallo quando commette una delle seguenti infrazioni:<br>a) si rende colpevole di comportamento antisportivo;<br>b) manifesta dissenso con parole e gesti;<br>c) trasgredisce ripetutamente le Regole del Giuoco;<br>d) ritarda la ripresa del giuoco;<br>e) non rispetta la distanza prescritta quando il giuoco viene ripreso con un calcio d\'angolo, una rimessa laterale, un calcio di punizione;<br>f) entra o rientra nel rettangolo di giuoco senza il permesso dell\'arbitro o infrange la procedura della sostituzione;<br>g) abbandona deliberatamente il rettangolo di giuoco senza il permesso dell\'arbitro.<br>Per una qualsiasi delle suddette infrazioni, viene accordato, alla squadra avversaria, un calcio di punizione indiretto da battersi nel punto in cui è stata commessa l\'infrazione. Se l\'infrazione è stata commessa all\'interno della propria area di rigore, il calcio di punizione indiretto deve essere battuto sulla linea dell\'area di rigore, nel punto più vicino a quello in cui è stata commessa l\'infrazione.<br>— Falli passibili di espulsione —<br>Un calciatore deve essere espulso con il cartellino rosso quando commette una delle seguenti infrazioni:<br>h) si rende colpevole di condotta violenta;<br>i) si rende colpevole di un fallo violento di giuoco;<br>l) sputa contro un avversario o qualsiasi altra persona;<br>m) priva la squadra avversaria di una rete o di una evidente opportunità di segnare una rete, toccando deliberatamente il pallone con le mani. Questo non si applica ad un portiere all\'interno della propria area di rigore (condotta gravemente sleale);<br>n) priva di una evidente opportunità di segnare una rete un avversario che si dirige verso la porta opposta, mediante un fallo punibile con un calcio di punizione diretto o un calcio di rigore (condotta gravemente<br>sleale);<br>o) usa un linguaggio offensivo, ingiurioso o minaccioso;<br>p) riceve una seconda ammonizione nella stessa gara.<br><br>Se il giuoco viene interrotto perché un calciatore viene espulso per le infrazioni (o) o (p), senza che siano state commesse ulteriori infrazioni alle Regole del Giuoco, il giuoco viene ripreso con un calcio di punizione indiretto, assegnato alla squadra avversaria, dal punto in cui è stata commessa l\'infrazione.<br>Tuttavia, se l\'infrazione viene commessa nella propria area di rigore, il calcio di punizione indiretto viene battuto dalla linea dell\'area di rigore, nel punto più vicino a quello in cui è stata commessa l\'infrazione.<br><br>—&nbsp;Provvedimenti disciplinari dopo espulsione—<br>Dopo un espulsione il giocatore dovrà saltare il turno successivo<br><br>—Condotta gravemente indisciplinata—<br>Se un giocatore si rende colpevole di una condotta gravemente indisciplinata verrà sanzionato dal Giudice sportivo con:<br>-Un numero superiore di una giornata di squalifica<br>-Radiazione del soggetto dal torneo</li></ol><p><strong>REGOLA 23 - CALCI DI PUNIZIONE</strong></p><ol>    <li>Calci di punizione<br>I calci di punizione sono diretti ed indiretti.<br>Nel momento in cui viene battuto il calcio di punizione, il pallone deve essere fermo ed il calciatore che lo ha effettuato non potrà giuocarlo una seconda volta fino a quando il pallone stesso non sia stato toccato o giuocato da un altro calciatore. La distanza dal pallone alla barriera sarà di 5 passi decretati dall’arbitro.</li>    <li>Il calcio di punizione diretto<br>Con un calcio di punizione diretto può essere segnata direttamente una rete contro la squadra che ha commesso il fallo.</li>    <li>Il calcio di punizione indiretto<br>Con un calcio di punizione indiretto può essere segnata una rete soltanto se il pallone tocca un altro calciatore prima di entrare in porta.</li>    <li>Posizione del calcio di punizione<br>a) Quando viene battuto un calcio di punizione, tutti i calciatori della squadra avversaria devono trovarsi ad una distanza di almeno 5 metri dal pallone prima che questo sia giuocato.<br>b) Il pallone è in giuoco dopo che è stato toccato e si muove.</li>    <li>Infrazioni e Sanzioni<br>a) Quando un calciatore della squadra avversaria non rispetta la distanza prescritta per l\'esecuzione di un calcio di punizione, il calcio di punizione deve essere ripetuto;<br>b) Se il calciatore che ha battuto il calcio di punizione tocca una seconda volta il pallone prima che questo sia stato toccato o giuocato da un altro calciatore deve essere concesso, a favore della squadra avversaria, un calcio di punizione indiretto dal punto in cui è stata commessa l\'infrazione. Tuttavia, se<br>quest\'ultima è stata commessa all\'interno dell\'area di rigore, il calcio di punizione indiretto sarà battuto dalla linea dell\'area di rigore, nel punto più vicino a quello in cui l\'infrazione è stata commessa;<br>c) Se la squadra che deve battere il calcio di punizione impiega più di 4 secondi per eseguirlo, sarà accordato un calcio di punizione indiretto alla squadra avversaria.</li>    <li>Segnali<br>a) Calcio di punizione diretto: l\'arbitro mantiene un braccio orizzontalmente indicando la direzione in cui il calcio deve essere battuto. Nel caso in cui il fallo venga considerato come un fallo cumulativo, l\'arbitro punta verso terra con l\'indice dell\'altro braccio appunto per indicare, al terzo arbitro o al cronometrista, che tale fallo viene considerato come fallo cumulativo.<br>b) Calcio di punizione indiretto: l\'arbitro indica un calcio di punizione indiretto alzando il suo braccio al di sopra della testa. Egli mantiene il braccio in tale posizione fino a quando il calcio di punizione non sia stato battuto ed il pallone non abbia toccato un altro calciatore o cessi di essere in giuoco.</li></ol><p><strong>REGOLA 24 - CALCIO DI RIGORE<br></strong>Un calcio di rigore viene concesso contro una squadra che commette una delle infrazioni punibili con un calcio di punizione diretto, all\'interno della propria area di rigore '),(2,'');
/*!40000 ALTER TABLE `tfc_torneo_reglamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_torneo_tabla_posiciones`
--

DROP TABLE IF EXISTS `tfc_torneo_tabla_posiciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_torneo_tabla_posiciones` (
  `idtorneo` int(11) NOT NULL,
  `idfase` int(11) NOT NULL,
  `idequipo` int(11) NOT NULL,
  `puntos` int(11) DEFAULT NULL,
  `partidos_jugados` int(11) DEFAULT NULL,
  `partidos_ganados` int(11) DEFAULT NULL,
  `partidos_empatados` int(11) DEFAULT NULL,
  `partidos_perdidos` int(11) DEFAULT NULL,
  `goles_a_favor` int(11) DEFAULT NULL,
  `goles_en_contra` int(11) DEFAULT NULL,
  PRIMARY KEY (`idtorneo`,`idequipo`,`idfase`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_torneo_tabla_posiciones`
--

LOCK TABLES `tfc_torneo_tabla_posiciones` WRITE;
/*!40000 ALTER TABLE `tfc_torneo_tabla_posiciones` DISABLE KEYS */;
INSERT INTO `tfc_torneo_tabla_posiciones` VALUES (1,1,1,0,9,0,0,9,0,6),(1,1,3,12,9,4,0,5,33,36),(1,1,8,4,9,1,1,7,28,63),(1,1,9,22,9,7,1,1,48,25),(1,1,10,10,9,3,1,5,39,47),(1,1,11,4,9,2,1,6,31,47),(1,1,12,22,9,7,1,1,59,27),(1,1,15,16,9,5,1,3,51,35),(1,1,17,22,9,7,1,1,45,22),(1,1,25,13,9,4,1,4,42,41),(2,1,2,10,9,3,1,5,29,42),(2,1,5,3,4,1,0,3,16,14),(2,1,7,22,9,7,1,1,65,27),(2,1,13,22,9,7,1,1,64,20),(2,1,14,9,9,3,0,6,40,47),(2,1,16,27,9,9,0,0,50,18),(2,1,18,18,9,6,0,3,52,29),(2,1,19,9,9,3,0,6,24,55),(2,1,27,3,7,1,0,6,14,55),(2,1,28,7,9,2,1,6,27,46);
/*!40000 ALTER TABLE `tfc_torneo_tabla_posiciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_user_cookies`
--

DROP TABLE IF EXISTS `tfc_user_cookies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_user_cookies` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(128) NOT NULL,
  `created_on` datetime NOT NULL,
  KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_user_cookies`
--

LOCK TABLES `tfc_user_cookies` WRITE;
/*!40000 ALTER TABLE `tfc_user_cookies` DISABLE KEYS */;
/*!40000 ALTER TABLE `tfc_user_cookies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_user_meta`
--

DROP TABLE IF EXISTS `tfc_user_meta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_user_meta` (
  `meta_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(20) unsigned NOT NULL DEFAULT '0',
  `meta_key` varchar(255) NOT NULL DEFAULT '',
  `meta_value` text,
  PRIMARY KEY (`meta_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_user_meta`
--

LOCK TABLES `tfc_user_meta` WRITE;
/*!40000 ALTER TABLE `tfc_user_meta` DISABLE KEYS */;
INSERT INTO `tfc_user_meta` VALUES (1,2,'state','SC'),(2,2,'country','US'),(3,2,'type','small');
/*!40000 ALTER TABLE `tfc_user_meta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfc_users`
--

DROP TABLE IF EXISTS `tfc_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfc_users` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '4',
  `email` varchar(120) NOT NULL,
  `username` varchar(30) NOT NULL DEFAULT '',
  `password_hash` varchar(40) NOT NULL,
  `reset_hash` varchar(40) DEFAULT NULL,
  `salt` varchar(7) NOT NULL,
  `last_login` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_ip` varchar(40) NOT NULL DEFAULT '',
  `created_on` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `banned` tinyint(1) NOT NULL DEFAULT '0',
  `ban_message` varchar(255) DEFAULT NULL,
  `reset_by` int(10) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT '',
  `display_name_changed` date DEFAULT NULL,
  `timezone` char(4) NOT NULL DEFAULT 'UM6',
  `language` varchar(20) NOT NULL DEFAULT 'english',
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `activate_hash` varchar(40) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfc_users`
--

LOCK TABLES `tfc_users` WRITE;
/*!40000 ALTER TABLE `tfc_users` DISABLE KEYS */;
INSERT INTO `tfc_users` VALUES (1,1,'hortelanobruno@gmail.com','hortelanobruno','e2304661e2ba51ad415a2a0767abdef0700e9bff',NULL,'utI9qQH','2013-09-11 00:14:29','127.0.0.1','0000-00-00 00:00:00',0,0,NULL,NULL,'',NULL,'UM6','english',1,''),(2,2,'gabrielrecchia@hotmail.com','gabrielrecchia','c0876adf312fc5401329dfd1e524af1a4cbc545b',NULL,'zjtCjUM','2013-08-29 03:42:19','186.23.96.234','2013-08-25 15:39:34',0,0,NULL,NULL,'gabriel',NULL,'UM3','english',1,'');
/*!40000 ALTER TABLE `tfc_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'tfc_dev3'
--

--
-- Dumping routines for database 'tfc_dev3'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-09-11  8:53:23
