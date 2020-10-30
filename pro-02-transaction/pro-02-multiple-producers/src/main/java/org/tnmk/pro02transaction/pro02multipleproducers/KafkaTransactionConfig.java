package org.tnmk.pro02transaction.pro02multipleproducers;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.tnmk.pro02transaction.pro02multipleproducers.producer.TopicConstants;

import java.util.UUID;

/**
 * No matter when I enable or disable this configuration, I still get error "org.springframework.transaction.CannotCreateTransactionException: Could not create Kafka transaction; nested exception is org.apache.kafka.common.errors.TimeoutException: Timeout expired after 60000milliseconds while awaiting InitProducerId"???
 */
//@Configuration
public class KafkaTransactionConfig {

    @Bean
    KafkaTemplate<String, String> kafkaTemplate(ProducerFactory producerFactory) {
        return new KafkaTemplate(producerFactory);
    }

    @Bean
    KafkaTransactionManager<String, String> kafkaTransactionManager(ProducerFactory producerFactory) {
        return new KafkaTransactionManager<>(producerFactory);
    }

    @Bean("standaloneKafkaTemplate")
    @Primary
    KafkaTemplate<String, String> standaloneKafkaTemplate(ProducerFactory producerFactory) {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate(producerFactory);
        kafkaTemplate.setTransactionIdPrefix(String.format("tx-%s-", UUID.randomUUID().toString()));
        return kafkaTemplate;
    }

    @Bean("standaloneKafkaTransactionManager")
    @Primary
    KafkaTransactionManager<String, String> standaloneKafkaTransactionManager(ProducerFactory producerFactory) {
        KafkaTransactionManager<String, String> kafkaTransactionManager = new KafkaTransactionManager<>(producerFactory);
        kafkaTransactionManager.setTransactionIdPrefix(String.format("tx-%s-", UUID.randomUUID().toString()));
        return kafkaTransactionManager;
    }

    @Bean
    public NewTopic topic01() {
        return TopicBuilder.name(TopicConstants.TOPIC01).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic topic02() {
        return TopicBuilder.name(TopicConstants.TOPIC02).partitions(1).replicas(1).build();
    }

}
