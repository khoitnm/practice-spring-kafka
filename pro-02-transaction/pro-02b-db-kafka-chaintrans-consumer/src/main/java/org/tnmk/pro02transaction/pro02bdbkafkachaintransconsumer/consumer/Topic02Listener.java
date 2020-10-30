package org.tnmk.pro02transaction.pro02bdbkafkachaintransconsumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Topic02Listener {

    @KafkaListener(groupId = "topic02AutoAckGroup", topics = TopicConstants.TOPIC02)
    public void receive(@Payload String message, @Headers MessageHeaders headers) {
        LogHelper.logReceiveData(message, headers);
    }

}