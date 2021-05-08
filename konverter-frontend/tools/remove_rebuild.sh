#!/usr/bin/env bash

docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

docker build . -t frontend
docker run --name frontend -d -p 5000:5000 frontend
