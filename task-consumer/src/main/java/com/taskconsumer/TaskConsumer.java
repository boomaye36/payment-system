package com.taskconsumer;

import com.common.RechargingMoneyTask;
import com.common.SubTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Properties;

@Component

public class TaskConsumer {
    private final KafkaConsumer<String, String> consumer;

    private final TaskResultProducer taskResultProducer;

    public TaskConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                        @Value("${task.topic}") String topic, TaskResultProducer taskResultProducer){
        this.taskResultProducer = taskResultProducer;

        // producer 초기화
        Properties props = new Properties(); // 브로커 여러 개 가능
        props.put("group.id", "my-group");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.consumer = new KafkaConsumer<String, String>(props);
        Thread consumerThread = new Thread(() -> {
            try{
                while(true){
                    ObjectMapper mapper = new ObjectMapper();
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records){
                        // record : RechargingMoneyTask (jsonString)
                        RechargingMoneyTask task;
                        // task run
                        try {
                             task = mapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        // task result
                        for (SubTask subTask : task.getSubTaskList()){

                            subTask.setStatus("SUCCESS");
                        }
                        // produce TaskResult
                        this.taskResultProducer.sendMessage(task.getTaskId(), task);
                        System.out.println("Received message : " + record.value());
                    }
                }
            } finally {
                consumer.close();
            }
        });
    }



}
