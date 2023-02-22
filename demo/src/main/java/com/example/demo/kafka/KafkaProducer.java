package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaProducer implements Runnable {

    private String topic;
    private  org.apache.kafka.clients.producer.KafkaProducer<String, String> producer;

    public KafkaProducer(String topic) {
        this.topic = topic;
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
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
    }

    @Override
    public void run() {
        //发送数据
        for (int i = 0; i < 20; i++) {
            producer.send(new ProducerRecord<>(topic, "zhu_key", "pig_" + i), ((recordMetadata, e) -> {
                if (e == null) {
                    System.out.println("success->" +
                            "   partition = " +recordMetadata.partition()+"  ~~~~~~~  offset = "+recordMetadata.offset());
                } else {
                    e.printStackTrace();
                }
            }));
        }
        //关闭资源
        producer.close();
    }
}
