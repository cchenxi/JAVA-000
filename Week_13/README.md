# 1、(必做)搭建ActiveMQ服务，基于JMS，写代码分别实现对于queue和topic的消息 生产和消费，代码提交到github。

[code](https://github.com/cchenxi/JAVA-000/tree/main/Week_13/activemq-jms-demo)

# 2、(必做)搭建一个3节点Kafka集群，测试功能和性能;实现spring kafka下对kafka集群 的操作，将代码提交到github。

[code](https://github.com/cchenxi/JAVA-000/tree/main/Week_13/spring-kafka-demo)

测试

1. 使用 单元测试`io.github.cchenxi.w13.SpringKafkaDemoApplicationTests.testProducer` 测试消息生产
2. 启动 `io.github.cchenxi.w13.SpringKafkaDemoApplication` 测试消费消息

## 搭建3节点的kafka集群

1. 准备配置文件

- [kafka9001.properties](./spring-kafka-demo/src/main/resources/kafka9001.properties)
- [kafka9002.properties](./spring-kafka-demo/src/main/resources/kafka9002.properties)
- [kafka9003.properties](./spring-kafka-demo/src/main/resources/kafka9003.properties)

2. 启动zk

```shell
cd kafka
bin/zookeeper-server-start.sh config/zookeeper.properties
```

3. 依次启动kafka

```shell
nohup bin/kafka-server-start.sh config/cluster/kafka9001.properties &
nohup bin/kafka-server-start.sh config/cluster/kafka9002.properties &
nohup bin/kafka-server-start.sh config/cluster/kafka9003.properties &
```

4. 检查

使用jps命令查看当前java进程，看到3个kafka进程表示启动成功

5. 创建topic

```shell
bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic test-32 --partitions 3 --replication-factor 2
```

命令行中看到返回值 

```shell
Created topic test-32.
```

6. 查看topic

```shell
bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic test-32
```

命令行中看到

```shell
Topic: test-32	PartitionCount: 3	ReplicationFactor: 2	Configs:
	Topic: test-32	Partition: 0	Leader: 1	Replicas: 1,3	Isr: 1,3
	Topic: test-32	Partition: 1	Leader: 2	Replicas: 2,1	Isr: 2,1
	Topic: test-32	Partition: 2	Leader: 3	Replicas: 3,2	Isr: 3,2
```

7. 生产消息

```shell
bin/kafka-console-producer.sh --bootstrap-server localhost:9001,localhost:9002,localhost:9003 --topic test-32
```

命令行中出现 `>` 符号，等待输入

8. 消费消息

```shell
bin/kafka-console-consumer.sh --bootstrap-server localhost:9001,localhost:9002,localhost:9003 --from-beginning --topic test-32
```

9. 性能测试

```shell
bin/kafka-producer-perf-test.sh --topic test-32 --num-records 4000000 --record-size 1000 --throughput 1000000 --producer-props bootstrap.servers=localhost:9001,localhost:9002,localhost:9003
```

结果如下

```shell
132529 records sent, 26505.8 records/sec (25.28 MB/sec), 974.7 ms avg latency, 1450.0 ms max latency.
211200 records sent, 42240.0 records/sec (40.28 MB/sec), 774.0 ms avg latency, 920.0 ms max latency.
195728 records sent, 39145.6 records/sec (37.33 MB/sec), 837.8 ms avg latency, 1147.0 ms max latency.
318960 records sent, 63664.7 records/sec (60.72 MB/sec), 529.6 ms avg latency, 944.0 ms max latency.
329200 records sent, 65840.0 records/sec (62.79 MB/sec), 496.8 ms avg latency, 693.0 ms max latency.
374944 records sent, 74958.8 records/sec (71.49 MB/sec), 444.1 ms avg latency, 833.0 ms max latency.
464944 records sent, 92988.8 records/sec (88.68 MB/sec), 353.1 ms avg latency, 605.0 ms max latency.
523840 records sent, 104768.0 records/sec (99.91 MB/sec), 313.4 ms avg latency, 482.0 ms max latency.
508976 records sent, 101795.2 records/sec (97.08 MB/sec), 322.7 ms avg latency, 613.0 ms max latency.
496336 records sent, 99267.2 records/sec (94.67 MB/sec), 328.0 ms avg latency, 619.0 ms max latency.
4000000 records sent, 73777.597433 records/sec (70.36 MB/sec), 437.01 ms avg latency, 1450.00 ms max latency, 407 ms 50th, 877 ms 95th, 1098 ms 99th, 1386 ms 99.9th.
```