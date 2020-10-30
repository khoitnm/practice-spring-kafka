#!/bin/bash

# Copied from here: https://docs.confluent.io/3.1.1/cp-docker-images/docs/quickstart.html
# Note: using docker-compose run broker will not work: cannot connect to boostrap-server.

# Explanation the command line:
#   seq 2: Used for creating 2 messages
docker-compose exec broker  \
  bash -c "seq 2 | kafka-console-producer --request-required-acks 1 --broker-list localhost:9092 --topic testtopic && echo 'Produced 2 messages.'"
