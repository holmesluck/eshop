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
-- Table structure for table `t_favoritycontent`
--

DROP TABLE IF EXISTS `t_favoritycontent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_favoritycontent` (
  `favoritycontent_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `favority_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`favoritycontent_id`),
  UNIQUE KEY `favority_computer_id_UNIQUE` (`favoritycontent_id`),
  KEY `fk__idx` (`goods_id`),
  KEY `fk_favoritycontent_favority_idx` (`favority_id`),
  CONSTRAINT `fk_favoritycontent_favority` FOREIGN KEY (`favority_id`) REFERENCES `t_favority` (`favority_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_favoritycontent_goods` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_favoritycontent`
--

LOCK TABLES `t_favoritycontent` WRITE;
/*!40000 ALTER TABLE `t_favoritycontent` DISABLE KEYS */;
INSERT INTO `t_favoritycontent` VALUES (1,2,2),(2,7,1),(4,10,3),(8,2,3),(11,10,5),(12,2,5),(13,6,5),(14,9,5),(15,8,5),(18,7,3),(19,68,3),(20,13,3),(21,31,3),(22,32,3),(23,16,3);
/*!40000 ALTER TABLE `t_favoritycontent` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-12 21:39:54
