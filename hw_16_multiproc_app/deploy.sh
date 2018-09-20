#!/usr/bin/env bash

JETTY_HOME=/opt/jetty/jetty-9.4.12

if ps -aux | grep -q '[j]etty'; then
    printf "%s\\n" "Jetty is running..."
else
    printf "%s\\n" "starting Jetty server..."
    ${JETTY_HOME}/bin/jetty.sh start
    sleep 2
fi

if ps -aux | grep -q '[m]ysqld'; then
    printf "%s\\n" "mysql is running..."
else
    printf "%s\\n" "starting mysql with Docker Compose..."
    docker-compose up -d
    sleep 2
fi

cd master && mvn clean && cd ..
cd front && mvn clean && cd ..
cd back && mvn clean && cd ..

mvn package

java -jar master/target/master.jar

# linux
#cp front/target/front.war ${JETTY_HOME}/webapps/root.war

