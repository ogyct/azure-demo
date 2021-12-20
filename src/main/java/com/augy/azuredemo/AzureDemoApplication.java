package com.augy.azuredemo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication

public class AzureDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AzureDemoApplication.class, args);
    }

}

@Data
class TestEntity {
    private Long id;
    private String name;
}

interface MyRepo extends ReactiveCrudRepository<TestEntity, Long> {

}

@RestController
@Slf4j
class MyController {
    @Autowired
    MyRepo myRepo;
    @Value("${my.secret}")
    private String secret;

    @GetMapping
    Mono<TestEntity> hello() {
        return myRepo.findAll().last();
    }

    @GetMapping("/secret")
    public Mono<String> secret() {
        return Mono.just(secret);
    }
}
