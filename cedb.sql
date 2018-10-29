/*
Navicat MySQL Data Transfer

Source Server         : aliCloud
Source Server Version : 50624
Source Host           : 101.132.72.117:3306
Source Database       : cedb

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-10-29 11:24:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for crawler_item
-- ----------------------------
DROP TABLE IF EXISTS `crawler_item`;
CREATE TABLE `crawler_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '景点id，同时也是景点编号',
  `title` varchar(100) NOT NULL COMMENT '景点标题',
  `focus_point` varchar(500) DEFAULT NULL COMMENT '景点看点',
  `price` bigint(20) NOT NULL COMMENT '门票价格，单位为：分',
  `num` int(10) NOT NULL COMMENT '门票数量',
  `barcode` varchar(30) DEFAULT NULL COMMENT '景点条形码',
  `image` varchar(500) DEFAULT NULL COMMENT '景点图片',
  `cid` bigint(10) NOT NULL COMMENT '所属类目，叶子类目',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '景点状态，1-正常，2-临时关闭，3-彻底关闭',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `status` (`status`),
  KEY `updated` (`updated`),
  KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=10741463465 DEFAULT CHARSET=utf8 COMMENT='景点表';

-- ----------------------------
-- Records of crawler_item
-- ----------------------------
INSERT INTO `crawler_item` VALUES ('2', '', null, '0', '0', null, null, '0', '0', '2016-10-16 12:05:08', '2016-10-16 12:05:08');

-- ----------------------------
-- Table structure for tb_item_cat
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_cat`;
CREATE TABLE `tb_item_cat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
  `name` varchar(50) DEFAULT NULL COMMENT '类目名称',
  `status` int(1) DEFAULT '1' COMMENT '状态。可选值:1(正常),2(删除)',
  `sort_order` int(4) DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `is_parent` tinyint(1) DEFAULT '1' COMMENT '该类目是否为父类目，1为true，0为false',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`,`status`) USING BTREE,
  KEY `sort_order` (`sort_order`)
) ENGINE=InnoDB AUTO_INCREMENT=1183 DEFAULT CHARSET=utf8 COMMENT='景点类目';

-- ----------------------------
-- Records of tb_item_cat
-- ----------------------------
INSERT INTO `tb_item_cat` VALUES ('1', '0', '图书、音像、电子书刊', '1', '1', '1', '2014-10-15 18:31:55', '2014-10-15 18:31:55');

-- ----------------------------
-- Table structure for tb_item_desc
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_desc`;
CREATE TABLE `tb_item_desc` (
  `item_id` bigint(20) NOT NULL COMMENT '景点D',
  `item_desc` text COMMENT '景点描述',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`item_id`),
  KEY `item_id` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='景点描述表';

-- ----------------------------
-- Records of tb_item_desc
-- ----------------------------
INSERT INTO `tb_item_desc` VALUES ('562379', '<div id=\"activity_header\" style=\"margin:0px;padding:0px;color:#666666;font-family:Arial, Verdana, 宋体;background-color:#FFFFFF;\">\r\n	<div align=\"center\" style=\"margin:0px;padding:0px;\">\r\n		<a href=\"http://huishou.jd.com/\" target=\"_blank\"><img border=\"0\" alt=\"\" class=\"\" src=\"http://img30.360buyimg.com/jgsq-productsoa/jfs/t2668/344/1180752391/111928/198211dc/5735a94fN3fe94b63.jpg\" /></a>\r\n	</div>\r\n	<div style=\"margin:0px;padding:0px;text-align:center;\">\r\n		<br />\r\n	</div>\r\n	<div style=\"margin:0px;padding:0px;text-align:center;\">\r\n		<img alt=\"\" class=\"\" src=\"http://img30.360buyimg.com/jgsq-productsoa/jfs/t1954/3/2074819795/82844/6883aa5a/56f0b912Nca2a4922.jpg\" /><br />\r\n	</div>\r\n</div>\r\n<div id=\"J-detail-content\" style=\"margin:0px;padding:0px;color:#666666;font-family:Arial, Verdana, 宋体;background-color:#FFFFFF;\">\r\n	<p style=\"text-align:center;\">\r\n		<img alt=\"\" class=\"\" src=\"http://img30.360buyimg.com/jgsq-productsoa/jfs/t2146/99/2563483512/378777/1eab3022/570e11e4N8c82868a.jpg\" />\r\n	</p>\r\n	<p style=\"text-align:center;\">\r\n		<img alt=\"\" class=\"\" src=\"http://img30.360buyimg.com/jgsq-productsoa/jfs/t2788/302/336398130/332995/de4c6196/570e11e6N2a5447a7.jpg\" />\r\n	</p>\r\n	<p style=\"text-align:center;\">\r\n		<img alt=\"\" class=\"\" src=\"http://img30.360buyimg.com/jgsq-productsoa/jfs/t2302/238/2586573688/94150/160b660a/570e122eN266b852e.jpg\" />\r\n	</p>\r\n<br />\r\n</div>\r\n<div id=\"activity_footer\" style=\"margin:0px;padding:0px;color:#666666;font-family:Arial, Verdana, 宋体;background-color:#FFFFFF;\">\r\n	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"750\" align=\"center\" class=\"ke-zeroborder\">\r\n		<tbody>\r\n			<tr>\r\n				<td height=\"31\" align=\"center\">\r\n					<div style=\"margin:0px;padding:5px;color:#666666;font-family:微软雅黑;font-size:18px;\">\r\n						所有图示仅供参考，每台设备均有专属序列号，具体序列号以实物设备为准。\r\n					</div>\r\n				</td>\r\n			</tr>\r\n		</tbody>\r\n	</table>\r\n</div>', '2016-08-23 09:15:09', '2016-08-23 09:15:09');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL COMMENT 'MD5加密',
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=528 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('7', 'tonychen', 'e10adc3949ba59abbe56e057f20f883e', '1233445555', '1233445555', '2016-03-04 16:22:40', '2016-03-04 16:22:40');
