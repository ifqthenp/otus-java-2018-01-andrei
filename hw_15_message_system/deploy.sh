#!/usr/bin/env bash

mvn clean package

# linux
cp target/hw_15_message_system.war ${JETTY_HOME}/webapps/root.war

# windows
# copy target\L13.1.3-spring.war c:\Apps\Jetty\webapps\root.war
