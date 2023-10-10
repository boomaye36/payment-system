package com.moneyservice.adapter.in.kafka;

import com.common.CountDownLatchManager;
import com.common.LoggingProducer;
import com.common.RechargingMoneyTask;
import com.common.SubTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

@Component

public class RechargingMoneyResultConsumer {
    private final KafkaConsumer<String, String> consumer;

    private final LoggingProducer loggingProducer;

    @NotNull
    private final CountDownLatchManager countDownLatchManager;
    public RechargingMoneyResultConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                                         @Value("${task.result.topic}") String topic, LoggingProducer loggingProducer, CountDownLatchManager countDownLatchManager){
        this.countDownLatchManager = countDownLatchManager;
        this.loggingProducer = loggingProducer;

        // producer 초기화
        Properties props = new Properties(); // 브로커 여러 개 가능
        props.put("bootstrap.servers", bootstrapServers);

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
                        System.out.println("Received message: " + record.key() + " / " + record.value());
                        // record : RechargingMoneyTask (jsonString)
                        RechargingMoneyTask task;
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        // task run
                        try {
                             task = mapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        List<SubTask> subTaskList = task.getSubTaskList();
                        boolean taskResult = true;
                        // task result
                        for (SubTask subTask : task.getSubTaskList()){
                            // 한번만 실패해도 실패한 task
                            if(subTask.getStatus().equals("fail")){
                                taskResult = false;
                                break;
                            }
                        }
                        if (taskResult){
                            this.loggingProducer.sendMessage(task.getTaskId(), "task success");
                            this.countDownLatchManager.setDataForKey(task.getTaskId(), "success");

                        }else{
                            this.loggingProducer.sendMessage(task.getTaskId(), "task fail");
                            this.countDownLatchManager.setDataForKey(task.getTaskId(), "fail");
                        }
                        // produce TaskResult
                        this.countDownLatchManager.getCountDownLatch("rechargingMoneyTask").countDown();
                        System.out.println("Received message : " + record.value());
                    }
                }
            } finally {
                consumer.close();
            }
        });
        consumerThread.start();
    }



}
