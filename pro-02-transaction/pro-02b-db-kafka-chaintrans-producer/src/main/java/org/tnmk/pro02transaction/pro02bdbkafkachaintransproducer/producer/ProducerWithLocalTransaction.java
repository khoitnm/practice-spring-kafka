package org.tnmk.pro02transaction.pro02bdbkafkachaintransproducer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class ProducerWithLocalTransaction {

    private static final Logger logger = LoggerFactory.getLogger(ProducerWithLocalTransaction.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMultiTopicsSuccessfully(String messageBody) {
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.executeInTransaction(kafkaOperations -> {
            ListenableFuture<SendResult<String, String>> listenableFuture1 = sendAndWriteLog(kafkaOperations, TopicConstants.TOPIC01, messageBody);
            ListenableFuture<SendResult<String, String>> listenableFuture2 = sendAndWriteLog(kafkaOperations, TopicConstants.TOPIC02, messageBody);
            return null;
        });
    }

    public void sendMultiTopicsFailAndRollback(String messageBody) {
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.executeInTransaction(kafkaOperations -> {
            sendAndWriteLog(kafkaOperations, TopicConstants.TOPIC01, messageBody);
            sendAndWriteLog(kafkaOperations, TopicConstants.TOPIC02, messageBody);
            throw new RuntimeException(String.format("%s message won't be sent to topic01 & topic02", messageBody));
        });
    }

    private ListenableFuture<SendResult<String, String>> sendAndWriteLog(KafkaOperations kafkaOperations, String topic, String messageBody) {
        logger.info("[KAFKA PUBLISHER] sending data='{}' to topic='{}'...", messageBody, topic);
        return kafkaOperations.send(topic, messageBody);
    }
}