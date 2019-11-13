package me.study.spring_boot_get_start.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class BaseConfiguration {
    @Bean
    public String hello(){
        return "hello";
    }
}
