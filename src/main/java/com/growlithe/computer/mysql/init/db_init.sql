/*
 Navicat Premium Data Transfer

 Source Server         : localhost_growlithe
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : practice

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 29/06/2018 17:49:30
*/


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

/*
computer 库
 */

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `book_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '书名',
  `book_english_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '书籍英文名',
  `book_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '作者',
  `book_class` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类 academic 学术  cartoon 卡通 literature 文学 other 其他（杂书） technology 技术 ',
  `book_subordinate_class` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '下级分类',
  `book_format` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '格式 exe pdf caj txt',
  `book_capacity` decimal(10, 3) NOT NULL COMMENT '容量',
  `capacity_unit` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '容量单位 G M KB',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态 1 有效 0 失效',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'WANGXin' COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '名称',
  `english_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '英文名称 ',
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '作者',
  `capacity` decimal(20, 3) NOT NULL COMMENT '容量大小 ',
  `capacity_unit` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '单位 T G M',
  `format` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'iso mp4 avi wmv rmvb',
  `language` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '语言 Chinese-中文 Japanese-日文 English-英文',
  `quality` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '1080P 720P 480P ',
  `video_class` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '分类 cartoon-卡通 tv-电视剧 movie-电影 open_class-公开课 documentary-纪录片 mv-mv celebration-庆典 concert-音乐会',
  `series` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系列 ',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态 1 有效 0 失效',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'WANGXin' COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;




/*
practice 库
 */
-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `age` int(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` bigint(10) NULL DEFAULT NULL,
  `age` int(3) NULL DEFAULT NULL,
  `parent_emp_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;