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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `que_ans` */

insert  into `que_ans`(`question_id`,`question_img_path`,`question_img_name`,`question_string`,`answer_img_path`,`answer_img_name`,`answer_string`,`knowledge_point`,`type_in_time`,`typer_name`,`typer_id`,`subject`,`difficulty`,`is_homework`) values (1,'c:/DB/IMG/QUE/','1_que.jpg','Frame frame= new MainFrame(\"北邮爱学习录入端,sw)\nframe setDefaultcloseOperation (JFrame EXIT ON CLOSE)\n','c:/DB/IMG/ANS/','1_ans.jpg','J Frame frame= new LoginFrame(\"北邮爱学习景入1ogin\",sw\nframe setDefaultcloseOperation (J Frame. EXIT ON CLOSE)\n','Test知识点','2021-05-03 13:05:59','丁坤圆',1,'JAVA',1,0),(2,'c:/DB/IMG/QUE/','2_que.jpg','Frame frame= new MainFrame(\"北邮爱学习录入端,sw)\nframe setDefaultcloseOperation (JFrame EXIT ON CLOSE)\n','c:/DB/IMG/ANS/','2_ans.jpg','J Frame frame= new LoginFrame(\"北邮爱学习景入1ogin\",sw\nframe setDefaultcloseOperation (J Frame. EXIT ON CLOSE)\n','Test知识点','2021-05-03 13:19:44','丁坤圆',1,'JAVA',1,0),(3,'c:/DB/IMG/QUE/','3_que.jpg','Frame frame= new MainFrame(\"北邮爱学习录入端,sw)\nframe setDefaultcloseOperation (JFrame EXIT ON CLOSE)\n','c:/DB/IMG/ANS/','3_ans.jpg','J Frame frame= new LoginFrame(\"北邮爱学习景入1ogin\",sw\nframe setDefaultcloseOperation (J Frame. EXIT ON CLOSE)\n','Test知识点','2021-05-03 13:20:03','丁坤圆',1,'JAVA',1,0),(4,'c:/DB/IMG/QUE/','4_que.jpg','USER class user.\npublic void createGUI(Swing2 Sw)\n','c:/DB/IMG/ANS/','4_ans.jpg','private static void createLOGIN(Swing2 sw)\nJ Frame frame= new LoginFrame(\"北邮爱学习景入1ogin\",sw)\nframe setDefaultcloseOperation (JFrame EXIT_ ON_ CLOSE);\n//设置窗口的其他参数,如窗口大小\nframe setsize(500, 200);\n//显示窗口\nframe setvisible(true)\npublic static void main(String[] args)\nSwing 2 Sw new Swing(;\njavax. swing. SwingUtilities invokeLater(new Runnableoi\npublic void run(\ncreateLOGIN(sw)\n//createGUIO\n','Test知识点','2021-05-03 13:27:28','丁坤圆',1,'JAVA',1,0),(5,'c:/DB/IMG/QUE/','5_que.jpg','USER class user.\npublic void createGUI(Swing2 Sw)\n','c:/DB/IMG/ANS/','5_ans.jpg','private static void createLOGIN(Swing2 sw)\nJ Frame frame= new LoginFrame(\"北邮爱学习景入1ogin\",sw)\nframe setDefaultcloseOperation (JFrame EXIT_ ON_ CLOSE);\n//设置窗口的其他参数,如窗口大小\nframe setsize(500, 200);\n//显示窗口\nframe setvisible(true)\npublic static void main(String[] args)\nSwing 2 Sw new Swing(;\njavax. swing. SwingUtilities invokeLater(new Runnableoi\npublic void run(\ncreateLOGIN(sw)\n//createGUIO\n','Test知识点','2021-05-03 13:27:33','丁坤圆',1,'JAVA',1,0),(6,'c:/DB/IMG/QUE/','6_que.jpg','USER class user.\npublic void createGUI(Swing2 Sw)\n','c:/DB/IMG/ANS/','6_ans.jpg','private static void createLOGIN(Swing2 sw)\nJ Frame frame= new LoginFrame(\"北邮爱学习景入1ogin\",sw)\nframe setDefaultcloseOperation (JFrame EXIT_ ON_ CLOSE);\n//设置窗口的其他参数,如窗口大小\nframe setsize(500, 200);\n//显示窗口\nframe setvisible(true)\npublic static void main(String[] args)\nSwing 2 Sw new Swing(;\njavax. swing. SwingUtilities invokeLater(new Runnableoi\npublic void run(\ncreateLOGIN(sw)\n//createGUIO\n','Test知识点','2021-05-03 13:30:12','丁坤圆',1,'JAVA',1,0),(7,'c:/DB/IMG/QUE/','7_que.jpg','USER class user.\npublic void createGUI(Swing2 Sw)\n','c:/DB/IMG/ANS/','7_ans.jpg','private static void createLOGIN(Swing2 sw)\nJ Frame frame= new LoginFrame(\"北邮爱学习景入1ogin\",sw)\nframe setDefaultcloseOperation (JFrame EXIT_ ON_ CLOSE);\n//设置窗口的其他参数,如窗口大小\nframe setsize(500, 200);\n//显示窗口\nframe setvisible(true)\npublic static void main(String[] args)\nSwing 2 Sw new Swing(;\njavax. swing. SwingUtilities invokeLater(new Runnableoi\npublic void run(\ncreateLOGIN(sw)\n//createGUIO\n','Test知识点','2021-05-03 13:32:26','丁坤圆',1,'JAVA',1,0),(8,'c:/DB/IMG/QUE/','8_que.jpg','priv\nint\nleRequest(YuanNetP\nestPkt, YuanNetConnection conn)throws Except\n//若为关闭服务请求\nif(requestPkt action==( short)-100&\nsock\nconn.c⊥ose\n/若为录入操作\nf(requestPkt action==(short)e)\nQA typeIn QA class decode(requestPkt\nSystem. out\ntin(QA type\nestion\nd)\nSystem, o\nmg path)\nSystem. out. println(QA typeIn question_ img_ name\nSystem. out. println(QA typeIn question string)\n','c:/DB/IMG/ANS/','8_ans.jpg','private static void createLOGIN(Swing2 sw)\nJ Frame frame= new LoginFrame(\"北邮爱学习景入1ogin\",sw)\nframe setDefaultcloseOperation (JFrame EXIT_ ON_ CLOSE);\n//设置窗口的其他参数,如窗口大小\nframe setsize(500, 200);\n//显示窗口\nframe setvisible(true)\npublic static void main(String[] args)\nSwing 2 Sw new Swing(;\njavax. swing. SwingUtilities invokeLater(new Runnableoi\npublic void run(\ncreateLOGIN(sw)\n//createGUIO\n','Test知识点','2021-05-03 13:38:41','丁坤圆',1,'JAVA',1,0),(9,'c:/DB/IMG/QUE/','9_que.jpg','import java. awt Container; l\n@author DingKunYuan\nbI\nass Swing\nUseR Class use\npublic void createGUI(Swing2 Sw\nJFrame frame new Main Frame(\name setDefaultcloseOperation (JFrame EXIT ON CLOSE\n/设置窗口的其他参数,如窗口大\name setsize(1280, 850);\name setvisible(true)\np\natelogin( Swing sw\name secDef\noseOperation(JFrame EXIT_ ON CLOSE)\n/设置窗口的其他参数\nrame. setsize(500, 200)\n/显示窗\nrame. setvisible(true)\npublic static void main(s\nwing Sw= new Swing()\njavax. swing. SwingUtilities invokeLater (new Runnableoi\npublic void runo\ncreateLOGIN(sw);\n//createGUI\n','c:/DB/IMG/ANS/','9_ans.jpg','public void createGUI(Swing2 sw)\name. setDefaultcloseOpera\nEXIT ON CLOSE\n/设置窗口的其他参数,如窗口\name setsize(1280, 850);\n/显示窗\nsetvisible(true)\nprivate static void createLOGIN(Swing2\nJ Frame f\name. setDefaultcloseO\n(Frame. EXIT_ ON CLOSE)\n/设置窗口的其他参数\nrame. setsize(500, 200);\n/显示窗口\nrame. setvisible(true);\nblic static void\nSwing Sw= new Swing()\njavax. swing. SwingUtilities invokeLater (new Runnableoi\npu\ncreateLOGIN(sw);\n//createGUI\n','Test知识点','2021-05-03 13:40:01','丁坤圆',1,'JAVA',1,0),(10,'c:/DB/IMG/QUE/','10_que.jpg','import java. awt Container; l\n@author DingKunYuan\nbI\nass Swing\nUseR Class use\npublic void createGUI(Swing2 Sw\nJFrame frame new Main Frame(\name setDefaultcloseOperation (JFrame EXIT ON CLOSE\n/设置窗口的其他参数,如窗口大\name setsize(1280, 850);\name setvisible(true)\np\natelogin( Swing sw\name secDef\noseOperation(JFrame EXIT_ ON CLOSE)\n/设置窗口的其他参数\nrame. setsize(500, 200)\n/显示窗\nrame. setvisible(true)\npublic static void main(s\nwing Sw= new Swing()\njavax. swing. SwingUtilities invokeLater (new Runnableoi\npublic void runo\ncreateLOGIN(sw);\n//createGUI\n','c:/DB/IMG/ANS/','10_ans.jpg','public void createGUI(Swing2 sw)\name. setDefaultcloseOpera\nEXIT ON CLOSE\n/设置窗口的其他参数,如窗口\name setsize(1280, 850);\n/显示窗\nsetvisible(true)\nprivate static void createLOGIN(Swing2\nJ Frame f\name. setDefaultcloseO\n(Frame. EXIT_ ON CLOSE)\n/设置窗口的其他参数\nrame. setsize(500, 200);\n/显示窗口\nrame. setvisible(true);\nblic static void\nSwing Sw= new Swing()\njavax. swing. SwingUtilities invokeLater (new Runnableoi\npu\ncreateLOGIN(sw);\n//createGUI\n','Test知识点','2021-05-03 13:41:52','丁坤圆',1,'JAVA',1,0),(11,'c:/DB/IMG/QUE/','11_que.jpg','public String path;\npublic String name;\npublic byte [] content;\n','c:/DB/IMG/ANS/','11_ans.jpg','/文件路径\nbyte[] strbuf2 file_c name getBytes(\"UTF-8\");\nbbuf. putshort((short) strbuf2 length);\nbbuf. put (strbuf2):\n','Test知识点','2021-05-03 13:43:11','丁坤圆',1,'JAVA',1,0);

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

insert  into `user`(`id`,`name`,`password`,`phone`,`mail`,`oranization`,`major`,`coin`,`Qserach_num`,`user_type`) values (1,'丁坤圆','dky','19801298990','dky@bupt.edu.cn','BUPT','TELECOMUNICATION',9999,520,0),(2,'何雨轩','hyx','111111','111111','BUPT','TELECOMUNICATION',9999,0,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
