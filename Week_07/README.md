# 周四作业

## 1. [模拟插入100万条测试订单数据](https://github.com/cchenxi/JAVA-000/tree/main/Week_07/db-insert-data)

- 用jdbcTemplate的execute()方法执行插入100万条数据 `时间太长，未执行完毕`
- 用PreparedStatement的addBatch()方法执行插入100万条数据 `cost 30,776 ms`
- 用PreparedStatement的addBatch()方法加多线程（10，每个线程插入100000）插入100万条数据 TODO
- 用mysql存储过程，插入100万条数据 TODO

# 周六作业

## 1. [配置复制](replication.md)

## 2. [读写分离-数据库框架1.0](https://github.com/cchenxi/JAVA-000/tree/main/Week_07/db-fx-01)

## 3. [读写分离-数据库框架2.0](https://github.com/cchenxi/JAVA-000/tree/main/Week_07/db-fx-02)