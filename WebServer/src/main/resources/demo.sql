/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2018-08-28 15:32:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('2');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_desc` varchar(200) DEFAULT NULL,
  `dept_name` varchar(50) DEFAULT NULL,
  `dept_parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '1', '1', null);
INSERT INTO `sys_dept` VALUES ('2', '2', '2', null);
INSERT INTO `sys_dept` VALUES ('3', '3', '3', '1');
INSERT INTO `sys_dept` VALUES ('13', 'root', 'root', null);
INSERT INTO `sys_dept` VALUES ('14', '31', '31', '3');
INSERT INTO `sys_dept` VALUES ('15', '41', '41', '3');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(50) DEFAULT NULL,
  `menu_parent_id` varchar(50) DEFAULT NULL,
  `menu_show` int(11) DEFAULT NULL,
  `menu_type` int(11) DEFAULT NULL,
  `meun_url` varchar(255) DEFAULT NULL,
  `menu_desc` varchar(255) DEFAULT NULL,
  `menu_sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', null, '1', '1', null, null, '1');
INSERT INTO `sys_menu` VALUES ('2', '菜单管理', '1', '1', '1', 'page/sys/menu.html', 'desc_menu', '1');
INSERT INTO `sys_menu` VALUES ('3', '用户管理', '1', '1', '1', 'page/sys/user.html', '', '2');
INSERT INTO `sys_menu` VALUES ('4', '角色管理', '1', '1', '1', 'page/sys/role.html', null, '3');
INSERT INTO `sys_menu` VALUES ('5', '部门管理', '1', '1', '1', 'page/sys/dept.html', null, '4');
INSERT INTO `sys_menu` VALUES ('8', 'load', '2', '0', '0', 'menu/query', '1', '1');
INSERT INTO `sys_menu` VALUES ('12', 'save', '2', '0', '0', 'menu/save', 'save', '1');
INSERT INTO `sys_menu` VALUES ('13', 'delById', '2', '0', '0', 'menu/delById', 'delById', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_desc` varchar(200) DEFAULT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'root', 'root');
INSERT INTO `sys_role` VALUES ('110', 'r2', 'r2');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FKf3mud4qoc7ayew8nml4plkevo` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('1', '12');
INSERT INTO `sys_role_menu` VALUES ('1', '13');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(60) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('a', 'a', 'a', 'a', 'a');
INSERT INTO `sys_user` VALUES ('root', '123456', '1', '1', '1');

-- ----------------------------
-- Table structure for sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept` (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dept_id` bigint(20) NOT NULL,
  PRIMARY KEY (`username`,`dept_id`),
  KEY `FK7bx7hnrbt5ikb4gqcgr339kmb` (`dept_id`),
  CONSTRAINT `FK7bx7hnrbt5ikb4gqcgr339kmb` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------
INSERT INTO `sys_user_dept` VALUES ('root', '2');
INSERT INTO `sys_user_dept` VALUES ('root', '13');
INSERT INTO `sys_user_dept` VALUES ('root', '14');
INSERT INTO `sys_user_dept` VALUES ('root', '15');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`username`,`role_id`),
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('root', '1');
INSERT INTO `sys_user_role` VALUES ('root', '110');
