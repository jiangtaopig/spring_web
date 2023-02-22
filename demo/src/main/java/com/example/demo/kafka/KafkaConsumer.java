package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;



public class KafkaConsumer implements Runnable {

    private String topic;
    private org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public KafkaConsumer(String topic) {
        this.topic = topic;
        //1.创建消费者配置信息
        Properties properties = new Properties();
        //链接的集群
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //开启自动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        //自动提交的延迟
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        //key,value的反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //消费者组
        /**
         * zhu_group1：组名 不同组名可以重复消费。例如你先使用了组名A消费了kafka的1000条数据，
         * 但是你还想再次进行消费这1000条数据，并且不想重新去产生，那么这里你只需要更改组名就可以重复消费了
         */
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "zhu_group1");
        //重置消费者offset的方法（达到重复消费的目的），设置该属性也只在两种情况下生效：1.上面设置的消费组还未消费(可以更改组名来消费)2.该offset已经过期
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //创建生产者
        consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(topic));
    }

    @Override
    public void run() {

        while (true) {
            //获取数据
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.parse("PT1M")); // parses as "1 minutes"
            //解析并打印consumerRecords
            for (ConsumerRecord consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.key() + "----" + consumerRecord.value());
            }
            atomicInteger.addAndGet(1);
            int count = atomicInteger.get();
            System.out.println("------------ count = " + count);
            if (count == 100) {
                break;
            }
        }
    }
}
