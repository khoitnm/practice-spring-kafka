package org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application to test Kafka integration.
 */
@SpringBootApplication
public class Pro04StreamProducerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(Pro04StreamProducerApplication.class, args);
    }
}
