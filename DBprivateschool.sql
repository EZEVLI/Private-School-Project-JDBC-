/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.22 : Database - cb_private_school
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cb_private_school` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `cb_private_school`;

/*Table structure for table `assignment` */

DROP TABLE IF EXISTS `assignment`;

CREATE TABLE `assignment` (
  `assignmentID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `subDateTime` datetime DEFAULT NULL,
  `totalMark` int DEFAULT NULL,
  `oralMark` int DEFAULT NULL,
  `courseID` int DEFAULT NULL,
  PRIMARY KEY (`assignmentID`),
  KEY `courseid` (`courseID`),
  CONSTRAINT `courseid` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `assignment` */

insert  into `assignment`(`assignmentID`,`title`,`description`,`subDateTime`,`totalMark`,`oralMark`,`courseID`) values 
(1,'CODING BOOTCAMP','CREATE A CODING BOOTCAMP IN JAVA','2020-01-31 15:03:37',100,50,1),
(2,'BOOKSTORE','CREATE A BOOKSTORE IN JAVA','2020-02-15 14:04:27',100,50,1),
(3,'UNIVERSITY','CREATE A UNIVERSITY IN C#','2020-01-31 15:03:37',100,50,3),
(4,'ESHOP','CREATE AN ESHOP IN C#','2020-02-15 14:04:27',100,50,3),
(5,'ESHOP','CREATE AN ESHOP IN JAVA','2020-09-15 16:00:00',100,50,2),
(6,'APPLICATION','CREATE AN APPLICATION IN C#','2020-09-15 16:00:00',100,50,4),
(7,'PROJECT','PROJECT DESCR JAVA','2020-10-30 12:00:00',100,50,2);

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `courseID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `stream` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`courseID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`courseID`,`title`,`stream`,`type`,`start_date`,`end_date`) values 
(1,'CB1','JAVA','FULL_TIME','2020-01-01','2020-03-31'),
(2,'CB1','JAVA','PART_TIME','2020-07-01','2020-12-20'),
(3,'CB1','C#','FULL_TIME','2020-01-01','2020-03-31'),
(4,'CB2','C#','PART_TIME','2020-07-01','2020-12-20');

/*Table structure for table `enroll` */

DROP TABLE IF EXISTS `enroll`;

CREATE TABLE `enroll` (
  `courseID` int NOT NULL,
  `studentID` int NOT NULL,
  PRIMARY KEY (`courseID`,`studentID`),
  KEY `sid` (`studentID`),
  CONSTRAINT `cid` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`),
  CONSTRAINT `sid` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `enroll` */

insert  into `enroll`(`courseID`,`studentID`) values 
(1,1),
(1,2),
(2,2),
(4,2),
(2,3),
(2,4),
(3,5),
(4,5),
(3,6),
(3,7),
(3,8),
(4,9),
(4,10);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `studentID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `tuitionFees` int DEFAULT NULL,
  PRIMARY KEY (`studentID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`studentID`,`firstName`,`lastName`,`dateOfBirth`,`tuitionFees`) values 
(1,'SFN1','SLN1','1990-01-01',100),
(2,'SFN2','SLN2','1990-01-01',100),
(3,'SFN3','SLN3','1990-01-01',100),
(4,'SFN4','SLN4','1990-01-01',100),
(5,'SFN5','SLN5','1990-01-01',100),
(6,'SFN6','SLN6','1990-01-01',100),
(7,'SFN7','SLN7','1990-01-01',100),
(8,'SFN8','SLN8','1990-01-01',100),
(9,'SFN9','SLN9','1990-01-01',100),
(10,'SFN10','SLN10','1990-01-01',100);

/*Table structure for table `studentassignment` */

DROP TABLE IF EXISTS `studentassignment`;

CREATE TABLE `studentassignment` (
  `studentID` int NOT NULL,
  `assignmentID` int NOT NULL,
  `totalMark` int DEFAULT NULL,
  `oralMark` int DEFAULT NULL,
  PRIMARY KEY (`studentID`,`assignmentID`),
  KEY `aid` (`assignmentID`),
  CONSTRAINT `aid` FOREIGN KEY (`assignmentID`) REFERENCES `assignment` (`assignmentID`),
  CONSTRAINT `stid` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `studentassignment` */

insert  into `studentassignment`(`studentID`,`assignmentID`,`totalMark`,`oralMark`) values 
(1,1,NULL,NULL),
(1,2,NULL,NULL),
(2,1,NULL,NULL),
(2,2,NULL,NULL),
(2,5,NULL,NULL),
(2,6,NULL,NULL),
(2,7,NULL,NULL),
(3,5,NULL,NULL),
(3,7,NULL,NULL),
(4,5,NULL,NULL),
(4,7,NULL,NULL),
(5,3,NULL,NULL),
(5,4,NULL,NULL),
(5,6,NULL,NULL),
(6,3,NULL,NULL),
(6,4,NULL,NULL),
(7,3,NULL,NULL),
(7,4,NULL,NULL),
(8,3,NULL,NULL),
(8,4,NULL,NULL),
(9,6,NULL,NULL),
(10,6,NULL,NULL);

/*Table structure for table `teach` */

DROP TABLE IF EXISTS `teach`;

CREATE TABLE `teach` (
  `courseID` int NOT NULL,
  `trainerID` int NOT NULL,
  PRIMARY KEY (`courseID`,`trainerID`),
  KEY `TRID` (`trainerID`),
  CONSTRAINT `COUID` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`),
  CONSTRAINT `TRID` FOREIGN KEY (`trainerID`) REFERENCES `trainer` (`trainerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teach` */

insert  into `teach`(`courseID`,`trainerID`) values 
(1,1),
(3,2),
(4,2),
(1,3),
(2,3),
(2,4),
(2,5),
(4,5),
(1,6),
(2,6);

/*Table structure for table `trainer` */

DROP TABLE IF EXISTS `trainer`;

CREATE TABLE `trainer` (
  `trainerID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `subject` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`trainerID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `trainer` */

insert  into `trainer`(`trainerID`,`firstName`,`lastName`,`subject`) values 
(1,'TFN1','TLN1','SQL'),
(2,'TFN2','TLN2','C#'),
(3,'TFN3','TLN3','JAVA'),
(4,'TFN4','TLN4','JAVASCRIPT'),
(5,'TFN5','TLN5','HTML'),
(6,'TFN6','TLN6','JAVA');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
