/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50522
 Source Host           : 127.0.0.1:3306
 Source Schema         : jianzhi

 Target Server Type    : MySQL
 Target Server Version : 50522
 File Encoding         : 65001

 Date: 20/03/2022 19:25:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aliyun_oss
-- ----------------------------
DROP TABLE IF EXISTS `aliyun_oss`;
CREATE TABLE `aliyun_oss`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `used` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件用途（0:文件作废    1：头像    2：其他。求职者为简历，招聘方为审核文件）',
  `jobseeker_id` int(11) NULL DEFAULT NULL,
  `recruit_unit_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 139 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of aliyun_oss
-- ----------------------------
INSERT INTO `aliyun_oss` VALUES (106, '742f5ee500f868d8df2a47188f0bdf94', 'recruitUnit/1/1612100826754.jpg', 'jpg', 'recruitUnitImage', '0', NULL, 1);
INSERT INTO `aliyun_oss` VALUES (107, 'b38b3d0e30af5509e2c11beda703d631', 'recruitUnit/3/1612577120864.jpg', 'jpg', 'recruitUnitImage', '0', NULL, 3);
INSERT INTO `aliyun_oss` VALUES (108, 'f79e09a88c704942e7e0faf20dd9b839', 'jobSeeker/1/1612594312524.jpeg', 'jpeg', 'jobSeekerResume', '0', 1, NULL);
INSERT INTO `aliyun_oss` VALUES (109, 'beijing', 'recruitUnit/1/1613021091199.jpg', 'jpg', 'recruitUnitImage', '0', NULL, 1);
INSERT INTO `aliyun_oss` VALUES (110, 'ee', 'jobSeeker/1/1613097973862.jpeg', 'jpeg', 'jobSeekerResume', '2', 1, NULL);
INSERT INTO `aliyun_oss` VALUES (112, 'q', 'jobSeeker/1/image1613109938297.jpg', 'jpg', 'jobSeekerImage', '0', 1, NULL);
INSERT INTO `aliyun_oss` VALUES (113, '742f5ee500f868d8df2a47188f0bdf94', 'jobSeeker/1/image1613110355413.jpg', 'jpg', 'jobSeekerImage', '0', 1, NULL);
INSERT INTO `aliyun_oss` VALUES (114, 'we', 'jobSeeker/1/image1613110374432.jpg', 'jpg', 'jobSeekerImage', '0', 1, NULL);
INSERT INTO `aliyun_oss` VALUES (115, 'beijing', 'jobSeeker/1/image1613110474262.jpg', 'jpg', 'jobSeekerImage', '0', 1, NULL);
INSERT INTO `aliyun_oss` VALUES (116, '742f5ee500f868d8df2a47188f0bdf94', 'jobSeeker/1/image1613110591894.jpg', 'jpg', 'jobSeekerImage', '0', 1, NULL);
INSERT INTO `aliyun_oss` VALUES (117, 'd9ef3f6d00f286e48cd6295993c04425', 'jobSeeker/3/image1613111352896.jpg', 'jpg', 'jobSeekerImage', '0', 3, NULL);
INSERT INTO `aliyun_oss` VALUES (118, 'f79e09a88c704942e7e0faf20dd9b839', 'jobSeeker/3/1614482950359.jpeg', 'jpeg', 'jobSeekerResume', '0', 3, NULL);
INSERT INTO `aliyun_oss` VALUES (119, 'ee', 'jobSeeker/3/1614482996622.jpeg', 'jpeg', 'jobSeekerResume', '0', 3, NULL);
INSERT INTO `aliyun_oss` VALUES (121, 'ee', 'jobSeeker/3/1614483893043.jpeg', 'jpeg', 'jobSeekerResume', '0', 3, NULL);
INSERT INTO `aliyun_oss` VALUES (124, 'f79e09a88c704942e7e0faf20dd9b839', 'jobSeeker/3/1614483999296.jpeg', 'jpeg', 'jobSeekerResume', '0', 3, NULL);
INSERT INTO `aliyun_oss` VALUES (128, 'f79e09a88c704942e7e0faf20dd9b839', 'jobSeeker/3/1614486655144.jpeg', 'jpeg', 'jobSeekerResume', '2', 3, NULL);
INSERT INTO `aliyun_oss` VALUES (129, 'e49b23613811ce843057f9d4cc34e483', 'jobSeeker/1/image1614607705299.jpg', 'jpg', 'jobSeekerImage', '0', 1, NULL);
INSERT INTO `aliyun_oss` VALUES (130, 'u=590710378,388937847&fm=11&gp=0', 'recruitUnit/1/1615304501292.jpg', 'jpg', 'recruitUnitImage', '0', NULL, 1);
INSERT INTO `aliyun_oss` VALUES (131, 'download', 'recruitUnit/3/1615304549581.png', 'png', 'recruitUnitImage', '1', NULL, 3);
INSERT INTO `aliyun_oss` VALUES (132, '算法', 'jobSeeker/1/image1615306860991.PNG', 'PNG', 'jobSeekerImage', '0', 1, NULL);
INSERT INTO `aliyun_oss` VALUES (133, 'we', 'jobSeeker/1/image1615306876917.jpg', 'jpg', 'jobSeekerImage', '0', 1, NULL);
INSERT INTO `aliyun_oss` VALUES (134, 'c811506d21fa1a29110ddffc1318dbdb', 'jobSeeker/1/image1619535634511.jpeg', 'jpeg', 'jobSeekerImage', '0', 1, NULL);
INSERT INTO `aliyun_oss` VALUES (135, 'd6ce86777f8bcb2a3af309e6d72c6bd2', 'jobSeeker/1/image1619535785630.jpeg', 'jpeg', 'jobSeekerImage', '1', 1, NULL);
INSERT INTO `aliyun_oss` VALUES (136, '9bfcd4d38d9722f49ee0eeace27f9d59', 'recruitUnit/1/1619694900911.jpeg', 'jpeg', 'recruitUnitImage', '1', NULL, 1);
INSERT INTO `aliyun_oss` VALUES (137, 'download', 'jobSeeker/3/image1621259872297.jpg', 'jpg', 'jobSeekerImage', '1', 3, NULL);
INSERT INTO `aliyun_oss` VALUES (138, 'u=3812336783,1127474957&fm=11&gp=0', 'jobSeeker/2/image1621261559204.jpg', 'jpg', 'jobSeekerImage', '1', 2, NULL);

-- ----------------------------
-- Table structure for base_type
-- ----------------------------
DROP TABLE IF EXISTS `base_type`;
CREATE TABLE `base_type`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方案类型（如导出excel）',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '方案内容',
  `source_page` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源页',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of base_type
-- ----------------------------
INSERT INTO `base_type` VALUES (1, '16932329429663820', 'cszy1', '123', 'custName,poundCode,recordPeople,sailDate,month,custType,proName,unit,priceWeight,priceVolume,unitProportion,falseRoughWeight,roughWeight,tareWeight,pureWeight,tranFee,tranDate,carNum,driver,driverPhone,remark,status,proPrice,totalPrice,checkStatus', '/poundList');
INSERT INTO `base_type` VALUES (2, '16932329429663820', 'cszy1', '1111', 'custName,poundCode,recordPeople,sailDate,month,custType,proName,unit,priceWeight,priceVolume,unitProportion,falseRoughWeight,roughWeight,tareWeight,pureWeight,tranFee,tranDate,carNum,driver,driverPhone,remark,status,proPrice,totalPrice,checkStatus', '/poundList');
INSERT INTO `base_type` VALUES (3, '1', 'cszy1', 'test2', 'name,code,advanceAmount,paymentStyle,deliveryTime,buildTime,validityTime,status,remark', '/order-management/quotation');

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `type` tinyint(4) NULL DEFAULT 1 COMMENT '用户类型： 1 求职者 2 招聘单位  3管理员（ 超级和普通）',
  `company_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业号',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问的ip地址',
  `wechat` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信号',
  `emp_id` bigint(20) NULL DEFAULT NULL COMMENT '关联的职员id',
  `salt` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码加盐',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态 0 未启用（已注销） 1 启用',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21508635113291777 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表（门户+管理）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES (16932329429663820, 'xiaoming', '352cc39aebf8cd878da87e6dfdb122ae59b4951e45a47bb235a9f605c496f508', 1, NULL, NULL, NULL, 154, 'ePDGCCLCIiEVFERpkeNn', 1, 'zzb2', '2020-11-02 09:33:09', 'zzb', NULL);
INSERT INTO `base_user` VALUES (16932329429663826, 'changlong', '352cc39aebf8cd878da87e6dfdb122ae59b4951e45a47bb235a9f605c496f508', 2, NULL, NULL, NULL, 154, 'ePDGCCLCIiEVFERpkeNn', 1, 'cszy1', '2021-02-06 01:32:08', 'cszy1', NULL);
INSERT INTO `base_user` VALUES (20700498266947584, 'waimaixiaozi', 'fd1323a8c52ec970cce29a140b8a630b0fc8a0e1167a3b04afe963c5b0056fa1', 2, NULL, NULL, NULL, NULL, 'zxXehVkCqsqIkNhPCVDq', 1, NULL, '2021-01-30 23:02:14', NULL, NULL);
INSERT INTO `base_user` VALUES (20717093480038400, 'jobseeker1', '388785bd679eb3975123a9f28a79b59c1e563aaa55834ec68a3f88a601831236', 1, NULL, NULL, NULL, NULL, 'dvlWYEvBnCCRhFVFClAV', 1, NULL, '2021-01-31 16:37:19', NULL, NULL);
INSERT INTO `base_user` VALUES (20838525226188800, 'guanliyuan', '8e21e39ab1361180e17e8a3690de3a8d53b9478ef2044abd787da4ad69b7f946', 3, NULL, NULL, '111ww', NULL, 'fHfDmlsbcgPZAxCqRTua', 1, NULL, '2021-02-06 01:17:45', NULL, '2021-02-25 21:12:14');
INSERT INTO `base_user` VALUES (20858498822176768, 'maidanglao', '153f7022780fc4783402ee601d527066cd08d7acb1a13239d3189c5ed2dfe4e3', 2, NULL, NULL, NULL, NULL, 'SLQpaFpCOYRrCDLoJiyw', 1, NULL, '2021-02-06 22:27:38', NULL, NULL);
INSERT INTO `base_user` VALUES (20986862173683712, 'xiaohua', '250c1e5ef470b9ad9e3aafdd104b9f15146fb13255feb6aa53f13afefa254a8a', 1, NULL, NULL, NULL, NULL, 'MttrlAdTfOjRrrrbAdJY', 1, NULL, '2021-02-12 14:28:45', NULL, NULL);
INSERT INTO `base_user` VALUES (21186718681530368, 'xiaozhang', 'bdb30a8a6f91b3734b4bb07efa524029665c8d56fab2059874c46c54b19b4082', 1, NULL, NULL, NULL, NULL, 'xcMvJbhwaoovfjPcUPti', 1, NULL, '2021-02-21 10:15:17', NULL, NULL);
INSERT INTO `base_user` VALUES (21186718681530369, 'guanliyuan2', '8e21e39ab1361180e17e8a3690de3a8d53b9478ef2044abd787da4ad69b7f946', 3, NULL, NULL, '123', NULL, 'fHfDmlsbcgPZAxCqRTua', 1, NULL, '2021-02-23 22:39:36', NULL, '2021-02-25 21:29:11');
INSERT INTO `base_user` VALUES (21505364075216896, 'xiaojia', '2a44f161636f0bb9e217f2b924e30e3acf030a601ba48cec7b1ffdc54e023965', 1, NULL, NULL, NULL, NULL, 'kOeJpsjsQzFpGOBlmpKY', 1, NULL, '2021-03-07 11:54:14', NULL, NULL);
INSERT INTO `base_user` VALUES (21508635113291776, 'changlong2', '9eb33f63df5954fcba625ed24b18092b3bb1035a6cc4ea0fa33b052d8b7e1369', 2, NULL, NULL, NULL, NULL, 'HUmMHZlhNKZOXnWLDzOm', 1, NULL, '2021-03-07 15:22:12', NULL, NULL);

-- ----------------------------
-- Table structure for hr_info
-- ----------------------------
DROP TABLE IF EXISTS `hr_info`;
CREATE TABLE `hr_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'HRid',
  `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户表id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'hr姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0男  1女',
  `recruit_unit_id` int(11) NULL DEFAULT NULL COMMENT '所属单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of hr_info
-- ----------------------------
INSERT INTO `hr_info` VALUES (1, 16932329429663826, '长隆负责人', '1', 1);
INSERT INTO `hr_info` VALUES (3, 20700498266947584, '我是外卖小子负责人', '0', 3);
INSERT INTO `hr_info` VALUES (4, 21508635113291776, '长隆HR', '0', 1);

-- ----------------------------
-- Table structure for invite_message
-- ----------------------------
DROP TABLE IF EXISTS `invite_message`;
CREATE TABLE `invite_message`  (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NULL DEFAULT NULL COMMENT '发送者id（user_id）',
  `receiver_id` int(11) NULL DEFAULT NULL COMMENT '接收者id（user_id）',
  `is_read` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否阅读（0未读  1已读）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `read_time` datetime NULL DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of invite_message
-- ----------------------------

-- ----------------------------
-- Table structure for job_info
-- ----------------------------
DROP TABLE IF EXISTS `job_info`;
CREATE TABLE `job_info`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '岗位信息id',
  `recruit_unit_id` int(11) NULL DEFAULT NULL COMMENT '招聘单位id',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位名称',
  `work_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '兼职时间',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地点',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '兼职标签（日结、java、计算机、开发）',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位描述（具体内容，地点）',
  `requirement` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位要求，人员要求',
  `salary_treatment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '薪资待遇',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位状态（1已发布    2岗位已关闭    3岗位已删除）',
  `need_resume_attachment` int(2) NULL DEFAULT NULL COMMENT '是否需要简历附件（0不需要    1需要）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者userid',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者userid',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of job_info
-- ----------------------------
INSERT INTO `job_info` VALUES (1, 1, '保安', '123', '珠海横琴', '{1}{2}{3}', '描述', '岗位要求，人员要求', '薪资待遇11', '1', 0, '2021-05-15 09:18:32', '16932329429663826', '2021-01-16 10:21:32', '16932329429663826', NULL);
INSERT INTO `job_info` VALUES (5, 1, '后厨', '每周五', '长隆', '{11}', '1、负责后厨整理、清洁，保证正常使用；2、为厨师配好所需食材、保证食材新鲜、卫生等。', '学历要求初中，工作经验要求不限。', '50/天', '1', 0, '2021-01-16 16:28:25', '16932329429663826', '2021-04-29 16:31:40', '16932329429663826', NULL);
INSERT INTO `job_info` VALUES (6, 1, '2211', '22', '22', '{3}', '1、负责后厨整理、清洁，保证正常使用；2、传达餐饮部或厨师长的相关通知、注意事项等；3、为厨师配好所需食材、保证食材新鲜、卫生等。', '2222', '22', '2', 0, '2021-01-16 16:29:52', '16932329429663826', '2021-01-16 16:31:28', '16932329429663826', NULL);
INSERT INTO `job_info` VALUES (7, 3, '骑手', '每天', '南山', '{4}{3}{11}', '（1）严格按照操作流程,到指定地点进行取货;\r\n（2）确保按时将客户的订单,送到指定地点;', '（1）年龄18-45岁之间；\r\n（2）身体健康；\r\n（3）吃苦耐劳，认真负责，听从工作安排，对具体上岗时间有充分的适应性；\r\n（4）熟悉校园道路情况，以快捷地到达顾客所在地；\r\n（5）按标准完整执行骑手作业流程，遵守交通规则；\r\n（6）有配送工作经验者优先。', '12元/h', '1', 1, '2021-01-31 10:15:23', '20700498266947584', '2021-02-12 18:37:27', '20700498266947584', NULL);
INSERT INTO `job_info` VALUES (8, 1, 'test', 'test', 'test', '{3}', 'test', 'test', 'test', '2', 0, '2021-02-19 00:44:29', '16932329429663826', '2021-04-27 22:55:36', '16932329429663826', NULL);

-- ----------------------------
-- Table structure for job_label
-- ----------------------------
DROP TABLE IF EXISTS `job_label`;
CREATE TABLE `job_label`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of job_label
-- ----------------------------
INSERT INTO `job_label` VALUES (1, '计算机');
INSERT INTO `job_label` VALUES (2, 'Java');
INSERT INTO `job_label` VALUES (3, '日结');
INSERT INTO `job_label` VALUES (4, '骑手');
INSERT INTO `job_label` VALUES (10, '包吃');
INSERT INTO `job_label` VALUES (11, '有提成');
INSERT INTO `job_label` VALUES (12, '完结工');

-- ----------------------------
-- Table structure for job_seeker
-- ----------------------------
DROP TABLE IF EXISTS `job_seeker`;
CREATE TABLE `job_seeker`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户详细信息id',
  `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户表的id',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0男 1女',
  `school_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sno` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级',
  `birthday` date NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wechat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '求职者信息表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of job_seeker
-- ----------------------------
INSERT INTO `job_seeker` VALUES (1, 16932329429663820, '0', 1, '林伟杰', '234', '2017级', '2021-01-09', '132****5677', '12345', '124', '11122', NULL);
INSERT INTO `job_seeker` VALUES (2, 20717093480038400, '1', 1, '小杰', '123', NULL, NULL, NULL, '456', '123', NULL, NULL);
INSERT INTO `job_seeker` VALUES (3, 20986862173683712, '0', 1, '小花', '04175252', NULL, NULL, '1111111', NULL, NULL, '111', NULL);
INSERT INTO `job_seeker` VALUES (4, 21186718681530368, '0', 1, '小张', '04181818', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `job_seeker` VALUES (5, 21505364075216896, NULL, 1, '1234', '1234', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for jobseeker_job_relation
-- ----------------------------
DROP TABLE IF EXISTS `jobseeker_job_relation`;
CREATE TABLE `jobseeker_job_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_seeker_id` int(11) NULL DEFAULT NULL COMMENT '求职者id',
  `resume_id` int(11) NULL DEFAULT NULL COMMENT '简历id',
  `recruit_unit_id` int(11) NULL DEFAULT NULL COMMENT '招聘单位id',
  `job_id` int(11) NULL DEFAULT NULL COMMENT '岗位id',
  `status` int(11) NULL DEFAULT NULL COMMENT '简历状态（1待处理/已提交    2待沟通      3不合适       4录用）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '求职者与岗位关系表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of jobseeker_job_relation
-- ----------------------------
INSERT INTO `jobseeker_job_relation` VALUES (112, 1, 2, 1, 5, 2, NULL, '2021-03-07 16:37:00', NULL, '2021-05-17 22:01:01', NULL);
INSERT INTO `jobseeker_job_relation` VALUES (113, 1, 2, 1, 6, 3, NULL, '2021-03-07 22:16:38', NULL, '2021-04-27 22:58:19', NULL);
INSERT INTO `jobseeker_job_relation` VALUES (114, 1, 2, 1, 8, 2, NULL, '2021-03-07 22:18:13', NULL, '2021-04-27 22:58:20', NULL);
INSERT INTO `jobseeker_job_relation` VALUES (115, 3, 4, 1, 5, 2, NULL, '2021-03-07 22:18:33', NULL, '2021-03-10 00:21:31', NULL);
INSERT INTO `jobseeker_job_relation` VALUES (117, 2, 3, 1, 5, 2, NULL, '2021-05-13 20:54:27', NULL, '2021-05-17 22:59:38', NULL);
INSERT INTO `jobseeker_job_relation` VALUES (118, 2, 3, 1, 1, 2, NULL, '2021-05-14 23:15:55', NULL, '2021-05-18 21:57:36', NULL);
INSERT INTO `jobseeker_job_relation` VALUES (122, 1, 2, 1, 1, 1, NULL, '2021-05-18 22:03:34', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pro_role
-- ----------------------------
DROP TABLE IF EXISTS `pro_role`;
CREATE TABLE `pro_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `updator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` date NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '生产角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pro_role
-- ----------------------------
INSERT INTO `pro_role` VALUES (2, '2', '角色2', NULL, NULL, NULL, '2020-11-24', NULL);
INSERT INTO `pro_role` VALUES (3, '3', '角色3', NULL, NULL, NULL, '2020-11-24', NULL);
INSERT INTO `pro_role` VALUES (4, '4', '角色4', NULL, '2020-11-24', NULL, '2020-11-24', NULL);

-- ----------------------------
-- Table structure for recruit_unit
-- ----------------------------
DROP TABLE IF EXISTS `recruit_unit`;
CREATE TABLE `recruit_unit`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '招聘方id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '招聘单位名称',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `field` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领域标签',
  `scale` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规模',
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态（0未通过审核  1审核中/待审核    2通过申请审核   3新注册用户/未认证   4被封禁）',
  `hr_id` int(11) NULL DEFAULT NULL COMMENT '企业注册者，也为管理者',
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `updator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '招聘单位表\r\n' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of recruit_unit
-- ----------------------------
INSERT INTO `recruit_unit` VALUES (1, '长隆', '珠海横琴', '{2}', '500人', '长隆集团旗下已建成并开放两大旅游度假区，即广州长隆旅游度假区和珠海横琴长隆国际海洋度假区。长隆集团的第三个度假区——清远长隆正在建设，定位为森林主题综合体。 长隆集团2019年接待游客接近4千万人次，其中珠海长隆海洋王国入园人数已突破千万，开业六年来保持稳定快速增长，跻身全球主题乐园前十。', '2', 1, NULL, NULL, '2021-02-06 10:40:02', NULL);
INSERT INTO `recruit_unit` VALUES (3, '外卖小子', '新饭堂', '{1}{3}', '10人', '用心做好每一份美食。', '2', 3, '2021-01-31 09:40:56', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for recruit_unit_label
-- ----------------------------
DROP TABLE IF EXISTS `recruit_unit_label`;
CREATE TABLE `recruit_unit_label`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of recruit_unit_label
-- ----------------------------
INSERT INTO `recruit_unit_label` VALUES (1, '餐饮');
INSERT INTO `recruit_unit_label` VALUES (2, '景点');
INSERT INTO `recruit_unit_label` VALUES (3, '外卖');
INSERT INTO `recruit_unit_label` VALUES (4, '其它');

-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '简历id',
  `job_seeker_id` int(11) NULL DEFAULT NULL COMMENT '绑定求职者id',
  `res_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简历姓名',
  `res_sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '简历性别 0男  1女',
  `res_expect_work` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简历期望工作',
  `res_work_exper` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作经历【作废】',
  `res_project_exper` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目经验【作废】',
  `res_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `res_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `res_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自我描述',
  `create_time` datetime NULL DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `updator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '简历信息【每个求职者只允许绑定一条 1对1 】' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of resume
-- ----------------------------
INSERT INTO `resume` VALUES (2, 1, '小明12', '0', NULL, NULL, NULL, '545***545@qq.com', '1345***7878', NULL, NULL, NULL, '2021-03-06 18:59:08', NULL, NULL);
INSERT INTO `resume` VALUES (3, 2, 'jobseeker1', '1', NULL, NULL, NULL, 'ddddd@qq.com', '1111112', 'sdsvsdvdfvdfvdfvdfvdfvdfvfvdvdfdfv1', '2021-01-31 16:37:20', NULL, '2021-01-31 17:29:24', NULL, NULL);
INSERT INTO `resume` VALUES (4, 3, '小花', '1', NULL, NULL, NULL, '90702@QC.VOM', '1345***7877', 'SDSDSDSDDS', '2021-02-12 14:28:46', NULL, '2021-02-28 12:32:10', NULL, NULL);
INSERT INTO `resume` VALUES (5, 4, '123', '0', NULL, NULL, NULL, NULL, NULL, NULL, '2021-02-21 10:15:17', NULL, NULL, NULL, NULL);
INSERT INTO `resume` VALUES (6, 5, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, '2021-03-07 11:54:14', NULL, NULL, NULL, NULL);
INSERT INTO `resume` VALUES (7, 6, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, '2021-03-07 15:12:29', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES (1, '**学校');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父功能ID',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '权限类别 0 菜单 1 按钮',
  `order_num` int(10) NULL DEFAULT NULL COMMENT '排序',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '功能路由',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间戳',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间戳',
  `available` tinyint(4) NULL DEFAULT NULL COMMENT '0 禁用 1 启用',
  `open` tinyint(4) NULL DEFAULT NULL COMMENT '是否默认展开 (冗余字段，父级展开1，子级不展开为0)',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 171 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '', 0, 1, '系统首页', '/index', NULL, NULL, 1, 0, '');
INSERT INTO `sys_menu` VALUES (2, 0, '', 0, 2, '基础数据', '', NULL, NULL, 1, 0, '');
INSERT INTO `sys_menu` VALUES (4, 2, 'permission', 0, 6, '角色管理', '/permission', NULL, NULL, 1, 0, '');
INSERT INTO `sys_menu` VALUES (5, 2, NULL, 0, 5, '菜单管理', '/menus-management', NULL, NULL, 1, 0, '');
INSERT INTO `sys_menu` VALUES (6, 2, 'users', 0, 4, '用户管理', '/user-management', NULL, NULL, 1, 0, '');
INSERT INTO `sys_menu` VALUES (23, 4, 'sysRole:authority', 1, 1, '角色授权', '', NULL, NULL, 1, 1, 'el-icon-document-add');
INSERT INTO `sys_menu` VALUES (24, 4, 'sysRole:add', 1, 2, '添加角色', '', NULL, NULL, 1, 1, 'el-icon-plus');
INSERT INTO `sys_menu` VALUES (25, 4, 'sysRole:update', 1, 3, '角色更新', NULL, NULL, NULL, 1, 0, 'el-icon-refresh-left');
INSERT INTO `sys_menu` VALUES (26, 4, 'sysRole:edit', 1, 4, '角色编辑', '', NULL, NULL, 1, 0, 'el-icon-s-promotion');
INSERT INTO `sys_menu` VALUES (27, 4, 'sysRole:delete', 1, 5, '角色删除', NULL, NULL, NULL, 1, 0, 'el-icon-s-marketing');
INSERT INTO `sys_menu` VALUES (28, 4, 'sysRole:status', 1, 6, '状态更新', NULL, NULL, NULL, 1, 0, 'el-icon-refresh');
INSERT INTO `sys_menu` VALUES (29, 5, 'sysMenu:add', 1, 1, '添加菜单', NULL, NULL, NULL, 1, 0, 'el-icon-plus');
INSERT INTO `sys_menu` VALUES (30, 5, 'sysMenu:edit', 1, 2, '编辑菜单', '', NULL, NULL, 1, 0, 'el-icon-edit');
INSERT INTO `sys_menu` VALUES (31, 5, 'sysMenu:update', 1, 2, '修改菜单', NULL, NULL, NULL, 1, 0, 'el-icon-share');
INSERT INTO `sys_menu` VALUES (32, 5, 'sysMenu:delete', 1, 4, '删除菜单', NULL, NULL, NULL, 1, 0, 'el-icon-s-opportunity');
INSERT INTO `sys_menu` VALUES (33, 6, 'user:assign', 1, 1, '分配角色', '', NULL, NULL, 1, 0, 'el-icon-s-tools');
INSERT INTO `sys_menu` VALUES (34, 6, 'user:status', 1, 2, '禁用用户', NULL, NULL, NULL, 1, 0, 'el-icon-circle-close');
INSERT INTO `sys_menu` VALUES (35, 6, 'user:delete', 1, 3, '用户删除', NULL, NULL, NULL, 1, 1, 'el-icon-remove');
INSERT INTO `sys_menu` VALUES (165, 0, '', 0, 1, '人员管理', '', NULL, NULL, 1, 0, NULL);
INSERT INTO `sys_menu` VALUES (166, 0, NULL, 0, 4, '企业管理', NULL, NULL, NULL, 1, 0, NULL);
INSERT INTO `sys_menu` VALUES (167, 0, NULL, 0, 5, '岗位管理', NULL, NULL, NULL, 1, 0, NULL);
INSERT INTO `sys_menu` VALUES (168, 165, NULL, 0, 1, '111', NULL, NULL, NULL, 1, 0, NULL);
INSERT INTO `sys_menu` VALUES (169, 166, NULL, 0, 1, '111', NULL, NULL, NULL, 1, 0, NULL);
INSERT INTO `sys_menu` VALUES (170, 167, NULL, 0, 1, '111', NULL, NULL, NULL, 1, 0, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '0   停用 1 启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (16, '所有权限', '', '2020-09-02 11:19:55', '2021-03-06 19:59:08', 1);
INSERT INTO `sys_role` VALUES (30, '求职者', NULL, '2021-02-21 16:56:46', '2021-03-06 19:59:56', 1);
INSERT INTO `sys_role` VALUES (31, '招聘单位-负责人', NULL, '2021-02-21 16:57:01', '2021-05-14 21:01:15', 1);
INSERT INTO `sys_role` VALUES (32, '招聘单位-普通用户', NULL, '2021-02-21 23:56:12', NULL, 1);
INSERT INTO `sys_role` VALUES (33, '超级管理员', NULL, '2021-02-21 16:57:14', NULL, 1);
INSERT INTO `sys_role` VALUES (34, '普通管理员', NULL, '2021-02-21 16:58:08', NULL, 1);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(22) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(22) NULL DEFAULT NULL COMMENT '权限ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色与权限关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (16, 1);
INSERT INTO `sys_role_menu` VALUES (16, 2);
INSERT INTO `sys_role_menu` VALUES (16, 4);
INSERT INTO `sys_role_menu` VALUES (16, 23);
INSERT INTO `sys_role_menu` VALUES (16, 24);
INSERT INTO `sys_role_menu` VALUES (16, 25);
INSERT INTO `sys_role_menu` VALUES (16, 26);
INSERT INTO `sys_role_menu` VALUES (16, 27);
INSERT INTO `sys_role_menu` VALUES (16, 28);
INSERT INTO `sys_role_menu` VALUES (16, 5);
INSERT INTO `sys_role_menu` VALUES (16, 29);
INSERT INTO `sys_role_menu` VALUES (16, 30);
INSERT INTO `sys_role_menu` VALUES (16, 31);
INSERT INTO `sys_role_menu` VALUES (16, 32);
INSERT INTO `sys_role_menu` VALUES (16, 6);
INSERT INTO `sys_role_menu` VALUES (16, 33);
INSERT INTO `sys_role_menu` VALUES (16, 34);
INSERT INTO `sys_role_menu` VALUES (16, 35);
INSERT INTO `sys_role_menu` VALUES (16, 165);
INSERT INTO `sys_role_menu` VALUES (16, 168);
INSERT INTO `sys_role_menu` VALUES (16, 166);
INSERT INTO `sys_role_menu` VALUES (16, 169);
INSERT INTO `sys_role_menu` VALUES (16, 167);
INSERT INTO `sys_role_menu` VALUES (16, 170);
INSERT INTO `sys_role_menu` VALUES (34, 1);
INSERT INTO `sys_role_menu` VALUES (34, 165);
INSERT INTO `sys_role_menu` VALUES (34, 168);
INSERT INTO `sys_role_menu` VALUES (34, 166);
INSERT INTO `sys_role_menu` VALUES (34, 169);
INSERT INTO `sys_role_menu` VALUES (34, 167);
INSERT INTO `sys_role_menu` VALUES (34, 170);
INSERT INTO `sys_role_menu` VALUES (33, 1);
INSERT INTO `sys_role_menu` VALUES (33, 2);
INSERT INTO `sys_role_menu` VALUES (33, 4);
INSERT INTO `sys_role_menu` VALUES (33, 23);
INSERT INTO `sys_role_menu` VALUES (33, 24);
INSERT INTO `sys_role_menu` VALUES (33, 25);
INSERT INTO `sys_role_menu` VALUES (33, 26);
INSERT INTO `sys_role_menu` VALUES (33, 27);
INSERT INTO `sys_role_menu` VALUES (33, 28);
INSERT INTO `sys_role_menu` VALUES (33, 5);
INSERT INTO `sys_role_menu` VALUES (33, 29);
INSERT INTO `sys_role_menu` VALUES (33, 30);
INSERT INTO `sys_role_menu` VALUES (33, 31);
INSERT INTO `sys_role_menu` VALUES (33, 32);
INSERT INTO `sys_role_menu` VALUES (33, 6);
INSERT INTO `sys_role_menu` VALUES (33, 33);
INSERT INTO `sys_role_menu` VALUES (33, 34);
INSERT INTO `sys_role_menu` VALUES (33, 35);
INSERT INTO `sys_role_menu` VALUES (33, 165);
INSERT INTO `sys_role_menu` VALUES (33, 168);
INSERT INTO `sys_role_menu` VALUES (33, 166);
INSERT INTO `sys_role_menu` VALUES (33, 169);
INSERT INTO `sys_role_menu` VALUES (33, 167);
INSERT INTO `sys_role_menu` VALUES (33, 170);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(22) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(22) NULL DEFAULT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与角色关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (16932329429663820, 16);
INSERT INTO `sys_user_role` VALUES (20700498266947584, 16);
INSERT INTO `sys_user_role` VALUES (20717093480038400, 16);
INSERT INTO `sys_user_role` VALUES (20858339555016704, 16);
INSERT INTO `sys_user_role` VALUES (20858469386551296, 16);
INSERT INTO `sys_user_role` VALUES (20858498822176768, 16);
INSERT INTO `sys_user_role` VALUES (20986862173683712, 16);
INSERT INTO `sys_user_role` VALUES (21186718681530368, 16);
INSERT INTO `sys_user_role` VALUES (21289744868573184, 16);
INSERT INTO `sys_user_role` VALUES (21505364075216896, 16);
INSERT INTO `sys_user_role` VALUES (21508635113291776, 16);
INSERT INTO `sys_user_role` VALUES (16932329429663826, 16);
INSERT INTO `sys_user_role` VALUES (21186718681530369, 34);
INSERT INTO `sys_user_role` VALUES (20838525226188800, 16);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `company_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业号',
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `wechat` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信号',
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `salt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态 0 未启用 1 启用',
  `is_del` int(4) NULL DEFAULT 0 COMMENT '逻辑删除标识',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改日期',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', '1', '15012345678', 'admin@126.com', NULL, '1', '1', 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, 'lzh', '123456', '1', '15012345678', 'lzh@126.com', NULL, '1', '1', 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (3, 'tc', '123456', '1', '15012345678', 'tc@126.com', NULL, '1', '1', 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, 'bowen', '2a2228fa6bd8fc5f929afaf7135cf6768b82296b5a29685d87eb36eb43bdd2ba', '1', '15012345678', 'bowen@126.com', '', '1', '1', 1, 0, '', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, '1', '7d118be535c241b2898b4d96b57ca21da961fab6bfc0c612e8f464b1e9f8d2a7', '1', '15012345678', 'one@126.com', NULL, '1', '1', 1, 0, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
