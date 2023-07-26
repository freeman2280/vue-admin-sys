/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.54 : Database - vue-admin-sys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`vue-admin-sys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `vue-admin-sys`;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(64) NOT NULL COMMENT '菜单名称',
  `path` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(255) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `component` varchar(255) DEFAULT NULL COMMENT '路由组件',
  `type` int(5) NOT NULL COMMENT '类型     0：目录   1：菜单   2：按钮',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单图标',
  `orderNum` int(11) DEFAULT NULL COMMENT '排序',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  `statu` int(5) unsigned zerofill NOT NULL DEFAULT '00000' COMMENT '状态，默认为0,1代表禁用',
  `is_deleted` int(11) DEFAULT '0' COMMENT '逻辑删除，默认为0,1代表删除',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`parent_id`,`name`,`path`,`perms`,`component`,`type`,`icon`,`orderNum`,`created`,`updated`,`statu`,`is_deleted`,`remark`) values (1,0,'系统管理','','sys:manage','',0,'el-icon-s-operation',1,'2021-01-15 18:58:18','2021-01-15 18:58:20',00001,0,NULL),(2,1,'用户管理','/main/users','sys:user:list','User',1,'el-icon-s-custom',1,'2021-01-15 19:03:45','2021-01-15 19:03:48',00001,0,NULL),(3,1,'角色管理','/main/roles','sys:role:list','Role',1,'el-icon-rank',2,'2021-01-15 19:03:45','2021-01-15 19:03:48',00001,0,NULL),(4,1,'菜单管理','/main/menus','sys:menu:list','Menu',1,'el-icon-menu',3,'2021-01-15 19:03:45','2021-01-15 19:03:48',00001,0,NULL),(5,0,'系统工具','','sys:tools',NULL,0,'el-icon-s-tools',2,'2021-01-15 19:06:11',NULL,00001,0,NULL),(6,5,'数字字典','/main/dicts','sys:dict:list','Dict',1,'el-icon-s-order',1,'2021-01-15 19:07:18','2021-01-18 16:32:13',00001,0,NULL),(7,3,'添加角色','','sys:role:save','',2,'',1,'2021-01-15 23:02:25','2021-01-17 21:53:14',00000,0,NULL),(9,2,'添加用户',NULL,'sys:user:save',NULL,2,NULL,1,'2021-01-17 21:48:32',NULL,00001,0,NULL),(10,2,'修改用户',NULL,'sys:user:update',NULL,2,NULL,2,'2021-01-17 21:49:03','2021-01-17 21:53:04',00001,0,NULL),(11,2,'删除用户',NULL,'sys:user:delete',NULL,2,NULL,3,'2021-01-17 21:49:21',NULL,00001,0,NULL),(12,2,'分配角色',NULL,'sys:user:role',NULL,2,NULL,4,'2021-01-17 21:49:58',NULL,00001,0,NULL),(13,2,'重置密码',NULL,'sys:user:repass',NULL,2,NULL,5,'2021-01-17 21:50:36',NULL,00001,0,NULL),(14,3,'修改角色',NULL,'sys:role:update',NULL,2,NULL,2,'2021-01-17 21:51:14',NULL,00001,0,NULL),(15,3,'删除角色',NULL,'sys:role:delete',NULL,2,NULL,3,'2021-01-17 21:51:39',NULL,00001,0,NULL),(16,3,'分配权限',NULL,'sys:role:perm',NULL,2,NULL,5,'2021-01-17 21:52:02',NULL,00001,0,NULL),(17,4,'添加菜单',NULL,'sys:menu:save',NULL,2,NULL,1,'2021-01-17 21:53:53','2021-01-17 21:55:28',00001,0,NULL),(18,4,'修改菜单',NULL,'sys:menu:update',NULL,2,NULL,2,'2021-01-17 21:56:12',NULL,00001,0,NULL),(19,4,'删除菜单',NULL,'sys:menu:delete',NULL,2,NULL,3,'2021-01-17 21:56:36',NULL,00001,0,NULL),(20,0,'首页','/main/index','sys:index','Index',0,'el-icon-s-order',1,'2023-06-20 20:33:24',NULL,00000,0,NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `code` varchar(64) NOT NULL COMMENT '角色码',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `created` datetime DEFAULT NULL COMMENT '角色被创建时间',
  `updated` datetime DEFAULT NULL COMMENT '角色更新时间',
  `statu` int(5) NOT NULL DEFAULT '0' COMMENT '状态 默认为0 1代表禁用',
  `is_deleted` int(11) DEFAULT '0' COMMENT '是否删除 默认为0 1代表已经删除，逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`code`,`remark`,`created`,`updated`,`statu`,`is_deleted`) values (3,'普通用户','normal','只有基本查看功能','2021-01-04 10:09:14','2021-01-30 08:19:52',1,0),(6,'超级管理员','admin','系统默认最高权限，不可以编辑和任意修改','2021-01-16 13:29:03','2021-01-17 15:50:45',1,0);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values (60,6,1),(61,6,2),(62,6,9),(63,6,10),(64,6,11),(65,6,12),(66,6,13),(67,6,3),(68,6,7),(69,6,14),(70,6,15),(71,6,16),(72,6,4),(73,6,17),(74,6,18),(75,6,19),(76,6,5),(77,6,6),(96,3,1),(97,3,2),(98,3,3),(99,3,4),(100,3,5),(101,3,6),(102,6,20),(103,3,20);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `created` datetime DEFAULT NULL COMMENT '被创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新资料时间',
  `last_login` datetime DEFAULT NULL COMMENT '上次登录时间',
  `is_deleted` int(11) DEFAULT '0' COMMENT '逻辑删除，默认为0,1代表删除',
  `is_account_non_expired` int(11) NOT NULL DEFAULT '1' COMMENT '帐户是否过期(1-未过期，0-已过期)',
  `is_account_non_locked` int(11) NOT NULL DEFAULT '1' COMMENT '帐户是否被锁定(1-未过期，0-已过期)',
  `is_credentials_non_expired` int(11) NOT NULL DEFAULT '1' COMMENT '密码是否过期(1-未过期，0-已过期)',
  `is_enabled` int(11) NOT NULL DEFAULT '1' COMMENT '帐户是否可用(1-可用，0-禁用)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`avatar`,`email`,`city`,`created`,`updated`,`last_login`,`is_deleted`,`is_account_non_expired`,`is_account_non_locked`,`is_credentials_non_expired`,`is_enabled`) values (1,'admin','$2a$10$W1S143n/eZOZMs5C7Y.hV.uAj6OYQjt1DeDhYHGQG6RTB6sPz7k1u','https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg','123@qq.com','广州','2021-01-12 22:13:53','2021-01-16 16:57:32','2020-12-30 08:38:37',0,1,1,1,1),(2,'test','$2a$10$0ilP4ZD1kLugYwLCs4pmb.ZT9cFqzOZTNaMiHxrBnVIQUGUwEvBIO','https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg','test@qq.com',NULL,'2021-01-30 08:20:22','2021-01-30 08:55:57',NULL,0,1,1,1,1),(4,'cr','$2a$10$W1S143n/eZOZMs5C7Y.hV.uAj6OYQjt1DeDhYHGQG6RTB6sPz7k1u',NULL,NULL,NULL,'2023-06-05 15:14:33','2023-06-05 15:14:33',NULL,0,1,1,1,1);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values (4,1,6),(7,1,3),(13,2,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
