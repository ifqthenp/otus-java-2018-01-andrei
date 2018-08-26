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

and run MySQL database in Docker container 

```shell
docker-compose up -d
```

export `JETTY_HOME` environment variable. For example:

```shell
export JETTY_HOME=/opt/my-jetty-home-folder
```

start Jetty server

```shell
$JETTY_HOME/bin/jetty.sh start
```

then run `deploy.sh` script from project root folder

```shell
./deploy.sh
```

finally, type in your browser's URL bar

```text
http://localhost:8080/
```

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
