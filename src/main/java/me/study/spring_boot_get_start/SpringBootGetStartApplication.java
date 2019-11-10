package me.study.spring_boot_get_start;

import me.dongchul.Holoman;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

    /**
     * autoconfiguration 으로 등록된 bean 은 component scan으로 등록하는 bean을 덮어 쓴다.
     *
     */
    @Bean
    public Holoman holoman() {
        Holoman holoman = new Holoman();
        holoman.setName("dongchul");
        holoman.setHowLong(60);
        return holoman;
    }

}
