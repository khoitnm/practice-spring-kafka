# Note
# 1. DB & Kafka are NOT atomic!!!
Kafka doesn't support XA and you have to deal with the possibility that the DB tx (transaction) might commit while the Kafka tx rolls back."
https://stackoverflow.com/questions/47354521/transaction-synchronization-in-spring-kafka
https://docs.spring.io/spring-kafka/reference/html/#transaction-synchronization
https://docs.spring.io/spring-kafka/reference/html/#ex-jdbc-sync

# 1. Don't worry about error message 
Don't worry if you see error message "org.apache.kafka.common.KafkaException: Failing batch since transaction was aborted" when running.
It just means the Kafka Transaction worked, and it rollback processes when there's an Exception. 