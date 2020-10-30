#!/bin/bash
docker-compose exec broker kafka-topics \
  --describe \
  --bootstrap-server localhost:9092 \
  --topic testtopic