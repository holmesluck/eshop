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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_orderlist`
--

LOCK TABLES `t_orderlist` WRITE;
/*!40000 ALTER TABLE `t_orderlist` DISABLE KEYS */;
INSERT INTO `t_orderlist` VALUES (1,1,1,'2013-05-10 00:00:00',1,6500),(2,2,2,'2013-05-11 00:00:00',1,3850),(3,3,3,'2012-12-03 12:13:05',0,6750),(4,3,3,'2013-01-21 15:03:12',0,5600),(5,3,3,'2013-06-05 16:42:25',1,4000),(6,4,4,'2012-05-06 17:05:00',1,8000),(7,4,4,'2012-08-21 01:05:30',0,13000),(8,4,4,'2011-12-05 18:21:12',1,3950),(9,4,4,'2013-02-21 17:12:13',0,16000),(10,5,5,'2013-07-01 10:05:00',1,7700),(11,5,5,'2013-07-01 10:13:16',2,118187),(12,6,6,'2013-07-01 14:43:27',2,3850),(13,6,6,'2013-07-01 14:58:29',1,7700),(14,5,5,'2013-07-01 16:02:15',2,3850),(15,5,5,'2013-07-01 16:33:51',2,4000),(16,5,5,'2013-07-01 16:34:45',2,3850),(17,5,5,'2013-07-02 10:13:39',2,13450),(18,5,5,'2013-07-02 10:25:54',2,27550),(19,7,7,'2013-07-02 10:41:14',2,19550),(20,7,7,'2013-07-02 10:47:23',2,629400),(21,6,6,'2013-07-02 16:06:58',2,3850),(22,5,6,'2013-07-03 15:02:47',2,42650),(23,5,6,'2013-07-03 15:03:51',2,39748),(24,5,6,'2013-07-03 15:05:01',2,94900),(25,5,6,'2013-07-05 10:12:48',2,45798),(26,5,6,'2013-07-05 10:18:31',2,10980),(27,5,6,'2013-07-05 10:30:47',2,10157.5),(28,5,6,'2013-07-05 15:18:06',0,22597.15),(29,5,6,'2013-07-05 16:04:35',1,3657.5),(30,5,6,'2013-07-08 09:21:54',2,17198.3),(31,5,17,'2013-07-08 10:32:15',2,17941),(32,5,19,'2013-07-08 10:43:44',0,6750);
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

-- Dump completed on 2013-07-08 11:00:10
