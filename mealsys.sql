/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : mealsys

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 21/02/2021 12:43:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单号，而且是订单详情id',
  `deskId` int(11) UNSIGNED NOT NULL COMMENT '桌号',
  `orderTime` varchar(14) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '下单时间   yyyyMMddhhmmss',
  `finish` bit(1) NOT NULL COMMENT '订单是否完成，0：未完成；1：完成',
  `userId` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '结束订单的员工账号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES (1, 1, '20210121113022', b'1', 1);
INSERT INTO `bill` VALUES (2, 2, '20210122081312', b'0', NULL);
INSERT INTO `bill` VALUES (3, 1, '20210128103442', b'1', 1);
INSERT INTO `bill` VALUES (4, 5, '20200128103612', b'0', NULL);
INSERT INTO `bill` VALUES (13, 7, '20210204114322', b'1', 1);
INSERT INTO `bill` VALUES (14, 7, '20210204145635', b'1', 1);
INSERT INTO `bill` VALUES (15, 7, '20210204150521', b'0', NULL);
INSERT INTO `bill` VALUES (16, 7, '20210204150629', b'0', NULL);
INSERT INTO `bill` VALUES (17, 7, '20210204152022', b'1', 1);
INSERT INTO `bill` VALUES (18, 7, '20210204152141', b'0', NULL);
INSERT INTO `bill` VALUES (19, 7, '20210204152345', b'1', 1);
INSERT INTO `bill` VALUES (20, 7, '20210205095130', b'1', 1);
INSERT INTO `bill` VALUES (21, 4, '20210216105554', b'1', 1);
INSERT INTO `bill` VALUES (22, 6, '20210216111017', b'1', 1);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜名id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜名',
  `quantity` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '菜的库存',
  `price` double(10, 2) UNSIGNED NOT NULL COMMENT '价格',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '关于菜的描述',
  `image` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '关于菜的图片，以imageId，imageId，imageId，……的形式保存',
  `typeId` int(10) UNSIGNED NOT NULL COMMENT '菜的类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '肠粉', 198, 6.00, '肠粉含有丰富的蛋白质、淀粉、微量元素、维生素等。', '', 6);
INSERT INTO `menu` VALUES (2, '蛋肠粉', 90, 8.10, '鸡蛋肠粉是用粘米粉、鸡蛋等材料制作的一道早点。鸡蛋含有丰富的蛋白质、脂肪、维生素和铁、钙、钾等人体所需要的矿物质，蛋白质为优质蛋白，对肝脏组织损伤有修复作用。', '', 6);
INSERT INTO `menu` VALUES (3, '瘦肉粥', 100, 8.50, '瘦肉粥是一道菜品，制作原料主要有猪瘦肉、大米、生菜等。猪肉中蛋白质和胆固醇的含量较高，还含有维生素和锌等元素，这些物质对宝宝生长大有裨益，能起到和胃补脾，润燥养肺的功效。', '', 6);
INSERT INTO `menu` VALUES (4, '瘦肉炒青瓜', 40, 21.00, NULL, NULL, 2);
INSERT INTO `menu` VALUES (6, '大良双皮奶', 39, 11.00, '大良双皮奶是一种甜品。主要由水牛奶、蛋清和糖等混合炖制而成，含有双层奶皮，一层甘香，二层清香，润肺养颜，堪称绝妙。', NULL, 5);
INSERT INTO `menu` VALUES (7, '鸡腿堡饭', 40, 28.00, NULL, '', 3);
INSERT INTO `menu` VALUES (8, '鸭腿煲饭', 39, 32.00, NULL, NULL, 3);
INSERT INTO `menu` VALUES (9, '鹅腿煲饭', 20, 41.00, NULL, '', 3);
INSERT INTO `menu` VALUES (10, '窝蛋双皮奶', 29, 15.00, NULL, NULL, 5);
INSERT INTO `menu` VALUES (11, '炸牛奶', 29, 15.00, NULL, NULL, 5);
INSERT INTO `menu` VALUES (12, '莲藕猪骨汤', 50, 34.00, NULL, NULL, 4);
INSERT INTO `menu` VALUES (13, '淮山猪骨汤', 50, 39.00, NULL, NULL, 4);
INSERT INTO `menu` VALUES (14, '紫菜扇贝汤', 40, 39.00, NULL, NULL, 4);
INSERT INTO `menu` VALUES (15, '豆鼓虾仁炒苦瓜', 30, 34.00, NULL, NULL, 2);
INSERT INTO `menu` VALUES (16, '清蒸草鱼', 50, 59.00, NULL, '', 2);
INSERT INTO `menu` VALUES (17, '招牌云吞面', 87, 16.00, '店里的面是纯手工打制成的全蛋银丝面，韧性均匀，条子粗细适中，入口爽滑，蛋香浓郁。汤底采用的是鲮鱼鱼骨熬制而成的鱼汤底，并搭配香菇、玉米、红萝卜等各种配料。', NULL, 6);
INSERT INTO `menu` VALUES (18, '牛奶木瓜', 10, 18.00, '', '', 5);

-- ----------------------------
-- Table structure for menubill
-- ----------------------------
DROP TABLE IF EXISTS `menubill`;
CREATE TABLE `menubill`  (
  `billId` int(10) UNSIGNED NOT NULL COMMENT '订单详情id，而且是订单号',
  `menuId` int(11) UNSIGNED NOT NULL COMMENT '菜Id',
  `quantity` int(11) UNSIGNED NOT NULL COMMENT '所点数量',
  `finish` bit(1) NOT NULL COMMENT '是否完成上菜，0：未完成；1：完成',
  `menuTime` varchar(14) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上菜时间    yyyyMMddhhmmss',
  `userId` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '完成上菜员工id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menubill
-- ----------------------------
INSERT INTO `menubill` VALUES (1, 2, 1, b'1', '20210121113532', 1);
INSERT INTO `menubill` VALUES (1, 3, 1, b'1', '20210121113556', 1);
INSERT INTO `menubill` VALUES (2, 1, 2, b'1', '20210128191749', 1);
INSERT INTO `menubill` VALUES (2, 3, 1, b'0', NULL, NULL);
INSERT INTO `menubill` VALUES (2, 2, 1, b'1', '20210122081812', 1);
INSERT INTO `menubill` VALUES (3, 7, 1, b'1', '20210128183614', 1);
INSERT INTO `menubill` VALUES (3, 9, 2, b'1', '20210128184035', 1);
INSERT INTO `menubill` VALUES (3, 16, 1, b'1', '20210128185444', 1);
INSERT INTO `menubill` VALUES (4, 15, 1, b'0', NULL, NULL);
INSERT INTO `menubill` VALUES (4, 14, 1, b'0', NULL, NULL);
INSERT INTO `menubill` VALUES (4, 13, 1, b'0', NULL, NULL);
INSERT INTO `menubill` VALUES (4, 12, 1, b'0', NULL, NULL);
INSERT INTO `menubill` VALUES (4, 11, 3, b'0', NULL, NULL);
INSERT INTO `menubill` VALUES (4, 9, 4, b'0', NULL, NULL);
INSERT INTO `menubill` VALUES (13, 1, 1, b'1', '20210204150359', 1);
INSERT INTO `menubill` VALUES (13, 7, 1, b'1', '20210204150359', 1);
INSERT INTO `menubill` VALUES (13, 9, 1, b'1', '20210204150400', 1);
INSERT INTO `menubill` VALUES (13, 16, 1, b'1', '20210204150400', 1);
INSERT INTO `menubill` VALUES (14, 1, 1, b'1', '20210204150306', 1);
INSERT INTO `menubill` VALUES (14, 7, 1, b'1', '20210204150306', 1);
INSERT INTO `menubill` VALUES (14, 9, 1, b'1', '20210204150307', 1);
INSERT INTO `menubill` VALUES (14, 16, 1, b'1', '20210204150308', 1);
INSERT INTO `menubill` VALUES (14, 10, 2, b'1', '20210204150308', 1);
INSERT INTO `menubill` VALUES (15, 13, 2, b'0', NULL, NULL);
INSERT INTO `menubill` VALUES (15, 12, 1, b'0', NULL, NULL);
INSERT INTO `menubill` VALUES (16, 7, 1, b'0', NULL, NULL);
INSERT INTO `menubill` VALUES (16, 8, 1, b'0', NULL, NULL);
INSERT INTO `menubill` VALUES (17, 7, 2, b'1', '20210204152115', 1);
INSERT INTO `menubill` VALUES (18, 11, 1, b'0', NULL, NULL);
INSERT INTO `menubill` VALUES (19, 1, 1, b'1', '20210219160613', 1);
INSERT INTO `menubill` VALUES (20, 8, 1, b'1', '20210205095800', 1);
INSERT INTO `menubill` VALUES (20, 6, 1, b'1', '20210205095800', 1);
INSERT INTO `menubill` VALUES (21, 1, 1, b'1', '20210216105627', 1);
INSERT INTO `menubill` VALUES (21, 2, 1, b'1', '20210216105637', 1);
INSERT INTO `menubill` VALUES (22, 2, 2, b'1', '20210216113334', 1);
INSERT INTO `menubill` VALUES (22, 10, 1, b'1', '20210216113335', 1);
INSERT INTO `menubill` VALUES (22, 11, 1, b'1', '20210216113335', 1);

-- ----------------------------
-- Table structure for menuimage
-- ----------------------------
DROP TABLE IF EXISTS `menuimage`;
CREATE TABLE `menuimage`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `menuId` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '菜单id',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '图片文件名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menutype
-- ----------------------------
DROP TABLE IF EXISTS `menutype`;
CREATE TABLE `menutype`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '类型名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menutype
-- ----------------------------
INSERT INTO `menutype` VALUES (1, '本周热门');
INSERT INTO `menutype` VALUES (2, '农家小菜');
INSERT INTO `menutype` VALUES (3, '港式烧腊');
INSERT INTO `menutype` VALUES (4, '老火靓汤');
INSERT INTO `menutype` VALUES (5, '点心甜品');
INSERT INTO `menutype` VALUES (6, '营养早餐');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(12) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `acot` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '员工账号，唯一',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码，',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'worker01', NULL, 'c888c9ce9e098d5864d3ded6ebcc140a12142263bace3a23a36f9905f12bd64a');

SET FOREIGN_KEY_CHECKS = 1;
