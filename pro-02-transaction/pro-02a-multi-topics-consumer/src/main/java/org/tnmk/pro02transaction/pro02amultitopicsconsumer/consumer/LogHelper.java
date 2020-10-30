package org.tnmk.pro02transaction.pro02amultitopicsconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;

public class LogHelper {
    private static final Logger logger = LoggerFactory.getLogger(LogHelper.class);

    public static void logReceiveData(String message, MessageHeaders headers) {
        String topic = (String) headers.get(KafkaHeaders.RECEIVED_TOPIC);
        Long offset = (Long) headers.get(KafkaHeaders.OFFSET);
        logger.info("[KAFKA LISTENER], topic={}, offset={}, message: '{}', headers: {}", topic,offset, message, headers.entrySet());
    }

}
