package org.tnmk.pro02transaction.pro02multipleproducers.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProducerWithPureApacheKafka {

    private static final Logger logger = LoggerFactory.getLogger(ProducerWithPureApacheKafka.class);



    public void sendMultiTopicsSuccessfully(String messageBody) {
        Map<String, String> properties = new HashMap<>();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("transactional.id", "tx-");
        KafkaProducer producer = new KafkaProducer(properties);
        producer.initTransactions();
        producer.beginTransaction();
        producer.send(new ProducerRecord(TopicConstants.TOPIC01, messageBody));
        producer.send(new ProducerRecord(TopicConstants.TOPIC02, messageBody));
        producer.commitTransaction();
    }

//    public void sendMultiTopicsFailAndRollback(String messageBody) {
//        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.executeInTransaction(kafkaOperations -> {
//            kafkaOperations.send(TopicConstants.TOPIC01, messageBody);
//            kafkaOperations.send(TopicConstants.TOPIC02, messageBody);
//            throw new RuntimeException(String.format("%s message won't be sent to topic01 & topic02", messageBody));
//        });
//
//    }
}