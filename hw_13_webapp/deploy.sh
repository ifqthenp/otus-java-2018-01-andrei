#!/usr/bin/env bash

mvn clean package

# linux
cp target/hw_13_webapp.war ${JETTY_HOME}/webapps/root.war

# windows
# copy target\L13.1.3-spring.war c:\Apps\Jetty\webapps\root.war
