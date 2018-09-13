#!/usr/bin/env bash

JETTY_HOME=/opt/jetty/jetty-9.4.12

mvn clean -pl front -pl master -pl back package

# linux
# cp front/target/front.war ${JETTY_HOME}/webapps/root.war

