#!/bin/bash

# If you receive error ""docker container rm" requires at least 1 argument.", it means that there's no stopped container to be deleted.
docker container rm $(docker container ls -aq)
