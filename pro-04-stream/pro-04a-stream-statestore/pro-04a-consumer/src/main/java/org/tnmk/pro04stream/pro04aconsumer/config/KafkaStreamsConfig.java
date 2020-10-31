package org.tnmk.pro04stream.pro04aconsumer.config;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.boot.autoconfigure.kafka.StreamsBuilderFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Configuration
//@EnableKafka //<-- this is unnecessary
@EnableKafkaStreams
public class KafkaStreamsConfig {

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
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
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


        Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);
        Serde<String> stringSerde = Serdes.String();

        KStream<String, String> stream = kStreamBuilder.stream("topic01");
        stream
                .flatMapValues(value -> Arrays.asList(pattern.split(value.toLowerCase())))
                .filter((key, value) -> value.trim().length() > 0)
                .map((key, value) -> new KeyValue<>(value, value))
//                .groupByKey()
//                .countByKey("counts")
//                .toStream()
//                .to(stringSerde, Serdes.Long(), "words-with-counts-" + System.nanoTime());
                .to("topic02");
        return stream;
    }

}