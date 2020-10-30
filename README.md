# Introduction
This project is different from https://github.com/khoitnm/practice-spring-kafka-grpc
`practice-spring-kafka-grpc` more focuses on integration between Kafka and gRPC, while this project more focuses on pure Kafka itself.

# Trouble shoot
## 1. Kafka connection
The best tutorial: https://www.confluent.io/blog/kafka-client-cannot-connect-to-broker-on-aws-on-docker-etc/

## 2. Kafka transaction in a producer: cannot create transaction
Error when starting the producer: "org.springframework.transaction.CannotCreateTransactionException: Could not create Kafka transaction; nested exception is org.apache.kafka.common.errors.TimeoutException: Timeout expired after 60000milliseconds while awaiting InitProducerId"
Solution: ???
    potential answers: (Note: we are not using Lister in our producer)
     - https://docs.spring.io/spring-kafka/reference/html/#tx-template-mixed
     - https://docs.spring.io/spring-kafka/reference/html/#exactly-once
    Real solution: 
    - Kafka Broker was NOT support Transaction.
    - Therefore, we need to add this into docker-compose: KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
    
## 3. Kafka transaction in a producer: infinite messages about "Discovered transaction coordinator localhost:9092 (id: 1 rack: null)"
After fixing issue 2, we get infinite messages "Discovered transaction coordinator localhost:9092 (id: 1 rack: null)"    

# References
Kafka security SASL: https://github.com/layonez/kafka-example
JSON message format and some application.yaml configs for Producers & Consumers: https://dzone.com/articles/spring-boot-and-kafka-configuration-tuning
Transaction:
    - https://www.confluent.io/blog/transactions-apache-kafka/?utm_medium=sem&utm_source=google&utm_campaign=ch.sem_br.nonbrand_tp.prs_tgt.dsa_mt.dsa_rgn.namer_lng.eng_dv.all&utm_term=&creative=&device=c&placement=&gclid=CjwKCAjw0On8BRAgEiwAincsHEGyvj9qvoZtrDhcacK11Fx7OMJ-Wt_cUUYSeULq6-s9TDo1puMV6BoCURoQAvD_BwE 
    - https://github.com/milofelipe/spring-kafka-transactions
    - https://www.confluent.io/blog/spring-for-apache-kafka-deep-dive-part-1-error-handling-message-conversion-transaction-support/
    - ChainTransaction with DB (example code): https://docs.spring.io/spring-kafka/reference/html/#ex-jdbc-sync
Error Handler with Error Topic: https://www.confluent.io/blog/spring-for-apache-kafka-deep-dive-part-1-error-handling-message-conversion-transaction-support/  
Kafka testing: https://dzone.com/articles/a-quick-and-practical-example-of-kafka-testing
  