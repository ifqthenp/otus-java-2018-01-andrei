# HW 10. Hibernate

- Arrange the solution in the form of `DBService` (`interface DBService`,
`class DBServiceImpl`, `UserDataSet`, etc)
- Add into `UserDataSet` following fields:
    - address (OneToOne)
    - phone number (OneToMany)

### How to get application running

Install [Docker](https://www.docker.com/community-edition)

```shell
docker-compose up -d
```

then type in the console

```shell
mvn clean package
java -jar target/hw_10_jdbc.jar
```

or simply run program from an IDE.


### Useful Docker commands:

- list images: `docker images`
- list containers: `docker container ls`
- fire up containers: `docker-compose up -d`
- stop running containers: `docker-compose stop`
- container shell access: `docker exec -it my-mysql-db bash`

MySQL on Docker Hub: [mysql](https://hub.docker.com/r/_/mysql/)

[Hibernate Documentation](http://hibernate.org/orm/documentation/5.2/)
