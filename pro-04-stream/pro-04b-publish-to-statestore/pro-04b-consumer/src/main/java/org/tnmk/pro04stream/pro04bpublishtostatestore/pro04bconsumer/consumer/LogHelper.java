package org.tnmk.pro04stream.pro04bpublishtostatestore.pro04bconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;

public class LogHelper {
    private static final Logger logger = LoggerFactory.getLogger(LogHelper.class);

    public static void logReceiveData(Object message, MessageHeaders headers) {
        Long offset = (Long) headers.get(KafkaHeaders.OFFSET);
        String topic = (String) headers.get(KafkaHeaders.RECEIVED_TOPIC);
        logger.info("[KAFKA LISTENER] topic: {}, offset: {}, message: '{}'", topic, offset, message);
    }
}
