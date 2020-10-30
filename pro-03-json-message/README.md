# Trouble Shoot
## Problem with JsonConverter in Consumer:
In application.yml, you may think about using `value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer`
However, it's very inconvenient because the class object of the message used by the provider must exist in consumer.
<br/>
So, another way is using `value-deserializer: org.apache.kafka.common.serialization.ByteArrayDeserializer` combine with `StringJsonMessageConverter` bean.
Then it can convert just any JSON String into any class.
<br/>
Please view detail code in `application.yml` and `KafkaJsonConverterConfig`

Reference link: https://stackoverflow.com/questions/50478267/classnotfoundexception-with-kafka-consumer/50489352#50489352
