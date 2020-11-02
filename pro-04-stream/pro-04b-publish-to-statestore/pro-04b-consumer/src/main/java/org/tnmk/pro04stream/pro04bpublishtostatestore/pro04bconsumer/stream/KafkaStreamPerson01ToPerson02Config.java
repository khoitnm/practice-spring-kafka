package org.tnmk.pro04stream.pro04bpublishtostatestore.pro04bconsumer.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;
import org.tnmk.pro04stream.pro04bpublishtostatestore.pro04bconsumer.consumer.TopicConstants;
import org.tnmk.pro04stream.pro04bpublishtostatestore.pro04bconsumer.consumer.model.Person01;
import org.tnmk.pro04stream.pro04bpublishtostatestore.pro04bconsumer.consumer.model.Person02;

import java.io.IOException;

//TODO Not finished yet.
@Service
public class KafkaStreamPerson01ToPerson02Config {

    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Autowired
    private StreamsBuilder kStreamBuilder;
    /**
     * When doing this, the stream will be started and closed automatically when the application is started and closed, respectively.
     * @return
     */
    public KStream<String, String> kStream() {
        KStream<String, String> stream = kStreamBuilder.stream(TopicConstants.PERSON01);
        KStream person02JsonStream = stream.mapValues((rawValue) -> {
                    Person01 person01 = toPerson01(rawValue);
                    Person02 person02 = toPerson02(person01);
                    String person02Json = toJsonString(person02);
                    return person02Json;
                });
        KTable kTable = person02JsonStream.toTable(Named.as("Person02KTable"));
        person02JsonStream.to(TopicConstants.PERSON02, Produced.with(Serdes.String(), new JsonSerde()));
//                .to(TopicConstants.PERSON02);

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