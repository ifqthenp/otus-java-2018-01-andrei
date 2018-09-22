#!/usr/bin/env bash

JETTY_HOME=/opt/jetty/jetty-9.4.12

cd master && mvn clean && cd ..
cd front && mvn clean && cd ..
cd back && mvn clean && cd ..

mvn package

if ps -aux | grep -q '[j]etty'; then
    printf "%s\\n" "Jetty is running..."
else
    printf "%s\\n" "Jetty is not running..."
fi

if ps -aux | grep -q '[m]ysqld'; then
    printf "%s\\n" "mysql is running..."
else
    printf "%s\\n" "starting mysql with Docker Compose..."
    docker-compose up -d
    sleep 2
fi

java -jar master/target/master.jar

