/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : usedbooksystem

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2023-01-31 23:01:27
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
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_1` (`user_ID`),
  KEY `FK_Reference_5` (`category_ID`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`user_ID`) REFERENCES `users` (`ID`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`category_ID`) REFERENCES `category` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', 'aa', 'ThinkJava', 'aa', 'aa', '2008', '13', '12.00', '11.25', null, 'has', '1', null);
INSERT INTO `book` VALUES ('2', '22', 'C# Design', '22', '22', '2018', '14', '3.00', '3.00', null, '33', '1', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookimage
-- ----------------------------
INSERT INTO `bookimage` VALUES ('1', '1', 'img/home.jpg', '11', '2023-01-30 14:43:29', '1');
INSERT INTO `bookimage` VALUES ('2', '2', 'img/home.jpg', '22', '2023-01-31 10:28:13', '1');

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
  `school` varchar(50) DEFAULT NULL,
  `programe` varchar(50) DEFAULT NULL,
  `createDT` datetime DEFAULT NULL,
  `studentID` varchar(50) DEFAULT NULL,
  `isDeleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'abc@gmail.com', '21218cca77804d2ba1922c33e0151105', 'JackLi', 'Tom', 'man', '2022-12-30', '1', '2', '3', '4', null, '1555544545', null);
