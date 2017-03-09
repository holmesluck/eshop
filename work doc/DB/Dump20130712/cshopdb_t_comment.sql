CREATE DATABASE  IF NOT EXISTS `cshopdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cshopdb`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: cshopdb
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `comment_date` datetime NOT NULL,
  `comment_mark` tinyint(4) DEFAULT '5',
  `comment_content` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  UNIQUE KEY `comment_id_UNIQUE` (`comment_id`),
  KEY `fk_comment_user_idx` (`user_id`),
  KEY `fk_comment_goods_idx` (`goods_id`),
  CONSTRAINT `fk_comment_goods` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
INSERT INTO `t_comment` VALUES (1,2,1,'2013-06-09 00:35:01',0,'很不错'),(2,6,2,'2013-06-15 16:10:05',0,'nice'),(4,6,1,'2013-03-21 12:02:11',0,'性价比很高。'),(5,6,6,'2013-07-02 14:44:47',2,'sdada'),(6,6,6,'2013-07-02 14:45:20',5,''),(7,6,6,'2013-07-02 16:07:27',5,'fvsdfg'),(8,6,5,'2013-07-03 14:54:26',5,''),(9,9,5,'2013-07-03 14:54:26',5,''),(10,13,5,'2013-07-03 14:54:26',5,''),(11,6,5,'2013-07-03 14:54:32',5,''),(12,6,5,'2013-07-03 14:55:18',5,''),(13,8,5,'2013-07-03 14:55:18',5,''),(14,9,5,'2013-07-03 14:55:18',5,''),(15,2,5,'2013-07-05 10:12:42',5,''),(16,6,5,'2013-07-05 10:12:42',5,''),(17,7,5,'2013-07-05 10:12:42',5,''),(18,8,5,'2013-07-05 10:12:42',5,''),(19,9,5,'2013-07-05 10:12:42',5,''),(20,2,5,'2013-07-05 10:23:48',5,''),(21,6,5,'2013-07-05 10:23:48',5,''),(22,7,5,'2013-07-05 10:23:48',5,''),(23,8,5,'2013-07-05 10:23:48',5,''),(24,9,5,'2013-07-05 10:23:48',5,''),(25,2,5,'2013-07-05 10:23:53',5,''),(26,6,5,'2013-07-05 10:23:53',5,''),(27,7,5,'2013-07-05 10:23:53',5,''),(28,8,5,'2013-07-05 10:23:53',5,''),(29,9,5,'2013-07-05 10:23:53',5,''),(30,2,5,'2013-07-05 10:31:39',5,''),(31,6,5,'2013-07-05 10:31:39',5,''),(32,17,9,'2013-07-05 14:39:24',5,''),(33,16,9,'2013-07-05 14:39:24',5,''),(34,6,9,'2013-07-05 14:39:24',5,''),(35,31,9,'2013-07-05 14:41:02',5,''),(36,29,9,'2013-07-05 14:47:25',5,''),(37,16,9,'2013-07-05 15:10:32',5,''),(38,2,9,'2013-07-05 15:10:32',5,''),(39,6,9,'2013-07-05 15:10:32',5,''),(40,9,9,'2013-07-05 15:10:32',5,''),(41,12,9,'2013-07-05 15:10:32',5,''),(42,6,9,'2013-07-06 21:49:41',5,'很好'),(43,6,9,'2013-07-09 09:34:27',5,'sdad'),(44,21,9,'2013-07-09 09:38:46',5,''),(45,6,9,'2013-07-09 11:12:50',5,''),(46,17,10,'2013-07-09 15:56:25',5,''),(47,6,10,'2013-07-09 15:56:25',5,''),(48,9,10,'2013-07-09 15:56:25',5,''),(49,31,10,'2013-07-09 15:56:25',5,''),(50,9,10,'2013-07-09 16:05:24',5,''),(51,9,10,'2013-07-09 16:06:27',5,''),(52,12,10,'2013-07-09 16:06:27',5,''),(53,13,10,'2013-07-09 16:06:27',5,''),(54,16,10,'2013-07-09 16:08:24',5,''),(55,8,10,'2013-07-09 16:08:24',5,''),(56,9,10,'2013-07-09 16:08:24',5,''),(57,16,10,'2013-07-09 16:11:50',5,''),(58,2,10,'2013-07-09 16:11:50',5,''),(59,9,10,'2013-07-09 16:11:50',5,''),(60,20,9,'2013-07-10 09:44:37',2,'很好'),(61,20,9,'2013-07-10 09:47:00',4,''),(62,20,9,'2013-07-10 09:49:03',5,'好');
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-12 21:39:56
