package org.tnmk.pro04stream.pro04aconsumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.support.serializer.JsonSerde;

public class GenericJsonSerde extends JsonSerde {

    public GenericJsonSerde() {
        super(new ObjectMapper());
    }
}
