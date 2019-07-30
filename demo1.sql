/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : demo1

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-07-30 16:45:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chapter_name` varchar(255) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhhaina8rg7bpmg1qesiluu8vu` (`course_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) DEFAULT NULL,
  `describe1` varchar(255) DEFAULT NULL,
  `difficulty` varchar(255) DEFAULT NULL,
  `lecturer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '11', '11', '11', '11');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(2) NOT NULL AUTO_INCREMENT,
  `auth` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'teacher', 'teacher');
INSERT INTO `role` VALUES ('2', 'student', 'student');
INSERT INTO `role` VALUES ('3', 'admin', 'admin');

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `section_content` varchar(255) DEFAULT NULL,
  `section_name` varchar(255) DEFAULT NULL,
  `chapter_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4ce3ouca101jd5h6xsa3f15pf` (`chapter_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES ('1', '11', '11', '11', '11', '1');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '12345', '12345');
INSERT INTO `student` VALUES ('2', '123456', '123456');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastpasswordresetdate` datetime NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `role_role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKs2ym81xl98n65ndx09xpwxm66` (`role_role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '305810827@qq.com', '', '1234', '2019-07-28 20:11:46', '1234', '$2a$10$u8cEsrGor339pr6Gh.RaXuawODgHGHLMJSQ9w5KE0y.STaH0oG03y', '123', '2');
INSERT INTO `user` VALUES ('3', '305810827@qq.com', '', 'tatata', '2019-07-28 20:20:57', 'tatata', '$2a$10$igCrg/cGZb4Dg5JzsJMdDeCvqLRd0aMBklzsftKHM4gN5EwS0aW.G', '3333', '1');
INSERT INTO `user` VALUES ('4', '305810827@qq.com', '', 'tatata', '2019-07-28 20:24:20', 'tatata', '$2a$10$yzSwZ2SPdgG3moi6gIsAAuaj6XF/b.E2snAsWd2FDKtFMLL0jZmym', '2222', '3');
INSERT INTO `user` VALUES ('5', '305810827@qq.com', '', '123', '2019-07-28 22:35:40', '123', '$2a$10$.MGBChPNse0gTVtZdF/a7OgjUzDjuYMML4UHU.5J2JeOsUxorONuy', '12344', '3');
INSERT INTO `user` VALUES ('6', '305810827@qq.com', '', 'tatata', '2019-07-28 22:36:45', 'tatata', '$2a$10$/gOUOWDK6cTyCpImsH4wve6NnfgNWxF6SY0XYOw0LDHwhnkAzMHiW', '123445', '1');
INSERT INTO `user` VALUES ('7', '305810827@qq.com', '', '213', '2019-07-28 22:53:10', '213', '$2a$10$sXuQWLxdgGDPXpLWo1N2Keb3DTG1BixatTjZhTX7ylru60h/y/IQC', '231321', '2');
INSERT INTO `user` VALUES ('8', '305810827@qq.com', '', '123', '2019-07-29 17:53:05', '123', '$2a$10$.8ZARQ94cgrEWe0OmNqHe.7ZWgtcA7bZt0M5wvf/Rq5fP4ozMZWsm', '222111', '2');
INSERT INTO `user` VALUES ('9', '305810827@qq.com', '', '11', '2019-07-29 22:42:32', '11', '$2a$10$IU.YGqh6ycEKZ5.Ep8uZBObuSvgnqy5K6zv/WP1vFDpsaIS8BruP6', '12345', '2');
INSERT INTO `user` VALUES ('10', '305810827@qq.com', '', '5555', '2019-07-29 22:58:36', '5555', '$2a$10$FxO/krQ0sWwSOn4qHaoRSu4fPxXCAgnNQN.DtrQvN8nayFYbPuROS', '5555', '1');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `video` varchar(255) DEFAULT NULL,
  `videoname` varchar(255) DEFAULT NULL,
  `videopicture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('1', '11', '11', '111');
