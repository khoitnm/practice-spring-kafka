package org.tnmk.pro01simple.sample.person.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EventListener {

    private static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    @KafkaListener(groupId = "personAutoAckGroup", topics = TopicConstants.PERSON)
    public void receive(@Payload String message, @Headers MessageHeaders headers) {
        logReceiveData(message, headers);
    }

    private void logReceiveData(String message, MessageHeaders headers) {
        Long offset = (Long) headers.get(KafkaHeaders.OFFSET);
        logger.info("[KAFKA LISTENER]received record[{}]='{}'", offset, message);
    }
}