# HW 13. Dependency Injection

### Task

- Build `war` file for application in Homework 12
- Create `CacheService` and `DBService` as Spring beans and inject them in servlets 
- Launch application in external Web server

### How to get application running

clone application to your computer

```shell
git clone -b master https://github.com/ifqthenp/otus-java-2018-01-andrei.git
```

`cd` into module folder

```shell
cd otus-java-2018-01-andrei/hw_13_webapp
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

MySQL on Docker Hub: [mysql](https://hub.docker.com/r/_/mysql/)

[Hibernate Documentation](http://hibernate.org/orm/documentation/5.2/)
