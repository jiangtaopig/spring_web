package com.example.demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyKafkaProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        //指定链接的kafka集群
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("acks", "all");//all等价于-1   0    1
        //重试次数
        properties.put("retries", 1);
        //批次大小
        properties.put("batch.size", 16384);//16k
        //等待时间
        properties.put("linger.ms", 1);
        //RecordAccumulator缓冲区大小
        properties.put("buffer.memory", 33554432);//32m
        //Key,Value的序列化类
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        //创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        //发送数据
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>(KafkaConstant.TOPIC, "pig_" + i));
        }

        //关闭资源
        producer.close();
    }
}
