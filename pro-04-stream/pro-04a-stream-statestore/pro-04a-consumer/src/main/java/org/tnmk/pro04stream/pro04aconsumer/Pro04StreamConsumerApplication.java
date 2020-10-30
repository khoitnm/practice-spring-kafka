package org.tnmk.pro04stream.pro04aconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application to test Kafka integration.
 */
@SpringBootApplication
public class Pro04StreamConsumerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(Pro04StreamConsumerApplication.class, args);
    }
}
