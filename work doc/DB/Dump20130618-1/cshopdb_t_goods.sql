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
-- Table structure for table `t_goods`
--

DROP TABLE IF EXISTS `t_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(45) NOT NULL,
  `goods_description` varchar(200) NOT NULL,
  `goods_image` varchar(45) DEFAULT NULL,
  `goods_brand` varchar(45) DEFAULT NULL COMMENT '品牌',
  `goods_model` varchar(45) DEFAULT NULL COMMENT '型号',
  `goods_operation_system` varchar(45) DEFAULT NULL,
  `goods_processor` varchar(45) DEFAULT NULL,
  `goods_memory` varchar(45) DEFAULT NULL,
  `goods_harddisk_capacity` varchar(45) DEFAULT NULL,
  `goods_screen_size` varchar(20) DEFAULT NULL COMMENT '屏幕尺寸',
  `goods_resolution_definition` varchar(45) DEFAULT NULL COMMENT '分辨率',
  `goods_price` double NOT NULL,
  `goods_stock` int(11) NOT NULL COMMENT '库存',
  `goods_discount` double NOT NULL DEFAULT '1',
  `goods_sales` int(11) DEFAULT NULL COMMENT '销量',
  `goods_isdel` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否下架\\n0：否\\n1：是',
  `goods_averagemark` double DEFAULT NULL COMMENT '平均评分',
  `firstcatalog_id` int(11) DEFAULT NULL COMMENT '1级目录',
  `secondcatalog_id` int(11) DEFAULT NULL COMMENT '2级目录',
  PRIMARY KEY (`goods_id`),
  UNIQUE KEY `goods_id_UNIQUE` (`goods_id`),
  KEY `fk_goods_1stcatalog_idx` (`firstcatalog_id`),
  KEY `fk_goods_2ndcatalog_idx` (`secondcatalog_id`),
  CONSTRAINT `fk_goods_firstcatalog` FOREIGN KEY (`firstcatalog_id`) REFERENCES `t_firstcatalog` (`firstcatalog_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_goods_secondcatalog` FOREIGN KEY (`secondcatalog_id`) REFERENCES `t_secondcatalog` (`secondcatalog_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_goods`
--

LOCK TABLES `t_goods` WRITE;
/*!40000 ALTER TABLE `t_goods` DISABLE KEYS */;
INSERT INTO `t_goods` VALUES (2,'联想Y400N-ISE','游戏旗舰机型，配置强悍，游戏性能出众','\\笔记本图片\\联想\\联想Y400N-ISE.jpg','联想','Y400N-ISE','Linux ','Intel 酷睿i7 3630QM ','4GB DDR3','1TB','14英寸','1366x768',6500,100,1,2,0,4.1,1,1),(6,'Acer E1-471G-53234G50Mnks','配置高，性价比高，做工还可以','\\笔记本图片\\宏基\\Acer E1-471G-53234G50Mnks.jpg','宏基',' E1-471G-53234G50Mnks','Linux ','Intel 酷睿i5 3230M','4GB DDR3','500GB 5400转','14英寸','1366x768',3850,200,1,0,0,4.2,2,2),(7,'戴尔Inspiron 灵越 14R Turbo（Ins14TD-1728）','采用GT640独显，性能不错','\\笔记本图片\\戴尔\\戴尔Inspiron 灵越 14R Turbo1728.jpg','戴尔','Inspiron 灵越 14R Turbo（Ins14TD-1728）','Windows 7 Home Bas','Intel 酷睿i7 3612Q','8GB DDR3 1600MHz','1TB 5400转','14英寸','1600x900',6999,68,1,5,0,4,3,3),(8,'华硕S550X3317CM-SL','性能较强的超极本拥有霸气独显和光驱','\\笔记本图片\\华硕\\华硕S550X3317CM-SL.jpg','华硕','S550X3317CM-SL','Windows 8（简体中文版）','Intel 酷睿i5 3317U ','4GB DDR3 1600MHz ','24GB+1TB 混合硬盘（SSD+HDD）','15.6英寸','1366x768 ',5600,75,1,10,0,4.5,4,4),(9,'Acer V5-471G-53334G50Makk','配置高,性价比高','\\笔记本图片\\宏基\\Acer V5-471G-53334G50Makk.jpg','宏基',' V5-471G-53334G50Makk','Linux','Intel 酷睿i5 3337U','4GB DDR3','500GB 5400转','14英寸','1366x768',4000,25,1,20,0,4.6,2,5),(10,'Acer V5-471G-53334G50Mass','适中的屏幕,性能不错','\\笔记本图片\\宏基\\Acer V5-471G-53334G50Mass.jpg','宏基','V5-471G-53334G50Mass','Linux','Intel 酷睿i5 3337U','4GB DDR3','500GB 5400转','14英寸','1366x768',4300,200,1,42,0,4.5,2,5),(11,'Acer V5-471G-73514G50Mabb','外观大气,配置较高','\\笔记本图片\\宏基\\Acer V5-471G-73514G50Mabb.jpg','宏基','V5-471G-73514G50Mabb','Linux','Intel 酷睿i7 3517U','4GB DDR3 ','500GB 5400转','14英寸','1366x768',4800,5,1,13,0,4.5,2,5),(12,'Acer V3-771G-33124G50Makk','17英寸中的高性价比产品，配置合理','\\笔记本图片\\宏基\\Acer V3-771G-33124G50Makk.jpg','宏基','V3-771G-33124G50Makk','Linux','Intel 酷睿i3 3120M','4GB DDR3','500GB 5400转','17.3英寸','1600x900',3800,42,1,10,0,4.1,2,6),(13,'Acer V3-771G-73638G1TMaii','17英寸中的高性价比产品，配置合理','\\笔记本图片\\宏基\\Acer V3-771G-73638G1TMaii.jpg','宏基','V3-771G-73638G1TMaii','Windows 8 Home Basic Edition 64bit','Intel 酷睿i7 3632QM','8GB DDR3 1600MHz','1TB 5400转','17.3英寸','1920x1080',8000,13,1,20,0,4.2,2,6),(14,'Acer V3-771G-53214G50Maii','17英寸中的高性价比产品，配置合理','\\笔记本图片\\宏基\\Acer V3-771G-53214G50Maii.jpg','宏基','V3-771G-53214G50Maii','Windows 8','Intel 酷睿i5 3210M','4GB DDR3 1600MHz','500GB 5400转','17.3英寸','1920x1080',6400,25,1,15,0,4.5,2,6),(16,'联想Y400N-ISE（T）','游戏旗舰机型，配置强悍，游戏性能出众','\\笔记本图片\\联想\\联想Y400N-ISE（T）.jpg','联想','Y400N-ISE（T）','Windows 8（简体中文版）','Intel 酷睿i7 3630QM','4GB DDR3','1TB 5400转','14英寸','1366x768',6750,14,1,12,0,3.9,1,1),(17,'联想Y400N-IFI（T）','游戏旗舰机型，配置强悍，游戏性能出众','\\笔记本图片\\联想\\联想Y400N-IFI（T）.jpg','联想','Y400N-IFI（T）','Windows 8（简体中文版）','Intel 酷睿i5 3230M','4GB DDR3','1TB','14英寸','1366x768',5300,15,1,14,0,4.1,1,1),(18,'联想G580A-IFI','屏幕分辨率比较高，价格比较实惠','\\笔记本图片\\联想\\联想G580A-IFI.jpg','联想','G580A-IFI','Windows 8（简体中文版）','Intel 酷睿i5 3230M','4GB DDR3','500GB 5400转','15.6英寸','1366x768',3900,16,1,21,0,4.3,1,7),(19,'联想G580A-ITH','屏幕分辨率比较高，价格比较实惠','\\笔记本图片\\联想\\联想G580A-ITH.jpg','联想','G580A-ITH','Windows 8（简体中文版）','Intel 酷睿i3 3120M','2GB DDR3','500GB 5400转','15.6英寸','1366x768',3600,78,1,45,0,4.7,1,7),(20,'联想G580A-ITH（T）','屏幕分辨率比较高，价格比较实惠','\\笔记本图片\\联想\\联想G580A-ITH（T）.jpg','联想','G580A-ITH（T）','Windows 8（简体中文版）','Intel 酷睿i3 3120M','2GB DDR3','500GB 5400转','15.6英寸','1366x768',3600,19,1,21,0,4.3,1,7),(21,'戴尔Inspiron 灵越 14R Turbo（Ins14TD-1628）','采用GT641独显，性能不错','\\笔记本图片\\戴尔\\戴尔Inspiron 灵越 14R Turbo1628.jpg','戴尔','Inspiron 灵越 14R Turbo（Ins14TD-1628）','Windows 7 Home Basic  64bit','Intel 酷睿i5 3210M','6GB DDR3 1600MHz','750GB 5400转','14英寸','1600x900',5999,43,1,5,0,4.8,3,3),(28,'戴尔Inspiron 灵越 14R Turbo（Ins14TD-2728）','采用GT642独显，性能不错','\\笔记本图片\\戴尔\\戴尔Inspiron 灵越 14R Turbo2728.jpg','戴尔','Inspiron 灵越 14R Turbo（Ins14TD-2728）','Windows 7 Home Basic  64bit','Intel 酷睿i7 3632QM','8GB DDR3 1600MHz','1TB 5400转','14英寸','1600x900',6999,9,1,32,0,4.5,3,3),(29,'戴尔Inspiron 灵越 17R Turbo（INS17TD-1728）','超大屏幕配高分屏，带来出色的视觉体验','\\笔记本图片\\戴尔\\戴尔Inspiron 灵越 17R Turbo1728.jpg','戴尔','Inspiron 灵越 17R Turbo（INS17TD-1728）','Windows 7 Home Basic  64bit','Intel 酷睿i7 3610QM','8GB DDR3 1600MHz','1TB 5400转','17.3英寸','1920x1080',7999,21,1,23,0,3.9,3,8),(30,'戴尔Inspiron 灵越 17R Turbo（INS17TD-3728）','超大屏幕配高分屏，带来出色的视觉体验','\\笔记本图片\\戴尔\\戴尔Inspiron 灵越 17R Turbo3728.jpg','戴尔','Inspiron 灵越 17R Turbo（INS17TD-3728）','Windows 8（中文版）','Intel 酷睿i7 3630QM','8GB DDR3 1600MHz','1TB 5400转','17.3英寸','1920x1080',8199,17,1,16,0,4.2,3,8),(31,'戴尔Inspiron 灵越 17R Turbo（INS17TD-1828）','超大屏幕配高分屏，带来出色的视觉体验','\\笔记本图片\\戴尔\\戴尔Inspiron 灵越 17R Turbo1828.jpg','戴尔','Inspiron 灵越 17R Turbo（INS17TD-1828）','Windows 7 Home Basic  64bit','Intel 酷睿i7 3610QM','8GB DDR3 1600MHz','1.5TB 7200转','17.3英寸','1920x1080',11000,18,1,24,0,4.3,3,8),(32,'华硕A53XI245SD-SL','机身做工细腻,有不错的影音视听效果','\\笔记本图片\\华硕\\华硕A53XI245SD-SL.jpg','华硕','A53XI245SD-SL','DOS','Intel 酷睿i5 2450M','4GB DDR3 1333MHz','500GB 5400转','15.6英寸','1366x768',4199,21,1,21,0,4.2,4,9),(33,'华硕A53EI267SM-SL','机身做工细腻,有不错的影音视听效果','\\笔记本图片\\华硕\\华硕A53EI267SM-SL.jpg','华硕','A53EI267SM-SL','DOS','Intel 酷睿i7 2670QM','8GB DDR3 1066MHz','750GB 7200转','15.6英寸','1366x768',6000,16,1,25,0,4.5,4,9),(34,'华硕A53XI243SV-SL','机身做工细腻,有不错的影音视听效果','\\笔记本图片\\华硕\\华硕A53XI243SV-SL.jpg','华硕','A53XI243SV-SL','DOS','Intel 酷睿i5 2430M','4GB DDR3 1333MHz','500GB 5400转','15.6英寸','1366x768',4750,14,1,24,0,4.1,4,9),(35,'华硕VivoBook S400EI3317CA','触控屏+Windows8系统带给用户全新体验','\\笔记本图片\\华硕\\华硕VivoBook S400EI3317CA.jpg','华硕','VivoBook S400EI3317CA','Windows 8','Intel 酷睿i5 3317U','4GB DDR3 1600MHz','24GB+500GB 混合硬盘（SSD+5400转HDD）','14英寸','1366x768',4800,19,1,21,0,4.2,4,10),(36,'华硕VivoBook S400EI3517CA','触控屏+Windows8系统带给用户全新体验','\\笔记本图片\\华硕\\华硕VivoBook S400EI3517CA.jpg','华硕','VivoBook S400EI3517CA','Windows 8','Intel 酷睿i7 3517U','4GB DDR3 1600MHz','24GB+500GB 混合硬盘（SSD+5400转HDD）','14英寸','1366x768',5700,15,1,12,0,4.1,4,10),(37,'华硕VivoBook S400EI2365CA','触控屏+Windows8系统带给用户全新体验','\\笔记本图片\\华硕\\华硕VivoBook S400EI2365CA.jpg','华硕','VivoBook S400EI2365CA','Windows 8','Intel 酷睿i3 2365M','4GB DDR3 1600MHz','500GB 5400转','14英寸','1366x768',4300,25,1,2,0,4.6,4,10);
/*!40000 ALTER TABLE `t_goods` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-18 11:17:48
