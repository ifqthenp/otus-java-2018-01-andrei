# HW 12. Jetty Web Server

### Task

Embed web server into application from Homework 11 (Caching Engine).

Create admin login page that would allow administrator to view the parameters and state of the cache.

### How to get application running

clone application to your computer

```shell
git clone -b master https://github.com/ifqthenp/otus-java-2018-01-andrei.git
```

`cd` into module folder

```shell
cd otus-java-2018-01-andrei/hw_12_jetty
```

and run MySQL database in Docker container 

```shell
docker-compose up -d
```

then build a `jar` and start application

```shell
mvn clean package && java -jar target/hw_12_jetty.jar
```

type in your browser's URL bar

```text
http://localhost:8090/
```

or simply run the program from your IDE.

### Useful Docker commands:

- list images: `docker images`
- list containers: `docker container ls`
- fire up containers: `docker-compose up -d`
- stop running containers: `docker-compose stop`
- container shell access: `docker exec -it my-mysql-db bash`

MySQL on Docker Hub: [mysql](https://hub.docker.com/r/_/mysql/)

[Hibernate Documentation](http://hibernate.org/orm/documentation/5.2/)
