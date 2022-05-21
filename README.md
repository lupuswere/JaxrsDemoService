# JaxrsDemoService

This repository shows you the barebone minimum code and configuration you'll need to build a Java web service using JAX-RS and its implementation Jersey, and Spring Boot.

The difference between this one and [SpringDemoService](https://github.com/lupuswere/SpringDemoService) is that this one uses JAX-RS to implement a REST service while the other one uses Spring Web. For their differences, you can refer to articles like https://www.baeldung.com/rest-api-jax-rs-vs-spring

## Environment

* Java 8
* Maven
* Spring Boot 2.7.0

## Steps

To start up a JAX-RS service, here is the minimum you'll need:

### 1. Pom configuration

You need to declare `spring-boot-starter-parent` as your parent:

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.0</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

### 2. Spring boot and Jersey dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jersey</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```
and this maven plugin:
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```
### 3. Application starter

```java
@SpringBootApplication
public class JaxrsDemoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaxrsDemoServiceApplication.class, args);
	}
}
```

### 4. Controller that declares and implements the API

In our case, we declared a REST API `GET /ping`:

```java
@Path("/ping")
public class PingController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping() {
        return Response.ok("Service is healthy").build();
    }
}
```

### 5. Registry of the controller

You need to let Jersey know that you have declared an API:

```java
@Component
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init() {
        registerClasses(PingController.class);
    }
}
```

### 6. Test

All done! Now, you can go to the root of the project and run:

```shell
$ mvn sprint-boot:run
```