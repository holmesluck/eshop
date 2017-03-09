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
-- Table structure for table `t_address`
--

DROP TABLE IF EXISTS `t_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `address_address` varchar(45) NOT NULL,
  `address_postcode` varchar(45) NOT NULL,
  `address_phone` varchar(45) NOT NULL,
  `address_linkman` varchar(45) NOT NULL,
  `address_isdefault` tinyint(1) DEFAULT '0' COMMENT '是否为默认地址：\\\\\\\\\\\\\\\\n0：否\\\\\\\\\\\\\\\\n1：是',
  `address_isdel` tinyint(1) DEFAULT '0' COMMENT '是否已被删除',
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `address_id_UNIQUE` (`address_id`),
  KEY `fk_address_user_idx` (`user_id`),
  CONSTRAINT `fk_address_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_address`
--

LOCK TABLES `t_address` WRITE;
/*!40000 ALTER TABLE `t_address` DISABLE KEYS */;
INSERT INTO `t_address` VALUES (1,1,'广州市番禺区贝岗','510000','2333333','陈东东',0,0),(2,2,'广州市华南理工大学大学城生活区','510000','1566666666','李铭',0,0),(3,3,'广州市越秀区','510000','1388888888','陈宏',0,0),(4,4,'广州天河区','510000','1586666666','杨凯',0,0),(5,5,'gergerg','gergg','gewrgr',' ggweg',0,1),(6,5,'广州市华南理工大学南校区生活区C10-725','5250000','15880088220','李东东',0,1),(7,6,'华南理工大学','5250000','15665652','何民宠',0,0),(8,7,'华南理工大学','510006','15896745981','李雷',0,0),(9,7,'华南理工大学','510006','15866219924','韩梅梅',0,0),(10,7,'奇怪的地方','520003','14777447774','尼达叶',0,0),(11,7,'啦啦啦','12','789','删了我吧',0,1),(12,5,'ffff','fff','ffff','ffff',0,1),(13,5,'','','','',0,1),(14,5,'烦烦烦','','','方法',0,1),(15,5,'','','','',0,1),(16,5,'14141','1414','14141','14141',0,1),(17,5,'华南理工大学大学城校区C10-745','5250000','15889936189','李冬冬',0,0),(18,5,'广州市华南理工大学南校区','5250000','15335557545','黄明',0,0),(19,5,'广州市华南理工大学南校区C4-433','5250000','13556886252','陈闽东',1,0);
/*!40000 ALTER TABLE `t_address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-08 15:43:06
