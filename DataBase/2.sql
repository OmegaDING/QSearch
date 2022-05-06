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

/*Table structure for table `que_ans` */

DROP TABLE IF EXISTS `que_ans`;

CREATE TABLE `que_ans` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_img_path` varchar(200) NOT NULL DEFAULT 'empty',
  `question_img_name` varchar(200) NOT NULL DEFAULT 'empty',
  `question_string` text NOT NULL,
  `answer_img_path` varchar(200) NOT NULL DEFAULT 'empty',
  `answer_img_name` varchar(200) NOT NULL DEFAULT 'empty',
  `answer_string` text NOT NULL,
  `knowledge_point` varchar(100) NOT NULL DEFAULT 'empty',
  `type_in_time` varchar(200) NOT NULL DEFAULT 'empty',
  `typer_name` varchar(50) NOT NULL DEFAULT 'empty',
  `typer_id` int(11) NOT NULL DEFAULT '0',
  `subject` varchar(50) NOT NULL DEFAULT 'empty',
  `difficulty` int(11) NOT NULL DEFAULT '0',
  `is_homework` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`question_id`),
  FULLTEXT KEY `question_string` (`question_string`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `que_ans` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '-1',
  `password` varchar(50) NOT NULL DEFAULT '-1',
  `phone` varchar(20) NOT NULL DEFAULT '-1',
  `mail` varchar(50) NOT NULL DEFAULT '-1',
  `oranization` varchar(50) NOT NULL DEFAULT '-1',
  `major` varchar(20) NOT NULL DEFAULT '-1',
  `coin` int(11) NOT NULL DEFAULT '-1',
  `Qserach_num` int(11) NOT NULL DEFAULT '-1',
  `user_type` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`password`,`phone`,`mail`,`oranization`,`major`,`coin`,`Qserach_num`,`user_type`) values (1,'丁坤圆','password','19801298990','dky@bupt.edu.cn','BUPT','TELECOMUNICATION',9999,520,0),(2,'何雨轩','hyx','111111','111111','BUPT','TELECOMUNICATION',9999,0,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
