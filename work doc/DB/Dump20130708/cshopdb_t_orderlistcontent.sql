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
-- Table structure for table `t_orderlistcontent`
--

DROP TABLE IF EXISTS `t_orderlistcontent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_orderlistcontent` (
  `orderlistcontent_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `orderlist_id` int(11) DEFAULT NULL,
  `orderlistcontent_num` int(11) NOT NULL COMMENT '商品数量',
  `orderlistcontent_originalprice` double NOT NULL COMMENT '原价',
  `orderlistcontent_price` double NOT NULL COMMENT '交易价',
  `orderlistcontent_totalprice` double NOT NULL COMMENT '总价',
  PRIMARY KEY (`orderlistcontent_id`),
  UNIQUE KEY `orderlistcontent_id_UNIQUE` (`orderlistcontent_id`),
  KEY `fk_orderlistcontent_orderlist_idx` (`orderlist_id`),
  KEY `fk_orderlistcontent_goods_idx` (`goods_id`),
  CONSTRAINT `fk_orderlistcontent_goods` FOREIGN KEY (`goods_id`) REFERENCES `t_goods` (`goods_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderlistcontent_orderlist` FOREIGN KEY (`orderlist_id`) REFERENCES `t_orderlist` (`orderlist_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_orderlistcontent`
--

LOCK TABLES `t_orderlistcontent` WRITE;
/*!40000 ALTER TABLE `t_orderlistcontent` DISABLE KEYS */;
INSERT INTO `t_orderlistcontent` VALUES (1,2,1,1,6500,6500,6500),(2,6,2,1,3850,3850,3850),(3,16,3,1,6750,6750,6750),(6,8,4,1,5600,5600,5600),(7,9,5,1,4000,4000,4000),(8,9,6,2,4000,4000,4000),(9,2,7,2,6500,6500,13000),(11,72,8,1,3950,3950,3950),(12,70,9,4,4000,400,16000),(13,2,10,3,6500,6500,19500),(14,6,10,4,3850,3850,15400),(15,7,10,5,6999,6999,34995),(16,8,10,6,5600,5600,33600),(17,9,10,7,4000,4000,28000),(18,2,11,3,6500,6500,19500),(19,6,11,4,3850,3850,15400),(20,7,11,5,6999,6999,34995),(21,8,11,6,5600,5600,33600),(22,9,11,7,4000,4000,28000),(23,6,12,1,3850,3850,3850),(24,6,13,2,3850,3850,7700),(25,6,14,1,3850,3850,3850),(26,9,15,1,4000,4000,4000),(27,6,16,1,3850,3850,3850),(28,6,17,1,3850,3850,3850),(29,8,17,1,5600,5600,5600),(30,9,17,1,4000,4000,4000),(31,6,18,3,3850,3850,11550),(32,9,18,2,4000,4000,8000),(33,13,18,1,8000,8000,8000),(34,6,19,3,3850,3850,11550),(35,9,19,2,4000,4000,8000),(36,2,20,92,6500,6500,598000),(37,6,20,4,3850,3850,15400),(38,9,20,4,4000,4000,16000),(39,6,21,1,3850,3850,3850),(40,6,22,9,3850,3850,34650),(41,13,22,1,8000,8000,8000),(42,34,23,1,4750,4750,4750),(43,33,23,1,6000,6000,6000),(44,40,23,1,9799,9799,9799),(45,31,23,1,11000,11000,11000),(46,30,23,1,8199,8199,8199),(47,64,24,13,4000,4000,52000),(48,65,24,11,3900,3900,42900),(49,68,25,4,3200,3200,12800),(50,7,25,2,6999,6999,13998),(51,13,25,1,8000,8000,8000),(52,31,25,1,11000,11000,11000),(53,2,26,1,6500,6500,6500),(54,8,26,1,5600,4480,4480),(55,2,27,1,6500,6500,6500),(56,6,27,1,3850,3657.5,3657.5),(57,29,28,1,7999,6799.15,6799.15),(58,30,28,1,8199,8199,8199),(59,60,28,1,7599,7599,7599),(60,6,29,1,3850,3657.5,3657.5),(61,19,30,1,3600,3600,3600),(62,29,30,2,7999,6799,13598.3),(63,17,31,1,5300,5141,5141),(64,9,31,4,4000,3200,12800),(65,16,32,1,6750,6615,6750);
/*!40000 ALTER TABLE `t_orderlistcontent` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-08 11:00:11
