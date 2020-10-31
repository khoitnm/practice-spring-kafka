package org.tnmk.pro04stream.pro04aforwardtoanothertopic.pro04aconsumer.config;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class GenericJsonSerde implements Serde {

    public final Class<?> targetClass;

    public GenericJsonSerde(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public Serializer serializer() {
        return new JsonSerializer();
    }

    @Override
    public Deserializer deserializer() {
        return new JsonDeserializer(targetClass);
    }
}
