#!/bin/bash

set -e

echo "Stopping and removing Docker containers..."
docker-compose -f docker-compose-postgres.yml down

echo "Checking if all containers are stopped..."
while docker ps | grep -q 'postgres'; do
    echo "Waiting for containers to stop..."
    sleep 1
done

echo "Removing existing postgres-data directory..."
rm -rf ./postgres-data

echo "Creating a new postgres-data directory..."
mkdir ./postgres-data

echo "Starting Docker containers..."
docker-compose -f docker-compose-postgres.yml up -d

echo "All tasks completed successfully!"
