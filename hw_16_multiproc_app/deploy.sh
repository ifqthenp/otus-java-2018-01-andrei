#!/usr/bin/env bash

JETTY_HOME=/opt/jetty/jetty-9.4.12

cd master && mvn clean && cd ..
cd front && mvn clean && cd ..
cd back && mvn clean && cd ..

mvn package

java -jar master/target/master.jar

# linux
#cp front/target/front.war ${JETTY_HOME}/webapps/root.war

