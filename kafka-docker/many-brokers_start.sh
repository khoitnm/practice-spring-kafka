#!/bin/bash

# KAFKA_ADVERTISED_HOST_NAME: 192.168.1.2 (producers & consumers should be able to connect via KAFKA_ADVERTISED_HOST_NAME:9092)
# ZOOKEEPER_CONNECT: zookeeper:2181
docker-compose up -d zookeeper
docker-compose scale kafka=2
