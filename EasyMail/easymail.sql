/*
Navicat MySQL Data Transfer

Source Server         : user
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : easymail

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-10-25 11:11:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `cid` int(50) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `pnum` int(11) DEFAULT NULL,
  `imgurl` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('15', '手机1', '7', '200', '100', 'F:\\Jproject\\EasyMail1\\out\\artifacts\\EasyMail1_war_exploded\\WEB-INF\\upload\\6\\d\\0\\0\\c\\2\\d\\3\\0dd9926b-0349-45a7-8117-e7445a1b572c.jpg', '手机1手机1手机1');
INSERT INTO `product` VALUES ('16', '内存条1', '9', '50', '50', 'F:\\Jproject\\EasyMail1\\out\\artifacts\\EasyMail1_war_exploded\\WEB-INF\\upload\\7\\6\\4\\8\\4\\9\\6\\9\\244c59c6-bf0a-451b-81e6-18f8bb257e5f.jpg', '金手指金手指');
INSERT INTO `product` VALUES ('17', '手机2', '7', '200', '200', 'F:\\Jproject\\EasyMail1\\out\\artifacts\\EasyMail1_war_exploded\\WEB-INF\\upload\\a\\6\\9\\a\\d\\9\\8\\6\\8728bc90-ad8a-43f7-b9c4-6a8327f797aa.jpg', '777');
INSERT INTO `product` VALUES ('18', '手机1', '7', '200', '50', 'F:\\Jproject\\EasyMail1\\out\\artifacts\\EasyMail1_war_exploded\\WEB-INF\\upload\\a\\6\\9\\a\\d\\9\\8\\6\\8728bc90-ad8a-43f7-b9c4-6a8327f797aa.jpg', '555');
INSERT INTO `product` VALUES ('19', '三星S8+', '7', '5000', '2', 'F:\\Jproject\\EasyMail1\\out\\artifacts\\EasyMail1_war_exploded\\WEB-INF\\upload\\4\\5\\d\\0\\9\\0\\a\\0\\2052958d-1388-41e6-b337-eb672d7592d0.jpg', '三星炸弹7  但这是S8+');
INSERT INTO `product` VALUES ('20', '三星S8+', '7', '5000', '2', 'F:\\Jproject\\EasyMail1\\out\\artifacts\\EasyMail1_war_exploded\\WEB-INF\\upload\\6\\d\\5\\2\\2\\c\\5\\d\\bf12d972-24a3-4ca0-bf58-a0545b864074.jpg', '三星炸弹7  但这是S8+');
INSERT INTO `product` VALUES ('21', '内存条1', '9', '200', '50', 'F:\\Jproject\\EasyMail1\\out\\artifacts\\EasyMail1_war_exploded\\WEB-INF\\upload\\7\\6\\4\\8\\4\\9\\6\\9\\244c59c6-bf0a-451b-81e6-18f8bb257e5f.jpg', '金手指内存条,');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('6', '333');
INSERT INTO `product_category` VALUES ('7', '手机');
INSERT INTO `product_category` VALUES ('8', '打印机');
INSERT INTO `product_category` VALUES ('9', '内存条');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `nickname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'martin', '00c66aaf5f2c3f49946f15c1ad2ea0d3', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('2', 'martin2222', '569d0f324a03fb1d9c41aed6182c0023', 'mmartin22222222', 'martin@mm.com');
INSERT INTO `users` VALUES ('3', 'martin2', '16fcb1091f8a0cc70c96e2ff97fdd213', '??????', '1312322@13.13');
INSERT INTO `users` VALUES ('30', '', 'd41d8cd98f00b204e9800998ecf8427e', '', '');
INSERT INTO `users` VALUES ('31', '', 'd41d8cd98f00b204e9800998ecf8427e', '', '');
INSERT INTO `users` VALUES ('32', '??', '00c66aaf5f2c3f49946f15c1ad2ea0d3', 'mm', '1312322@13.13');
INSERT INTO `users` VALUES ('33', '??', '00c66aaf5f2c3f49946f15c1ad2ea0d3', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('34', '??', '00c66aaf5f2c3f49946f15c1ad2ea0d3', 'mmartin', '1312322@13.13');
INSERT INTO `users` VALUES ('35', '??', '00c66aaf5f2c3f49946f15c1ad2ea0d3', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('36', '??', '00c66aaf5f2c3f49946f15c1ad2ea0d3', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('37', 'tom', '00c66aaf5f2c3f49946f15c1ad2ea0d3', '1111', '111@11.11');
INSERT INTO `users` VALUES ('38', '??222', 'fae0b27c451c728867a567e8c1bb4e53', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('39', '??22', '9fc3d7152ba9336a670e36d0ed79bc43', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('40', '??22', '3083202a936b7d0ef8b680d7ae73fa1a', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('41', '??34', '3083202a936b7d0ef8b680d7ae73fa1a', 'mmartin', '1312322@13.13');
INSERT INTO `users` VALUES ('42', '??55', '3b712de48137572f3849aabd5666a4e3', '??', 'martin@mm.com');
INSERT INTO `users` VALUES ('43', '??999', '202cb962ac59075b964b07152d234b70', '??', 'martin@mm.com');
INSERT INTO `users` VALUES ('44', '??9999', 'a0a080f42e6f13b3a2df133f073095dd', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('45', '马丁33', '202cb962ac59075b964b07152d234b70', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('46', '马丁3333', '4297f44b13955235245b2497399d7a93', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('47', 'martin33', '00c66aaf5f2c3f49946f15c1ad2ea0d3', '嚣张', 'xiaozhang@163.com');
INSERT INTO `users` VALUES ('48', '??666', '202cb962ac59075b964b07152d234b70', '???', '6666@666.66');
INSERT INTO `users` VALUES ('49', '??66666', '202cb962ac59075b964b07152d234b70', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('50', '??666666666', '202cb962ac59075b964b07152d234b70', '123', '123123@123.123');
INSERT INTO `users` VALUES ('51', '马丁888888', '202cb962ac59075b964b07152d234b70', '123', '1312322@13.13');
INSERT INTO `users` VALUES ('52', '马丁555555', '202cb962ac59075b964b07152d234b70', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('53', '马丁7777', 'd79c8788088c2193f0244d8f1f36d2db', 'mmartin', 'martin@mm.com');
INSERT INTO `users` VALUES ('54', 'martinz', '4297f44b13955235245b2497399d7a93', '123', '123123@123.123');
INSERT INTO `users` VALUES ('55', 'root', '63a9f0ea7bb98050796b649e85481845', 'root', 'root@root.root');
