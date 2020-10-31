package org.tnmk.pro02transaction.pro02amultitopicsproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.tnmk.pro02transaction.pro02amultitopicsproducer.producer.TopicConstants;

@Configuration
public class KafkaTopicsConfig {
    @Bean
    public NewTopic topic01() {
        return TopicBuilder.name(TopicConstants.TOPIC01).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic topic02() {
        return TopicBuilder.name(TopicConstants.TOPIC02).partitions(1).replicas(1).build();
    }
}
