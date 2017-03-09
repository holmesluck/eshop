CREATE DATABASE  IF NOT EXISTS `cshopdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cshopdb`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: cshopdb
-- ------------------------------------------------------
-- Server version	5.5.31

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
-- Table structure for table `t_secondcatalog`
--

DROP TABLE IF EXISTS `t_secondcatalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_secondcatalog` (
  `secondcatalog_id` int(11) NOT NULL AUTO_INCREMENT,
  `secondcatalog_parentid` int(11) DEFAULT NULL COMMENT '2级目录父目录',
  `secondcatalog_description` varchar(200) DEFAULT NULL,
  `secondcatalog_name` varchar(45) NOT NULL,
  PRIMARY KEY (`secondcatalog_id`),
  UNIQUE KEY `2ndcatalog_id_UNIQUE` (`secondcatalog_id`),
  KEY `fk_2ndcatalog_1stcatalog_idx` (`secondcatalog_parentid`),
  CONSTRAINT `fk_secondcatalog_1stcatalog` FOREIGN KEY (`secondcatalog_parentid`) REFERENCES `t_firstcatalog` (`firstcatalog_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_secondcatalog`
--

LOCK TABLES `t_secondcatalog` WRITE;
/*!40000 ALTER TABLE `t_secondcatalog` DISABLE KEYS */;
INSERT INTO `t_secondcatalog` VALUES (1,1,'','y400系列'),(2,2,'',' E1-471G系列'),(3,3,'','Inspiron 灵越 14R Turbo系列'),(4,4,'','S550系列'),(5,2,NULL,'V5-471G'),(6,2,NULL,'V3-771G'),(7,1,NULL,'G580'),(8,3,NULL,'Inspiron 灵越 17R Turbo系列'),(9,4,NULL,'A53'),(10,4,NULL,'VivoBook S400'),(11,1,NULL,'Yoga13 '),(12,1,NULL,'Z500'),(13,1,NULL,'U310'),(14,2,NULL,'S7-391'),(15,2,NULL,'R7-571'),(16,3,NULL,'Vostro 成就 5460'),(17,3,NULL,'Inspiron 灵越 15R Turbo'),(18,3,NULL,'Inspiron 灵越 14R（5421）'),(19,4,NULL,'A45'),(20,4,NULL,'X55'),(21,4,NULL,'S46');
/*!40000 ALTER TABLE `t_secondcatalog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-26 15:45:29
