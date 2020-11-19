CREATE TABLE `user`
(
    `id`          int(10)     NOT NULL AUTO_INCREMENT,
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `name`        varchar(20) NOT NULL DEFAULT '' COMMENT '姓名',
    `password`    varchar(36) NOT NULL DEFAULT '' COMMENT '密码',
    `phone`       varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
    `nick_name`   varchar(20) NOT NULL DEFAULT '' COMMENT '昵称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;