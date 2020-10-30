package org.tnmk.pro02transaction.transactionconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application to test Kafka integration.
 */
@SpringBootApplication
public class Pro02TransactionConsumerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(Pro02TransactionConsumerApplication.class, args);
    }
}
