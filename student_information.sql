/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50649
Source Host           : localhost:3306
Source Database       : student_information

Target Server Type    : MYSQL
Target Server Version : 50649
File Encoding         : 65001

Date: 2020-12-21 11:59:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `bh` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `class_name` varchar(255) COLLATE utf8_bin NOT NULL,
  KEY `cl_mj` (`bh`),
  KEY `id` (`id`),
  CONSTRAINT `cl_mj` FOREIGN KEY (`bh`) REFERENCES `major` (`bh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1001', '1', '数科1943');
INSERT INTO `class` VALUES ('1001', '2', '数科1843');
INSERT INTO `class` VALUES ('1002', '1', '数科1941');
INSERT INTO `class` VALUES ('1002', '2', '数科1841');
INSERT INTO `class` VALUES ('1003', '1', '会计1941');
INSERT INTO `class` VALUES ('1003', '2', '会计1942');
INSERT INTO `class` VALUES ('1004', '1', '数科1945');
INSERT INTO `class` VALUES ('1004', '2', '数科1845');
INSERT INTO `class` VALUES ('1005', '1', '财政1941');
INSERT INTO `class` VALUES ('1005', '2', '财政1942');
INSERT INTO `class` VALUES ('1006', '1', '工程1941');
INSERT INTO `class` VALUES ('1006', '2', '工程1942');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `bh` int(11) NOT NULL,
  `major_name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`bh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('1001', '计算机专业');
INSERT INTO `major` VALUES ('1002', '软件工程');
INSERT INTO `major` VALUES ('1003', '会计学');
INSERT INTO `major` VALUES ('1004', '金融学');
INSERT INTO `major` VALUES ('1005', '财政学');
INSERT INTO `major` VALUES ('1006', '工程造价');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `xh` int(11) NOT NULL,
  `xm` varchar(255) COLLATE utf8_bin NOT NULL,
  `xb` varchar(255) COLLATE utf8_bin NOT NULL,
  `csrq` date NOT NULL,
  `zy` int(11) NOT NULL,
  `bj` int(11) NOT NULL,
  `rxnd` date NOT NULL,
  `gkzf` double NOT NULL,
  PRIMARY KEY (`xh`) USING BTREE,
  KEY `st_cl` (`bj`),
  KEY `st_mj` (`zy`),
  CONSTRAINT `st_cl` FOREIGN KEY (`bj`) REFERENCES `class` (`id`),
  CONSTRAINT `st_mj` FOREIGN KEY (`zy`) REFERENCES `major` (`bh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'she', '男', '2000-01-15', '1002', '2', '2019-09-30', '740');
INSERT INTO `student` VALUES ('2', 'haha', '男', '2000-10-07', '1001', '1', '2020-11-29', '600');
INSERT INTO `student` VALUES ('3', 'enen', '女', '2000-02-10', '1002', '2', '2020-11-28', '666');
INSERT INTO `student` VALUES ('4', 'yeye', '女', '2000-03-20', '1003', '1', '2020-11-18', '500');
INSERT INTO `student` VALUES ('5', 'xixi', '女', '2000-06-15', '1004', '1', '2020-11-11', '678');
INSERT INTO `student` VALUES ('6', 'papa', '男', '2000-12-09', '1003', '2', '2020-11-03', '654');
INSERT INTO `student` VALUES ('7', 'huhu', '男', '2000-03-14', '1004', '1', '2020-10-26', '578');
INSERT INTO `student` VALUES ('8', 'gugu', '男', '1999-12-12', '1001', '1', '2020-11-04', '701');
INSERT INTO `student` VALUES ('9', 'dudu', '男', '1999-03-05', '1004', '2', '2020-10-29', '546');
INSERT INTO `student` VALUES ('10', 'susu', '女', '2001-11-11', '1005', '1', '2020-11-18', '653');
INSERT INTO `student` VALUES ('11', 'jiji', '女', '2001-12-13', '1005', '2', '2020-11-16', '611');
INSERT INTO `student` VALUES ('14', 'dede', '男', '2000-02-05', '1001', '2', '2020-01-01', '704');
INSERT INTO `student` VALUES ('15', 'cici', '女', '2000-05-06', '1001', '1', '2020-10-29', '455');
INSERT INTO `student` VALUES ('16', 'sss', '女', '2000-05-12', '1002', '2', '2020-11-26', '555');
INSERT INTO `student` VALUES ('17', 'ddd', '男', '2000-05-10', '1001', '1', '2020-11-10', '467');
INSERT INTO `student` VALUES ('18', 'eee', '女', '1999-12-14', '1003', '2', '2020-11-01', '534');
INSERT INTO `student` VALUES ('19', 'ggg', '男', '1998-03-14', '1005', '2', '2020-11-04', '587');
INSERT INTO `student` VALUES ('20', 'fff', '女', '2004-12-12', '1005', '1', '2020-11-25', '521');
INSERT INTO `student` VALUES ('21', 'esd', '女', '2002-12-13', '1003', '2', '2020-11-17', '569');
INSERT INTO `student` VALUES ('22', 'waa', '男', '2002-10-10', '1003', '1', '2020-11-19', '659');
INSERT INTO `student` VALUES ('23', 'hhh', '女', '2002-09-08', '1002', '2', '2020-11-05', '698');
INSERT INTO `student` VALUES ('24', 'kkk', '男', '2003-09-20', '1002', '1', '2020-11-18', '668');
INSERT INTO `student` VALUES ('25', 'lll', '女', '2003-09-10', '1001', '1', '2020-12-02', '702');
INSERT INTO `student` VALUES ('26', 'yyy', '女', '2000-03-07', '1001', '1', '2020-01-02', '534');
INSERT INTO `student` VALUES ('27', 'hehe', '女', '2003-08-05', '1002', '2', '2020-11-10', '679');
INSERT INTO `student` VALUES ('28', 'keke', '女', '2002-10-14', '1002', '1', '2020-11-19', '621');
INSERT INTO `student` VALUES ('30', 'aiai', '男', '2009-12-02', '1003', '1', '2020-11-11', '669');
INSERT INTO `student` VALUES ('33', 'hcy', '女', '2000-10-07', '1005', '2', '2019-10-09', '666');
INSERT INTO `student` VALUES ('35', 'sad', '男', '2000-10-24', '1006', '2', '2020-12-01', '537');
INSERT INTO `student` VALUES ('36', 'dow', '男', '1999-09-03', '1004', '1', '2020-11-06', '643');
INSERT INTO `student` VALUES ('37', 'dew', '男', '1999-02-04', '1005', '2', '2020-12-01', '654');
INSERT INTO `student` VALUES ('40', 'gww', '男', '2000-12-11', '1004', '1', '2020-12-03', '463');
INSERT INTO `student` VALUES ('41', 'hda', '男', '1999-03-04', '1003', '2', '2020-12-05', '573');
INSERT INTO `student` VALUES ('42', 'nav', '女', '2000-04-20', '1002', '2', '2020-11-30', '475');
INSERT INTO `student` VALUES ('43', 'nfh', '女', '2002-03-15', '1002', '1', '2020-11-20', '575');
INSERT INTO `student` VALUES ('44', 'ndh', '女', '2002-09-08', '1003', '2', '2020-12-04', '496');
INSERT INTO `student` VALUES ('45', 'dhi', '女', '2001-02-05', '1004', '1', '2020-11-10', '647');
INSERT INTO `student` VALUES ('46', 'dhe', '女', '2001-10-04', '1001', '1', '2020-12-17', '549');
INSERT INTO `student` VALUES ('47', 'jdh', '男', '2001-12-30', '1001', '2', '2020-12-09', '486');
INSERT INTO `student` VALUES ('48', 'hsn', '女', '2000-04-20', '1002', '2', '2020-12-03', '584');
INSERT INTO `student` VALUES ('49', 'haei', '女', '2000-03-07', '1005', '2', '2020-11-24', '583');
INSERT INTO `student` VALUES ('50', 'fhwi', '男', '2001-02-10', '1006', '1', '2020-12-07', '468');
INSERT INTO `student` VALUES ('51', 'rhsi', '男', '2000-12-10', '1005', '1', '2020-12-10', '460');
INSERT INTO `student` VALUES ('52', 'feiwu', '男', '2000-12-03', '1001', '2', '2020-11-11', '683');
INSERT INTO `student` VALUES ('53', 'fiea', '女', '2000-10-04', '1003', '2', '2020-12-10', '682');
INSERT INTO `student` VALUES ('54', 'ncei', '女', '1999-02-10', '1004', '1', '2020-12-09', '529');
INSERT INTO `student` VALUES ('55', 'cnei', '女', '2000-10-30', '1001', '2', '2020-11-23', '720');
INSERT INTO `student` VALUES ('56', 'cjeo', '女', '2003-09-20', '1002', '1', '2020-12-02', '462');
INSERT INTO `student` VALUES ('57', 'fdjp', '男', '2000-10-05', '1005', '2', '2020-11-13', '673');
INSERT INTO `student` VALUES ('58', 'nvru', '女', '2001-05-02', '1003', '2', '2020-11-08', '671');
INSERT INTO `student` VALUES ('59', 'nfie', '女', '2002-04-10', '1004', '2', '2020-12-10', '582');
INSERT INTO `student` VALUES ('60', 'fnis', '女', '2001-05-29', '1001', '1', '2020-12-12', '603');
INSERT INTO `student` VALUES ('61', 'jdia', '男', '2001-03-20', '1005', '2', '2020-12-04', '630');
INSERT INTO `student` VALUES ('62', 'fncs', '女', '2000-05-29', '1004', '1', '2020-11-24', '673');
INSERT INTO `student` VALUES ('63', 'mfis', '男', '2000-04-29', '1002', '2', '2020-11-18', '629');
INSERT INTO `student` VALUES ('64', 'fmix', '女', '2002-03-09', '1001', '2', '2020-11-26', '493');
INSERT INTO `student` VALUES ('65', 'mdie', '男', '2000-06-09', '1006', '1', '2020-12-05', '673');
INSERT INTO `student` VALUES ('66', 'mdia', '男', '2000-04-06', '1006', '2', '2020-12-13', '674');
INSERT INTO `student` VALUES ('67', 'mmmd', '男', '2000-05-20', '1002', '1', '2020-11-25', '583');
INSERT INTO `student` VALUES ('68', 'mfow', '男', '2001-04-10', '1004', '2', '2020-09-25', '673');
INSERT INTO `student` VALUES ('69', 'mdkw', '男', '2001-03-12', '1001', '2', '2020-12-04', '489');
INSERT INTO `student` VALUES ('70', 'jdma', '女', '2002-09-03', '1003', '1', '2020-11-23', '683');
INSERT INTO `student` VALUES ('71', 'mfil', '女', '2002-05-08', '1002', '2', '2020-10-29', '693');
INSERT INTO `student` VALUES ('72', 'mdik', '女', '2000-04-20', '1004', '2', '2020-11-17', '699');
INSERT INTO `student` VALUES ('73', 'loe', '女', '2001-09-03', '1002', '1', '2020-11-11', '521');
INSERT INTO `student` VALUES ('74', 'flso', '女', '2000-03-26', '1003', '2', '2020-11-13', '701');
INSERT INTO `student` VALUES ('75', 'msie', '女', '2000-05-17', '1002', '2', '2020-12-05', '708');
INSERT INTO `student` VALUES ('76', 'eqoq', '男', '2000-05-16', '1005', '1', '2020-11-25', '688');
INSERT INTO `student` VALUES ('77', 'qiur', '男', '2001-05-19', '1004', '2', '2020-11-29', '529');
INSERT INTO `student` VALUES ('78', 'fjoe', '男', '2001-06-27', '1003', '1', '2020-10-22', '679');
INSERT INTO `student` VALUES ('79', 'mfin', '男', '2001-09-18', '1002', '2', '2020-10-19', '680');
INSERT INTO `student` VALUES ('80', 'doka', '男', '1999-04-19', '1004', '1', '2020-11-03', '580');
INSERT INTO `student` VALUES ('81', 'fmai', '女', '1999-04-16', '1004', '2', '2020-11-21', '629');
INSERT INTO `student` VALUES ('82', 'dkia', '男', '2000-10-16', '1005', '2', '2020-12-09', '672');
INSERT INTO `student` VALUES ('83', 'cjg', '男', '2000-07-09', '1003', '1', '2020-10-29', '690');
INSERT INTO `student` VALUES ('84', 'msl', '女', '2000-07-16', '1005', '1', '2020-11-15', '492');
INSERT INTO `student` VALUES ('85', 'ldo', '女', '2000-05-19', '1004', '2', '2020-12-14', '673');
INSERT INTO `student` VALUES ('86', 'newo', '女', '2000-07-14', '1003', '2', '2020-12-15', '703');
INSERT INTO `student` VALUES ('87', 'msoe', '女', '2001-05-01', '1004', '1', '2020-12-06', '693');
INSERT INTO `student` VALUES ('88', 'fmaq', '女', '2002-08-19', '1003', '2', '2020-11-18', '692');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', '2001');
INSERT INTO `user` VALUES ('she', '1234');
