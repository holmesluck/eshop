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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_orderlistcontent`
--

LOCK TABLES `t_orderlistcontent` WRITE;
/*!40000 ALTER TABLE `t_orderlistcontent` DISABLE KEYS */;
INSERT INTO `t_orderlistcontent` VALUES (1,2,1,1,6500,6500,6500),(2,6,2,1,3850,3850,3850),(3,16,3,1,6750,6750,6750),(6,8,4,1,5600,5600,5600),(7,9,5,1,4000,4000,4000),(8,9,6,2,4000,4000,4000),(9,2,7,2,6500,6500,13000),(11,72,8,1,3950,3950,3950),(12,70,9,4,4000,400,16000);
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

-- Dump completed on 2013-06-27 11:24:10
