package org.tnmk.pro02transaction.pro02bdbkafkachaintransconsumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Topic01Listener {

    @KafkaListener(groupId = "topic01AutoAckGroup", topics = TopicConstants.TOPIC01)
    public void receive(@Payload String message, @Headers MessageHeaders headers) {
        LogHelper.logReceiveData(message, headers);
    }

}