# Note
Don't worry if you see error message "org.apache.kafka.common.KafkaException: Failing batch since transaction was aborted" when running.
It just means the Kafka Transaction worked, and it rollback processes when there's an Exception. 

With this case, two-phase commit happened. Ref: [20180328-EB-Confluent_Designing_Event_Driven_Systems_free_from_confluent.io.pdf, page 117'