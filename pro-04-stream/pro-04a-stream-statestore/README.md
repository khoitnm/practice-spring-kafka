# Some thought
Spring Kafka Stream seems to be supported by Spring Cloud Kafka Stream Binder.
Now, that Spring Cloud Kafka Stream will come with Spring Integration patterns which provides a lot of abstract layers so that we can swap different things if we want.
However, the cost is that it makes things become much more complicated (with experience of Spring Integration in the past, it's just unnecessary complicated for most of regular usecase and makes things much more difficult to debug, to maintain).
<p/>
So I prefer working with pure `Apache Kafka Stream` or only `Spring Boot Kafka` only, NOT `Spring Cloud Kafka Stream`.

# References
- Document: https://medium.com/@ceyhunuzngl/kafka-stream-processor-api-in-spring-boot-4e251067a58f
- Source code of the above document: https://github.com/mrceyhun/kafka-producer-api-example
- Pure Kafka Stream: https://stackoverflow.com/questions/51733039/kafka-streams-with-spring-boot 
- Spring Cloud Stream Binder: https://spring.io/blog/2019/12/09/stream-processing-with-spring-cloud-stream-and-apache-kafka-streams-part-6-state-stores-and-interactive-queries
- https://github.com/1123/spring-kafka-stream-with-state-store