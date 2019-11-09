package me.study.spring_boot_get_start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

/**
 * @SpringBootApplication 은 아래 3가지 애노테이션을 포함 하고 있다.
 *
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan
 */
public class SpringBootGetStartApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SpringBootGetStartApplication.class, args);
        SpringApplication application = new SpringApplication(SpringBootGetStartApplication.class);
        application.run(args);
    }

}
