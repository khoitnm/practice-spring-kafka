package org.tnmk.pro04stream.pro04aconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.tnmk.pro04stream.pro04aconsumer.model.Person01;

@Service
public class Person01Listener {

    private static final Logger logger = LoggerFactory.getLogger(Person01Listener.class);

    @KafkaListener(groupId = "personAutoAckGroup", topics = TopicConstants.PERSON01)
    public void receive(@Payload Person01 message, @Headers MessageHeaders headers) {
        logReceiveData(message, headers);
    }

    private void logReceiveData(Person01 message, MessageHeaders headers) {
        Long offset = (Long) headers.get(KafkaHeaders.OFFSET);
        logger.info("[KAFKA LISTENER]received record[{}]='{}'", offset, message);
    }
}