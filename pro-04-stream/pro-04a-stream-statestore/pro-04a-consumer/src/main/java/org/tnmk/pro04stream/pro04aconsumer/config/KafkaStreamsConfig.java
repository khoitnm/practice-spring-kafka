package org.tnmk.pro04stream.pro04aconsumer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.boot.autoconfigure.kafka.StreamsBuilderFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.tnmk.pro04stream.pro04aconsumer.consumer.TopicConstants;
import org.tnmk.pro04stream.pro04aconsumer.model.Person01;
import org.tnmk.pro04stream.pro04aconsumer.model.Person02;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
//@EnableKafka //<-- this is unnecessary
@EnableKafkaStreams
public class KafkaStreamsConfig {

//    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Have to configure serdes manually like this, it doesn't support automatically config yet: https://github.com/quarkusio/quarkus/issues/3202
     * @return
     */
    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "pro04a");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, GenericJsonSerde.class.getName());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, new JsonSerde(Person01.class, new ObjectMapper()).getClass());
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public StreamsBuilderFactoryBeanCustomizer customizer() {
        return fb -> fb.setStateListener((newState, oldState) -> {
            System.out.println("State transition from " + oldState + " to " + newState);
        });
    }

    @Bean
    public KStream<String, String> kStream(StreamsBuilder kStreamBuilder) {
//        KStream<Integer, String> stream = kStreamBuilder.stream("streamingTopic1");
//        stream
//                .mapValues((ValueMapper<String, String>) String::toUpperCase)
//                .groupByKey()
//                .windowedBy(TimeWindows.of(Duration.ofMillis(1000)))
//                .reduce((String value1, String value2) -> value1 + value2,
//                		Named.as("windowStore"))
//                .toStream()
//                .map((windowedId, value) -> new KeyValue<>(windowedId.key(), value))
//                .filter((i, s) -> s.length() > 40)
//                .to("streamingTopic2");
//
//
//
//        stream.print(Printed.toSysOut());

        KStream<String, String> stream = kStreamBuilder.stream(TopicConstants.PERSON01);
        stream
                .mapValues((rawValue) -> {
                    try {
                        return objectMapper.readValue(rawValue, Person01.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Convert exception", e);
                    }
                })
                .mapValues(new ValueMapper<Person01, String>() {
                    @Override
                    public String apply(Person01 person01) {
                        Person02 person02 = new Person02(person01.getId(), person01.getName());
                        try {
                            return objectMapper.writeValueAsString(person02);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException("Convert exception", e);
                        }
                    }
                })
//                .flatMapValues(value -> Arrays.asList(pattern.split(value.toLowerCase())))
//                .filter((key, value) -> value.trim().length() > 0)
//                .map((key, value) -> new KeyValue<>(value, value))
//                .groupByKey()
//                .countByKey("counts")
//                .toStream()
//                .to(stringSerde, Serdes.Long(), "words-with-counts-" + System.nanoTime());
                .to(TopicConstants.PERSON02);

        stream.print(Printed.toSysOut());
        return stream;
    }

}