#!/bin/bash
docker-compose scale kafka=0
docker-compose stop zookeeper
