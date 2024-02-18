#!/bin/bash
cd /home/ec2-user/spring-boot-mongo-etc
docker-compose down
docker-compose build --no-cache
docker-compose up -d
