package dev.springallergies.proj2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication
@ComponentScan(basePackages = "dev.springallergies")
@EntityScan(basePackages = "dev.springallergies.entities")
@EnableJpaRepositories(basePackages = "dev.springallergies.repos")
public class Proj2potluckApplication {

	public static void main(String[] args) {
		SpringApplication.run(Proj2potluckApplication.class, args);
	}

}
