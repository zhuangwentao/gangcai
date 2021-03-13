DROP SCHEMA IF EXISTS gangcai ;
CREATE SCHEMA IF NOT EXISTS gangcai DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

USE gangcai;

-- ----------------------------
-- 资源单表--------------------
-- ----------------------------
DROP TABLE IF EXISTS `resource_list`;
CREATE TABLE `resource_list` (
  `ID` varchar(100) not null comment '用户id+时间.getTime()',
  `company` varchar(100) not null comment '公司名',
  `uploder_user` int(11) not null comment '上传者(id)',
  `classification` varchar(200) default '' comment '大类' ,
  `main_varieties` varchar(200) default '' comment '主营品种' ,
  `main_steelfactory` varchar(200) default '' comment '主营钢厂' ,
  `description` varchar(500) default '' comment '资源单说明' ,
  `url` varchar(200) default '' comment '文件地址url' ,
  `area` varchar(200) default '' comment '地区' ,
  `uploder_time` datetime comment '上传时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- 现货单表-----------------
-- ----------------------------
DROP TABLE IF EXISTS `stock_goods`;
CREATE TABLE `stock_goods` (
  `ID` int(11) AUTO_INCREMENT,
  `resource_list_id` varchar(100) not null comment '资源单ID',
  `product_name` varchar(200) not null comment '品名',
  `material` varchar(200) default '' comment '材质',
  `specifications` varchar(200) default '' comment '规格' ,
  `origin` varchar(200) default '' comment '钢厂（产地）' ,
  `price` double(11,2) default 0 comment '价格' ,
  `wearhouse` varchar(200) default '' comment '仓库' ,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN_NAME` varchar(20) NOT NULL comment '登录名',
  `ROLE` varchar(20) DEFAULT 'user' comment '规则 user和admin',
  `PASSWORD` varchar(255) NOT NULL comment '密码',
  `EMAIL` varchar(255) DEFAULT '' comment '邮件',
  `PHONE` varchar(20) DEFAULT '' comment '电话',
  `COMPANY` varchar(120) DEFAULT '' comment '公司',
  `CREATE_DATE` datetime DEFAULT NULL comment '创建时间',
  `DEL_FLAG` varchar(1) DEFAULT 0 comment '删除标志:0-未删除 1-删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user values (1,'admin','admin','21232f297a57a5a743894a0e4a801fc3','70873523@qq.com','15821882062','上海三中实业有限公司',now(),0);
-- ----------------------------
-- 每日特价表-----------------
-- ----------------------------
DROP TABLE IF EXISTS `hotsales_goods`;
CREATE TABLE `hotsales_goods` (
  `ID` int(11) AUTO_INCREMENT,
  `product_name` varchar(200) not null comment '品名',
  `specifications` varchar(200) default '' comment '规格' ,
  `material` varchar(200) default '' comment '材质',
  `origin` varchar(200) default '' comment '钢厂（产地）' ,
  `area` varchar(200) default '' comment '地区' ,
  `num` double(11,2) default '0' comment '数量' ,
  `wearhouse` varchar(200) default '' comment '仓库' ,
  `company` varchar(100) not null comment '公司名(供应商)',
  `price` double(11,2) default 0 comment '价格' ,
  `effective_date` date  comment '特价日期',
  `description` varchar(500) default '' comment '特价说明' ,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


