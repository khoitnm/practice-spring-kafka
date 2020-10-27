#!/bin/bash
docker-compose up -d zookeeper
docker-compose scale kafka=2
