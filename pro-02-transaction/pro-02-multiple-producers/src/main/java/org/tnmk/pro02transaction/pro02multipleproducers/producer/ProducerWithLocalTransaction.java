package org.tnmk.pro02transaction.pro02multipleproducers.producer;

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

    //    @Transactional
    public void sendSuccessfully(String messageBody) {
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.executeInTransaction(ikafkaTemplate -> {
            send(ikafkaTemplate, TopicConstants.TOPIC01, messageBody);
            send(ikafkaTemplate, TopicConstants.TOPIC02, messageBody);
            return null;
        });
        LogHelper.logResult(listenableFuture);
    }

    //    @Transactional
    public void sendFailAndRollback(String messageBody) {
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.executeInTransaction(ikafkaTemplate -> {
            send(ikafkaTemplate, TopicConstants.TOPIC01, messageBody);
            send(ikafkaTemplate, TopicConstants.TOPIC02, messageBody);
            throw new RuntimeException(String.format("%s message won't be sent to topic01 & topic02", messageBody));
        });

    }

    private ListenableFuture<SendResult<String, String>> send(KafkaOperations kafkaOperations, String topic, String messageBody) {
        logger.info("[KAFKA PUBLISHER] sending data='{}' to topic='{}'...", messageBody, topic);
        String messageKey = "sample" + System.nanoTime();
        return kafkaOperations.send(topic, messageKey, messageBody);
    }
}