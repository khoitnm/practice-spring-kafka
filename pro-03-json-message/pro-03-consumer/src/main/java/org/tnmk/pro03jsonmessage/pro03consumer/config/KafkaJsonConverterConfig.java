package org.tnmk.pro03jsonmessage.pro03consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * In application.yml, you may think about using `value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer`
 * However, it's very inconvenient because the class object in provider must exist in consumer.
 * <br/>
 * So, another way is using `value-deserializer: org.apache.kafka.common.serialization.ByteArrayDeserializer` combine with `StringJsonMessageConverter` bean.
 * Then it can convert just any JSON String into any class.
 *
 * Reference link: https://stackoverflow.com/questions/50478267/classnotfoundexception-with-kafka-consumer/50489352#50489352
 */
@Configuration
public class KafkaJsonConverterConfig {

    @Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }
}
