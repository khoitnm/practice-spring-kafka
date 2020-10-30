package org.tnmk.pro02transaction.pro02bdbkafkachaintransproducer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.transaction.ChainedKafkaTransactionManager;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
public class KafkaTransactionManagerConfig {

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    /**
     * This is used for combining between Kafka Transaction and JPA DB Transaction.
     * TODO Question: how about MongoDB Transaction??? Will it still work the same way?
     * @param kafkaTransactionManager
     * @param jpaTransactionManager
     * @return
     */
    @Bean
    @Primary
    public ChainedKafkaTransactionManager<Object, Object> chainedKafkaTransactionManager(
            KafkaTransactionManager kafkaTransactionManager,
            JpaTransactionManager jpaTransactionManager) {
        return new ChainedKafkaTransactionManager<>(kafkaTransactionManager, jpaTransactionManager);
    }
}
