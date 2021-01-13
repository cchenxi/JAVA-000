学习笔记

启动kafka集群

```shell
➜  kafka_2.13-2.7.0 nohup bin/kafka-server-start.sh config/cluster/kafka9001.properties &
[2] 78045
appending output to nohup.out
➜  kafka_2.13-2.7.0 nohup bin/kafka-server-start.sh config/cluster/kafka9002.properties &
[3] 78369
appending output to nohup.out
➜  kafka_2.13-2.7.0 nohup bin/kafka-server-start.sh config/cluster/kafka9003.properties &
[4] 78689
appending output to nohup.out
```

生产

```shell
➜  kafka_2.13-2.7.0 bin/kafka-console-producer.sh --bootstrap-server localhost:9001,localhost:9002,localhost:9003 --topic test-32
```

消费

```shell
➜  kafka_2.13-2.7.0 bin/kafka-console-consumer.sh --bootstrap-server localhost:9001,localhost:9002,localhost:9003 --from-beginning --topic test-32
```

性能测试

```shell
➜  kafka_2.13-2.7.0 bin/kafka-producer-perf-test.sh --topic test-32 --num-records 4000000 --record-size 1000 --throughput 1000000 --producer-props bootstrap.servers=localhost:9001,localhost:9
002,localhost:9003
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