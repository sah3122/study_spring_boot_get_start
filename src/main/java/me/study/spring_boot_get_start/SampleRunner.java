package me.study.spring_boot_get_start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by dongchul on 2019-11-13.
 */
@Component
public class SampleRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(SampleRunner.class);
//    @Value("${dongchul.name}")
//    private String name;
//
//    @Value("${dongchul.age}")
//    private int age;
    @Autowired
    DonghculProperties donghculProperties;

    @Autowired
    public String hello;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug(hello);
        logger.info(hello);
        System.out.println(donghculProperties.getName());
        System.out.println(donghculProperties.getAge());
    }
}
