/*
 Navicat Premium Data Transfer

 Source Server         : localhost3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : library

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 01/09/2022 12:31:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_log
-- ----------------------------
DROP TABLE IF EXISTS `book_log`;
CREATE TABLE `book_log`  (
  `log_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `book_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书id',
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书名称',
  `book_press` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书出版社',
  `book_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书分类号',
  `book_status` int(0) NULL DEFAULT 0 COMMENT '图书状态',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户电话',
  `borrow_created` datetime(0) NULL DEFAULT NULL COMMENT '借书时间',
  `borrow_end` datetime(0) NULL DEFAULT NULL COMMENT '还书时间',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_log
-- ----------------------------
INSERT INTO `book_log` VALUES (1, '4130142031546010book', '', '', '2101817525683039type', 0, '7307785468161619user', 'ggg', 'ggg', '15234435771', '2022-08-29 09:14:47', NULL);
INSERT INTO `book_log` VALUES (2, '5981059953070823book', '三国演义', '中国人民出版社', '0624302661729225type', 0, '7307785468161619user', 'ggg', 'ggg', '15234435771', '2022-08-29 09:23:52', '2022-08-29 09:40:56');
INSERT INTO `book_log` VALUES (3, '5981059953070823book', '三国演义', '中国人民出版社', '0624302661729225type', 0, '7307785468161619user', 'ggg', 'ggg', '15234435771', '2022-08-29 09:38:32', '2022-08-29 09:40:56');

-- ----------------------------
-- Table structure for book_type
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type`  (
  `book_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图书分类id',
  `book_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书分类名称',
  `logic_deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除(0未删除 1已删除)',
  `gmt_created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`book_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_type
-- ----------------------------
INSERT INTO `book_type` VALUES ('0624302661729225type', '小说', 0, '2022-08-26 11:00:03', '2022-08-26 11:13:29');
INSERT INTO `book_type` VALUES ('0946349918124535type', '通用', 0, '2022-08-26 11:01:21', NULL);
INSERT INTO `book_type` VALUES ('2101817525683039type', '文学', 0, '2022-08-26 11:00:57', NULL);
INSERT INTO `book_type` VALUES ('2161247170320235type', '美食', 0, '2022-08-26 11:01:11', NULL);
INSERT INTO `book_type` VALUES ('3959083525364293type', '教育', 0, '2022-08-26 11:01:05', NULL);

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `book_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图书id',
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书名称',
  `book_press` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书出版社',
  `book_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '图书价格',
  `book_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书图片地址',
  `book_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书分类号',
  `book_status` int(0) NULL DEFAULT 0 COMMENT '图书状态',
  `logic_deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除(0未删除 1已删除)',
  `gmt_created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('4130142031546010book', '红楼梦', '中国人民出版社', 789.00, 'https://nucstudy.oss-cn-hangzhou.aliyuncs.com/20220826185221335300.jpg', '2101817525683039type', 0, 0, '2022-08-26 18:52:22', '2022-08-29 09:56:23');
INSERT INTO `books` VALUES ('5022585266907596book', '西游记', '中国人民出版社', 23.00, NULL, '2101817525683039type', 0, 0, '2022-08-26 18:34:18', NULL);
INSERT INTO `books` VALUES ('5981059953070823book', '三国演义', '中国人民出版社', 45.00, 'https://nucstudy.oss-cn-hangzhou.aliyuncs.com/20220829090051930912.jpg', '0624302661729225type', 0, 0, '2022-08-29 09:00:53', NULL);
INSERT INTO `books` VALUES ('6991684925761195book', '西游记', '中国人民出版社', 23.00, NULL, '2101817525683039type', 0, 0, '2022-08-26 18:40:08', NULL);
INSERT INTO `books` VALUES ('7654451335815759book', '西游记', '中国人民出版社', 23.00, NULL, '2101817525683039type', 0, 0, '2022-08-26 18:36:00', NULL);
INSERT INTO `books` VALUES ('7982466894744347book', '西游记', '中国人民出版社', 23.00, NULL, '2101817525683039type', 0, 0, '2022-08-26 18:29:28', '2022-08-26 18:49:05');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `roles_id` int(0) NOT NULL COMMENT '角色id',
  `roles_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`roles_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (0, '管理员');
INSERT INTO `roles` VALUES (1, '普通用户');

-- ----------------------------
-- Table structure for roles_permission
-- ----------------------------
DROP TABLE IF EXISTS `roles_permission`;
CREATE TABLE `roles_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roles_id` int(0) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` int(0) NULL DEFAULT NULL COMMENT '功能权限号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles_permission
-- ----------------------------
INSERT INTO `roles_permission` VALUES (1, 0, 4);
INSERT INTO `roles_permission` VALUES (2, 0, 6);
INSERT INTO `roles_permission` VALUES (3, 0, 5);
INSERT INTO `roles_permission` VALUES (4, 1, 5);
INSERT INTO `roles_permission` VALUES (5, 0, 7);
INSERT INTO `roles_permission` VALUES (6, 0, 8);
INSERT INTO `roles_permission` VALUES (7, 0, 9);
INSERT INTO `roles_permission` VALUES (8, 0, 11);
INSERT INTO `roles_permission` VALUES (9, 0, 12);
INSERT INTO `roles_permission` VALUES (10, 0, 13);
INSERT INTO `roles_permission` VALUES (11, 0, 14);
INSERT INTO `roles_permission` VALUES (12, 1, 14);
INSERT INTO `roles_permission` VALUES (13, 0, 15);
INSERT INTO `roles_permission` VALUES (14, 1, 16);
INSERT INTO `roles_permission` VALUES (15, 1, 17);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_roles_id` int(0) NULL DEFAULT NULL COMMENT '用户角色id',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户电话',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户token',
  `logic_deleted` int(0) NULL DEFAULT 0 COMMENT '逻辑删除(0未删除 1已删除)',
  `gmt_created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'aaa', 'e10adc3949ba59abbe56e057f20f883e', 0, 'aaa', '6666666666', '618475', 0, '2022-08-25 18:11:05', NULL);
INSERT INTO `user` VALUES ('7307785468161619user', 'ggg', 'e10adc3949ba59abbe56e057f20f883e', 1, 'ggg', '15234435771', '023657', 0, '2022-08-25 11:13:00', NULL);

SET FOREIGN_KEY_CHECKS = 1;
