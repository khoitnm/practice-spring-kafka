package org.tnmk.pro04stream.pro04bpublishtostatestore.pro04bconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application to test Kafka integration.
 */
@SpringBootApplication
public class Pro04bConsumerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(Pro04bConsumerApplication.class, args);
    }
}
