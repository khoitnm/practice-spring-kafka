package org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aconsumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aconsumer.consumer.model.Person02;

@Service
public class Person02Listener {


    @KafkaListener(groupId = "personAutoAckGroup", topics = TopicConstants.PERSON02)
    public void receive(@Payload Person02 message, @Headers MessageHeaders headers) {
        LogHelper.logReceiveData(message, headers);
    }


}