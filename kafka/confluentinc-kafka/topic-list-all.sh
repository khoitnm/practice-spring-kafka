#!/bin/bash
docker-compose exec broker kafka-topics \
  --list \
  --bootstrap-server localhost:9092