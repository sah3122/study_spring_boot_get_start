package me.study.spring_boot_get_start;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by dongchul on 2019-11-13.
 */
@Component
public class SampleRunner implements ApplicationRunner {

    @Value("${dongchul.name}")
    private String name;

    @Value("${dongchul.age}")
    private int age;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(name);
        System.out.println(age);
    }
}
