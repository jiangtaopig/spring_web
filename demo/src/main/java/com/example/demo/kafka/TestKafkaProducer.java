package com.example.demo.kafka;

public class TestKafkaProducer {
    public static void main(String[] args) {
        KafkaProducer kafkaProducer = new KafkaProducer(KafkaConstant.TOPIC);
        Thread thread = new Thread(kafkaProducer, "producer_thread");
        thread.start();
    }
}
