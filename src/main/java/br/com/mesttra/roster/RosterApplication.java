package br.com.mesttra.roster;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableRabbit
@EnableCaching
public class RosterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosterApplication.class, args);
    }

}
