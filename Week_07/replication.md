# 复制

## docker 配置1主2从的MySQL 5.7，进行异步复制

### 1. 主库

```shell
docker pull mysql:5.7

docker run -d \
-p 3307:3306 \
-e MYSQL_ROOT_PASSWORD=123456 \
-v ~/tools/mysql/master/data:/var/lib/mysql \
-v ~/tools/mysql/master/conf.d:/etc/mysql/conf.d \
--name mysql-5.7-master \
mysql:5.7
```

自定义MySQL配置文件`~/tools/mysql/master/conf.d/my-custom.cnf`配置内容

```plain text
[mysqld]
server_id = 1

sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 
log_bin=mysql-bin
binlog-format=Row
```


### 2. 从库

```shell
docker run -d \
-p 3317:3306 \
-e MYSQL_ROOT_PASSWORD=123456 \
-v ~/tools/mysql/slave01/data:/var/lib/mysql \
-v ~/tools/mysql/slave01/conf.d:/etc/mysql/conf.d \
--name mysql-5.7-slave01 \
mysql:5.7
```

```plain text
[mysqld]
server_id = 3

sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 
log_bin=mysql-bin
binlog-format=Row
```


```shell
docker run -d \
-p 3327:3306 \
-e MYSQL_ROOT_PASSWORD=123456 \
-v ~/tools/mysql/slave02/data:/var/lib/mysql \
-v ~/tools/mysql/slave02/conf.d:/etc/mysql/conf.d \
--name mysql-5.7-slave02 \
mysql:5.7
```

```plain text
[mysqld]
server_id = 4

sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 
log_bin=mysql-bin
binlog-format=Row
```

### 3. 配置异步主从复制

#### 3.1. 登录进入主库，执行以下SQL

```sql
-- 创建用于复制的用户
CREATE USER 'repl'@'%' IDENTIFIED BY '123456';
-- 用户授权
GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
flush privileges;
-- 查看master状态 获取binlog信息
show master status;
-- file:mysql-bin.000003
-- Position:3676
```

#### 3.2. 登录进入从库，执行以下SQL

```sql
CHANGE MASTER TO
MASTER_HOST='172.17.0.2', -- 执行docker命令获取主库的ip `docker inspect --format='{{.NetworkSettings.IPAddress}}' mysql-5.7-master`
MASTER_PORT = 3306,
MASTER_USER='repl',
MASTER_PASSWORD='123456',
MASTER_LOG_FILE='mysql-bin.000003', -- 根据3.1. 获取的binlog文件
MASTER_LOG_POS=3676; -- 根据3.1. 获取的binlog position

-- 开启同步
start slave ;

show slave status ; -- 看到结果中 Slave_IO_Running: Yes , Slave_SQL_Runniing:Yes 即表示配置成功
```

#### 3.3. 验证

1. 在主库执行 `create schema db5;`

2. 在从库执行 `show databases;`，可以看到 `db5`。

## 半同步复制



## 组复制