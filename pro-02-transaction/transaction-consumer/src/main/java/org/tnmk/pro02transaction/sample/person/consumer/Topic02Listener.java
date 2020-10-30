package org.tnmk.pro02transaction.sample.person.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Topic02Listener {

    private static final Logger logger = LoggerFactory.getLogger(Topic02Listener.class);

    @KafkaListener(groupId = "personAutoAckGroup", topics = TopicConstants.TOPIC02)
    public void receive(@Payload String message, @Headers MessageHeaders headers) {
        LogHelper.logReceiveData(message, headers);
    }

}