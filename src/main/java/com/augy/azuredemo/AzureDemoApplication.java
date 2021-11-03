package com.augy.azuredemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@SpringBootApplication

public class AzureDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzureDemoApplication.class, args);
	}

}

@RestController
@Slf4j
class MyController {
	@GetMapping
	Mono<String> hello() {
		Mono<String> hello_azure = Mono.just("Hello azure").delayElement(Duration.ofSeconds(5));
		System.out.println("Test");
		return hello_azure;
	}
}
