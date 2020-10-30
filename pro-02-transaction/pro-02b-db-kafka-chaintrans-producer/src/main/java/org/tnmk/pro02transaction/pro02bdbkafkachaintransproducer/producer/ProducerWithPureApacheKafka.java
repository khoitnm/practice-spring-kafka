package org.tnmk.pro02transaction.pro02bdbkafkachaintransproducer.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Service
public class ProducerWithPureApacheKafka {


    private static final Logger logger = LoggerFactory.getLogger(ProducerWithPureApacheKafka.class);

    public void sendMultiTopicsSuccessfully(String messageBody) {
        KafkaProducer kafkaProducer = createKafkaProducer();
        kafkaProducer.initTransactions();
        kafkaProducer.beginTransaction();
        sendAndWriteLog(kafkaProducer, TopicConstants.TOPIC01, messageBody);
        sendAndWriteLog(kafkaProducer, TopicConstants.TOPIC02, messageBody);
        kafkaProducer.commitTransaction();
    }

    //This is just a quick example, we actually can load values from Spring configuration, and initiate KafkaProducer as a Spring Bean
    private KafkaProducer createKafkaProducer() {
        Map<String, String> properties = new HashMap<>();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("transactional.id", "tx-");
        KafkaProducer producer = new KafkaProducer(properties);
        return producer;
    }

    private Future<RecordMetadata> sendAndWriteLog(KafkaProducer kafkaProducer, String topic, String messageBody) {
        logger.info("[KAFKA PUBLISHER] sending data='{}' to topic='{}'...", messageBody, topic);
        return kafkaProducer.send(new ProducerRecord(topic, messageBody));
    }
}