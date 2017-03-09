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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
INSERT INTO `t_comment` VALUES (1,2,1,'2013-06-09 00:35:01',3,'很不错'),(2,6,2,'2013-06-15 16:10:05',4,'nice'),(4,6,1,'2013-03-21 12:02:11',4,'性价比很高。'),(5,6,6,'2013-07-02 14:44:47',2,'还行。。'),(6,6,6,'2013-07-02 14:45:20',5,'太差了。'),(7,6,6,'2013-07-02 16:07:27',5,'很不错的样子'),(8,6,5,'2013-07-03 14:54:26',2,'别买，买了你会后悔'),(9,9,5,'2013-07-03 14:54:26',3,'还不错，一般般'),(10,13,5,'2013-07-03 14:54:26',4,'玩游戏挺赞的。。'),(11,6,5,'2013-07-03 14:54:32',3,'性价比不高'),(12,6,5,'2013-07-03 14:55:18',5,'硬件超给力。。'),(13,8,5,'2013-07-03 14:55:18',5,'买过的最好的一款笔记本'),(14,9,5,'2013-07-03 14:55:18',4,'不错，不错'),(15,2,5,'2013-07-05 10:12:42',3,'老是死机。。'),(16,6,5,'2013-07-05 10:12:42',5,'完美的笔记本。。。'),(17,7,5,'2013-07-05 10:12:42',2,'一个字，差'),(18,8,5,'2013-07-05 10:12:42',5,'用的很流畅'),(19,9,5,'2013-07-05 10:12:42',5,'给力，超给力'),(20,2,5,'2013-07-05 10:23:48',4,'一般。性能还行'),(21,6,5,'2013-07-05 10:23:48',2,'修了又修，建议别买。'),(22,7,5,'2013-07-05 10:23:48',3,'不好。'),(23,8,5,'2013-07-05 10:23:48',5,'用的还不错'),(24,9,5,'2013-07-05 10:23:48',4,'可以试一试，还行'),(25,2,5,'2013-07-05 10:23:53',5,'perfect！！！'),(26,6,5,'2013-07-05 10:23:53',3,'很中庸的一款笔记本'),(27,7,5,'2013-07-05 10:23:53',2,'没啥优点，慢的要死'),(28,8,5,'2013-07-05 10:23:53',5,'不错不错。'),(29,9,5,'2013-07-05 10:23:53',5,'真心不错'),(30,2,5,'2013-07-05 10:31:39',5,'挺好看的，性能也不错'),(31,6,5,'2013-07-05 10:31:39',5,''),(32,17,5,'2013-07-08 10:35:36',5,'看起来还不错的样子。'),(33,9,5,'2013-07-08 10:35:36',5,''),(34,19,5,'2013-07-08 10:36:36',5,'很不错的性能。。'),(35,29,5,'2013-07-08 10:36:36',4,'电脑散热很差。');
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

-- Dump completed on 2013-07-09 16:32:07
