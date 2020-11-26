-- 电商相关表设计
-- 基于电商交易场景（用户、商品、订单），设计一套简单的表结构

create database if not exists d_mall;

-- 1. 商品相关表

-- 1.1. 品类
create table t_class (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `class_no` varchar(100) not null default '' comment '品类编号',
    `class_name` varchar(100) not null default '' comment '品类名称',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment '商品品类表';

-- 1.2. 品牌
create table t_brand (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `brand_name` varchar(200) not null default '' comment '品牌名称',
    `brand_image` varchar(500) not null default '' comment '商品品牌图',
    `letter` char(1) not null default '' comment '品牌首字母',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment '商品品牌表';

-- 1.3. 分类
create table t_category (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `category_name` varchar(200) not null default '' comment '分类名称',
    `parent_category_id` bigint(20) unsigned not null default 0 comment '上级分类id',
    `is_parent` tinyint(1) not null default 0 comment '是否含有下级分类',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment '商品分类表';

-- 1.4. 品牌分类表
create table t_brand_category (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `brand_id` bigint(20) unsigned not null default 0 comment '品牌id',
    `category_id` bigint(20) unsigned not null default 0 comment '分类id',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment '商品品牌分类表';

-- 1.5. SPU
create table t_spu (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `title` varchar(200) not null default '' comment '商品标题',
    `sub_title` varchar(200) not null default '' comment '商品副标题',
    `class_id` bigint(20) unsigned not null default 0 comment '品类id',
    `brand_id` bigint(20) unsigned not null default 0 comment '品牌id',
    `category_id` bigint(20) unsigned not null default 0 comment '分类id',
    `sale_able` tinyint(1) unsigned not null default 0 comment '是否上架',
    `valid` tinyint(1) unsigned not null default 0 comment '是否有效',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment '商品SPU表';

-- 1.6. SKU
create table t_product (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `spu_id` bigint(20) unsigned not null default 0 comment '商品SPU id',
    `price` decimal(10, 2) unsigned not null default 0 comment 'SKU价格',
    `sale_able` tinyint(1) unsigned not null default 0 comment '是否上架',
    `valid` tinyint(1) unsigned not null default 0 comment '是否有效',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment '商品SKU表';

-- 2. 客户相关表

-- 2.1. 客户表
create table t_customer (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `username` varchar(200) not null default '' comment '用户名',
    `password` varchar(200) not null default '' comment '密码',
    `nick_name` varchar(200) not null default '' comment '昵称',
    `telephone` varchar(15) not null default '' comment '电话',
    `e_mail` varchar(255) not null default '' comment '电子邮箱',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment '客户表';

-- 2.2. 客户收货地址表
create table t_customer_address (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `customer_id` bigint(20) unsigned not null default 0 comment '客户id',
    `receiver_name` varchar(200) not null default '' comment '收件人姓名',
    `receiver_telephone` varchar(200) not null default '' comment '收件人电话',
    `receiver_address` varchar(200) not null default '' comment '收件人地址',
    `receiver_postcode` varchar(10) not null default '' comment '收件人邮编',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment '客户收货地址表';

-- 3. 库存相关表

-- 3.1. 省份表
create table t_province (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `province_name` varchar(20) not null default '' comment '省份名称',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment '省份表';

-- 3.2. 城市表
create table t_city (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `city_name` varchar(20) not null default '' comment '省份名称',
    `province_id` bigint(20) unsigned not null default 0 comment '省份id',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment '城市表';

-- 3.3. 仓库表
create table t_store_house (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `province_id` bigint(20) unsigned not null default 0 comment '省份id',
    `city_id` bigint(20) unsigned not null default 0 comment '城市id',
    `address` varchar(200) not null default '' comment '地址',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment '仓库表';

-- 3.4. SKU_仓库表
create table t_sku_store_house (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `store_house_id` bigint(20) unsigned not null default 0 comment '仓库id',
    `sku_id` bigint(20) unsigned not null default 0 comment 'SKU id',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment 'SKU_仓库表';

-- 4. 订单相关表
create table t_order (
    `pk_id` bigint(20) unsigned not null default 0 primary key comment '主键',
    `order_no` varchar(200) not null default '' comment '订单编号',
    `order_status` varchar(100) not null default '' comment '订单状态',
    `receiver_name` varchar(200) not null default '' comment '收件人姓名',
    `receiver_telephone` varchar(200) not null default '' comment '收件人电话',
    `receiver_address` varchar(200) not null default '' comment '收件人地址',
    `receiver_postcode` varchar(10) not null default '' comment '收件人邮编',
    `sku_id` bigint(20) unsigned not null default 0 comment 'SKU id',
    `sku_price` decimal(10, 2) unsigned not null default 0 comment 'SKU价格',
    `order_time` datetime not null default current_timestamp comment '下单时间',
    `pay_time` datetime not null default current_timestamp comment '支付时间',
    `dispatch_time` datetime not null default current_timestamp comment '发货时间',
    `receive_time` datetime not null default current_timestamp comment '收货时间',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted` tinyint(2) not null default 0 comment '逻辑删除标识，未删除(0)，已删除(1)'
) default charset utf8mb4 comment '订单表';
