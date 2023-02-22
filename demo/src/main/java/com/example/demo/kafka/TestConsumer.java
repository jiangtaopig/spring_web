package com.example.demo.kafka;

public class TestConsumer {
    public static void main(String[] args) {
        KafkaConsumer kafkaConsumer = new KafkaConsumer(KafkaConstant.TOPIC);
        Thread thread = new Thread(kafkaConsumer, "consumerThread");
        thread.start();
    }
}
