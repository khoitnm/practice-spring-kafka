package org.tnmk.pro02transaction.sample.person.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Service
public class MultipleTopicsProducer {

    private static final Logger logger = LoggerFactory.getLogger(MultipleTopicsProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public void sendSuccessfully(String messageBody) {
        sendSuccessfully(TopicConstants.TOPIC01, messageBody);
        sendSuccessfully(TopicConstants.TOPIC02, messageBody);
    }

    @Transactional
    public void sendFailAndRollback(String messageBody) {
        sendSuccessfully(TopicConstants.TOPIC01, messageBody);
        sendSuccessfully(TopicConstants.TOPIC02, messageBody);
        throw new RuntimeException(String.format("%s message won't be sent to topic01 & topic02", messageBody));
    }

    private void sendSuccessfully(String topic, String messageBody){
        logger.info("[KAFKA PUBLISHER] sending data='{}' to topic='{}'", messageBody, topic);
        String messageKey = "sample"+System.nanoTime();
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic, messageKey, messageBody);
        try {
            SendResult<String, String> sendResult = listenableFuture.get();
            logger.info("[KAFKA PUBLISHER] send result: {}", sendResult.toString());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("[KAFKA PUBLISHER] Error: "+e.getMessage(), e);
        }
    }
}