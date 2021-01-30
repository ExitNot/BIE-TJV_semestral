package cz.cvut.fit.tjv.semestral.rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication
@ComponentScan({ "cz.cvut.fit.tjv.semestral.rest_api", "cz.cvut.fit.tjv.semestral.business" })
@EnableJpaRepositories("cz.cvut.fit.tjv.semestral.data")
@EntityScan("cz.cvut.fit.tjv.semestral.data.entities")
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
