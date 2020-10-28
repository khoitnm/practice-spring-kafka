#!/bin/bash

#export KAFKA_ADVERTISED_HOST_NAME=$(ip a | sed -En 's/127.0.0.1//;s/172.*.0.1//;s/.*inet (addr:)?(([0-9]*\.){3}[0-9]*).*/\2/p')
#echo "KAFKA_ADVERTISED_HOST_NAME: ${KAFKA_ADVERTISED_HOST_NAME}"

#KAFKA_ADVERTISED_HOST_NAME: localhost (producers & consumers should be able to connect via KAFKA_ADVERTISED_HOST_NAME:9092)
#   Problem: producer can connect to brokers, but there's no leader.
docker-compose -f docker-compose-single-broker.yml up -d
