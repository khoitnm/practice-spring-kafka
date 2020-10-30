package org.tnmk.pro03jsonmessage.pro03consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application to test Kafka integration.
 */
@SpringBootApplication
public class Pro03JsonMessageConsumerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(Pro03JsonMessageConsumerApplication.class, args);
    }
}
