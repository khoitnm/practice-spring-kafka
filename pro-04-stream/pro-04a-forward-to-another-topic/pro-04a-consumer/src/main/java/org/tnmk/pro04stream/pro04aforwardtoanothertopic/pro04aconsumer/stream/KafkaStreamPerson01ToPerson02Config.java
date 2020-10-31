package org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aconsumer.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aconsumer.consumer.TopicConstants;
import org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aconsumer.consumer.model.Person01;
import org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aconsumer.consumer.model.Person02;

import java.io.IOException;

@Configuration
public class KafkaStreamPerson01ToPerson02Config {

    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public KStream<String, String> kStream(StreamsBuilder kStreamBuilder) {
        KStream<String, String> stream = kStreamBuilder.stream(TopicConstants.PERSON01);
        stream
                .mapValues((rawValue) -> {
                    Person01 person01 = toPerson01(rawValue);
                    Person02 person02 = toPerson02(person01);
                    String person02Json = toJsonString(person02);
                    return person02Json;
                })
                .to(TopicConstants.PERSON02);

        stream.print(Printed.toSysOut());
        return stream;
    }

    private Person01 toPerson01(String rawValue) {
        try {
            return objectMapper().readValue(rawValue, Person01.class);
        } catch (IOException e) {
            throw new RuntimeException("Error when converting raw data to Person01", e);
        }
    }

    private Person02 toPerson02(Person01 person01) {
        Person02 person02 = new Person02(person01.getId(), person01.getName());
        return person02;
    }

    private String toJsonString(Object object) {
        try {
            return objectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error when converting object to String:" + object, e);
        }
    }
}