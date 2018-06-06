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
  `exam_date` date NOT NULL,
  `exam_name` char(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_inf
-- ----------------------------
INSERT INTO `exam_inf` VALUES ('9', '2017-12-13', '八单元');

-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=613 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score_inf
-- ----------------------------
INSERT INTO `score_inf` VALUES ('98', '9', '1', '545');
INSERT INTO `score_inf` VALUES ('99', '9', '2', '546');
INSERT INTO `score_inf` VALUES ('98', '9', '3', '547');
INSERT INTO `score_inf` VALUES ('95', '9', '4', '548');
INSERT INTO `score_inf` VALUES ('96', '9', '5', '549');
INSERT INTO `score_inf` VALUES ('93', '9', '6', '550');
INSERT INTO `score_inf` VALUES ('97', '9', '7', '551');
INSERT INTO `score_inf` VALUES ('93', '9', '8', '552');
INSERT INTO `score_inf` VALUES ('97', '9', '9', '553');
INSERT INTO `score_inf` VALUES ('98', '9', '10', '554');
INSERT INTO `score_inf` VALUES ('91', '9', '11', '555');
INSERT INTO `score_inf` VALUES ('94', '9', '12', '556');
INSERT INTO `score_inf` VALUES ('94', '9', '13', '557');
INSERT INTO `score_inf` VALUES ('96', '9', '14', '558');
INSERT INTO `score_inf` VALUES ('92', '9', '15', '559');
INSERT INTO `score_inf` VALUES ('93', '9', '16', '560');
INSERT INTO `score_inf` VALUES ('93', '9', '17', '561');
INSERT INTO `score_inf` VALUES ('92', '9', '18', '562');
INSERT INTO `score_inf` VALUES ('94', '9', '19', '563');
INSERT INTO `score_inf` VALUES ('92', '9', '20', '564');
INSERT INTO `score_inf` VALUES ('89', '9', '21', '565');
INSERT INTO `score_inf` VALUES ('93', '9', '22', '566');
INSERT INTO `score_inf` VALUES ('90', '9', '23', '567');
INSERT INTO `score_inf` VALUES ('89', '9', '24', '568');
INSERT INTO `score_inf` VALUES ('88', '9', '25', '569');
INSERT INTO `score_inf` VALUES ('94', '9', '26', '570');
INSERT INTO `score_inf` VALUES ('86', '9', '27', '571');
INSERT INTO `score_inf` VALUES ('90', '9', '28', '572');
INSERT INTO `score_inf` VALUES ('91', '9', '29', '573');
INSERT INTO `score_inf` VALUES ('90', '9', '30', '574');
INSERT INTO `score_inf` VALUES ('88', '9', '31', '575');
INSERT INTO `score_inf` VALUES ('92', '9', '32', '576');
INSERT INTO `score_inf` VALUES ('85', '9', '33', '577');
INSERT INTO `score_inf` VALUES ('82', '9', '34', '578');
INSERT INTO `score_inf` VALUES ('84', '9', '35', '579');
INSERT INTO `score_inf` VALUES ('85', '9', '36', '580');
INSERT INTO `score_inf` VALUES ('87', '9', '37', '581');
INSERT INTO `score_inf` VALUES ('89', '9', '38', '582');
INSERT INTO `score_inf` VALUES ('88', '9', '39', '583');
INSERT INTO `score_inf` VALUES ('90', '9', '40', '584');
INSERT INTO `score_inf` VALUES ('87', '9', '41', '585');
INSERT INTO `score_inf` VALUES ('88', '9', '42', '586');
INSERT INTO `score_inf` VALUES ('86', '9', '43', '587');
INSERT INTO `score_inf` VALUES ('88', '9', '44', '588');
INSERT INTO `score_inf` VALUES ('82', '9', '45', '589');
INSERT INTO `score_inf` VALUES ('81', '9', '46', '590');
INSERT INTO `score_inf` VALUES ('84', '9', '47', '591');
INSERT INTO `score_inf` VALUES ('91', '9', '48', '592');
INSERT INTO `score_inf` VALUES ('81', '9', '49', '593');
INSERT INTO `score_inf` VALUES ('80', '9', '50', '594');
INSERT INTO `score_inf` VALUES ('89', '9', '51', '595');
INSERT INTO `score_inf` VALUES ('89', '9', '52', '596');
INSERT INTO `score_inf` VALUES ('85', '9', '53', '597');
INSERT INTO `score_inf` VALUES ('91', '9', '54', '598');
INSERT INTO `score_inf` VALUES ('76', '9', '55', '599');
INSERT INTO `score_inf` VALUES ('88', '9', '56', '600');
INSERT INTO `score_inf` VALUES ('89', '9', '57', '601');
INSERT INTO `score_inf` VALUES ('83', '9', '58', '602');
INSERT INTO `score_inf` VALUES ('67', '9', '59', '603');
INSERT INTO `score_inf` VALUES ('76', '9', '60', '604');
INSERT INTO `score_inf` VALUES ('73', '9', '61', '605');
INSERT INTO `score_inf` VALUES ('62', '9', '62', '606');
INSERT INTO `score_inf` VALUES ('74', '9', '63', '607');
INSERT INTO `score_inf` VALUES ('73', '9', '64', '608');
INSERT INTO `score_inf` VALUES ('36', '9', '65', '609');
INSERT INTO `score_inf` VALUES ('63', '9', '66', '610');
INSERT INTO `score_inf` VALUES ('32', '9', '67', '611');
INSERT INTO `score_inf` VALUES ('10', '9', '68', '612');

-- ----------------------------
-- Table structure for stu_inf
-- ----------------------------
DROP TABLE IF EXISTS `stu_inf`;
CREATE TABLE `stu_inf` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `stu_num` int(8) unsigned NOT NULL,
  `stu_name` char(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `stu_num` (`stu_num`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=279 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_inf
-- ----------------------------
INSERT INTO `stu_inf` VALUES ('211', '1', '凡紫钰');
INSERT INTO `stu_inf` VALUES ('212', '2', '孙俊雪');
INSERT INTO `stu_inf` VALUES ('213', '3', '杨烨');
INSERT INTO `stu_inf` VALUES ('214', '4', '阳纯羽');
INSERT INTO `stu_inf` VALUES ('215', '5', '黄依然');
INSERT INTO `stu_inf` VALUES ('216', '6', '张宇勋');
INSERT INTO `stu_inf` VALUES ('217', '7', '刘天赐');
INSERT INTO `stu_inf` VALUES ('218', '8', '蒋肖凡');
INSERT INTO `stu_inf` VALUES ('219', '9', '黄子依');
INSERT INTO `stu_inf` VALUES ('220', '10', '张子豪');
INSERT INTO `stu_inf` VALUES ('221', '11', '陈梓洋');
INSERT INTO `stu_inf` VALUES ('222', '12', '李迅哲');
INSERT INTO `stu_inf` VALUES ('223', '13', '熊晨睿');
INSERT INTO `stu_inf` VALUES ('224', '14', '姚定荣');
INSERT INTO `stu_inf` VALUES ('225', '15', '周正');
INSERT INTO `stu_inf` VALUES ('226', '16', '张韶妍');
INSERT INTO `stu_inf` VALUES ('227', '17', '王晨熙');
INSERT INTO `stu_inf` VALUES ('228', '18', '万雨菲');
INSERT INTO `stu_inf` VALUES ('229', '19', '孙铭煊');
INSERT INTO `stu_inf` VALUES ('230', '20', '黄晓东');
INSERT INTO `stu_inf` VALUES ('231', '21', '周思艺');
INSERT INTO `stu_inf` VALUES ('232', '22', '王梦宇');
INSERT INTO `stu_inf` VALUES ('233', '23', '余鑫宇');
INSERT INTO `stu_inf` VALUES ('234', '24', '孙佳乐');
INSERT INTO `stu_inf` VALUES ('235', '25', '贺小星');
INSERT INTO `stu_inf` VALUES ('236', '26', '关羽骁');
INSERT INTO `stu_inf` VALUES ('237', '27', '徐亚博');
INSERT INTO `stu_inf` VALUES ('238', '28', '汪子裕');
INSERT INTO `stu_inf` VALUES ('239', '29', '解俊豪');
INSERT INTO `stu_inf` VALUES ('240', '30', '邹子玉');
INSERT INTO `stu_inf` VALUES ('241', '31', '刘宇菲');
INSERT INTO `stu_inf` VALUES ('242', '32', '刘雪晴');
INSERT INTO `stu_inf` VALUES ('243', '33', '杨子纯');
INSERT INTO `stu_inf` VALUES ('244', '34', '刘瑞雪');
INSERT INTO `stu_inf` VALUES ('245', '35', '孙雅思');
INSERT INTO `stu_inf` VALUES ('246', '36', '陈雨涵');
INSERT INTO `stu_inf` VALUES ('247', '37', '何梦诗');
INSERT INTO `stu_inf` VALUES ('248', '38', '王子航');
INSERT INTO `stu_inf` VALUES ('249', '39', '张文哲');
INSERT INTO `stu_inf` VALUES ('250', '40', '张丞晟');
INSERT INTO `stu_inf` VALUES ('251', '41', '卢宇航');
INSERT INTO `stu_inf` VALUES ('252', '42', '卢运欣');
INSERT INTO `stu_inf` VALUES ('253', '43', '张钰暄');
INSERT INTO `stu_inf` VALUES ('254', '44', '贺嘉怡');
INSERT INTO `stu_inf` VALUES ('255', '45', '程宇');
INSERT INTO `stu_inf` VALUES ('256', '46', '雷佳慧');
INSERT INTO `stu_inf` VALUES ('257', '47', '方永康');
INSERT INTO `stu_inf` VALUES ('258', '48', '蒋卓妍');
INSERT INTO `stu_inf` VALUES ('259', '49', '卢佳琪');
INSERT INTO `stu_inf` VALUES ('260', '50', '刘雅琪');
INSERT INTO `stu_inf` VALUES ('261', '51', '程诗影');
INSERT INTO `stu_inf` VALUES ('262', '52', '刘志远');
INSERT INTO `stu_inf` VALUES ('263', '53', '韩雨馨');
INSERT INTO `stu_inf` VALUES ('264', '54', '邓安泽');
INSERT INTO `stu_inf` VALUES ('265', '55', '王康');
INSERT INTO `stu_inf` VALUES ('266', '56', '邓紫馨');
INSERT INTO `stu_inf` VALUES ('267', '57', '王子涵');
INSERT INTO `stu_inf` VALUES ('268', '58', '毛泽龙');
INSERT INTO `stu_inf` VALUES ('269', '59', '杨书琴');
INSERT INTO `stu_inf` VALUES ('270', '60', '王梦琪');
INSERT INTO `stu_inf` VALUES ('271', '61', '王凯杰');
INSERT INTO `stu_inf` VALUES ('272', '62', '王子洛');
INSERT INTO `stu_inf` VALUES ('273', '63', '贺语涵');
INSERT INTO `stu_inf` VALUES ('274', '64', '习世博');
INSERT INTO `stu_inf` VALUES ('275', '65', '潘佳琪');
INSERT INTO `stu_inf` VALUES ('276', '66', '徐雨欣');
INSERT INTO `stu_inf` VALUES ('277', '67', '李宇丹');
INSERT INTO `stu_inf` VALUES ('278', '68', '施博');

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `authority` varchar(10) NOT NULL,
  `enabled` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES ('1', 'chen', '$2a$10$8uBJMKiSitxAZmksbFey/uA.N9fTRDw.l06f9sD4YPjcj2NoNNil6', 'USER', '');
