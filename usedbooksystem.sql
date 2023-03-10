/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : usedbooksystem

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2023-03-10 19:42:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ISBN` varchar(50) DEFAULT NULL,
  `bookTitle` varchar(250) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `editor` varchar(50) DEFAULT NULL,
  `publishYear` varchar(50) DEFAULT NULL,
  `category_ID` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `salePrice` decimal(10,2) DEFAULT NULL,
  `remark` varchar(250) DEFAULT NULL,
  `readingNotes` varchar(50) DEFAULT NULL,
  `user_ID` int(11) DEFAULT NULL,
  `ceateDT` datetime DEFAULT NULL,
  `isSaled` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_1` (`user_ID`),
  KEY `FK_Reference_5` (`category_ID`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`user_ID`) REFERENCES `users` (`ID`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`category_ID`) REFERENCES `category` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', 'aa', 'ThinkJava', 'aa', 'aa', '1982', '13', '12.00', '11.25', '333', 'no', '1', '2023-03-30 23:09:01', '0');
INSERT INTO `book` VALUES ('2', '22', 'C# Design', '22', '22', '2018', '14', '3.00', '3.00', '', '33', '1', '2023-03-02 23:08:57', '0');
INSERT INTO `book` VALUES ('30', '1', '1', '1', '1', '1980', '14', '1.00', '1.00', '1', 'has', '1', '2023-03-02 21:36:56', '0');

-- ----------------------------
-- Table structure for bookcommunicate
-- ----------------------------
DROP TABLE IF EXISTS `bookcommunicate`;
CREATE TABLE `bookcommunicate` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `book_ID` int(11) DEFAULT NULL,
  `content` varchar(250) DEFAULT NULL,
  `user_ID` int(11) DEFAULT NULL,
  `isOwner` int(11) DEFAULT NULL,
  `createDT` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_3` (`book_ID`),
  KEY `FK_Reference_4` (`user_ID`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`book_ID`) REFERENCES `book` (`ID`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`user_ID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookcommunicate
-- ----------------------------
INSERT INTO `bookcommunicate` VALUES ('1', '1', 'test', '1', '1', '2023-02-03 22:16:23');
INSERT INTO `bookcommunicate` VALUES ('5', '1', '555', '1', '0', '2023-02-04 11:39:41');
INSERT INTO `bookcommunicate` VALUES ('6', '1', 'e3ee', '1', '1', '2023-02-04 11:41:07');
INSERT INTO `bookcommunicate` VALUES ('7', '1', '333', '1', '1', '2023-02-04 14:13:14');
INSERT INTO `bookcommunicate` VALUES ('8', '1', 'dd', '1', '1', '2023-02-05 02:28:59');
INSERT INTO `bookcommunicate` VALUES ('9', '1', '', '1', '1', '2023-03-10 06:53:18');
INSERT INTO `bookcommunicate` VALUES ('10', '1', '', '1', '1', '2023-03-10 06:53:20');
INSERT INTO `bookcommunicate` VALUES ('11', '1', '特色它', '1', '1', '2023-03-10 06:57:07');
INSERT INTO `bookcommunicate` VALUES ('12', '1', 'ddd', '1', '1', '2023-03-10 07:17:26');

-- ----------------------------
-- Table structure for bookfavorites
-- ----------------------------
DROP TABLE IF EXISTS `bookfavorites`;
CREATE TABLE `bookfavorites` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Book_ID` int(11) DEFAULT NULL,
  `User_ID` int(11) DEFAULT NULL,
  `createDT` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_7` (`User_ID`),
  KEY `FK_Reference_8` (`Book_ID`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`User_ID`) REFERENCES `users` (`ID`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`Book_ID`) REFERENCES `book` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookfavorites
-- ----------------------------
INSERT INTO `bookfavorites` VALUES ('1', '1', '1', '2023-02-11 14:18:52');

-- ----------------------------
-- Table structure for bookimage
-- ----------------------------
DROP TABLE IF EXISTS `bookimage`;
CREATE TABLE `bookimage` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `book_ID` int(11) DEFAULT NULL,
  `imageUrl` varchar(250) DEFAULT NULL,
  `fileName` varchar(100) DEFAULT NULL,
  `createDT` datetime DEFAULT NULL,
  `isMain` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_2` (`book_ID`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`book_ID`) REFERENCES `book` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookimage
-- ----------------------------
INSERT INTO `bookimage` VALUES ('1', '1', 'img/ai2.jpg', '11', '2023-01-30 14:43:29', '1');
INSERT INTO `bookimage` VALUES ('2', '2', 'img/ai2.jpg', '22', '2023-01-31 10:28:13', '1');
INSERT INTO `bookimage` VALUES ('19', '30', 'img/30Abe66546484b948879e51d8c612703b33941.png', 'fileList', '2023-03-02 21:36:56', '1');
INSERT INTO `bookimage` VALUES ('23', '30', 'img/30Aba3c03309ff940d9a47151ca5459d3cd695.jpg', 'fileList', '2023-03-03 23:15:00', '0');
INSERT INTO `bookimage` VALUES ('24', '30', 'img/30A236fa8ab979846c0bdc03e997955479a623.png', 'fileList', '2023-03-03 23:15:00', '0');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(50) DEFAULT NULL,
  `categoryDescribe` varchar(50) DEFAULT NULL,
  `layer` int(11) DEFAULT NULL,
  `sortNumber` int(11) DEFAULT NULL,
  `createDT` datetime DEFAULT NULL,
  `fatherID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'Alberta', '1', '1', '1', null, '0');
INSERT INTO `category` VALUES ('2', 'British', '1', '1', '2', null, '0');
INSERT INTO `category` VALUES ('3', 'Manitoba', '3', '1', '1', null, '0');
INSERT INTO `category` VALUES ('4', 'Newfoundland', '2', '1', '1', null, '0');
INSERT INTO `category` VALUES ('5', 'New Brunswick', '3', '1', '1', null, '0');
INSERT INTO `category` VALUES ('6', 'Northwest Territories', '1', '1', '1', null, '0');
INSERT INTO `category` VALUES ('7', 'School1', '1', '2', '1', null, '1');
INSERT INTO `category` VALUES ('8', 'School2', '1', '2', '1', null, '1');
INSERT INTO `category` VALUES ('9', 'School3', '1', '2', '1', null, '1');
INSERT INTO `category` VALUES ('10', 'Computer', '1', '3', '1', null, '7');
INSERT INTO `category` VALUES ('11', 'Business', '1', '3', '1', null, '7');
INSERT INTO `category` VALUES ('12', 'Media', '1', '3', '3', null, '7');
INSERT INTO `category` VALUES ('13', 'JAVA', '1', '4', '1', null, '10');
INSERT INTO `category` VALUES ('14', 'Mobile APP', '1', '4', '2', null, '10');
INSERT INTO `category` VALUES ('15', 'Game', '1', '4', '3', null, '10');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `book_ID` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `seller_ID` int(11) DEFAULT NULL,
  `buyer_ID` int(11) DEFAULT NULL,
  `transDate` datetime DEFAULT NULL,
  `contactEmail` varchar(50) DEFAULT NULL,
  `meetingAddress` varchar(255) DEFAULT NULL,
  `createDT` datetime DEFAULT NULL,
  `rating` varchar(255) DEFAULT NULL,
  `isEnd` int(11) DEFAULT NULL,
  `isAgree` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_9` (`book_ID`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`book_ID`) REFERENCES `book` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2105376774 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `schoolId` int(11) DEFAULT NULL,
  `school` varchar(50) DEFAULT NULL,
  `programeId` int(11) DEFAULT NULL,
  `programe` varchar(50) DEFAULT NULL,
  `createDT` datetime DEFAULT NULL,
  `studentID` varchar(50) DEFAULT NULL,
  `isDeleted` int(11) DEFAULT NULL,
  `area` varchar(50) DEFAULT NULL,
  `areaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'abc@gmail.com', '21218cca77804d2ba1922c33e0151105', 'JackLi', 'Tom', 'female', '2022-11-30', '1', '2', '7', 'School1', '10', 'Computer', null, '1555544545', '0', 'Alberta', '1');
INSERT INTO `users` VALUES ('2', '33@c.com', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null);
INSERT INTO `users` VALUES ('3', '33@c.com1', '96e79218965eb72c92a549dd5a330112', null, null, null, null, null, null, null, null, null, null, null, null, '0', null, null);
INSERT INTO `users` VALUES ('4', 'test@google.com', '21218cca77804d2ba1922c33e0151105', null, null, 'male', null, null, '', '7', 'School1', '10', 'Computer', null, 'a121233', '0', 'Alberta', '1');
