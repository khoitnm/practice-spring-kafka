package org.tnmk.pro04stream.pro04aconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.tnmk.pro04stream.pro04aconsumer.model.Person02;

@Service
public class Person02Listener {

    private static final Logger logger = LoggerFactory.getLogger(Person02Listener.class);

    @KafkaListener(groupId = "personAutoAckGroup", topics = TopicConstants.PERSON02)
    public void receive(@Payload Person02 message, @Headers MessageHeaders headers) {
        logReceiveData(message, headers);
    }

    private void logReceiveData(Person02 message, MessageHeaders headers) {
        Long offset = (Long) headers.get(KafkaHeaders.OFFSET);
        logger.info("[KAFKA LISTENER]received record[{}]='{}'", offset, message);
    }
}