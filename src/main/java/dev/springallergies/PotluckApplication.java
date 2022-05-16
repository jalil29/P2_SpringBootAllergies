package dev.springallergies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "dev.springallergies")
@EntityScan(basePackages = "dev.springallergies.entities")
@EnableJpaRepositories(basePackages = "dev.springallergies.repos")
public class PotluckApplication {
	public static void main(String[] args) {
    /**
     * test
     */
    
    public static void main(String[] args) {
        SpringApplication.run(PotluckApplication.class, args);
    }
}
