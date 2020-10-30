#!/bin/bash

# Check the Zookeeper logs to verify that Zookeeper is healthy.
docker-compose logs zookeeper | grep -i binding
# Result will be like this:
#   `zookeeper    | [2020-10-30 20:16:19,567] INFO binding to port 0.0.0.0/0.0.0.0:2181 (org.apache.zookeeper.server.NIOServerCnxnFactory)`

# Check the Kafka logs to verify that broker is healthy.
docker-compose logs broker | grep -i started
# Should see this similar message:
#   `broker       | [2020-10-30 20:16:22,324] INFO [KafkaServer id=1] started (kafka.server.KafkaServer)`

# Check the detail log message:
docker-compose logs --tail 10 broker
