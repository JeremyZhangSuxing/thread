CREATE TABLE `user_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `nick_name` varchar(30) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `pass_word` varchar(8) NOT NULL DEFAULT '' COMMENT '密码',
  `sex` tinyint(1) unsigned NOT NULL COMMENT '性别',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '电话号码',
  `image` varchar(150) NOT NULL DEFAULT '' COMMENT '头像',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0：否，1：是',
  PRIMARY KEY (`id`),
  KEY `idx_nick_name` (`nick_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COMMENT='用户信息表';