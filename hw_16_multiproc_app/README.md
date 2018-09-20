# HW 16. Multi-process Applications

### Task

- Split server from HW 15 into three apps:
  - MessageServer
  - Frontend
  - DbService
- Start `Frontend` and `DbService` from `MessageServer`
- Convert `MessageServer` into socket server, make `Frontend` and `DbService` its clients
- Send messages from `Frontend` to `DbService` through `MessageServer`
- Launch application with two fronts (on different ports) and two database servers
- If running application in containers, `MessageServer` may copy `root.war` in 
containers upon application start

### How to get application running

clone application to your computer

```shell
git clone -b master https://github.com/ifqthenp/otus-java-2018-01-andrei.git
```

`cd` into module folder

```shell
cd otus-java-2018-01-andrei/hw_16_multiproc_app
```

and run `deploy.sh` script

```shell
./deploy.sh
```

finally, type in your browser's URL bar

```text
http://localhost:8080/
```

This application requires `docker-compose` to be installed on the system and
Jetty installed in `/opt/jetty/jetty-9.4.12`

### Useful Docker commands:

- list images: `docker images`
- list containers: `docker container ls`
- fire up containers: `docker-compose up -d`
- stop running containers: `docker-compose stop`
- container shell access: `docker exec -it my-mysql-db bash`

### Useful links

[MySQL on Docker Hub](https://hub.docker.com/r/_/mysql/)

[Hibernate Documentation](http://hibernate.org/orm/documentation/5.2/)

[Jetty Documentation](https://www.eclipse.org/jetty/documentation/)

[EhCache Documentation](http://www.ehcache.org/documentation/)

[Spring Framework Documentation](https://docs.spring.io/spring/docs/current/spring-framework-reference/index.html)
