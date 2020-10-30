# Note
# 1. DB & Kafka are NOT atomic!!!
With this example code, Spring supports ChainKafkaTransactionManager which combines both Kafka and DB Transaction.
It means that when committing, Spring framework will send "commit" command to both Kafka and DB, and they <strong>"seem" to be atomic</strong>.   
However, in some rare cases, after sending commit successfully to Kafka, the commit to DB still could be fail for some reason such as network, crash, etc. (or vise versa),
<strong>So, that's still not a real two-phase commit between Kafka and DB.</strong> 
<br/>
Kafka doesn't support XA (2 phase commit) and you have to deal with the possibility that the DB tx (transaction) might commit while the Kafka tx rolls back."
https://stackoverflow.com/questions/47354521/transaction-synchronization-in-spring-kafka
https://docs.spring.io/spring-kafka/reference/html/#transaction-synchronization
https://docs.spring.io/spring-kafka/reference/html/#ex-jdbc-sync

# 1. Don't worry about error message 
Don't worry if you see error message "org.apache.kafka.common.KafkaException: Failing batch since transaction was aborted" when running.
It just means the Kafka Transaction worked, and it rollback processes when there's an Exception. 