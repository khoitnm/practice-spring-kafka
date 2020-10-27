#!/bin/bash

# KAFKA_ADVERTISED_HOST_NAME: localhost (producers & consumers should be able to connect via KAFKA_ADVERTISED_HOST_NAME:9092)
# ZOOKEEPER_CONNECT: zookeeper:2181
#   Problem: producer can connect to brokers, but there's no leader.
docker-compose -f docker-compose-single-broker.yml up -d
