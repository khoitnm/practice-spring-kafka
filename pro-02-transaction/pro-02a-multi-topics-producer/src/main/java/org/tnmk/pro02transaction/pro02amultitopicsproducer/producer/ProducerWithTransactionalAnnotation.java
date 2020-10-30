package org.tnmk.pro02transaction.pro02amultitopicsproducer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class ProducerWithTransactionalAnnotation {

    private static final Logger logger = LoggerFactory.getLogger(ProducerWithTransactionalAnnotation.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Transactional//(transactionManager = "kafkaTransactionManager")
    public void sendMultiTopicsSuccessfully(String messageBody) {
        sendAndWriteLog(kafkaTemplate, TopicConstants.TOPIC01, messageBody);
        sendAndWriteLog(kafkaTemplate, TopicConstants.TOPIC02, messageBody);
    }

    @Transactional//(transactionManager = "kafkaTransactionManager")
    public void sendMultiTopicsFailAndRollback(String messageBody) {
        sendAndWriteLog(kafkaTemplate, TopicConstants.TOPIC01, messageBody);
        sendAndWriteLog(kafkaTemplate, TopicConstants.TOPIC02, messageBody);
        throw new RuntimeException(String.format("%s message won't be sent to topic01 & topic02", messageBody));
    }

    private ListenableFuture<SendResult<String, String>> sendAndWriteLog(KafkaOperations kafkaOperations, String topic, String messageBody) {
        logger.info("[KAFKA PUBLISHER] sending data='{}' to topic='{}'...", messageBody, topic);
        return kafkaOperations.send(topic, messageBody);
    }
}