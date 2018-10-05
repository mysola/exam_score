/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : exam_score

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-06-06 10:26:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam_inf
-- ----------------------------
DROP TABLE IF EXISTS `exam_inf`;
CREATE TABLE `exam_inf` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `exam_date` varchar(20) NOT NULL,
  `exam_name` char(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Table structure for score_inf
-- ----------------------------
DROP TABLE IF EXISTS `score_inf`;
CREATE TABLE `score_inf` (
  `chinese_score` double unsigned NOT NULL,
  `exam_id` int(8) unsigned NOT NULL,
  `stu_num` int(8) unsigned NOT NULL,
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `exam_id_score` (`exam_id`,`chinese_score`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Table structure for stu_inf
-- ----------------------------
DROP TABLE IF EXISTS `stu_inf`;
CREATE TABLE `stu_inf` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `stu_num` int(8) unsigned NOT NULL,
  `stu_name` char(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `stu_num` (`stu_num`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

