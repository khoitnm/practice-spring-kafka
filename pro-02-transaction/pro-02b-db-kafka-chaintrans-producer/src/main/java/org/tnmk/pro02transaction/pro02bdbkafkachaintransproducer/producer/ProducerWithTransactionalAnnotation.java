package org.tnmk.pro02transaction.pro02bdbkafkachaintransproducer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.tnmk.pro02transaction.pro02bdbkafkachaintransproducer.dbstorage.Message;
import org.tnmk.pro02transaction.pro02bdbkafkachaintransproducer.dbstorage.MessageRepository;

@Service
public class ProducerWithTransactionalAnnotation {

    private static final Logger logger = LoggerFactory.getLogger(ProducerWithTransactionalAnnotation.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public void sendMultiTopicsSuccessfully(String messageBody) {
        sendAndWriteLog(kafkaTemplate, TopicConstants.TOPIC01, messageBody);
        messageRepository.save(new Message(messageBody));
    }

    @Transactional
    public void sendMultiTopicsFailAndRollback(String messageBody) {
        messageRepository.save(new Message(messageBody));
        sendAndWriteLog(kafkaTemplate, TopicConstants.TOPIC01, messageBody);
        throw new RuntimeException(String.format("%s message won't be sent to topic01 & topic02", messageBody));
    }

    private ListenableFuture<SendResult<String, String>> sendAndWriteLog(KafkaOperations kafkaOperations, String topic, String messageBody) {
        logger.info("[KAFKA PUBLISHER] sending data='{}' to topic='{}'...", messageBody, topic);
        return kafkaOperations.send(topic, messageBody);
    }
}