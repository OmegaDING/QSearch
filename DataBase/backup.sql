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
  `question_img_path_1` varchar(200) NOT NULL DEFAULT 'empty' COMMENT '备用题目地址',
  `question_img_name` varchar(200) NOT NULL DEFAULT 'empty',
  `question_img_name_1` varchar(200) NOT NULL DEFAULT 'empty' COMMENT '备用题目地址',
  `question_string` text NOT NULL,
  `question_num` int(11) NOT NULL DEFAULT '1',
  `answer_img_path_0` varchar(200) NOT NULL DEFAULT 'empty',
  `answer_img_path_1` varchar(200) NOT NULL DEFAULT 'empty',
  `answer_img_path_2` varchar(200) NOT NULL DEFAULT 'empty',
  `answer_img_path_3` varchar(200) NOT NULL DEFAULT 'empty',
  `answer_img_path_4` varchar(200) DEFAULT 'empty' COMMENT '备用答案地址',
  `answer_img_path_5` varchar(200) DEFAULT 'empty' COMMENT '备用答案地址',
  `answer_img_name_0` varchar(200) NOT NULL DEFAULT 'empty',
  `answer_img_name_1` varchar(200) NOT NULL DEFAULT 'empty',
  `answer_img_name_2` varchar(200) NOT NULL DEFAULT 'empty',
  `answer_img_name_3` varchar(200) NOT NULL DEFAULT 'empty',
  `answer_img_name_4` varchar(200) DEFAULT 'empty' COMMENT '备用答案地址',
  `answer_img_name_5` varchar(200) DEFAULT 'empty' COMMENT '备用答案地址',
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `que_ans` */

insert  into `que_ans`(`question_id`,`question_img_path`,`question_img_path_1`,`question_img_name`,`question_img_name_1`,`question_string`,`question_num`,`answer_img_path_0`,`answer_img_path_1`,`answer_img_path_2`,`answer_img_path_3`,`answer_img_path_4`,`answer_img_path_5`,`answer_img_name_0`,`answer_img_name_1`,`answer_img_name_2`,`answer_img_name_3`,`answer_img_name_4`,`answer_img_name_5`,`answer_string`,`knowledge_point`,`type_in_time`,`typer_name`,`typer_id`,`subject`,`difficulty`,`is_homework`) values (1,'empty','empty','empty','empty','aaaa',1,'empty','empty','empty','empty','empty','empty','empty','empty','empty','empty','empty','empty','aaa','empty','empty','empty',0,'empty',0,0),(2,'123','123','123','123','123',5,'123','123','123','123','123','123','123','123','123','123','123','123','123','123','122','123',123,'123',12,1),(3,'c:/DB/IMG/QUE/','c:/DB/IMG/QUE/','3_que.jpg','3_1_que.jpg','北邮爱',1,'c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','3_0_ans.jpg','3_0_ans.jpg','3_0_ans.jpg','3_0_ans.jpg','3_0_ans.jpg','3_0_ans.jpg','222\n','Test知识点','2021-05-15 21:33:14','丁坤圆',1,'JAVA',1,0),(4,'c:/DB/IMG/QUE/','c:/DB/IMG/QUE/','4_que.jpg','4_1_que.jpg','QA now answer img _path 0 =c:/DB/IMG/ANS/\";\nQA now answer img path 1 =\"c:/DB/IMG/ANS/\nQA now answer img path 2=\"c:/DBIMG/ANS/\nQA now answer img path 3 =\":/DB/IMG/ANS/\nOA now answer img path 4\n/DB/IMG/ANS/\n',1,'c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','4_0_ans.jpg','4_0_ans.jpg','4_0_ans.jpg','4_0_ans.jpg','4_0_ans.jpg','4_0_ans.jpg','QA now answer string jta ans getText o:\nint num=0;\nif(imageview ansl=null)\nnum++\n','Test知识点','2021-05-16 00:07:24','丁坤圆',1,'JAVA',1,0),(5,'c:/DB/IMG/QUE/','c:/DB/IMG/QUE/','5_que.jpg','5_1_que.jpg','QA now answer img_ path 2=\"c: /DB/IMG /ANS/\nQA now answer img path_3=\"C: /DB/IMG/ANS/\nQA now answer img_path 4=\"c: /DB/IMG/ANS/\";\nA now answer img_path_5=\": /DB/IMG/ANS/\"i\nA_now answer_img name_0=-1_ans. Jpg\",\n',1,'c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','5_0_ans.jpg','5_0_ans.jpg','5_0_ans.jpg','5_0_ans.jpg','5_0_ans.jpg','5_0_ans.jpg','se\n(Imageview ans\nnum+中\nelse if(imageview ans_ 2l=null)\nnum++\nelse if(imageview ans 3l=null)\nnum++,\n','Test知识点','2021-05-16 00:09:41','丁坤圆',1,'JAVA',1,0),(6,'c:/DB/IMG/QUE/','c:/DB/IMG/QUE/','6_que.jpg','6_1_que.jpg','QA now answer img name 0 id\n0 ans\nQA_ now answer_img_ name_1 =id+1_ans\nQA now answer_ img_name_2= id+2_ans\nQA_ now answer img_ name_3= id +3 ans\nQA now answer img name 4 id+4ans\n',6,'c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','6_0_ans.jpg','6_0_ans.jpg','6_0_ans.jpg','6_0_ans.jpg','6_0_ans.jpg','6_0_ans.jpg','ns setimage(null)j\nns_1. setImage(null\nns_2. setImage (null\nns_3. setImage(null\n','Test知识点','2021-05-16 00:14:30','丁坤圆',1,'JAVA',1,0),(7,'c:/DB/IMG/QUE/','c:/DB/IMG/QUE/','7_que.jpg','7_1_que.jpg','.D USER Class. java\ngu\n.D CaptureFrame java\nd LoginFrame java\nIn Main Frame. java\n&D Mypaneljava\n',3,'c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','7_0_ans.jpg','7_0_ans.jpg','7_0_ans.jpg','7_0_ans.jpg','7_0_ans.jpg','7_0_ans.jpg','QA now answer string jta ans getText\nint num=e\nif(imageview ans!=null)\n','Test知识点','2021-05-16 00:16:13','丁坤圆',1,'JAVA',1,0),(8,'c:/DB/IMG/QUE/','c:/DB/IMG/QUE/','8_que.jpg','8_1_que.jpg','QA now answer img name 2= id +2 ans. jpg j\nQA_now answer_img_name3= id+3_ans. jpg\nQA now answer img name 4= id +4 ans. jpg\nQA now answer_ img name 5= id+5 ans. jpg\";\n传题目图片\n',4,'c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','8_0_ans.jpg','8_0_ans.jpg','8_0_ans.jpg','8_0_ans.jpg','8_0_ans.jpg','8_0_ans.jpg','//上传题目图片\nFileUploadHandler fuh1\nfuh1. start o;\n//上传答案图片\n','Test知识点','2021-05-16 00:25:07','丁坤圆',1,'JAVA',1,0),(9,'c:/DB/IMG/QUE/','c:/DB/IMG/QUE/','9_que.jpg','9_1_que.jpg','port java. awt Container;\nAuthor DingKunYuan\n',5,'c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','c:/DB/IMG/ANS/','9_0_ans.jpg','9_1_ans.jpg','9_2_ans.jpg','9_3_ans.jpg','9_4_ans.jpg','9_5_ans.jpg','tatic void createLOGIN (Swing2 sw)\ne frame= new Login Frame(\"北邮爱学习景入-1ogin\",sw)\nsetDefaultcloseOperation (JFrame EXIT ON CLOSE);\n','Test知识点','2021-05-16 00:28:33','丁坤圆',1,'JAVA',1,0);

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `subject` varchar(200) NOT NULL DEFAULT 'empty',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `subject` */

insert  into `subject`(`id`,`subject`) values (00000000001,'大学英语1-2'),(00000000002,'大学英语3'),(00000000003,'高等数学（上）'),(00000000004,'线性代数'),(00000000005,'大学计算机（计算机文化）'),(00000000006,'大学英语3'),(00000000007,'高等数学（下）'),(00000000008,'大学物理（上）'),(00000000009,'C语言程序设计'),(00000000010,'电子系统基础'),(00000000011,'工程数学'),(00000000012,'大学物理（下）'),(00000000013,'数据库'),(00000000014,'数据结构'),(00000000015,'电子电路基础（模电）'),(00000000016,'信号与系统'),(00000000017,'企业管理'),(00000000018,'概率论与随机过程'),(00000000019,'JAVA高级语言程序设计'),(00000000020,'数字电路与逻辑设计（数电）'),(00000000021,'数字信号处理DSP'),(00000000022,'产品开发'),(00000000023,'电磁场与电磁波'),(00000000024,'电信系统'),(00000000025,'互联网协议'),(00000000026,'数字系统设计'),(00000000027,'高级网络程序设计'),(00000000028,'多媒体基础'),(00000000029,'高级变换'),(00000000030,'软件工程'),(00000000031,'通信原理I'),(00000000032,'互联网应用'),(00000000033,'微波与光传输'),(00000000034,'微处理器系统设计'),(00000000035,'交互式媒体设计'),(00000000036,'图形与视频处理'),(00000000037,'无线网络'),(00000000038,'通信系统电子学'),(00000000039,'3D图形程序设计'),(00000000040,'计算机视觉'),(00000000041,'hyx是我儿子');

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
