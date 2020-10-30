# Introduction
This project is different from https://github.com/khoitnm/practice-spring-kafka-grpc
`practice-spring-kafka-grpc` more focuses on integration between Kafka and gRPC, while this project more focuses on pure Kafka itself.

# Trouble shoot
## Kafka connection
The best tutorial: https://www.confluent.io/blog/kafka-client-cannot-connect-to-broker-on-aws-on-docker-etc/

## Kafka transaction in a producer:
Error when starting the producer: "org.springframework.transaction.CannotCreateTransactionException: Could not create Kafka transaction; nested exception is org.apache.kafka.common.errors.TimeoutException: Timeout expired after 60000milliseconds while awaiting InitProducerId"
Solution: ???
    potential answers: (Note: we are not using Lister in our producer)
     - https://docs.spring.io/spring-kafka/reference/html/#tx-template-mixed
     - https://docs.spring.io/spring-kafka/reference/html/#exactly-once


# References
Kafka security SASL: https://github.com/layonez/kafka-example
JSON message format and some application.yaml configs for Producers & Consumers: https://dzone.com/articles/spring-boot-and-kafka-configuration-tuning
Transaction: 
    - https://github.com/milofelipe/spring-kafka-transactions
    - https://www.confluent.io/blog/spring-for-apache-kafka-deep-dive-part-1-error-handling-message-conversion-transaction-support/
    - ChainTransaction with DB (example code): https://docs.spring.io/spring-kafka/reference/html/#ex-jdbc-sync
Error Handler with Error Topic: https://www.confluent.io/blog/spring-for-apache-kafka-deep-dive-part-1-error-handling-message-conversion-transaction-support/  
Kafka testing: https://dzone.com/articles/a-quick-and-practical-example-of-kafka-testing
  