package org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aproducer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aproducer.producer.model.Person;

import java.util.concurrent.ExecutionException;

@Service
public class PersonProducer {

    private static final Logger logger = LoggerFactory.getLogger(PersonProducer.class);

    @Autowired
    private KafkaTemplate<String, Person> kafkaTemplate;

    private String topic = TopicConstants.PERSON01;

    public void send(Person messageBody){
        logger.info("[KAFKA PUBLISHER] sending data='{}' to topic='{}'", messageBody, topic);
        String messageKey = "sample"+System.nanoTime();
        ListenableFuture<SendResult<String, Person>> listenableFuture = kafkaTemplate.send(topic, messageKey, messageBody);
        try {
            SendResult<String, Person> sendResult = listenableFuture.get();
            logger.info("[KAFKA PUBLISHER] send result: {}", sendResult.toString());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("[KAFKA PUBLISHER] Error: "+e.getMessage(), e);
        }
    }
}