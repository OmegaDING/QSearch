/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.39 : Database - que_ans
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`que_ans` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `que_ans`;

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `subject` varchar(200) NOT NULL DEFAULT 'empty',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `subject` */

insert  into `subject`(`id`,`subject`) values (00000000001,'大学英语1-2'),(00000000002,'大学英语3'),(00000000003,'高等数学（上）'),(00000000004,'线性代数'),(00000000005,'大学计算机（计算机文化）'),(00000000006,'大学英语3'),(00000000007,'高等数学（下）'),(00000000008,'大学物理（上）'),(00000000009,'C语言程序设计'),(00000000010,'电子系统基础'),(00000000011,'工程数学'),(00000000012,'大学物理（下）'),(00000000013,'数据库'),(00000000014,'数据结构'),(00000000015,'电子电路基础（模电）'),(00000000016,'信号与系统'),(00000000017,'企业管理'),(00000000018,'概率论与随机过程'),(00000000019,'JAVA高级语言程序设计'),(00000000020,'数字电路与逻辑设计（数电）'),(00000000021,'数字信号处理DSP'),(00000000022,'产品开发'),(00000000023,'电磁场与电磁波'),(00000000024,'电信系统'),(00000000025,'互联网协议'),(00000000026,'数字系统设计'),(00000000027,'高级网络程序设计'),(00000000028,'多媒体基础'),(00000000029,'高级变换'),(00000000030,'软件工程'),(00000000031,'通信原理I'),(00000000032,'互联网应用'),(00000000033,'微波与光传输'),(00000000034,'微处理器系统设计'),(00000000035,'交互式媒体设计'),(00000000036,'图形与视频处理'),(00000000037,'无线网络'),(00000000038,'通信系统电子学'),(00000000039,'3D图形程序设计'),(00000000040,'计算机视觉'),(00000000041,'hyx是我儿子');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
