package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableMongoRepositories
@SpringBootApplication
@RestController
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

    @RequestMapping("/home")
    public String hello() {
        return "Hello buddy!";
    }
}
