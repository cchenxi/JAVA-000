create database if not exists `d_order_0` default character set utf8mb4 collate utf8mb4_unicode_ci;

use `d_order_0`;

create table if not exists `t_order_0`
(
    `pk_id`              bigint(20) unsigned     not null default 0 primary key comment '主键',
    `order_no`           varchar(200)            not null default '' comment '订单编号',
    `order_status`       varchar(100)            not null default '' comment '订单状态',
    `buyer_id`           bigint(20) unsigned     not null default 0 comment '买家id',
    `receiver_name`      varchar(200)            not null default '' comment '收件人姓名',
    `receiver_telephone` varchar(200)            not null default '' comment '收件人电话',
    `receiver_address`   varchar(200)            not null default '' comment '收件人地址',
    `receiver_postcode`  varchar(10)             not null default '' comment '收件人邮编',
    `sku_id`             bigint(20) unsigned     not null default 0 comment 'SKU id',
    `sku_price`          decimal(10, 2) unsigned not null default 0 comment 'SKU价格',
    `order_time`         datetime                not null default current_timestamp comment '下单时间',
    `pay_time`           datetime                not null default current_timestamp comment '支付时间',
    `dispatch_time`      datetime                not null default current_timestamp comment '发货时间',
    `receive_time`       datetime                not null default current_timestamp comment '收货时间',
    `create_time`        datetime                not null default current_timestamp comment '创建时间',
    `update_time`        datetime                not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted`         tinyint(2)              not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) engine = InnoDB
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment '订单表';

create table `t_order_1` like `t_order_0`;
create table `t_order_2` like `t_order_0`;
create table `t_order_3` like `t_order_0`;
create table `t_order_4` like `t_order_0`;
create table `t_order_5` like `t_order_0`;
create table `t_order_6` like `t_order_0`;
create table `t_order_7` like `t_order_0`;
create table `t_order_8` like `t_order_0`;
create table `t_order_9` like `t_order_0`;
create table `t_order_10` like `t_order_0`;
create table `t_order_11` like `t_order_0`;
create table `t_order_12` like `t_order_0`;
create table `t_order_13` like `t_order_0`;
create table `t_order_14` like `t_order_0`;
create table `t_order_15` like `t_order_0`;

create database if not exists `d_order_1` default character set utf8mb4 collate utf8mb4_unicode_ci;

use `d_order_1`;

create table if not exists `t_order_0`
(
    `pk_id`              bigint(20) unsigned     not null default 0 primary key comment '主键',
    `order_no`           varchar(200)            not null default '' comment '订单编号',
    `order_status`       varchar(100)            not null default '' comment '订单状态',
    `buyer_id`           bigint(20) unsigned     not null default 0 comment '买家id',
    `receiver_name`      varchar(200)            not null default '' comment '收件人姓名',
    `receiver_telephone` varchar(200)            not null default '' comment '收件人电话',
    `receiver_address`   varchar(200)            not null default '' comment '收件人地址',
    `receiver_postcode`  varchar(10)             not null default '' comment '收件人邮编',
    `sku_id`             bigint(20) unsigned     not null default 0 comment 'SKU id',
    `sku_price`          decimal(10, 2) unsigned not null default 0 comment 'SKU价格',
    `order_time`         datetime                not null default current_timestamp comment '下单时间',
    `pay_time`           datetime                not null default current_timestamp comment '支付时间',
    `dispatch_time`      datetime                not null default current_timestamp comment '发货时间',
    `receive_time`       datetime                not null default current_timestamp comment '收货时间',
    `create_time`        datetime                not null default current_timestamp comment '创建时间',
    `update_time`        datetime                not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted`         tinyint(2)              not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) engine = InnoDB
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment '订单表';

create table `t_order_1` like `t_order_0`;
create table `t_order_2` like `t_order_0`;
create table `t_order_3` like `t_order_0`;
create table `t_order_4` like `t_order_0`;
create table `t_order_5` like `t_order_0`;
create table `t_order_6` like `t_order_0`;
create table `t_order_7` like `t_order_0`;
create table `t_order_8` like `t_order_0`;
create table `t_order_9` like `t_order_0`;
create table `t_order_10` like `t_order_0`;
create table `t_order_11` like `t_order_0`;
create table `t_order_12` like `t_order_0`;
create table `t_order_13` like `t_order_0`;
create table `t_order_14` like `t_order_0`;
create table `t_order_15` like `t_order_0`;