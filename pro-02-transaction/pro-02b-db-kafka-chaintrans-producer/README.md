# Note
## 1. DB & Kafka are atomic (or NOT??! Need to recheck)
With this example code, Spring supports ChainKafkaTransactionManager which combines both Kafka and DB Transaction.
It means that when committing, Spring framework will send "commit" command to both Kafka and DB, and they <strong>"seem" to be atomic</strong>.
However, this is not 2 phase commit. This is a cascading transaction, called an "application dual-write" as mentioned in 
https://github.com/kbastani/event-sourcing-microservices-example/issues/28 
> "If any system partaking in the transaction dies, we have a 100% guarantee that our application dual-write cannot result in an inconsistent state."

I'm still quite a bit confused between the above link with comments in below links:
> "Kafka doesn't support XA (2 phase commit) and 
> you have to deal with the possibility that the DB tx (transaction) might commit while the Kafka tx rolls back." (???)

- https://stackoverflow.com/questions/47354521/transaction-synchronization-in-spring-kafka
- https://docs.spring.io/spring-kafka/reference/html/#transaction-synchronization
- https://docs.spring.io/spring-kafka/reference/html/#ex-jdbc-sync

## 2. Don't worry about error message when running application (for testing)
Don't worry if you see error message "org.apache.kafka.common.KafkaException: Failing batch since transaction was aborted" when running.
It just means the Kafka Transaction worked, and it rollback processes when there's an Exception. 