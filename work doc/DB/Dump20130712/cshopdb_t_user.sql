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
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(45) NOT NULL COMMENT '邮箱地址',
  `user_password` varchar(45) NOT NULL,
  `user_nickname` varchar(45) NOT NULL,
  `user_levelid` int(11) NOT NULL COMMENT '会员级别',
  `user_rewardpoints` int(11) DEFAULT '0' COMMENT '积分',
  `user_isdel` tinyint(1) DEFAULT '0' COMMENT '是否被管理员删除',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_email`),
  KEY `fk_user_userlevel_idx` (`user_levelid`),
  KEY `fk_user_userlevel_idx1` (`user_levelid`),
  CONSTRAINT `fk_user_userlevel` FOREIGN KEY (`user_levelid`) REFERENCES `t_userlevel` (`userlevel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'12345678@qq.com','123456','东东',1,0,0),(2,'LIMING@126.com','a123456','LIMING',2,16000,0),(3,'22222333@qq.com','222222','随风',3,60000,0),(4,'9888888@qq.com','000000','逗比',4,200000,0),(5,'908882679@qq.com','447197E1D1F7D504A866E878A5EA79A0','zzzzzzzzzzzzzzzzzzzzzzzzzzzz',1,0,0),(6,'1783407440@qq.com','447197E1D1F7D504A866E878A5EA79A0','52542',1,0,1),(7,'387602234@qq.com','447197E1D1F7D504A866E878A5EA79A0','Hmc_ 9',1,0,0),(8,'123456@qq.com','447197E1D1F7D504A866E878A5EA79A0','',1,0,0),(9,'550338919@qq.com','447197E1D1F7D504A866E878A5EA79A0','RichardChan',4,178629,0),(10,'crongqia@126.com','447197E1D1F7D504A866E878A5EA79A0','RichardChan',4,601700,0),(11,'crong','447197E1D1F7D504A866E878A5EA79A0','crongqiang@126.com',1,0,0),(12,'crongqi@126.com','447197E1D1F7D504A866E878A5EA79A0','crongqiang@126.com',1,0,0),(14,'crongq@126.com','447197E1D1F7D504A866E878A5EA79A0','crongqiang@126.com',1,0,0),(15,'crongqg@126.com','447197E1D1F7D504A866E878A5EA79A0','crongqiang@126.com',1,0,0),(16,'crongqiangom','447197E1D1F7D504A866E878A5EA79A0','crongqiang@126.com',1,0,0),(17,'crongqiang@126.com','447197E1D1F7D504A866E878A5EA79A0','crongqiang@126.com',1,0,0);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-12 21:39:48
