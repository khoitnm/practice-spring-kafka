# Note
Don't worry if you see error message "org.apache.kafka.common.KafkaException: Failing batch since transaction was aborted" when running.
It just means the Kafka Transaction worked, and it rollback processes when there's an Exception. 