package com.common;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component

public class LoggingProducer {
    private final KafkaProducer<String, String> producer;
    private final String topic;



    public LoggingProducer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                           @Value("${logging.topic}") String topic){

        // producer 초기화
        Properties props = new Properties(); // 브로커 여러 개 가능
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<String, String>(props);
        this.topic = topic;
    }

    public void sendMessage(String key, String value){
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record, ((metadata, exception) -> {
            if (exception == null){
                System.out.println("Message send successfully . Offset: " + metadata.offset());
            }else{
                exception.printStackTrace();
                System.out.println("Failed to send message: " + exception.getMessage());
            }
        }));
    }

}
