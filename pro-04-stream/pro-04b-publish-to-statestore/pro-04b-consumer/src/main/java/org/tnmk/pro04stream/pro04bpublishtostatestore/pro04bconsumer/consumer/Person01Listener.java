package org.tnmk.pro04stream.pro04bpublishtostatestore.pro04bconsumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.tnmk.pro04stream.pro04bpublishtostatestore.pro04bconsumer.consumer.model.Person01;

@Service
public class Person01Listener {

    @KafkaListener(groupId = "personAutoAckGroup", topics = TopicConstants.PERSON01)
    public void receive(@Payload Person01 message, @Headers MessageHeaders headers) {
        LogHelper.logReceiveData(message, headers);
    }
}