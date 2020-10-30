#!/bin/bash

# Note: using docker-compose run broker will not work: cannot connect to boostrap-server.
docker-compose exec broker  \
  kafka-console-consumer --bootstrap-server localhost:9092 --topic testtopic --from-beginning --max-messages 2
