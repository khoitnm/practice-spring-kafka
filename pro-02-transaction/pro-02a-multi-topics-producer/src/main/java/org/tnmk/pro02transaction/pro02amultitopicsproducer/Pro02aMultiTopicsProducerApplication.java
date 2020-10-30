package org.tnmk.pro02transaction.pro02amultitopicsproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application to test Kafka integration.
 */
//@EnableTransactionManagement
@SpringBootApplication
public class Pro02aMultiTopicsProducerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(Pro02aMultiTopicsProducerApplication.class, args);
    }
}
