CREATE DATABASE  IF NOT EXISTS `cshopdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cshopdb`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: cshopdb
-- ------------------------------------------------------
-- Server version	5.5.31-log

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
-- Table structure for table `t_orderlist`
--

DROP TABLE IF EXISTS `t_orderlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_orderlist` (
  `orderlist_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '会员id',
  `address_id` int(11) DEFAULT NULL COMMENT '地址簿id',
  `orderlist_orderdate` datetime NOT NULL COMMENT '下单时间',
  `orderlist_status` int(11) DEFAULT '0' COMMENT '订单状态：\\\\n0：未发货\\\\n1：已发货\\\\n2：已确认发货',
  `orderlist_totalprice` double NOT NULL COMMENT '总价',
  PRIMARY KEY (`orderlist_id`),
  UNIQUE KEY `oderlist_id_UNIQUE` (`orderlist_id`),
  KEY `fk_orderlist_user_idx` (`user_id`),
  KEY `fk_orderlist_address_idx` (`address_id`),
  KEY `FK795DAE1508004F2` (`user_id`),
  CONSTRAINT `FK795DAE1508004F2` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`),
  CONSTRAINT `fk_orderlist_address` FOREIGN KEY (`address_id`) REFERENCES `t_address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_orderlist`
--

LOCK TABLES `t_orderlist` WRITE;
/*!40000 ALTER TABLE `t_orderlist` DISABLE KEYS */;
INSERT INTO `t_orderlist` VALUES (1,1,1,'2013-05-10 00:00:00',0,6500),(2,2,2,'2013-05-11 00:00:00',1,3850),(3,3,3,'2012-12-03 12:13:05',0,6750),(4,3,3,'2013-01-21 15:03:12',0,5600),(5,3,3,'2013-06-05 16:42:25',1,4000),(6,4,4,'2012-05-06 17:05:00',0,8000),(7,4,4,'2012-08-21 01:05:30',0,13000),(8,4,4,'2011-12-05 18:21:12',0,3950),(9,4,4,'2013-02-21 17:12:13',0,16000);
/*!40000 ALTER TABLE `t_orderlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-25 12:03:28
